package com.golem.app.commandSystem.commandsSystem;

import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.OpenedScriptFileException;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.commandSystem.CommandCreator;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;
import com.golem.app.fileSystem.ScriptInputer;

import java.util.ArrayList;
import java.util.List;

public class ExecuteScript implements Command {
    private final List<String> openedScripts;
    private final List <String> commandsQueue = new ArrayList<>();
    private final CommandCreator creator;
    private ScriptInputer scriptInputer;
    public ExecuteScript (List<String> openedScripts, CommandCreator creator) {
        this.openedScripts = openedScripts;
        this.creator = creator;
    }
    @Override
    public void process() {
        String input = "";
        while ((input = scriptInputer.input()) != null) {
            commandsQueue.add(input);
        }
        while (commandsQueue.size() > 0 && (input = commandsQueue.get(0)) != null) {
            try {
                commandsQueue.remove(0);
                creator.create(input, scriptInputer).process();
            }
            catch (Exception e) {
                ConsolePrinter.out(e.getMessage());
            }
        }
        openedScripts.clear();
    }

    @Override
    public Command args(List<String> args, Input inputer) throws WrongArgumentsException, OpenedScriptFileException {
        if (args.size() != 1) throw new WrongArgumentsException();
        for (String s : openedScripts) {
            if (s.equals(args.get(0))) {
                throw new OpenedScriptFileException();
            }
        }
        String file = args.get(0);
        openedScripts.add(file);
        ScriptInputer.addTail(commandsQueue);
        scriptInputer = new ScriptInputer(file);
        return this;
    }

    /**
     * Содержит описание назначения команды.
     * @return Описание команды в строковом представлении.
     */
    @Override
    public String description() {
        return null;
    }
}
