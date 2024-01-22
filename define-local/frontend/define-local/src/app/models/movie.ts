import {MovieOffer} from "./movie-offer";

export interface Movie {
  id: number,
  title: string,
  description?: string,
  lengthMinutes?: number,
  imageUrl?: string,
  genres?: string[],
  movieOffers?: MovieOffer[]
}
