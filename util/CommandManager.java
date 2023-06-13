package util;

import abstractions.ICommand;
import commands.*;
import exceptions.WrongCommandNameException;
import exceptions.WrongInputException;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * CommandManager use HashMap of commands to operate with commands.
 * CommandManager can register and validate commandData and send it to the executor.
 */
public class CommandManager {
    static{
        commandMap = new LinkedHashMap<>();
        registerCommands();
    }
    private static final LinkedHashMap<String, ICommand> commandMap;
    private static void register(ICommand command) {
        commandMap.put(command.getName(), command);
    }
    private static void registerCommands(){
        CommandManager.register(new HelpCommand());
        CommandManager.register(new InfoCommand());
        CommandManager.register(new ShowCommand());
        CommandManager.register(new AddCommand());
        CommandManager.register(new UpdateByIdCommand());
        CommandManager.register(new RemoveByIdCommand());
        CommandManager.register(new ClearCommand());
        CommandManager.register(new SaveCommand());
        CommandManager.register(new ExecuteScriptCommand());
        CommandManager.register(new ExitCommand());
        CommandManager.register(new InsertAtIdCommand());
        CommandManager.register(new RemoveFirstCommand());
        CommandManager.register(new SortCommand());
        CommandManager.register(new MinByCTCommand());
        CommandManager.register(new FilterContainsNameCommand());
        CommandManager.register(new PrintDescendingCommand());
    }
    public static ICommand getCommand(String commandName) throws WrongInputException{
        ICommand command = commandMap.get(commandName);
        if (command == null) {
            throw new WrongCommandNameException(commandName);
        }
        return command;
    }
    public static HashMap<String, ICommand> getCommandMap(){
        return commandMap;
    }
}
