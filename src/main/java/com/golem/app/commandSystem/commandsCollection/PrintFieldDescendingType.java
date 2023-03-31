package com.golem.app.commandSystem.commandsCollection;

import com.golem.app.collection.TicketCollection;
import com.golem.app.collection.ticket.Ticket;
import com.golem.app.collection.ticket.InversedTicketTypeComparator;
import com.golem.app.commandSystem.Command;
import com.golem.app.commandSystem.commandExceptions.WrongArgumentsException;
import com.golem.app.fileSystem.ConsolePrinter;
import com.golem.app.fileSystem.Input;

import java.util.ArrayList;
import java.util.List;

/**
 * Выводит значение поля type всех элементов коллекции в порядке убывания.
 */
public class PrintFieldDescendingType implements Command {
    private final TicketCollection collection;

    /**
     * Конструктор команды print_field_descending_type.
     * @param collection Объект для работы с коллекцией.
     */
    public PrintFieldDescendingType (TicketCollection collection) {
        this.collection = collection;
    }

    /**
     * Метод process выполняет действия команды.
     */
    @Override
    public void process() {
        List<Ticket> list = new ArrayList<>(collection.getCollection().values());
        list.sort(new InversedTicketTypeComparator());
        for (Ticket t : list) {
            ConsolePrinter.out(t.getType().toString());
        }
    }

    /**
     * Проверяет полученные аргументы на соответствие требуемому количеству.
     * @return Текущую команду.
     */
    @Override
    public Command args(List<String> args, Input inputer) throws WrongArgumentsException {
        if (args.size() != 0) throw  new WrongArgumentsException();
        return this;
    }

    /**
     * Содержит описание назначения команды.
     * @return Описание команды в строковом представлении.
     */
    @Override
    public String description() {
        return "display the value of the type field of all elements in descending order";
    }
}
