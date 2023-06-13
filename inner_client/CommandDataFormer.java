package inner_client;

import abstractions.ICommand;
import data.CommandData;
import exceptions.DigitRequiredException;
import exceptions.StringRequiredException;
import exceptions.WrongInputException;
import util.CommandManager;

public class CommandDataFormer {
    public CommandDataFormer(){}
    public CommandData getNewCommandData(){
        return new CommandData();
    }
    public void validateCommand(CommandData commandData) throws WrongInputException, NumberFormatException{
        ICommand command = commandData.command;
        if (command.hasIntDigit() && commandData.intDigit == null) {
            throw new DigitRequiredException(command.getName());
        }
        if (command.hasString() && commandData.string == null) {
            throw new StringRequiredException(command.getName());
        }
        if (command.hasElement()) {
            if (commandData.client.isReadingScript()) {
                commandData.element = commandData.client.getInputHandler().readScriptElement(commandData.scriptScanner);
            } else {
                commandData.element = commandData.client.getInputHandler().readInputElement();
            }
        }
    }
    public void fillCommandData(String[] words, CommandData commandData) throws WrongInputException, NumberFormatException {
        int rememberI = 0;
        for (int i = 0; i<words.length; i++) {
            String word = words[i];
            if (word.isBlank()) {
                continue;
            }
            if (commandData.commandName == null) {
                commandData.commandName = word;
                rememberI = i + 1;
                break;
            }
        }
        if (commandData.commandName == null || commandData.commandName.isBlank()){
            return;
        }
        commandData.command = CommandManager.getCommand(commandData.commandName);
        for (int i = rememberI; i<words.length; i++){
            String word = words[i];
            if (word.isBlank()) {
                continue;
            }
            if(commandData.command.hasIntDigit()) {
                try {
                    commandData.intDigit = Integer.valueOf(word);
                } catch (NumberFormatException e) {
                    commandData.intDigit = null;
                }
            }
            if (commandData.command.hasString()) {
                commandData.string = word;
                continue;
            }
            break;
        }

    }
}
