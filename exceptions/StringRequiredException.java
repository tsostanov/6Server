package exceptions;

public class StringRequiredException extends WrongInputException{
    public StringRequiredException(String commandName){
        this.commandName = commandName;
    }
}
