package com.cinema.clientservice.web.requests;

import java.util.List;

public record AllBoughtTicketsDetails(
        List<SingleTicketDetails> ticketDetails
) {
}
