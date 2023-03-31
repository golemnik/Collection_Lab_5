package com.golem.app.commandSystem.commandsCollection;

import com.golem.app.collection.TicketCollection;
import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;

import java.util.List;
import java.util.LinkedHashMap;

/**
 * Очищение всех элементов коллекции
 */
public class Clear implements Command {
    TicketCollection collection;

    /**
     * Конструктора команды clear.
     * @param collection Объект для работы с коллекцией.
     */
    public Clear (TicketCollection collection) {
        this.collection = collection;
    }

    /**
     * Метод process выполняет действия команды.
     */
    @Override
    public void process() {
        collection.setCollection(new LinkedHashMap<>());
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
        return "delete all collection elements. " +
                ConsolePrinter.YELLOW("Warning!") +
                " No conformation for this command!";
    }
}
