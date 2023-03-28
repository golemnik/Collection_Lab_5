package com.golem.app.commandSystem.commandsCollection;

import com.golem.app.collection.TicketCollection;
import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;

import java.util.List;

public class RemoveKey implements Command {

    private final TicketCollection collection;
    private String ticketKey = null;
    public RemoveKey (TicketCollection collection) {
        this.collection = collection;
    }
    @Override
    public void process() {
        if (collection.getCollection().remove(ticketKey) != null) {
            ConsolePrinter.out("Element successfully deleted.");
            return;
        }
        ConsolePrinter.out("Element for this key doesn't exist.");
    }

    @Override
    public Command args(List<String> args, Input inputer) throws WrongArgumentsException {
        if (args.size() != 1) throw new WrongArgumentsException();
        ticketKey = args.get(0);
        return this;
    }

    @Override
    public String description() {
        return "delete element from the collection due it's key value.";
    }
}
