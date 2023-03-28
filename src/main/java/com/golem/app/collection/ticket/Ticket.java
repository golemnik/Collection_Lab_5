package com.golem.app.collection.ticket;

import com.golem.app.collection.ConsoleRead;

import java.time.LocalDate;

public class Ticket implements Comparable <Ticket>, ConsoleRead {
    private int id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private String name = ""; //Поле не может быть null, Строка не может быть пустой

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private Coordinates coordinates; //Поле не может быть null

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private Double price; //Значение поля должно быть больше 0

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
            return price;
        }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private String comment; //Строка не может быть пустой, Поле не может быть null

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
}
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private TicketType type; //Поле не может быть null

    public void setType(TicketType type) {
        this.type = type;
    }

    public TicketType getType() {
        return type;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private Venue venue; //Поле может быть null

    public void setVenue (Venue venue) {
        this.venue = venue;
    }
    public Venue getVenue() {
        return venue;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    {
        coordinates = new Coordinates();
        venue = new Venue();
    }
    public Ticket () {}
    public Ticket (boolean init) {
        if (init) this.testLoad();
    }

    public void testLoad() {
        id = -1;
        name = "unnamed";
        coordinates = new Coordinates(true);
        creationDate = LocalDate.now();
        price = 0.0;
        comment = "no comments";
        type = TicketType.CHEAP;
        venue = new Venue(true);
    }

    @Override
    public int compareTo(Ticket o) {
        int compare;
        if ((compare = getName().compareTo(o.getName())) != 0) return compare;
        if ((compare = getCoordinates().compareTo(o.getCoordinates())) != 0) return compare;
        if ((compare = getPrice().compareTo(o.getPrice())) != 0) return compare;
        if ((compare = getComment().compareTo(o.getComment())) != 0) return compare;
        if ((compare = getType().compareTo(o.getType())) != 0) return compare;
        if ((compare = getVenue().compareTo(o.getVenue())) != 0) return compare;
        if ((compare = getCreationDate().compareTo(o.getCreationDate())) != 0) return compare;
        return o.getId() - getId();
    }

    @Override
    public String toReadString() {
        return "Ticket >>\n" +
                "   id: " + id + "\n"+
                "   name: " + name + "\n"+
                "   creationDate: " + creationDate + "\n"+
                "   price: " + price + "\n"+
                "   comment: " + comment + "\n"+
                "   type: " + type.toString() + "\n"+
                coordinates.toReadString() + "\n"+
                venue.toReadString();
    }
    public enum TicketType {
        VIP,
        USUAL,
        BUDGETARY,
        CHEAP
    }

}