package exceptions;

public class DigitRequiredException extends WrongInputException{
    public DigitRequiredException(String commandName){
        this.commandName = commandName;
    }
}
