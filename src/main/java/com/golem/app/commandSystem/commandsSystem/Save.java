package com.golem.app.commandSystem.commandsSystem;

import com.golem.app.collection.TicketCollection;
import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.JsonParser;

import java.util.List;

public class Save implements Command {
    private final TicketCollection collection;
    private final String file;
    private JsonParser jp;
    public Save (TicketCollection collection, String file) {
        this.collection = collection;
        this.file = file;
    }
    @Override
    public void process() {
        jp = new JsonParser(file);
        jp.parseSave(collection);
    }

    @Override
    public Command args(List<String> args) throws WrongArgumentsException {
        if (args.size() != 0) throw new WrongArgumentsException();
        return this;
    }

    @Override
    public String description() {
        return "save collection to file";
    }
}
