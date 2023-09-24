package inner_client;

import abstractions.IClientCommandExecutor;
import data.CommandData;
import exceptions.NestingLevelException;
import exceptions.WrongInputException;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

public class ScriptExecutor {
    public ScriptExecutor(){}
    public void executeScript(CommandData commandData) throws NestingLevelException, IOException,
            ClassNotFoundException, InterruptedException {
        IClientCommandExecutor client = commandData.client;
        client.setNestingLevel(client.getNestingLevel() + 1);
        LinkedList<CommandData> commandsList = new LinkedList<>();
        if (client.getNestingLevel() > 5) {
            throw new NestingLevelException();
        }
        String filePath = commandData.string;
        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(filePath));
            String nextLine = client.getScriptReader().nextScriptLine(scanner);
            while (nextLine != null) {
                try {
                    String[] words = nextLine.split("\\s+");
                    CommandData newCommandData = client.getCommandDataFormer().getNewCommandData();
                    newCommandData.client = commandData.client;
                    newCommandData.scriptScanner = scanner;
                    commandData.client.getCommandDataFormer().fillCommandData(words, newCommandData);
                    if (newCommandData.isEmpty()) {
                        return;
                    }
                    commandData.client.getCommandDataFormer().validateCommand(newCommandData);
                    commandsList.addLast(newCommandData);
                } catch (WrongInputException e) {
                    System.out.println("An error occurred while reading: " + e.getMessage());
                    break;
                }
                nextLine = client.getScriptReader().nextScriptLine(scanner);
            }
        } catch (NoSuchFileException e) {
            return;
        }
        while (commandsList.iterator().hasNext()) {
            CommandData currentCommand = commandsList.removeFirst();
            client.getWebDispatcher().sendCommandDataToExecutor(currentCommand);
            Thread.sleep(300);
            commandData.client.showServerRespond();
        }
        client.getMessageComponent().printEmptyLine();
        client.setNestingLevel(client.getNestingLevel() - 1);
    }
}
