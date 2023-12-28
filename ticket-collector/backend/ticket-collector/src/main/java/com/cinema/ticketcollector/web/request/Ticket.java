package com.cinema.ticketcollector.web.request;

public class Ticket{
    private final Long id;
    private boolean isValid;
    private final boolean isStudent;
    private final int room;
    private final int seat;
    private final int row;

    public Ticket(Long id, boolean isValid, boolean isStudent, int room, int seat, int row) {
        this.id = id;
        this.isValid = isValid;
        this.isStudent = isStudent;
        this.room = room;
        this.seat = seat;
        this.row = row;
    }

    public void setNotValid() {
        isValid = false;
    }

    public Long getId() {
        return id;
    }

    public boolean isValid() {
        return isValid;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public int getRoom() {
        return room;
    }

    public int getSeat() {
        return seat;
    }

    public int getRow() {
        return row;
    }
}
