import {Movie} from "../models/movie";
import {Cinema} from "../models/cinema";

export interface MovieCinemaDTO {
  movieTitles: Movie[],
  cinemas: Cinema[]
}
