package exceptions;

public class MustBeLowerException extends WrongInputException{
    public MustBeLowerException(int value){
        this.someInfo = Integer.toString(value);
    }
}
