package inner_client;

import abstractions.IClientCommandExecutor;
import data.CommandData;
import exceptions.NestingLevelException;
import exceptions.WrongInputException;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

public class ScriptExecutor {
    public ScriptExecutor(){}
    public void executeScript(CommandData commandData) throws NestingLevelException, IOException, WrongInputException, ClassNotFoundException, InterruptedException {
        IClientCommandExecutor client = commandData.client;
        client.setNestingLevel(client.getNestingLevel() + 1);
        LinkedList<CommandData> commandsList = new LinkedList<>();
            if (client.getNestingLevel() > 5){
                throw new NestingLevelException();
            }
            String filePath = commandData.string;
            Scanner scanner = new Scanner(Paths.get(filePath));
            String nextLine = client.getScriptReader().nextScriptLine(scanner);
            while (nextLine != null){
                String[] words = nextLine.split("\\s+");
                CommandData newCommandData = client.getCommandDataFormer().getNewCommandData();
                newCommandData.client = commandData.client;
                newCommandData.scriptScanner = scanner;
                commandData.client.getCommandDataFormer().fillCommandData(words, newCommandData);
                if (newCommandData.isEmpty()) {
                    continue;
                }
                commandData.client.getCommandDataFormer().validateCommand(commandData);
                commandsList.addLast(newCommandData);
                nextLine = client.getScriptReader().nextScriptLine(scanner);
            }
            while (commandsList.iterator().hasNext()){
                CommandData currentCommand = commandsList.removeFirst();
                client.getWebDispatcher().sendCommandDataToExecutor(currentCommand);
                Thread.sleep(300);
                commandData.client.showServerRespond();
            }
            client.getMessageComponent().printEmptyLine();
            client.setNestingLevel(client.getNestingLevel() - 1);;
    }
}