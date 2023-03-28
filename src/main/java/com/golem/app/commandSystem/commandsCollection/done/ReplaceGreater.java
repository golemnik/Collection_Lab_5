package com.golem.app.commandSystem.commandsCollection.done;

import com.golem.app.collection.TicketCollection;
import com.golem.app.collection.ticket.Ticket;
import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.commandSystem.commandsCollection.miniCommands.GenerateID;
import com.golem.app.commandSystem.commandsCollection.miniCommands.InputCollectionElement;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReplaceGreater implements Command {

    private final TicketCollection collection;
    private final Scanner scanner;
    private String ticketKey;

    public ReplaceGreater (TicketCollection collection) {
        this.collection = collection;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void process() {
        InputCollectionElement ice = new InputCollectionElement();
        Ticket ticket = ice.inputElement(scanner, new Ticket(), true);
        ticket.setCreationDate(LocalDate.now());
        ticket.getVenue().setId(GenerateID.generate(collection));
        ticket.setId(GenerateID.generate(collection));
        if (collection.getCollection().get(ticketKey).compareTo(ticket) <= 0 ) {
            collection.getCollection().replace(ticketKey,ticket);
        }
    }

    @Override
    public Command args(List<String> args) throws WrongArgumentsException {
        if (args.size() != 1) throw new WrongArgumentsException();
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
