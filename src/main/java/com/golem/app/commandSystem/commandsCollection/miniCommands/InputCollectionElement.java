package com.golem.app.commandSystem.commandsCollection.miniCommands;

import com.golem.app.collection.TicketCollection;
import com.golem.app.collection.ticket.Address;
import com.golem.app.collection.ticket.Coordinates;
import com.golem.app.collection.ticket.Ticket;
import com.golem.app.collection.ticket.Venue;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;

import java.time.LocalDate;

/**
 * subcommand class for inputting ticket information from user or script
 */
public class InputCollectionElement {
    /**
     * set id for ticket and it's venue
     * @param collection - ticket collection
     * @param ticket - current ticket
     */
    public static void setID (TicketCollection collection, Ticket ticket) {
        ticket.setId(GenerateID.generate(collection));
        if (ticket.getVenue() != null) {
            ticket.getVenue().setId(GenerateID.generate(collection));
        }
    }

    /**
     *
     * @param scanner - class for choose input stream - script or console
     * @param ticket
     * @param comment
     * @return
     * @throws WrongArgumentsException
     */
    public static Ticket inputElement(Input scanner, Ticket ticket, boolean comment) throws WrongArgumentsException {
        ticket.setCreationDate(LocalDate.now());

        tName(scanner, ticket, comment);
        tPrice(scanner, ticket, comment);
        tComment(scanner, ticket, comment);
        tType(scanner, ticket, comment);
        Coordinates coord = new Coordinates();
        tCoordinatesX(scanner, coord, comment);
        tCoordinatesY(scanner, coord, comment);
        ticket.setCoordinates(coord);

        Venue venue = new Venue();
        venue = inputVenue(scanner, venue, comment);

        ticket.setVenue(venue);
        return ticket;
    }

