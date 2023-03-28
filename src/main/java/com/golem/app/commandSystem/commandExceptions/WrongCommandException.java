package com.golem.app.commandSystem.commandExceptions;

public class WrongCommandException extends Exception{
    public WrongCommandException () {
        super("Unsupported command input.");
    }
}
