package commands;

import abstractions.ICommand;
import data.CommandData;
import data.ResultData;

import java.io.Serializable;

public class ClearCommand implements ICommand, Serializable {

    @Override
    public ResultData execute(CommandData commandData){
        return commandData.labCollection.clear(commandData);
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
    public String getName(){
        return "clear";
    }

    @Override
    public String getDescription(){
        return "clear the collection";
    }


}
