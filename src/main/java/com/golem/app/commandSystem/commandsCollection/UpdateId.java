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

public class UpdateId implements Command {
    private final TicketCollection collection;
    private int ticketId;
    private Input scanner;
    public UpdateId (TicketCollection collection) {
        this.collection = collection;
    }
    @Override
    public void process() {
        InputCollectionElement ice = new InputCollectionElement();
        Ticket ticket;
        try {
            ticket = ice.inputElement(scanner, new Ticket(), scanner.script());
        } catch (WrongArgumentsException e) {
            ConsolePrinter.out("Update failed due: " + ConsolePrinter.YELLOW(e.getMessage()));
            return;
        }
        ticket.setCreationDate(LocalDate.now());
        ticket.getVenue().setId(GenerateID.generate(collection));
        ticket.setId(ticketId);
        for (String s : collection.getCollection().keySet()) {
            if (collection.getCollection().get(s).getId() == ticketId) {
                collection.getCollection().put(s, ticket);
                return;
            }
        }
    }
    @Override
    public Command args(List<String> args, Input inputer) throws WrongArgumentsException {
        if (args.size() != 1) throw new WrongArgumentsException();
        try {
            scanner = inputer;
            ticketId = Integer.parseInt(args.get(0));
            if (ticketId <= 0 ) throw new WrongArgumentsException("Current collection element's id doesn't support.");
            for (Ticket t : collection.getCollection().values()) {
                if (t.getId() == ticketId) {
                    return this;
                }
            }
        }
        catch (Exception e) {
            throw new WrongArgumentsException("Input value doesn't supported as id.");
        }
        throw new WrongArgumentsException("Current collection element for this id doesn't exist.");
    }

    @Override
    public String description() {
        return "cause user to add new element to current collection.";
    }
}
