package commands;

import data.CommandData;
import data.ResultData;
import abstractions.ICommand;

import java.io.Serializable;

public class InsertAtIdCommand implements ICommand, Serializable {
    @Override
    public ResultData execute(CommandData commandData) {
        return commandData.labCollection.insertAtId(commandData);
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
        return "insert_at_id";
    }

    @Override
    public String getDescription() {
        return "insert the element at id number";
    }
}
