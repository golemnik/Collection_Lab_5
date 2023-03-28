package com.golem.app.commandSystem.commandsCollection;

import com.golem.app.collection.TicketCollection;
import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.Input;

import java.util.List;

public class RemoveGreaterKey implements Command {
    private final TicketCollection collection;
    private String ticketKey;

    public RemoveGreaterKey(TicketCollection collection) {
        this.collection = collection;
    }

    @Override
    public void process() {
        collection.getCollection().keySet().removeIf(s -> s.compareTo(ticketKey) > 0);
    }

    @Override
    public Command args(List<String> args, Input inputer) throws WrongArgumentsException {
        if (args.size() != 1) throw new WrongArgumentsException();
        ticketKey = args.get(0);
        return this;
    }

    @Override
    public String description() {
        return "remove elements, which key is greater, than user will input.";
    }
}
