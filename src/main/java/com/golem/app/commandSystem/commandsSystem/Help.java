package com.golem.app.commandSystem.commandsSystem;

import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;

import java.util.List;
import java.util.Map;

public class Help implements Command {
    private final Map<String, Command> list;

    public Help (Map<String, Command> list) {
        this.list = list;
        process();
    }
    @Override
    public void process() {
        for (String s : list.keySet()) {
            ConsolePrinter.out(ConsolePrinter.CYAN(s) + " - " + list.get(s).description());
        }
    }

    @Override
    public Command args(List<String> args, Input inputer) throws WrongArgumentsException {
        if (args.size() != 0) throw  new WrongArgumentsException();
        return this;
    }

    @Override
    public String description() {
        return "show list of supported commands and their descriptions.";
    }
}
