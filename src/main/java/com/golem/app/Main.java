package com.golem.app;

import com.golem.app.commandSystem.AppCycle;
import com.golem.app.fileSystem.ConsolePrinter;

public class Main {
    public static void main(String[] args) {
        if (args.length > 1) {
            System.out.println("secondary arguments wouldn't be used to.");
        }
        String file;
        if (args.length != 0) {
            try {
                file = System.getenv(args[0]);
            }
            catch (Exception e) {
                ConsolePrinter.out(e.getMessage());
                file = null;
            }
        }
        else {
            file = null;
        }
        AppCycle ac = new AppCycle(file);
        ac.globalCycle();
    }
}