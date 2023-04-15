package com.golem.app.commandSystem;

import com.golem.app.collection.TicketCollection;
import com.golem.app.commandSystem.commandExceptions.OpenedScriptFileException;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.commandSystem.commandExceptions.WrongCommandException;
import com.golem.app.commandSystem.commandsCollection.*;
import com.golem.app.commandSystem.commandsSystem.Exit;
import com.golem.app.commandSystem.commandsSystem.Help;
import com.golem.app.commandSystem.commandsSystem.Save;
import com.golem.app.commandSystem.commandsSystem.ExecuteScript;
import com.golem.app.fileSystem.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class CommandCreator {
    private final LinkedHashMap<String, Command> commands = new LinkedHashMap<>();
    private ArrayList<String> openedScripts = new ArrayList<>();
    private Boolean script = false;

    /**
     * Конструктор, который содержит информацию о командах и объектах одноименных классов.
     * @param file Передает информацию о файле для выгрузки.
     * @param ticketCollection Передает текущую коллекцию.
     */
    public CommandCreator (String file, TicketCollection ticketCollection) {
        commands.put("help", new Help(commands));
        commands.put("exit", new Exit());
        commands.put("info", new Info(ticketCollection, false));
        commands.put("clear", new Clear(ticketCollection));
        commands.put("show", new Show(ticketCollection));
        commands.put("insert", new Insert(ticketCollection));
        commands.put("remove_key", new RemoveKey(ticketCollection));
        commands.put("update", new UpdateId(ticketCollection));
        commands.put("save", new Save(ticketCollection, file));
        commands.put("remove_greater", new RemoveGreater(ticketCollection));
        commands.put("replace_greater", new ReplaceGreater(ticketCollection));
        commands.put("replace_greater_key", new RemoveGreaterKey(ticketCollection));
        commands.put("count_less_than_price", new CountLessPrice(ticketCollection));
        commands.put("filter_greater_than_venue", new FilterGreaterVenue(ticketCollection));
        commands.put("print_field_type", new PrintFieldDescendingType(ticketCollection));
        commands.put("execute_script", new ExecuteScript(openedScripts, this));
    }// todo factory/builder

    /**
     * Метод, преобразующий пользовательский ввод в команду для исполнения.
     * @param string Введенная пользователем информация.
     * @param inputer information о том, откуда команда к нам пришла. (консоль/скрипт)
     * @return Команду, проверенную на правильность аргументов
     */
    public Command create (String string, Input inputer) throws WrongCommandException, WrongArgumentsException, OpenedScriptFileException {
        List<String> input = new ArrayList<>(Arrays.asList(string.trim().split(" ")));
        if (commands.get(input.get(0)) == null) {
            throw new WrongCommandException();
        }
        String command = input.get(0);
        input.remove(0);
        return commands.get(command).args(input, inputer);
    }
}
