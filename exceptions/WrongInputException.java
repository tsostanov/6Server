package exceptions;

public class WrongInputException extends Exception {
    String commandName;
    String someInfo;

    public WrongInputException(){
    }

    public String getCommandName(){
        return commandName;
    }

    public String getInfo() {
        return someInfo;
    }
}
