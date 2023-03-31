package com.golem.app.commandSystem.commandsCollection;

import com.golem.app.collection.TicketCollection;
import com.golem.app.collection.ticket.Ticket;
import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.commandSystem.commandsCollection.miniCommands.InputCollectionElement;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;

import java.util.List;

public class Insert implements Command {
    private final TicketCollection collection;
    private String ticketKey = null;
    private Input scanner;
    public Insert (TicketCollection collection) {
        this.collection = collection;
    }
    @Override
    public void process() {
        Ticket ticket;
        try {
            ticket = InputCollectionElement.inputElement(scanner, new Ticket(), scanner.script());
        } catch (WrongArgumentsException e) {
            ConsolePrinter.out("Insert failed due: " + ConsolePrinter.YELLOW(e.getMessage()));
            return;
        }
        InputCollectionElement.setID(collection, ticket);
        collection.getCollection().put(ticketKey, ticket);
    }
    @Override
    public Command args(List<String> args, Input inputer) throws WrongArgumentsException {
        if (args.size() != 1) throw new WrongArgumentsException();
        ticketKey = args.get(0);
        for (String s : collection.getCollection().keySet()) {
            if (ticketKey.equals(s)) throw new WrongArgumentsException("Current collection element id already exist.");
        }
        scanner = inputer;
        return this;
    }
    @Override
    public String description() {
        return "cause user to add new element to current collection.";
    }
}
