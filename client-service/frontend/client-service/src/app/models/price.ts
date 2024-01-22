export interface Price {
  id: number,
  basePrice: number,
  reductionPct: number,
  promotionPct: number,
  dateSince: Date,
  dateUntil: Date
}
