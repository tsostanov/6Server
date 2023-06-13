package inner_client;
import abstractions.ICommand;
import data.LabWork;


/**
 * Message print values, info and exceptions.
 */
public class Message {
    public Message(){}
    public void printNothing(){
    }
    public void printText(String text){
        System.out.println(text);
    }
    public void printElement(LabWork labWork){
        String[] strLab = labWork.toStringArray();
        StringBuilder outputString = new StringBuilder();
        String decimetre = " | ";
        for (int i = 0; i< strLab.length; i++){
            outputString.append(strLab[i]);
            if (i != strLab.length-1){
                outputString.append(decimetre);
            }
        }
        printText(outputString.toString());

    }
    public void printEmptyLine(){
        printText("");
    }
    public void showCommandDescription(ICommand command){
        printText(           command.getName() + " " +
                            (command.hasIntDigit() ? "<int> " : "" ) +
                            (command.hasString() ? "<str> " : "" ) +
                            (command.hasElement() ? "<element> " : "" ) +
                            "- " + command.getDescription());
    }

    public void connectionSuccess(){
        printText("Connected to server!");
    }


}
