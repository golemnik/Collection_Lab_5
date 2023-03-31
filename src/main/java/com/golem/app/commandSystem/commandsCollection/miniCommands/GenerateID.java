package com.golem.app.commandSystem.commandsCollection.miniCommands;

import com.golem.app.collection.TicketCollection;

import java.util.ArrayList;

public class GenerateID {
    public static int generate (TicketCollection collection) {
        int id = (int) System.currentTimeMillis();
        ArrayList<Integer> existId = new ArrayList<>();
        for (String s : collection.getCollection().keySet()) {
            Integer _int = collection.getCollection().get(s).getId();
            existId.add(_int);
            _int = collection.getCollection().get(s).getVenue().getId();
            if (_int != null) existId.add(_int);
        }
        for (int i = 0; i < existId.size(); i++) {
            if (existId.get(i) == id) {
                id = (int) System.currentTimeMillis();
                i = 0;
            }
        }
        return id;
    }
}
