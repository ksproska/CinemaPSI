import {MovieDetailsDto} from "./movieDetailsDto";
import {RepertoireDetails} from "./RepertoireDetails";

export interface RepertoireByDate {
  movieVersionDetails: MovieDetailsDto,
  genres: string[],
  repertoires: RepertoireDetails[]
}
