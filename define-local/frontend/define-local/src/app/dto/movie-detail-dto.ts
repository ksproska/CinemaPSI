import {Cinema} from "../models/cinema";
import {Movie} from "../models/movie";
import {Hall} from "../models/hall";

export interface MovieDetailDTO {
  cinema: Cinema,
  halls: Hall[],
  movieData: Movie
}
