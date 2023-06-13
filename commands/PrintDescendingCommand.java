package commands;

import data.CommandData;
import data.ResultData;
import abstractions.ICommand;

import java.io.Serializable;

public class PrintDescendingCommand implements ICommand, Serializable{
    @Override
    public ResultData execute(CommandData commandData) {
        return commandData.labCollection.printDescending(commandData);
    }
    @Override
    public boolean isClientCommand() {
        return false;
    }

    @Override
    public boolean hasElement() {
        return false;
    }

    @Override
    public boolean hasIntDigit() {
        return false;
    }

    @Override
    public boolean hasString() {
        return false;
    }

    @Override
    public String getName() {
        return "print_descending";
    }

    @Override
    public String getDescription() {
        return "sorts the elements in descending order and show them";
    }
}
