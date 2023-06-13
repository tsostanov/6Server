package exceptions;



public class MustBeHigherException extends WrongInputException{
    public MustBeHigherException(int value){
        this.someInfo = Integer.toString(value);
    }
}
