import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {SeatInfo} from "../../models/seatInfo";
import {HallSetupForRepertoire} from "../../models/hallSetupforRepertoire";
import {map, scan, share, startWith, Subject} from "rxjs";



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
  selectedSeats: Object[] = [];
  seatsTypeMap: Map<string, TicketType> =  new Map()
  readonly noneMessage = "nothing";
  readonly selectSeat$ = new Subject<string>();

  constructor(private route: ActivatedRoute) {}
  ngOnInit(): void {
    this.route.data.subscribe(
      ({data}) => {
        this.data = data;
        this.seats = data.seats;
      });

    console.log(this.isTaken(0, 0));
  }

  registerSeats = (selected: Set<string>, seatName: string) => {

    if (selected.has(seatName)) {
      console.log(seatName);
      selected.delete(seatName);
      this.selectedSeats.splice(this.selectedSeats.indexOf(seatName), 1)
      this.seatsTypeMap.delete(seatName);
    } else {
      let seatNameList = this.nameToRowAndCol(seatName);
      console.log(seatName);
      if (!this.isTaken(seatNameList[0], seatNameList[1])) {
        selected.add(seatName);
        this.selectedSeats.push(seatName)
        this.seatsTypeMap.set(seatName, TicketType.Normalny);
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
  }

  protected readonly TicketType = TicketType;
}
