package commands;

import data.CommandData;
import data.ResultData;
import abstractions.ICommand;

import java.io.Serializable;

public class RemoveFirstCommand implements ICommand, Serializable{
    @Override
    public ResultData execute(CommandData commandData) {
        return commandData.labCollection.removeFirst(commandData);
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
        return "remove_first";
    }

    @Override
    public String getDescription() {
        return "remove first element from collection";
    }
}
