package com.golem.app.commandSystem.commandExceptions;

/**
 * Exception thrown when command get wrong format or amount of arguments.
 */
public class WrongArgumentsException extends Exception{
    public WrongArgumentsException () {
        super("Unsupported arguments for the current command.");
    }
    public WrongArgumentsException (String message) {
        super(message);
    }
}
