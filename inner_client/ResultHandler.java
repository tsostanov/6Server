package inner_client;

import data.LabWork;
import data.ResultData;

import java.util.LinkedList;

/**
 * ResultHandler shows the result of the command.
 */
public class ResultHandler {
    private final Message messageComponent;
    private final Warning warningComponent;
    public ResultHandler(Message messageComponent, Warning warningComponent){
        this.messageComponent = messageComponent;
        this.warningComponent = warningComponent;
    }

    private LinkedList<ResultData> results = new LinkedList<>();

    public void addResult(ResultData resultData){
        results.addLast(resultData);
    }
    public void showResults(){
        boolean haveSomething = false;
        while(results.iterator().hasNext()){
            boolean res = showResult(results.removeFirst());
            if(res){
                haveSomething = true;
            };
        }
        if (haveSomething){
            messageComponent.printEmptyLine();
        }

    }
    private boolean showResult(ResultData resultData){
        if (ResultData.isEmpty(resultData)){
            messageComponent.printNothing();
            return false;
        }
        if (resultData.hasElements()){
            for (LabWork labWork : resultData.labsList){
                messageComponent.printElement(labWork);
            }
        }
        if(resultData.hasText()) {
            messageComponent.printText(resultData.resultText);
        }
        if(resultData.hasErrorMessage()){
            warningComponent.warningMessage(resultData.errorMessage);
        }
        return true;
    }
}
