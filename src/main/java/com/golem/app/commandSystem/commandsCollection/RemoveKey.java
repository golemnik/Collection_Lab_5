package com.golem.app.commandSystem.commandsCollection;

import com.golem.app.collection.TicketCollection;
import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;

import java.util.List;

/**
 * Удалить элемент из коллекции по его ключу.
 */
public class RemoveKey implements Command {
    private final TicketCollection collection;
    private String ticketKey = null;

    /**
     * Конструктор команды remove_key.
     * @param collection Объект для работы с коллекцией.
     */
    public RemoveKey (TicketCollection collection) {
        this.collection = collection;
    }

    /**
     * Метод process выполняет действия команды.
     */
    @Override
    public void process() {
        if (collection.getCollection().remove(ticketKey) != null) {
            ConsolePrinter.out("Element successfully deleted.");
            return;
        }
        ConsolePrinter.out("Element for this key doesn't exist.");
    }

    /**
     * Проверяет полученные аргументы на соответствие требуемому количеству.
     * @return Текущую команду.
     */
    @Override
    public Command args(List<String> args, Input inputer) throws WrongArgumentsException {
        if (args.size() != 1) throw new WrongArgumentsException();
        ticketKey = args.get(0);
        return this;
    }

    /**
     * Содержит описание назначения команды.
     * @return Описание команды в строковом представлении.
     */
    @Override
    public String description() {
        return "delete element from the collection due it's key value.";
    }
}
