package com.golem.app.commandSystem;

import com.golem.app.commandSystem.commandExceptions.OpenedScriptFileException;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.Input;

import java.util.List;

public interface Command {
    void process ();
    Command args (List<String> args, Input inputer) throws WrongArgumentsException, OpenedScriptFileException;
    String description ();
}
