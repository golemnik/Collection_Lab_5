package com.golem.app.commandSystem.commandsCollection.miniCommands;

import com.golem.app.collection.TicketCollection;

import java.util.ArrayList;

/**
 *  generate id for tickets and there venue from system milliseconds time
 *  and check if it is in the collection already used. Otherwise, it will be regenerated.
 */
public class GenerateID {
    public static int generate (TicketCollection collection) {
        int id = (int) System.currentTimeMillis();
        ArrayList<Integer> existId = new ArrayList<>();
        for (String s : collection.getCollection().keySet()) {
            Integer _int = collection.getCollection().get(s).getId();
            existId.add(_int);
            if (collection.getCollection().get(s).getVenue() != null) {
                _int = collection.getCollection().get(s).getVenue().getId();
            }
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
