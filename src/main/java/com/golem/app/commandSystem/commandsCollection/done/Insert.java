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

public class Insert implements Command {
    private final TicketCollection collection;
    private String ticketKey = null;
    private final Scanner scanner;
    public Insert (TicketCollection collection) {
        this.collection = collection;
        this.scanner = new Scanner(System.in);
    }
    @Override
    public void process() {
        InputCollectionElement ice = new InputCollectionElement();
        Ticket ticket = ice.inputElement(scanner, new Ticket(), true);
        ticket.setCreationDate(LocalDate.now());
        collection.getCollection().put(ticketKey, ticket);
        ticket.getVenue().setId(GenerateID.generate(collection));
        ticket.setId(GenerateID.generate(collection));
    }
    @Override
    public Command args(List<String> args) throws WrongArgumentsException {
        if (args.size() != 1) throw new WrongArgumentsException();
        ticketKey = args.get(0);
        for (String s : collection.getCollection().keySet()) {
            if (ticketKey.equals(s)) throw new WrongArgumentsException("Current collection element id already exist.");
        }
        return this;
    }

    @Override
    public String description() {
        return "cause user to add new element to current collection.";
    }
}
