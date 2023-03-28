package com.golem.app.commandSystem.commandsSystem;

import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;

import java.util.List;

public class Exit implements Command {
    @Override
    public void process() {
        System.exit(0);
    }

    @Override
    public Command args(List<String> args, Input inputer) throws WrongArgumentsException {
        if (args.size() != 0) throw  new WrongArgumentsException();
        return this;
    }

    @Override
    public String description() {
        return "finish program execution. " +
                ConsolePrinter.YELLOW("Warning!") +
                " No conformation for this command, collection could lose all changes!";
    }
}
