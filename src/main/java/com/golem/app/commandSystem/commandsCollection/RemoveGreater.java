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

public class RemoveGreater implements Command {
    private final TicketCollection collection;
    private Input scanner;
    public RemoveGreater (TicketCollection collection) {
        this.collection = collection;
    }

    @Override
    public void process() {
        InputCollectionElement ice = new InputCollectionElement();
        Ticket ticket;
        try {
            ticket = ice.inputElement(scanner, new Ticket(), scanner.script());
        } catch (WrongArgumentsException e) {
            ConsolePrinter.out("RemoveGreater failed due: " + ConsolePrinter.YELLOW(e.getMessage()));
            return;
        }
        ticket.setCreationDate(LocalDate.now());
        ticket.getVenue().setId(GenerateID.generate(collection));
        ticket.setId(GenerateID.generate(collection));
        collection.getCollection().values().removeIf(t -> t.compareTo(ticket) > 0);
    }

    @Override
    public Command args(List<String> args, Input inputer) throws WrongArgumentsException {
        if (args.size() != 1) throw new WrongArgumentsException();
        if (!args.get(0).equals("null")) throw new WrongArgumentsException();
        scanner = inputer;
        return this;
    }

    @Override
    public String description() {
        return "remove element greater than user will input.";
    }
}
