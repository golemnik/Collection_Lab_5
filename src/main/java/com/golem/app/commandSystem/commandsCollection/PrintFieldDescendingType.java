package com.golem.app.commandSystem.commandsCollection;

import com.golem.app.collection.TicketCollection;
import com.golem.app.collection.ticket.Ticket;
import com.golem.app.collection.ticket.TicketTypeComparator;
import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;

import java.util.ArrayList;
import java.util.List;

public class PrintFieldDescendingType implements Command {
    private final TicketCollection collection;
    public PrintFieldDescendingType (TicketCollection collection) {
        this.collection = collection;
    }
    @Override
    public void process() {
        List<Ticket> list = new ArrayList<>(collection.getCollection().values());
        list.sort(new TicketTypeComparator());
        for (Ticket t : list) {
            ConsolePrinter.out(t.getType().toString());
        }
    }

    @Override
    public Command args(List<String> args, Input inputer) throws WrongArgumentsException {
        if (args.size() != 0) throw  new WrongArgumentsException();
        return this;
    }

    @Override
    public String description() {
        return "no args, todo desc";
    }
}
