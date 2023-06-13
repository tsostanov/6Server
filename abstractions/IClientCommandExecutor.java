package abstractions;

import inner_client.*;
import data.CommandData;
import data.ResultData;

import java.io.IOException;


public interface IClientCommandExecutor extends ICommandExecutor{
    boolean isReadingScript();
    int getNestingLevel();
    void setNestingLevel(int num);

    CommandDataFormer getCommandDataFormer();
    InputHandler getInputHandler();
    ResultHandler getResultHandler();
    Message getMessageComponent();
    Warning getWarningComponent();
    ScriptReader getScriptReader();
    WebDispatcher getWebDispatcher();

    ResultData help(CommandData commandData);
    ResultData exit(CommandData commandData);
    ResultData executeScript(CommandData commandData);
    void showServerRespond() throws IOException, ClassNotFoundException, InterruptedException;
}
