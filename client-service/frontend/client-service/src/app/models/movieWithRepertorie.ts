import {RepertoireDetails} from "./RepertoireDetails";
import {MovieDetailsDto} from "./movieDetailsDto";

export interface MovieWithRepertoire {
  movieVersionDetails : MovieDetailsDto,
  genres: string,
  repertoires : RepertoireDetails
}
