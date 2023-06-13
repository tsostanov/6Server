package abstractions;

import data.CommandData;
import data.ResultData;

public interface ICommandExecutor {
    public ResultData execute(CommandData commandData);

}
