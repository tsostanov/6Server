package commands;

import abstractions.ICommand;
import data.CommandData;
import data.ResultData;

import java.io.Serializable;

public class UpdateByIdCommand implements ICommand, Serializable {

    @Override
    public ResultData execute(CommandData commandData) {
        return commandData.labCollection.updateById(commandData);
    }

    @Override
    public boolean isClientCommand() {
        return false;
    }

    @Override
    public boolean hasElement() {
        return true;
    }

    @Override
    public boolean hasIntDigit() {
        return true;
    }

    @Override
    public boolean hasString() {
        return false;
    }

    @Override
    public String getName() {
        return "update_by_id";
    }

    @Override
    public String getDescription() {
        return "update the element by his id number";
    }
}
