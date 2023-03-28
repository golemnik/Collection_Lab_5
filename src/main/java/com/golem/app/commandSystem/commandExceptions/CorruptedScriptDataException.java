package com.golem.app.commandSystem.commandExceptions;

public class CorruptedScriptDataException extends Exception{
    public CorruptedScriptDataException(String file) {
        super("Data in file <<" + file + ">> is corrupted. Execution stopped.");
    }
}
