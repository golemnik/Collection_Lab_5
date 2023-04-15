package com.golem.app.commandSystem.commandExceptions;

/**
 * Exception thrown when user tried to insert unsupported command.
 */
public class WrongCommandException extends Exception{
    public WrongCommandException () {
        super("Unsupported command input.");
    }
}
