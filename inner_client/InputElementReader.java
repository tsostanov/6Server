package inner_client;

import data.LabWork;
import exceptions.WrongInputException;

import java.util.Scanner;

/**
 * InputElementReader forms a new data.LabWork using input values.
 */
public class InputElementReader {
    public InputElementReader(Scanner scanner, Warning warningComponent){
        this.scanner = scanner;
        this.warningComponent = warningComponent;
    }
    private final Scanner scanner;
    Warning warningComponent;

    public LabWork readElementFromInput() {
        LabWork labWork = new LabWork();
        readName(labWork);
        readCoordinatesX(labWork);
        readCoordinatesY(labWork);
        readMinimalPoint(labWork);
        readTunedInWork(labWork);
        readDifficulty(labWork);
        readPersonName(labWork);
        readPersonHeight(labWork);
        readPersonEyeColor(labWork);
        readPersonHairColor(labWork);
        readPersonNationality(labWork);
        readPersonLocationX(labWork);
        readPersonLocationY(labWork);
        readPersonLocationName(labWork);
        return labWork;
    }
    private void readName(LabWork labWork){
        try {
            labWork.setName(readWord("Type the name"));
        }catch (WrongInputException e){
            warningComponent.showExceptionWarning(e);
            readName(labWork);
        }
    }
    private void readCoordinatesX(LabWork labWork){
        try{
            labWork.setCoordinatesX(readWord("Type the x coordinate"));
        }
        catch (WrongInputException e){
            warningComponent.showExceptionWarning(e);
            readCoordinatesX(labWork);
        }
        catch (NumberFormatException e){
            warningComponent.mustBeType("float");
            readCoordinatesX(labWork);
        }
    }
    private void readCoordinatesY(LabWork labWork){
        try{
            labWork.setCoordinatesY(readWord("Type the y coordinate"));
        }
        catch (WrongInputException e){
            warningComponent.showExceptionWarning(e);
            readCoordinatesY(labWork);
        }
        catch (NumberFormatException e){
            warningComponent.mustBeType("long");
            readCoordinatesY(labWork);
        }
    }
    private void readMinimalPoint(LabWork labWork){
        try{
            labWork.setMinimalPoint(readWord("Type the minimal point"));
        }
        catch (WrongInputException e){
            warningComponent.showExceptionWarning(e);
            readMinimalPoint(labWork);
        }
        catch (NumberFormatException e){
            warningComponent.mustBeType("long");
            readMinimalPoint(labWork);
        }
    }
    private void readTunedInWork(LabWork labWork){
        try{
            labWork.setTunedInWorks(readWord("Type amount of tuned in work assistants"));
        } catch (NumberFormatException e){
            warningComponent.mustBeType("integer");
            readTunedInWork(labWork);
        }
    }
    private void readDifficulty(LabWork labWork) {
        try {
            String str = readWord("Type the difficulty level.\n" +
                    "VERY_HARD, INSANE or TERRIBLE");
            labWork.setDifficulty(str);
        }catch (WrongInputException e){
            warningComponent.showExceptionWarning(e);
            readDifficulty(labWork);
        }
        catch (IllegalArgumentException e){
            warningComponent.wrongEnumValue();
            readDifficulty(labWork);
        }
    }
    private void readPersonName(LabWork labWork){
        try {
            labWork.setPersonName(readWord("Type Person's name"));
        }catch (WrongInputException e){
            warningComponent.showExceptionWarning(e);
            readPersonName(labWork);
        }
    }
    private void readPersonHeight(LabWork labWork){
        try{
            labWork.setPersonHeight(readWord("Type Person's height"));
        }
        catch (WrongInputException e){
            warningComponent.showExceptionWarning(e);
            readPersonHeight(labWork);
        }
        catch (NumberFormatException e){
            warningComponent.mustBeType("long");
            readPersonHeight(labWork);
        }
    }
    private void readPersonEyeColor(LabWork labWork){
        try{
            String str = readWord("Type Person's eye color.\n" +
                    "RED, BLACK, YELLOW, BLUE, ORANGE or WITHE");
            labWork.setPersonEyeColor(str);
        }
        catch (WrongInputException e){
            warningComponent.showExceptionWarning(e);
            readPersonEyeColor(labWork);
        }
        catch (IllegalArgumentException e){
            warningComponent.wrongEnumValue();
            readPersonEyeColor(labWork);
        }
    }
    private void readPersonHairColor(LabWork labWork){
        try{
            String str = readWord("Type Person's eye color.\n" +
                    "RED, BLACK, YELLOW, BLUE, ORANGE or WITHE");
            labWork.setPersonHairColor(str);
        }
        catch (WrongInputException e){
            warningComponent.showExceptionWarning(e);
            readPersonHairColor(labWork);
        }
        catch (IllegalArgumentException e){
            warningComponent.wrongEnumValue();
            readPersonHairColor(labWork);
        }
    }
    private void readPersonNationality(LabWork labWork){
        try{
            String str = readWord("Type Person's nationality.\n" +
                    "RUSSIA, INDIA or NORTH_KOREA");
            labWork.setPersonNationality(str);
        }
        catch (WrongInputException e){
            warningComponent.showExceptionWarning(e);
            readPersonNationality(labWork);
        }
        catch (IllegalArgumentException e){
            warningComponent.wrongEnumValue();
            readPersonNationality(labWork);
        }
    }
    private void readPersonLocationX(LabWork labWork){
        try{
            labWork.setPersonLocationX(readWord("Type x Person's location coordinate"));
        }
        catch (WrongInputException e){
            warningComponent.showExceptionWarning(e);
            readPersonLocationX(labWork);
        }
        catch (NumberFormatException e){
            warningComponent.mustBeType("integer");
            readPersonLocationX(labWork);
        }
    }
    private void readPersonLocationY(LabWork labWork){
        try{
            labWork.setPersonLocationY(readWord("Type y Person's location coordinate"));
        }
        catch (WrongInputException e){
            warningComponent.showExceptionWarning(e);
            readPersonLocationY(labWork);
        }
        catch (NumberFormatException e){
            warningComponent.mustBeType("float");
            readPersonLocationY(labWork);
        }
    }
    private void readPersonLocationName(LabWork labWork){
        try{
            labWork.setPersonLocationName(readWord("Type name of Person's location"));
        }
        catch (WrongInputException e){
            warningComponent.showExceptionWarning(e);
            readPersonLocationName(labWork);
        }
        catch (NumberFormatException e){
            warningComponent.mustBeType("string");
            readPersonLocationName(labWork);
        }
    }


    private String readWord(String helpMessage){
        if(!isNullOrEmpty(helpMessage)){
            System.out.print(helpMessage + ": ");
        }
        String str = scanner.nextLine();
        String[] words = str.split("\\s+");
        for(String word: words){
            if (isNullOrEmpty(word)){
                continue;
            }
            return word;
        }
        return null;
    }
    private boolean isNullOrEmpty(String str){
        return (str == null || str.isBlank());
    }
}