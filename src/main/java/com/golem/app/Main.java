package com.golem.app;

import com.golem.app.commandSystem.AppCycle;

public class Main {
    public static void main(String[] args) {
        if (args.length > 1) {
            System.out.println("secondary arguments wouldn't be used to.");
        }
        String file;
        if (args.length != 0) {
            file = args[0];
        }
        else {
            file = null;
        }
        AppCycle ac = new AppCycle(file);
//        JsonParser jp = new JsonParser(file);
//        TicketCollection tc = new TicketCollection();
//        tc.testLoad();
//        jp.parseSave(tc);
        ac.globalCycle();
    }
}