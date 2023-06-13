package commands;

import abstractions.ICommand;
import data.CommandData;
import data.ResultData;

import java.io.Serializable;

public class ShowCommand implements ICommand, Serializable {
    @Override
    public ResultData execute(CommandData commandData){
        return commandData.labCollection.show(commandData);
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
        return "show";
    }
    @Override
    public String getDescription(){
        return "show all elements in the collection";
    }

}
