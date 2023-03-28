package com.golem.app.commandSystem.commandsCollection;

import com.golem.app.collection.TicketCollection;
import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;

import java.util.List;

public class CountLessPrice implements Command {

    private final TicketCollection collection;
    private Double ticketPrice = null;
    public CountLessPrice (TicketCollection collection) {
        this.collection = collection;
    }
    @Override
    public void process() {
        int counter = 0;
        for (String s : collection.getCollection().keySet()) {
            counter += collection.getCollection().get(s).getPrice() < ticketPrice ? 1 : 0;
        }
        ConsolePrinter.out("Amount elements which price less than <<" +
                        ConsolePrinter.PURPLE(ticketPrice.toString()) + ">>:\n"+
                ConsolePrinter.PURPLE(String.valueOf(counter)));
    }

    @Override
    public Command args(List<String> args, Input inputer) throws WrongArgumentsException {
        if (args.size() != 1) throw new WrongArgumentsException();
        double arg;
        try {
            arg = Double.parseDouble(args.get(0));
        }
        catch (Exception e) {
            throw new WrongArgumentsException("Input value is not a double value.");
        }
        ticketPrice = arg;
        return this;
    }

    @Override
    public String description() {
        return null;
    }
}
