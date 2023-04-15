package com.golem.app.commandSystem.commandsCollection.miniCommands;

import com.golem.app.collection.TicketCollection;
import com.golem.app.collection.ticket.Ticket;
import com.golem.app.fileSystem.ConsolePrinter;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DuplicateUnload {
    public static void dupUnload (TicketCollection collection) {
        ArrayList<Ticket> tickets = new ArrayList<>(collection.getCollection().values());
        LinkedHashMap<String, Ticket> col = new LinkedHashMap<>();
        boolean b = false;
        System.out.println(collection.getCollection().size());
        for (String s : collection.getCollection().keySet()) {
            for (String ss : col.keySet()) {
                if (collection.getCollection().get(s).getId() == col.get(ss).getId()){
                    ConsolePrinter.out("Duplicate for id <<" + collection.getCollection().get(s).getId() + ">> was unloaded");
                    b = true;
                }
            }
            if (!b) {
                col.put(s, collection.getCollection().get(s));
            }
            b = false;
        }
        collection.setCollection(col);
    }
}
