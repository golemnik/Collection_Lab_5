package com.golem.app.commandSystem.commandsSystem;

import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;

import java.util.List;
import java.util.Map;

/**
 * Выводит информацию о всех доступных командах.
 */
public class Help implements Command {
    private final Map<String, Command> list;

    /**
     * Конструктор команды help.
     * @param list Список команд с их описанием.
     */
    public Help (Map<String, Command> list) {
        this.list = list;
        process();
    }

    /**
     * Метод process выполняет действия команды.
     */
    @Override
    public void process() {
        for (String s : list.keySet()) {
            ConsolePrinter.out(ConsolePrinter.CYAN(s) + " - " + list.get(s).description());
        }
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
        return "show list of supported commands and their descriptions.";
    }
}
