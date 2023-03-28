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

public class RemoveGreater implements Command {
    private final TicketCollection collection;
    private final Scanner scanner;
    public RemoveGreater (TicketCollection collection) {
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
        collection.getCollection().values().removeIf(t -> t.compareTo(ticket) > 0);
    }

    @Override
    public Command args(List<String> args) throws WrongArgumentsException {
        if (args.size() != 1) throw new WrongArgumentsException();
        if (!args.get(0).equals("null")) throw new WrongArgumentsException();
        return this;
    }

    @Override
    public String description() {
        return "remove element greater than user will input.";
    }
}
