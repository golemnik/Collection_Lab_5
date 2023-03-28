package com.golem.app.commandSystem.commandsCollection.miniCommands;

import com.golem.app.collection.ticket.Address;
import com.golem.app.collection.ticket.Coordinates;
import com.golem.app.collection.ticket.Ticket;
import com.golem.app.collection.ticket.Venue;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.ConsolePrinter;

import java.util.Scanner;

public class InputCollectionElement {
    public Ticket inputElement(Scanner scanner, Ticket ticket, boolean comment) {
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

        Address address = new Address();
        tAddress(scanner, address, comment);

        venue.setAddress(address);
        ticket.setVenue(venue);
        return ticket;
    }

    public Venue inputVenue (Scanner scanner, Venue venue, boolean comment) {
        tVenueName(scanner, venue, comment);
        tVenueCapacity(scanner, venue, comment);
        tVenueType(scanner, venue, comment);
        return venue;
    }

    private void tName(Scanner scanner, Ticket ticket, boolean comment) {
        if (comment) {
            ConsolePrinter.out("Type ticket name. It can't be " +
                    ConsolePrinter.PURPLE("null") + " or " +
                    ConsolePrinter.PURPLE("empty") + ":");
        }
        do {
            try {
                String value = scanner.nextLine();
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
            }
        } while (comment);
    }
    private void tPrice (Scanner scanner, Ticket ticket, boolean comment) {
        if (comment) {
            ConsolePrinter.out("Type ticket price. It must be greater than " +
                    ConsolePrinter.PURPLE("0") + ", but could be " +
                    ConsolePrinter.PURPLE("double") + ":");
        }
        do {
            try {
                String value = scanner.nextLine();
                double tempValue = Double.parseDouble(value);
                if (tempValue <= 0) {
                    throw new WrongArgumentsException("Input price is less than zero. Try again.");
                }
                ticket.setPrice(tempValue);
                comment = false;
            } catch (WrongArgumentsException e) {
                if (comment) {
                    ConsolePrinter.out(e.getMessage());
                }
            } catch (Exception e) {
                if (comment) {
                    ConsolePrinter.out("Input value isn't a number. Try again.");
                }
            }
        } while (comment);
    }
    private void tComment (Scanner scanner, Ticket ticket, boolean comment) {
        if (comment) {
            ConsolePrinter.out("Type comment to this ticket. It can't be " +
                    ConsolePrinter.PURPLE("null") + " or " +
                    ConsolePrinter.PURPLE("empty") + ":");
        }
        do {
            try {
                String value = scanner.nextLine();
                if (value == null || value.equals("")) {
                    throw new WrongArgumentsException("Unsupported string for comment. Try again.");
                }
                ticket.setComment(value);
                comment = false;
            }
            catch (Exception e) {
                if (comment) {
                    ConsolePrinter.out(e.getMessage());
                }
            }
        } while (comment);

    }
    private void tType (Scanner scanner, Ticket ticket, boolean comment) {
        if (comment) {
            String temp = "";
            for (Ticket.TicketType t : Ticket.TicketType.values()) {
                temp += "   " + ConsolePrinter.PURPLE(t.toString()) + "\n";
            }
            ConsolePrinter.out("Choose type for this ticket. It can be one from the following list:\n" +
                    temp + "Write chosen type:");
        }
        do {
            String value = scanner.nextLine();
            try {
                ticket.setType(Ticket.TicketType.valueOf(value));
                comment = false;
            }
            catch (Exception e) {
                if (comment) {
                    ConsolePrinter.out("Unsupported type was chosen. Try again.");
                }
            }
        }while (comment);
    }
    private void tCoordinatesX (Scanner scanner, Coordinates coord, boolean comment) {
        if (comment) {
            ConsolePrinter.out("Type ticket " +
                    ConsolePrinter.BLUE("x") +
                    " coordinate. It can't be " +
                    ConsolePrinter.PURPLE("null") + ", but could be " +
                    ConsolePrinter.PURPLE("long") + ":");
        }
        do {
            try {
                String value = scanner.nextLine();
                coord.setX(Long.parseLong(value));
                comment = false;
            } catch (Exception e) {
                if (comment) {
                    ConsolePrinter.out("Input value isn't a number for long format. Try again.");
                }
            }
        } while (comment);
    }
    private void tCoordinatesY (Scanner scanner, Coordinates coord, boolean comment) {
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
                String value = scanner.nextLine();
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
            }
            catch (Exception e) {
                if (comment) {
                    ConsolePrinter.out("Input value isn't a number for long format. Try again.");
                }
            }
        } while (comment);
    }
    private void tVenueName (Scanner scanner, Venue venue, boolean comment) {
        if (comment) {
            ConsolePrinter.out("Type ticket's venue name. It can't be " +
                    ConsolePrinter.PURPLE("null") + " or " +
                    ConsolePrinter.PURPLE("empty") + ":");
        }
        do {
            try {
                String value = scanner.nextLine();
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
            }
        } while (comment);
    }
    private void tVenueCapacity (Scanner scanner, Venue venue, boolean comment) {
        if (comment) {
            ConsolePrinter.out("Type ticket's venue capacity. It must be greater than " +
                    ConsolePrinter.PURPLE("0") + " and not " +
                    ConsolePrinter.PURPLE("null") + ", but could be " +
                    ConsolePrinter.PURPLE("long") + ":");
        }
        do {
            try {
                String value = scanner.nextLine();
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
            } catch (Exception e) {
                if (comment) {
                    ConsolePrinter.out("Input value isn't a number. Try again.");
                }
            }
        } while (comment);
    }
    private void tVenueType (Scanner scanner, Venue venue, boolean comment) {
        if (comment) {
            String temp = "";
            for (Venue.VenueType t : Venue.VenueType.values()) {
                temp += "   " + ConsolePrinter.PURPLE(t.toString()) + "\n";
            }
            ConsolePrinter.out("Choose type for this ticket's venue. It can be one from the following list:\n" +
                    temp + "Write chosen type:");
        }
        do {
            String value = scanner.nextLine();
            try {
                venue.setType(Venue.VenueType.valueOf(value));
                comment = false;
            }
            catch (Exception e) {
                if (comment) {
                    ConsolePrinter.out("Unsupported type was chosen. Try again.");
                }
            }
        } while (comment);
    }
    private void tAddress(Scanner scanner, Address address, boolean comment) {
        if (comment) {
            ConsolePrinter.out("Type ticket's venue address. It can't be " +
                    ConsolePrinter.PURPLE("null") + ":");
        }
        do {
            try {
                String value = scanner.nextLine();
                if (value == null) {
                    throw new WrongArgumentsException("Unsupported string for name. Try again.");
                }
                address.setStreet(value);
                comment = false;
            }
            catch (Exception e) {
                if (comment) {
                    ConsolePrinter.out(e.getMessage());
                }
            }
        } while (comment);
    }
}
