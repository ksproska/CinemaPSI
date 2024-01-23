import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {SeatInfo} from "../../models/seatInfo";
import {HallSetupForRepertoire} from "../../models/hallSetupforRepertoire";
import {map, scan, share, startWith, Subject} from "rxjs";
import {PriceService} from "../../services/price.service";
import {Price} from "../../models/price";
import {RepertoireDetails} from "../../models/RepertoireDetails";
import {TicketsService} from "../../services/tickets.service";
import {Reservation} from "../../models/reservation";


const numberToLetterMap: Record<number, string> = {
  1: 'A', 2: 'B', 3: 'C', 4: 'D', 5: 'E', 6: 'F', 7: 'G', 8: 'H', 9: 'I', 10: 'J', 11: 'K', 12: 'L',
  13: 'M', 14: 'N', 15: 'O', 16: 'P', 17: 'Q', 18: 'R', 19: 'S', 20: 'T', 21: 'U', 22: 'V', 23: 'W',
  24: 'X', 25: 'Y', 26: 'Z',
};

const letterToNumberMap: Record<string, number> = {
  'A': 1, 'B': 2, 'C': 3, 'D': 4, 'E': 5, 'F': 6, 'G': 7, 'H': 8, 'I': 9, 'J': 10, 'K': 11, 'L': 12, 'M': 13,
  'N': 14, 'O': 15, 'P': 16, 'Q': 17, 'R': 18, 'S': 19, 'T': 20, 'U': 21, 'V': 22, 'W': 23, 'X': 24, 'Y': 25, 'Z': 26

};

enum TicketType {
  Uczniowski = 'Uczniowski',
  Studencki = 'Studencki',
  Normalny = 'Normalny',
}

@Component({
  selector: 'app-choose-ticket',
  templateUrl: './choose-ticket.component.html',
  styleUrls: ['./choose-ticket.component.css']
})
export class ChooseTicketComponent implements OnInit {



  data: HallSetupForRepertoire | undefined;
  seats: SeatInfo[][] | undefined;
  selectedSeats: string[] = [];
  seatsTypeMap: Map<string, TicketType> =  new Map()
  readonly noneMessage = "nothing";
  readonly selectSeat$ = new Subject<string>();
  price: Price | undefined;
  studentPrice: number = 100;
  basePrice = 100;
  repertoire: RepertoireDetails | undefined;
  isPromotion: boolean = false;
  total = 0;
  promotionPct = 0;

  constructor(private route: ActivatedRoute, private priceService: PriceService,  private cdRef: ChangeDetectorRef,
              private ticketsService: TicketsService, private router: Router) {}
  ngOnInit(): void {
    this.route.data.subscribe(
      ({data}) => {
        this.data = data;
        this.seats = data.seats;
        console.log(this.seats);
        this.priceService.getCurrentPrice().subscribe((price : Price )=> {
            this.price = price;
            this.basePrice = price.basePrice;
            this.studentPrice = this.price.basePrice - this.price.basePrice * (this.price.reductionPct/100);
            // @ts-ignore
          console.log(this.addDays(new Date(), price.promotionMinDays), new Date(data.startingTime));
          if ( this.addDays(new Date(), price.promotionMinDays) < new Date(data.startingTime)){
            console.log('here')
              this.isPromotion = true
              this.promotionPct = price.promotionPct;
            }
              console.log(this.price);
          }
        )
      });
  }

  registerSeats = (selected: Set<string>, seatName: string) => {

    if (selected.has(seatName)) {
      console.log(seatName);
      selected.delete(seatName);
      this.selectedSeats.splice(this.selectedSeats.indexOf(seatName), 1)
      this.seatsTypeMap.delete(seatName);
      this.total -= parseInt(this.getSelectedTicketPrice(seatName));
    } else {
      let seatNameList = this.nameToRowAndCol(seatName);
      console.log(seatName);
      if (!this.isTaken(seatNameList[0] - 1, seatNameList[1] - 1)) {
        selected.add(seatName);
        this.selectedSeats.push(seatName)
        this.seatsTypeMap.set(seatName, TicketType.Normalny);
        this.total += this.basePrice;
      }
    }
    return selected;
  };

  rowAndColToName(row: number, col:number): string {
    let rowName = numberToLetterMap[row];
    return rowName + col;
  }

  nameToRowAndCol(seatName: string): [number, number]{
    let seatNameList = seatName.split("");
    return [letterToNumberMap[seatNameList[0]], parseInt(seatNameList[1])];
  }


  readonly selectedMessage$ = this.selectSeat$.pipe(
    scan(this.registerSeats, new Set<string>()),
    startWith(new Set<string>()),
    map(set => (set.size ? Array.from(set).join(", ") : this.noneMessage)),
    share()
  );

  isTaken(row: number, seat: number): boolean {
    // console.log(row, seat, this.seats);
    let seatInfo = this.seats![row][seat];
    return seatInfo.isTaken;

  }

  protected readonly numberToLetterMap = numberToLetterMap;

  selectedTicket: TicketType | undefined;

  selectTicket(event: any, key: string): void {
    // const element = event.currentTarget as HTMLInputElement
    this.seatsTypeMap.set(key, event.value);
    if (event.value == TicketType.Studencki) {
      this.total -= this.basePrice;
      this.total += this.studentPrice;
    } else {
      this.total -= this.studentPrice;
      this.total += this.basePrice;
    }
    this.cdRef.detectChanges();
  }

  addDays(date: Date, days: number): Date {
    date.setDate(date.getDate() + days);
    return date;
  }

  goToSummary(){

    localStorage.setItem("ticketChosen", "1");
    let studentCnt = 0;
    let seatIds = []
    for (let [key, value] of this.seatsTypeMap) {
      let nameList = this.nameToRowAndCol(key);
      seatIds.push(this.seats![nameList[0]-1][nameList[1]-1].seatId);
      if (value === TicketType.Studencki){
        studentCnt += 1
      }
    }

    let reservation: Reservation = {
        repertoireId: (this.data ? parseInt(String(this.data.repertoireId)) : -1),
        seatIds: seatIds,
        numberOfStudentTickets: studentCnt
    };

    console.log(reservation)
    this.ticketsService.reserveTickets(reservation).subscribe((reservationId : any) => {
      console.log(reservationId);
      this.router.navigate([`/summary/${reservationId.reservationId}`]);
    });

  }

  protected readonly TicketType = TicketType;

  getSelectedTicketPrice(key: string): string {
    let selected = this.seatsTypeMap.get(key);
    if (selected === TicketType.Studencki){
      return this.studentPrice.toFixed(2);
    } else {
      return this.price ? this.price.basePrice.toFixed(2) : "100.00";
    }
  }
}
