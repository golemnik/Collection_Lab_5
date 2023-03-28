package com.golem.app.commandSystem.commandsCollection;

import com.golem.app.collection.TicketCollection;
import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;

import java.util.List;

public class Info implements Command {
    private final TicketCollection ticketCollection;
    private boolean showElements;
    public Info (TicketCollection ticketCollection, boolean showElements) {
        this.ticketCollection = ticketCollection;
        this.showElements = showElements;
    }
    @Override
    public void process() {
        String temp = "";
        if (ticketCollection.getCollection().size() == 0 && showElements) {
            temp = "Collection is " +
                    ConsolePrinter.PURPLE("empty") +
                    ". Nothing to " +
                    ConsolePrinter.CYAN("show") + ".";
        }
        else if (showElements) {
            temp += "\n";
            for (String s : ticketCollection.getCollection().keySet()) {
                temp += "~~~~~~~~~~~~~~~~~~~~~~~~\n";
                temp += "key: " + s + "\n";
                temp += ticketCollection.getCollection().get(s).toReadString() + "\n";
            }
        }
        ConsolePrinter.out(ticketCollection.toReadString()+temp);
    }

    @Override
    public Command args(List<String> args, Input inputer) throws WrongArgumentsException {
        if (args.size() > 1) throw new WrongArgumentsException();
        if (args.size() == 1) {
            boolean arg;
            switch (args.get(0)) {
                case "true", "false", "" -> arg = Boolean.parseBoolean(args.get(0));
                default -> throw new WrongArgumentsException();
            }
            showElements = arg;
        }
        return this;
    }
    @Override
    public String description() {
        return "shows information for current collection." +
                " Unnecessary argument will cause program to type collection in detailed format.";
    }
}
