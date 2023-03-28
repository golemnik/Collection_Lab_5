package com.golem.app.commandSystem.commandsCollection;

import com.golem.app.collection.TicketCollection;
import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;

import java.util.List;

public class Show implements Command {
    private final TicketCollection ticketCollection;
    public Show (TicketCollection ticketCollection) {
        this.ticketCollection = ticketCollection;
    }
    @Override
    public void process() {
        if (ticketCollection.getCollection().size() == 0) {
            ConsolePrinter.out("Collection is " +
                    ConsolePrinter.PURPLE("empty") +
                    ". Nothing to " +
                    ConsolePrinter.CYAN("show") + ".");
            return;
        }
        String temp = "";
        temp += "\n";
        for (String s : ticketCollection.getCollection().keySet()) {
            temp += "~~~~~~~~~~~~~~\n";
            temp += ticketCollection.getCollection().get(s).toReadString() + "\n";
        }
        ConsolePrinter.out(temp);
    }

    @Override
    public Command args(List<String> args, Input inputer) throws WrongArgumentsException {
        if (args.size() != 0) throw  new WrongArgumentsException();
        return this;
    }

    @Override
    public String description() {
        return "shows information about current elements collection hold on.s";
    }
}
