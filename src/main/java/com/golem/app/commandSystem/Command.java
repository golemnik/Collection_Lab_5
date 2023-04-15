package com.golem.app.commandSystem;

import com.golem.app.commandSystem.commandExceptions.OpenedScriptFileException;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.Input;

import java.util.List;

/**
 * Interface for all commands, declare general methods.
 */

public interface Command {

    /**
     * Метод process выполняет действия команды.
     */
    void process ();

    /**
     * Проверяет полученные аргументы на соответствие требуемому количеству.
     * @return Текущую команду.
     */
    Command args (List<String> args, Input inputer) throws WrongArgumentsException, OpenedScriptFileException;

    /**
     * Содержит описание назначения команды.
     * @return Описание команды в строковом представлении.
     */
    String description ();
}
