package com.golem.app.commandSystem.commandsCollection;

import com.golem.app.collection.TicketCollection;
import com.golem.app.collection.ticket.Ticket;
import com.golem.app.collection.ticket.Venue;
import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.commandSystem.commandsCollection.miniCommands.InputCollectionElement;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;

import java.util.List;
import java.util.Scanner;

/**
 * Вывести элементы, значение поля venue который больше заданного.
 */
public class FilterGreaterVenue implements Command {
    private final TicketCollection collection;
    private final InputCollectionElement ice = new InputCollectionElement();
    private Input scanner;

    /**
     * Конструктора команды filter_greater_than_venue.
     * @param collection Объект для работы с коллекцией.
     */
    public FilterGreaterVenue (TicketCollection collection) {
        this.collection = collection;
    }

    /**
     * Метод process выполняет действия команды.
     */
    @Override
    public void process() {
        Venue venue;
        try {
            venue = InputCollectionElement.inputVenue(scanner, new Venue(), true);
        } catch (WrongArgumentsException e) {
            ConsolePrinter.out("Insert failed due: " + ConsolePrinter.YELLOW(e.getMessage()));
            return;
        }
        for (Ticket t : collection.getCollection().values()) {
            if (venue == null) {
                continue;
            }
            if (t.getVenue().compareTo(venue) > 0) {
                ConsolePrinter.out(t.toReadString() + "\n");
            }
        }
    }

    /**
     * Проверяет полученные аргументы на соответствие требуемому количеству.
     * @return Текущую команду.
     */
    @Override
    public Command args(List<String> args, Input inputer) throws WrongArgumentsException {
        if (args.size() != 0) throw  new WrongArgumentsException();
        scanner = inputer;
        return this;
    }

    /**
     * Содержит описание назначения команды.
     * @return Описание команды в строковом представлении.
     */
    @Override
    public String description() {
        return "display elements whose venue field value is greater than the given one.";
    }
}
