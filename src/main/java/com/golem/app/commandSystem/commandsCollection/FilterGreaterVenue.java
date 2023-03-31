package com.golem.app.commandSystem.commandsCollection;

import com.golem.app.collection.TicketCollection;
import com.golem.app.collection.ticket.Ticket;
import com.golem.app.collection.ticket.Venue;
import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.commandSystem.commandsCollection.miniCommands.InputCollectionElement;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;

import java.util.List;
import java.util.Scanner;

public class FilterGreaterVenue implements Command {
    private final TicketCollection collection;
    private final InputCollectionElement ice = new InputCollectionElement();
    private Input scanner;
    public FilterGreaterVenue (TicketCollection collection) {
        this.collection = collection;
    }
    @Override
    public void process() {
        Venue venue;
        try {
            venue = InputCollectionElement.inputVenue(scanner, new Venue(), true);
        } catch (WrongArgumentsException e) {
            ConsolePrinter.out("Insert failed due: " + ConsolePrinter.YELLOW(e.getMessage()));
            return;
        }
        for (Ticket t : collection.getCollection().values()) {
            if (venue == null) {
                continue;
            }
            if (t.getVenue().compareTo(venue) > 0) {
                ConsolePrinter.out(t.toReadString() + "\n");
            }
        }
    }

    @Override
    public Command args(List<String> args, Input inputer) throws WrongArgumentsException {
        if (args.size() != 0) throw  new WrongArgumentsException();
        scanner = inputer;
        return this;
    }

    @Override
    public String description() {
        return "no args, todo desc";
    }
}
