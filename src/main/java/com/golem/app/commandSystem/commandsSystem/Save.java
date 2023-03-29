package com.golem.app.commandSystem.commandsSystem;

import com.golem.app.collection.TicketCollection;
import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;
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
        try {
            jp = new JsonParser(file);
            jp.parseSave(collection);
        }
        catch (Exception e) {
            ConsolePrinter.out(e.getMessage() +
                    "\nDue error to save in file, collection will be saved in system file <<save.json>>");
            jp = new JsonParser("save.json");
            jp.parseSave(collection);
        }

    }

    @Override
    public Command args(List<String> args, Input inputer) throws WrongArgumentsException {
        if (args.size() != 0) throw new WrongArgumentsException();
        return this;
    }

    @Override
    public String description() {
        return "save collection to file";
    }
}
