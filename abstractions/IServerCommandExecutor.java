package abstractions;

import data.CommandData;
import data.ResultData;

public interface IServerCommandExecutor extends ICommandExecutor {
    ResultData add(CommandData commandData);
    ResultData clear(CommandData commandData);
    ResultData nameContains(CommandData commandData);
    ResultData printDescending(CommandData commandData);
    ResultData info (CommandData commandData);
    ResultData insertAtId(CommandData commandData);
    ResultData removeFirst(CommandData commandData);
    ResultData sort(CommandData commandData);
    ResultData minByCT(CommandData commandData);
    ResultData readCSVFile (CommandData commandData);
    ResultData removeById (CommandData commandData);
    ResultData saveToCSV (CommandData commandData);
    ResultData show (CommandData commandData);
    ResultData updateById (CommandData commandData);

}
