package com.golem.app.commandSystem;

import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;

import java.util.ArrayList;
import java.util.List;

public interface Command {
    void process ();
    Command args (List<String> args) throws WrongArgumentsException;
    String description ();
}
