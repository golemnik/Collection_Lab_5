package com.golem.app.commandSystem.commandExceptions;

public class WrongArgumentsException extends Exception{
    public WrongArgumentsException () {
        super("Unsupported arguments for the current command.");
    }
    public WrongArgumentsException (String message) {
        super(message);
    }
}
