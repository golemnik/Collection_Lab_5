package com.golem.app.commandSystem.commandsSystem;

import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;

import java.util.List;

/**
 * Завершает работу с приложением.
 */
public class Exit implements Command {

    /**
     * Метод process выполняет действия команды.
     */
    @Override
    public void process() {
        System.exit(0);
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
        return "finish program execution. " +
                ConsolePrinter.YELLOW("Warning!") +
                " No conformation for this command, collection could lose all changes!";
    }
}
