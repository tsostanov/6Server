package exceptions;

public class WrongCommandNameException extends WrongInputException {
    public WrongCommandNameException(String commandName){
        this.commandName = commandName;
    }
}
