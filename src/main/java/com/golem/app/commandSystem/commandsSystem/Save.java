package com.golem.app.commandSystem.commandsSystem;

import com.golem.app.collection.TicketCollection;
import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;
import com.golem.app.fileSystem.JsonParser;

import java.util.List;

/**
 * Записывает коллекцию в файл.
 */
public class Save implements Command {
    private final TicketCollection collection;
    private final String file;

    /**
     * Конструктор команды save.
     * @param collection Объект для работы с коллекцией.
     * @param file Объект для работы с файлом.
     */
    public Save (TicketCollection collection, String file) {
        this.collection = collection;
        this.file = file;
    }

    /**
     * Метод process выполняет действия команды.
     */
    @Override
    public void process() {
        JsonParser jp;
        try {
            jp = new JsonParser(file);
            jp.parseSave(collection);
        }
        catch (Exception e) {
            ConsolePrinter.out(e.getMessage() +
                    "\nDue error to save in file, collection will be saved in system file <<save.json>>");
            jp = new JsonParser("save.json");
            jp.parseSave(collection);
        }

    }

    /**
     * Проверяет полученные аргументы на соответствие требуемому количеству.
     * @return Текущую команду.
     */
    @Override
    public Command args(List<String> args, Input inputer) throws WrongArgumentsException {
        if (args.size() != 0) throw new WrongArgumentsException();
        return this;
    }

    /**
     * Содержит описание назначения команды.
     * @return Описание команды в строковом представлении.
     */
    @Override
    public String description() {
        return "save collection to file";
    }
}
