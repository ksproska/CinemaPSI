import {RepertoireCandidate} from "../models/repertoire-candidate";

export interface AddRepertoireRequest {
  movieId: number,
  repertoireCandidates: RepertoireCandidate[]
}
