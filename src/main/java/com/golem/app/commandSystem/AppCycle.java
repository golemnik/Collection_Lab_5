package com.golem.app.commandSystem;

import com.golem.app.collection.TicketCollection;
import com.golem.app.commandSystem.commandsCollection.CommandCreator;
import com.golem.app.fileSystem.ConsoleInputer;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.JsonParser;


public class AppCycle {
    private final TicketCollection ticketCollection;
    private final CommandCreator creator;
    private final ConsoleInputer consoleInputer = new ConsoleInputer();
    public AppCycle (String file) {
        JsonParser jp = new JsonParser(file);
        ticketCollection = jp.parseLoad();
        creator = new CommandCreator(file, ticketCollection);
    }
    public void globalCycle () {
        while (true) {
            try {
                ConsolePrinter.out("Input command from the list. To display list, type <<" + ConsolePrinter.CYAN("help") + ">>.");
                creator.create(consoleInputer.input()).process();
            } catch (Exception e) {
                ConsolePrinter.out(e.getMessage());
            }
        }
    }
}