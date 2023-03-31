package com.golem.app.commandSystem.commandsCollection;

import com.golem.app.collection.TicketCollection;
import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;

import java.util.List;

/**
 * Все элементы коллекции в строковом представлении.
 */
public class Show implements Command {
    private final TicketCollection ticketCollection;

    /**
     * Конструктор команды show.
     * @param ticketCollection Объект для работы с коллекцией.
     */
    public Show (TicketCollection ticketCollection) {
        this.ticketCollection = ticketCollection;
    }

    /**
     * Метод process выполняет действия команды.
     */
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

    /**
     * Проверяет полученные аргументы на соответствие требуемому количеству.
     * @return Текущую команду.
     */
    @Override
    public Command args(List<String> args, Input inputer) throws WrongArgumentsException {
        if (args.size() != 0) throw  new WrongArgumentsException();
        return this;
    }

    /**
     * Содержит описание назначения команды.
     * @return Описание команды в строковом представлении.
     */
    @Override
    public String description() {
        return "shows information about current elements collection hold on.s";
    }
}
