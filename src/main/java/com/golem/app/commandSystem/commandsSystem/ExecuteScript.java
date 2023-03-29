package com.golem.app.commandSystem.commandsSystem;

import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.commandSystem.CommandCreator;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;
import com.golem.app.fileSystem.ScriptInputer;

import java.util.List;

public class ExecuteScript implements Command {
    private String file;
    private final List<String> openedScripts;
    private final CommandCreator creator;
    private ScriptInputer scriptInputer;
    public ExecuteScript (List<String> openedScripts, CommandCreator creator) {
        this.openedScripts = openedScripts;
        this.creator = creator;
    }
    @Override
    public void process() {
        String input = "";
        do {
            input = scriptInputer.input();
            if (input == null) {
                openedScripts.remove(file);
                return;
            }
            try {
                creator.create(input, scriptInputer).process();
            }
            catch (Exception e) {
                ConsolePrinter.out(e.getMessage());
            }
        } while (true);
    }

    @Override
    public Command args(List<String> args, Input inputer) throws WrongArgumentsException {
        if (args.size() != 1) throw new WrongArgumentsException();
        file = args.get(0);
        for (String s : openedScripts) {
            if (s.equals(file)) throw new WrongArgumentsException("This file already opened.");
        }
        openedScripts.add(file);
        scriptInputer = new ScriptInputer(file);
        return this;
    }
    @Override
    public String description() {
        return null;
    }
}
