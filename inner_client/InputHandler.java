package inner_client;

import data.LabWork;
import exceptions.WrongInputException;

import java.util.Scanner;

/**
 * InputHandler reads input values from command line or file.
 * InputHandler forms a commandData using these values.
 */
public class InputHandler {
    public InputHandler(Warning warningComponent){
        inputElementReader = new InputElementReader(scanner, warningComponent);
        scriptReader = new ScriptReader();
    }
    private final Scanner scanner = new Scanner(System.in);
    private final InputElementReader inputElementReader;
    private final ScriptReader scriptReader;

    public ScriptReader getScriptReader(){
        return scriptReader;
    }
    public LabWork readInputElement(){
        return inputElementReader.readElementFromInput();
    }
    public LabWork readScriptElement(Scanner fileScanner) throws WrongInputException {return scriptReader.readElementFromScript(fileScanner);}
    public String[] readLine(){
        String str = scanner.nextLine();
        String[] words = str.split("\\s+");
        return words;
    }


}
