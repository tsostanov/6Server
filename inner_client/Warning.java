package inner_client;

import exceptions.*;

public class Warning {
    public Warning(){}
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";

    public void showWarning(Exception e) {
            warningMessage(e.getMessage());
    }
    public void showExceptionWarning (Exception e) {
        if (e instanceof WrongCommandNameException){
            WrongInputException wrongInputException = (WrongInputException) e;
            wrongCommand(wrongInputException.getCommandName());
            return;
        }
        if (e instanceof DigitRequiredException){
            WrongInputException wrongInputException = (WrongInputException) e;
            digitIsRequired(wrongInputException.getCommandName());
            return;
        }
        if (e instanceof StringRequiredException){
            WrongInputException wrongInputException = (WrongInputException) e;
            stringIsRequired(wrongInputException.getCommandName());
            return;
        }
        if (e instanceof EmptyFieldException){
            WrongInputException wrongInputException = (WrongInputException) e;
            canNotBeEmpty();
            return;
        }
        if (e instanceof MustBeHigherException){
            WrongInputException wrongInputException = (WrongInputException) e;
            mustBeHigher(wrongInputException.getInfo());
            return;
        }
        if (e instanceof MustBeLowerException){
            WrongInputException wrongInputException = (WrongInputException) e;
            mustBeLower(wrongInputException.getInfo());
            return;
        }
        if (e instanceof ShouldNotContainException){
            WrongInputException wrongInputException = (WrongInputException) e;
            mustBeHigher(wrongInputException.getInfo());
            return;
        }
        warningMessage("Something went wrong. Please, try again.");
        e.printStackTrace();
    }

    public void warningMessage(String message) {
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }

    private void wrongCommand(String command) {
        String message = "Command '" + command + "' is not registered.\n" +
                "Please, type it correctly or use command 'help'.";
        warningMessage(message);
    }

    private void digitIsRequired(String command) {
        String message = "Integer digit is required for command '" + command + "'.\n" +
                "Please, type it correctly or use command 'help'.";
        warningMessage(message);
    }

    private void stringIsRequired(String command) {
        String message = "String is required for command '" + command + "'.\n" +
                "Please, type it correctly or use command 'help'.";
        warningMessage(message);
    }

    private void canNotBeEmpty() {
        warningMessage("This field can not be empty!");
    }

    public void mustBeType(String type) {
        warningMessage("This field must be type of " + type + "!");
    }

    private void mustBeHigher(String value) {
        warningMessage("This field must be higher than " + value + "!");
    }
    private void mustBeLower(String value) {
        warningMessage("This field must be lower than " + value + "!");
    }

    public void wrongEnumValue() {
        warningMessage("This value is not supported!");
    }

    public void shouldNotContain(String value) {
        warningMessage("Fields should not contain '" + value + "'!");
    }

}
