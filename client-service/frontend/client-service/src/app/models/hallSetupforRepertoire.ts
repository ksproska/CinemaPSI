import {SeatInfo} from "./seatInfo";

export interface HallSetupForRepertoire {
  movieTitle: string;
  languageVersion: string;
  repertoireId: number;
  hallId: number;
  date: Date;
  startingTime: Date;
  numbOfRows: number;
  numbOfSeatsInRow: number;
  seats: SeatInfo[][];
}
