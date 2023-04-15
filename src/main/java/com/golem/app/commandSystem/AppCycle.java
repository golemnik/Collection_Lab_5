package com.golem.app.commandSystem;

import com.golem.app.collection.TicketCollection;
import com.golem.app.commandSystem.commandsCollection.miniCommands.DuplicateUnload;
import com.golem.app.fileSystem.ConsoleInputer;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;
import com.golem.app.fileSystem.JsonParser;

import java.io.File;
import java.io.IOException;


public class AppCycle {
    private final CommandCreator creator;
    private final Input inputer;
    private final ConsoleInputer consoleInputer = new ConsoleInputer();

    /**
     * Конструктор, производящий первичную загрузку данных из json файла.
     * @param file Файл из которого будет производиться чтение.
     */
    public AppCycle (String file) {
        inputer = new ConsoleInputer();
        TicketCollection ticketCollection = new TicketCollection();
        if (file != null) {
            JsonParser jp = new JsonParser(file);
            ticketCollection = jp.parseLoad();
            ConsolePrinter.out("file <<" + ConsolePrinter.RED(file) +
                    ">> will be created and used for command " +
                    ConsolePrinter.CYAN("save") + " to this collection.");
        }
        else {
            try {
                file = "save.json";
                File f = new File(file);
                if (f.createNewFile()) {
                    ConsolePrinter.out("Created new file <<save.json>> by system, " +
                            "cause of invalid user's file path. Collection will be empty.");
                    ticketCollection = new TicketCollection();
                }
                else {
                    ConsolePrinter.out("Will be used existed system file <<save.json>>," +
                            " cause of no file input or invalid path.");
                    JsonParser jp = new JsonParser(file);
                    ticketCollection = jp.parseLoad();
                    if (ticketCollection == null) {
                        ticketCollection = new TicketCollection();
                    }
                }
            } catch (IOException ex) {
                ConsolePrinter.out(ex.getMessage() + "\n" +
                        "Current doesn't support no file working process. Add permission to create system file" +
                        " or input correct path to it, by starting the jar.");
                System.exit(1);
            }
        }
        DuplicateUnload.dupUnload(ticketCollection);
        ConsolePrinter.out("Init collection state:" +
                ticketCollection.toReadString());
        creator = new CommandCreator(file, ticketCollection);
    }

    /**
     * Основной цикл выполнения команд пользовательского ввода.
     */
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