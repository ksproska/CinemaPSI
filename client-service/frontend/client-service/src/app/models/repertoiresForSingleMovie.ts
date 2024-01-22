import {Movie} from "./movie";
import {RepertoiresForDates} from "./repertoiresForDates";

export interface RepertoiresForSingleMovie {
  movie : Movie,
  repertoiresForDate : RepertoiresForDates[]
}
