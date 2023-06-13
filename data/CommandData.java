package data;

import abstractions.IClientCommandExecutor;
import abstractions.ICommand;
import abstractions.IServerCommandExecutor;

import java.io.Serializable;
import java.util.Scanner;

/**
 * CommandData is a special open storage class, that contains full info about command.
 */

public class CommandData implements Serializable {
    private static final long serialVersionUID = 1L;

    public ICommand command;
    public String commandName;
    public Integer intDigit;
    public String string;
    public LabWork element;
    transient public IServerCommandExecutor labCollection;
    transient public IClientCommandExecutor client;
    transient public Scanner scriptScanner;

    public boolean isEmpty(){
        return this.command == null;
    }

    @Override
    public String toString(){
        String str = "intDigit: " + (intDigit == null ? "-" : intDigit) + "\n"
                   + "string: " + (string == null ? "-" : string) + "\n"
                   + "element: " + (element == null ? "-" : element.toString());
        return str;
    }
}
