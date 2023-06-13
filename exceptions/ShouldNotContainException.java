package exceptions;

public class ShouldNotContainException extends WrongInputException{
    public ShouldNotContainException(String sequence){
        someInfo = sequence;
    }
}
