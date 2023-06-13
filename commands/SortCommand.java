package commands;

import data.CommandData;
import data.ResultData;
import abstractions.ICommand;

import java.io.Serializable;

public class SortCommand implements ICommand, Serializable{
    @Override
    public ResultData execute(CommandData commandData) {
        return commandData.labCollection.sort(commandData);
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
        return "sort";
    }

    @Override
    public String getDescription() {
        return "reorder elements in collection by their id";
    }
}

