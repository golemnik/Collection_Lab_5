package com.golem.app.commandSystem;

import com.golem.app.collection.TicketCollection;
import com.golem.app.fileSystem.ConsoleInputer;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;
import com.golem.app.fileSystem.JsonParser;


public class AppCycle {
    private final TicketCollection ticketCollection;
    private final CommandCreator creator;
    private final Input inputer;
    private final ConsoleInputer consoleInputer = new ConsoleInputer();
    public AppCycle (String file) {
        JsonParser jp = new JsonParser(file);
        inputer = new ConsoleInputer();
        ticketCollection = jp.parseLoad();
        creator = new CommandCreator(file, ticketCollection);
    }
    public void globalCycle () {
        while (true) {
            try {
                ConsolePrinter.out("Input command from the list. To display list, type <<" + ConsolePrinter.CYAN("help") + ">>.");
                creator.create(consoleInputer.input(), inputer).process();
            } catch (Exception e) {
                ConsolePrinter.out(e.getMessage());
            }
        }
    }
}