package com.golem.app.commandSystem.commandExceptions;

/**
 * Exception thrown when in script array already opened script tried to open more than one time.
 */
public class OpenedScriptFileException extends Exception{
    public OpenedScriptFileException () {
        super("This file already opened.");
    }
}
