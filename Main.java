
import labCollection.LabCollection;
import server.InnerClientThread;
import server.Server;

public class Main {
    public static void main(String[] args) throws Exception {
        LabCollection labCollection = new LabCollection();
        if(args.length > 0){
            labCollection.setFilePath(args[0]);
        }
        Server server = new Server(labCollection);
        //new InnerClientThread().start();
        server.doWhileTrue();
    }

}


