package bailey;
import java.io.*;
import java.util.Scanner;

public class CreateEmpiricalStrategy {

    public static void main(String[] args) throws Exception
    {
        File file =
                new File("src/bailey/TicTacToeLogs/WorldCupLog748590260422212.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("strategyFile.txt"), "utf-8"));
        while (reader.readLine() != null) {
//            PrintWriter strategyFile = new PrintWriter("src/bailey/strategyFile");
//            strategyFile.write(reader.readLine());
//            strategyFile.close();
            System.out.println(reader.readLine());
            writer.write(reader.readLine());

        }
        writer.close();
            //            String test = reader.readLine();
//            String[] line = test.split("\\s+");
//            String toPrint = "";
//            if (line[1].equals("WIN")) {
//                toPrint+= "0,";
//                for (int i=0; i<line.length; i++) {
//                    if (i > 3) {
//                        if (i == 4) {
//                            char[] fourthElement = line[4].toCharArray();
//                            char[] newArray = new char[fourthElement.length - 1];
//                            System.arraycopy(fourthElement, 1, newArray, 0, fourthElement.length - 1);
//                            toPrint += newArray.toString() + ",";
//                        } else if (i == line.length) {
//                            char[] lastElement = line[line.length].toCharArray();
//                            char[] newArray = new char[lastElement.length - 1];
//                            System.arraycopy(lastElement, 0, newArray, 0, lastElement.length - 1);
//                            toPrint += newArray.toString();
//                        } else {
//                            toPrint += line[i] + ",";
//                        }
//                    }
//                }
//                strategyFile.println(toPrint);
//            } else if (line[1].equals("LOSE")) {
//                toPrint+= "1,";
//                for (int i=0; i<line.length; i++) {
//                    if (i > 3) {
//                        if (i == 4) {
//                            char[] fourthElement = line[4].toCharArray();
//                            char[] newArray = new char[fourthElement.length - 1];
//                            System.arraycopy(fourthElement, 1, newArray, 0, fourthElement.length - 1);
//                            toPrint += newArray.toString() + ",";
//                        } else if (i == line.length) {
//                            char[] lastElement = line[line.length].toCharArray();
//                            char[] newArray = new char[lastElement.length - 1];
//                            System.arraycopy(lastElement, 0, newArray, 0, lastElement.length - 1);
//                            toPrint += newArray.toString();
//                        } else {
//                            toPrint += line[i] + ",";
//                        }
//                    }
//                }
          //  strategyFile.println(reader.readLine());
           // }
       // }
    }

    //writes the information out to a “strategy” file





}


