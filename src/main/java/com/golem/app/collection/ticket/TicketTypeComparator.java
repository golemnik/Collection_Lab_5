package com.golem.app.collection.ticket;

import java.util.Comparator;

public class TicketTypeComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket t1, Ticket t2) {
        return -1 * t1.getType().compareTo(t2.getType());
    }
}
