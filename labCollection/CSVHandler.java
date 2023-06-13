package labCollection;

import data.LabWork;
import exceptions.WrongInputException;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * CSVHandler read and parse CSV files.
 */
public class CSVHandler {
    private static final String regExp = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

    public static List<LabWork> readCSV(String path) throws IOException, WrongInputException, NumberFormatException{
        List<LabWork> labWorks = new LinkedList<>();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
             Scanner scanner = new Scanner(bis)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                    labWorks.add(parseCSVLine(line));
                }
            }
        } catch (WrongInputException e) {
            throw new RuntimeException(e);
        }
        return labWorks;
    }

    private static LabWork parseCSVLine(String line) throws WrongInputException, NumberFormatException, NoSuchElementException {
        LabWork labWork = new LabWork();
        String[] words = line.split(regExp);
        if (words.length < 16){
            throw new NoSuchElementException();
        }
        labWork.setId(Integer.parseInt(words[0]));
        labWork.setName(words[1]);
        labWork.setCreationDate(words[2].trim());
        labWork.setCoordinatesX(words[3].trim());
        labWork.setCoordinatesY(words[4].trim());
        labWork.setMinimalPoint(words[5].trim());
        labWork.setTunedInWorks(words[6].trim());
        labWork.setDifficulty(words[7].trim());
        labWork.setPersonName(words[8].trim());
        labWork.setPersonHeight(words[9].trim());
        labWork.setPersonEyeColor(words[10].trim());
        labWork.setPersonHairColor(words[11].trim());
        labWork.setPersonNationality(words[12].trim());
        labWork.setPersonLocationX(words[13].trim());
        labWork.setPersonLocationY(words[14].trim());
        labWork.setPersonLocationName(words[15].trim());
        return labWork;
    }

    public static void writeCollectionToCSV(String path, LabCollection labCollection) throws IOException{
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));
             Writer writer = new OutputStreamWriter(bos)) {
            for (LabWork lab : labCollection.getCollection()){
                writer.write(labToCSV(lab));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String labToCSV(LabWork labWork){
        String delimiter = ", ";
        String lineDelimiter = System.getProperty("line.separator");
        StringBuilder str = new StringBuilder();
        for (int i = 0; i< labWork.toStringArray().length; i++){
            String word = labWork.toStringArray()[i];
            if(word.equals("null") || word.isBlank()){
                word = "";
            }
            str.append(word);
            if (i != labWork.toStringArray().length-1){
                str.append(delimiter);
            }
        }
        str.append(lineDelimiter);
        return str.toString();
    }
}
