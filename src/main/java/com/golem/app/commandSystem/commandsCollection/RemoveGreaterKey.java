package com.golem.app.commandSystem.commandsCollection;

import com.golem.app.collection.TicketCollection;
import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.Input;

import java.util.List;

/**
 * Удалить из коллекции все элементы, ключ которых превышает заданный.
 */
public class RemoveGreaterKey implements Command {
    private final TicketCollection collection;
    private String ticketKey;

    /**
     * Конструктора команды remove_greater_key.
     * @param collection Объект для работы с коллекцией.
     */
    public RemoveGreaterKey(TicketCollection collection) {
        this.collection = collection;
    }

    /**
     * Метод process выполняет действия команды.
     */
    @Override
    public void process() {
        collection.getCollection().keySet().removeIf(s -> s.compareTo(ticketKey) > 0);
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
        return "remove elements, which key is greater, than user will input.";
    }
}
