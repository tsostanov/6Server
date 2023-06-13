package labCollection;

/**
 * ServerTextFormer forms text for a ResultData.
 */
public class ServerTextFormer {
    public static String collectionInfo(LabCollection labCollection){
        String str =    "Collection type: Linked List\n" +
                        "Initialization date: " + labCollection.getCreationDate() + "\n" +
                        "Elements type: Lab Work\n" +
                        "Amount of elements in the collection: " + labCollection.getCollection().size();
        return str;
    }
}
