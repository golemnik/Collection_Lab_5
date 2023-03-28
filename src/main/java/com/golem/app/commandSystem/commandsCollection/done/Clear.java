package com.golem.app.commandSystem.commandsCollection.done;

import com.golem.app.collection.TicketCollection;
import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.ConsolePrinter;

import java.util.List;
import java.util.LinkedHashMap;

public class Clear implements Command {
    TicketCollection collection;
    public Clear (TicketCollection collection) {
        this.collection = collection;
    }
    @Override
    public void process() {
        collection.setCollection(new LinkedHashMap<>());
    }

    @Override
    public Command args(List<String> args) throws WrongArgumentsException {
        if (args.size() != 0) throw  new WrongArgumentsException();
        return this;
    }

    @Override
    public String description() {
        return "delete all collection elements. " +
                ConsolePrinter.YELLOW("Warning!") +
                " No conformation for this command!";
    }
}
