package abstractions;

import data.CommandData;
import data.ResultData;


public interface ICommand {
    ResultData execute(CommandData commandData);
    boolean isClientCommand();
    boolean hasElement();
    boolean hasIntDigit();
    boolean hasString();
    String getName();
    default String getDescription(){
        return "";
    }
}
