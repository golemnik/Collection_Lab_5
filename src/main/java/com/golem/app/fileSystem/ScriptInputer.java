package com.golem.app.fileSystem;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ScriptInputer implements Input{
    private final List<String> inputLines = new ArrayList<>();
    public ScriptInputer (String file) {
        char sym;
        String line = "";
        int character;
        try (BufferedInputStream bif = new BufferedInputStream(new FileInputStream(file))) {
            while ((character = bif.read())!=-1) {
                sym = (char)character;
                if (sym == '\n') {
                    inputLines.add(line);
                    line = "";
                }
                else {
                    line += sym;
                }
            }
        }
        catch (Exception e) {
            ConsolePrinter.out(e.getMessage());
        }
    }
    @Override
    public String input() {
        String input;
        if (inputLines.size() > 0) {
            input = inputLines.get(0);
            inputLines.remove(0);
            return input;
        }
        return null;
    }
}
