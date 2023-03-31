package com.golem.app.commandSystem.commandsCollection;

import com.golem.app.collection.TicketCollection;
import com.golem.app.collection.ticket.Ticket;
import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.commandSystem.commandsCollection.miniCommands.GenerateID;
import com.golem.app.commandSystem.commandsCollection.miniCommands.InputCollectionElement;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReplaceGreater implements Command {

    private final TicketCollection collection;
    private Input scanner;
    private String ticketKey;

    public ReplaceGreater (TicketCollection collection) {
        this.collection = collection;
    }

    @Override
    public void process() {
        Ticket ticket;
        try {
            ticket = InputCollectionElement.inputElement(scanner, new Ticket(), scanner.script());
        } catch (WrongArgumentsException e) {
            ConsolePrinter.out("Replace greater failed due: " + ConsolePrinter.YELLOW(e.getMessage()));
            return;
        }
        InputCollectionElement.setID(collection, ticket);
        if (collection.getCollection().get(ticketKey).compareTo(ticket) <= 0 ) {
            collection.getCollection().replace(ticketKey,ticket);
        }
    }

    @Override
    public Command args(List<String> args, Input inputer) throws WrongArgumentsException {
        if (args.size() != 1) throw new WrongArgumentsException();
        scanner = inputer;
        ticketKey = args.get(0);
        for (String s : collection.getCollection().keySet()) {
            if (ticketKey.equals(s)) return this;
        }
        throw new WrongArgumentsException("Collection element for this id doesn't exist.");
    }

    @Override
    public String description() {
        return "replace element that user will input if it is greater.";
    }
}