    public static Venue inputVenue (Input scanner, Venue venue, boolean comment) throws WrongArgumentsException {
        ConsolePrinter.out("Need to input Venue? Type yes for input. Other input - for no.");
        if (scanner.input().equals("yes")) {
            tVenueName(scanner, venue, comment);
            tVenueCapacity(scanner, venue, comment);
            tVenueType(scanner, venue, comment);
            Address address = new Address();
            tAddress(scanner, address, comment);

            venue.setAddress(address);
            return venue;
        }
        return null;
    }
    private static void tName(Input scanner, Ticket ticket, boolean comment) throws WrongArgumentsException {
        if (comment) {
            ConsolePrinter.out("Type ticket name. It can't be " +
                    ConsolePrinter.PURPLE("null") + " or " +
                    ConsolePrinter.PURPLE("empty") + ":");
        }
        do {
            try {
                String value = scanner.input();
                if (value == null || value.equals("")) {
                    throw new WrongArgumentsException("Unsupported string for name. Try again.");
                }
                ticket.setName(value);
                comment = false;
            }
            catch (Exception e) {
                if (comment) {
                    ConsolePrinter.out(e.getMessage());
                }
                else {
                    throw new WrongArgumentsException("Script mistake input.");
                }
            }
        } while (comment);
    }
    private static void tPrice (Input scanner, Ticket ticket, boolean comment) throws WrongArgumentsException {
        if (comment) {
            ConsolePrinter.out("Type ticket price. It must be greater than " +
                    ConsolePrinter.PURPLE("0") + ", but could be " +
                    ConsolePrinter.PURPLE("double") + ":");
        }
        do {
            try {
                String value = scanner.input();
                double tempValue = Double.parseDouble(value);
                if (tempValue <= 0) {
                    throw new WrongArgumentsException("Input price can't be less or equal to zero. Try again.");
                }
                if (tempValue == Double.POSITIVE_INFINITY){
                    throw new WrongArgumentsException("Input price can't be INFINITY. Try again.");
                }
                if (Double.isNaN(tempValue)) {
                    throw new WrongArgumentsException("Input price can't be NaN. Try again.");
                }
                ticket.setPrice(tempValue);
                comment = false;
            } catch (WrongArgumentsException e) {
                if (comment) {
                    ConsolePrinter.out(e.getMessage());
                }
                else {
                    throw new WrongArgumentsException("Script mistake input.");
                }
            } catch (Exception e) {
                if (comment) {
                    ConsolePrinter.out("Input value isn't a number. Try again.");
                }
                else {
                    throw new WrongArgumentsException("Script mistake input.");
                }
            }
        } while (comment);
    }
    private static void tComment (Input scanner, Ticket ticket, boolean comment) throws WrongArgumentsException {
        if (comment) {
            ConsolePrinter.out("Type comment to this ticket. It can be " +
                    ConsolePrinter.PURPLE("null") + " or " +
                    ConsolePrinter.PURPLE("empty") + ":");
        }
        do {
            try {
                String value = scanner.input();
                ticket.setComment(value);
                comment = false;
            }
            catch (Exception e) {
                if (comment) {
                    ConsolePrinter.out(e.getMessage());
                }
                else {
                    throw new WrongArgumentsException("Script mistake input.");
                }
            }
        } while (comment);

    }
    private static void tType (Input scanner, Ticket ticket, boolean comment) throws WrongArgumentsException {
        if (comment) {
            String temp = "";
            for (Ticket.TicketType t : Ticket.TicketType.values()) {
                temp += "   " + ConsolePrinter.PURPLE(t.toString()) + "\n";
            }
            ConsolePrinter.out("Choose type for this ticket. It can be one from the following list:\n" +
                    temp + "Write chosen type:");
        }
        do {
            String value = scanner.input();
            try {
                ticket.setType(Ticket.TicketType.valueOf(value));
                comment = false;
            }
            catch (Exception e) {
                if (comment) {
                    ConsolePrinter.out("Unsupported type was chosen. Try again.");
                }
                else {
                    throw new WrongArgumentsException("Script mistake input.");
                }
            }
        }while (comment);
    }
    private static void tCoordinatesX (Input scanner, Coordinates coord, boolean comment) throws WrongArgumentsException {
        if (comment) {
            ConsolePrinter.out("Type ticket " +
                    ConsolePrinter.BLUE("x") +
                    " coordinate. It can't be " +
                    ConsolePrinter.PURPLE("null") + ", but could be " +
                    ConsolePrinter.PURPLE("long") + ":");
        }
        do {
            try {
                String value = scanner.input();
                coord.setX(Long.parseLong(value));
                comment = false;
            } catch (Exception e) {
                if (comment) {
                    ConsolePrinter.out("Input value isn't a number for long format. Try again.");
                }
                else {
                    throw new WrongArgumentsException("Script mistake input.");
                }
            }
        } while (comment);
    }
    private static void tCoordinatesY (Input scanner, Coordinates coord, boolean comment) throws WrongArgumentsException {
        if (comment) {
            ConsolePrinter.out("Type ticket " +
                    ConsolePrinter.BLUE("y") +
                    " coordinate. It can't be null " +
                    ConsolePrinter.PURPLE("null") + " or greater than " +
                    ConsolePrinter.PURPLE("990") + ", but could be " +
                    ConsolePrinter.PURPLE("long") + ":");
        }
        do {
            try {
                String value = scanner.input();
                Long tempValue = Long.parseLong(value);
                if (tempValue > 990) {
                    throw new WrongArgumentsException("Input y coordinate is too big. Try again.");
                }
                coord.setY(tempValue);
                comment = false;
            } catch (WrongArgumentsException e) {
                if (comment) {
                    ConsolePrinter.out(e.getMessage());
                }
                else {
                    throw new WrongArgumentsException("Script mistake input.");
                }
            }
            catch (Exception e) {
                if (comment) {
                    ConsolePrinter.out("Input value isn't a number for long format. Try again.");
                }
                else {
                    throw new WrongArgumentsException("Script mistake input.");
                }
            }
        } while (comment);
    }
    private static void tVenueName (Input scanner, Venue venue, boolean comment) throws WrongArgumentsException {
        if (comment) {
            ConsolePrinter.out("Type ticket's venue name. It can't be " +
                    ConsolePrinter.PURPLE("null") + " or " +
                    ConsolePrinter.PURPLE("empty") + ":");
        }
        do {
            try {
                String value = scanner.input();
                if (value == null || value.equals("")) {
                    throw new WrongArgumentsException("Unsupported string for name. Try again.");
                }
                venue.setName(value);
                comment = false;
            }
            catch (Exception e) {
                if (comment) {
                    ConsolePrinter.out(e.getMessage());
                }
                else {
                    throw new WrongArgumentsException("Script mistake input.");
                }
            }
        } while (comment);
    }
    private static void tVenueCapacity (Input scanner, Venue venue, boolean comment) throws WrongArgumentsException {
        if (comment) {
            ConsolePrinter.out("Type ticket's venue capacity. It must be greater than " +
                    ConsolePrinter.PURPLE("0") + " and not " +
                    ConsolePrinter.PURPLE("null") + ", but could be " +
                    ConsolePrinter.PURPLE("long") + ":");
        }
        do {
            try {
                String value = scanner.input();
                long tempValue = Long.parseLong(value);
                if (tempValue <= 0) {
                    throw new WrongArgumentsException("Input capacity is less than zero. Try again.");
                }
                venue.setCapacity(tempValue);
                comment = false;
            } catch (WrongArgumentsException e) {
                if (comment) {
                    ConsolePrinter.out(e.getMessage());
                }
                else {
                    throw new WrongArgumentsException("Script mistake input.");
                }
            } catch (Exception e) {
                if (comment) {
                    ConsolePrinter.out("Input value isn't a number. Try again.");
                }
                else {
                    throw new WrongArgumentsException("Script mistake input.");
                }
            }
        } while (comment);
    }
    private static void tVenueType (Input scanner, Venue venue, boolean comment) throws WrongArgumentsException {
        if (comment) {
            String temp = "";
            for (Venue.VenueType t : Venue.VenueType.values()) {
                temp += "   " + ConsolePrinter.PURPLE(t.toString()) + "\n";
            }
            temp += "   " + ConsolePrinter.PURPLE("null") + "\n";
            ConsolePrinter.out("Choose type for this ticket's venue. It can be one from the following list:\n" +
                    temp + "Write chosen type:");
        }
        do {
            String value = scanner.input();
            if (value.isEmpty()) {
                venue.setType(null);
                return;
            }
            try {
                venue.setType(Venue.VenueType.valueOf(value));
                comment = false;
            }
            catch (Exception e) {
                if (comment) {
                    ConsolePrinter.out("Unsupported type was chosen. Try again.");
                }
                else {
                    throw new WrongArgumentsException("Script mistake input.");
                }
            }
        } while (comment);
    }
    private static void tAddress(Input scanner, Address address, boolean comment) throws WrongArgumentsException {
        if (comment) {
            ConsolePrinter.out("Type ticket's venue address. It can't be " +
                    ConsolePrinter.PURPLE("null") + ":");
        }
        do {
            try {
                String value = scanner.input();
                if (value.isEmpty()) {
                    address.setStreet(null);
                }
                else {
                    address.setStreet(value);
                }
                comment = false;
            }
            catch (Exception e) {
                if (comment) {
                    ConsolePrinter.out(e.getMessage());
                }
                else {
                    throw new WrongArgumentsException("Script mistake input.");
                }
            }
        } while (comment);
    }
}
