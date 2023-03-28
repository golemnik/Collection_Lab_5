package com.golem.app;

import com.golem.app.collection.TicketCollection;
import com.golem.app.commandSystem.AppCycle;
import com.golem.app.fileSystem.JsonParser;

public class Main {
    public static void main(String[] args) {
        String file = "test/test.json";
        AppCycle ac = new AppCycle(file);
        JsonParser jp = new JsonParser(file);
        TicketCollection tc = new TicketCollection();
        tc.testLoad();
        jp.parseSave(tc);
        ac.globalCycle();
    }
}