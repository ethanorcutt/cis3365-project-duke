package simple;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Created by Ethan Orcutt on 9/11/2016.
 */
class Data_Importer
{
    private FileReader in;
    private FileWriter outOne;
    private FileWriter outTwo;
    private BufferedReader bR;
    private StringBuffer sB;
    private StringBuffer pB;

    Data_Importer() throws IOException
    {

        /*File pureNameList = new File("Data/purenamelist.txt");
        File otherNameList = new File("Data/othernamelist.txt");*/
        File clientDataList = new File("D:\\Dropbox\\School\\Fall 2016\\CIS 3365\\SQL Scripts\\Ethan\\Master Files\\Data\\projectdata.txt");

        in = new FileReader("D:\\Dropbox\\School\\Fall 2016\\CIS 3365\\SQL Scripts\\Ethan\\Master Files\\Data\\Project.csv");
        bR = new BufferedReader(in);
        sB = new StringBuffer();
        pB = new StringBuffer();
        outOne = new FileWriter(clientDataList);
        /*outOne = new FileWriter(pureNameList);
        outTwo = new FileWriter(otherNameList);*/

        //importData();
        //correctInvalidNames();
        copyData();
    }

    private void importData() throws IOException
    {
        try
        {
            String line;
            bR.readLine(); // Eats the first row of data. This is a title row, unneeded.

            // Used for copying ALL lines of data.
            while((line = bR.readLine()) != null)
            {
                if(!(line.contains(":")))
                    sB.append(line + "\n");
                else
                    pB.append(line + "\n");
            }

/*
            // Used for copying x number of lines of data.
            for(int x = 0; x < 10; x++)
            {
                line = bR.readLine();

                if(!(line.contains(":")))
                    sB.append(line + "\n");
                else
                    pB.append(line + "\n");
            }
*/
            sB.deleteCharAt(sB.length()-1);
            pB.deleteCharAt(pB.length()-1);

            outOne.write(sB.toString());
            outTwo.write(pB.toString());
        }
        finally
        {
            in.close();
            outOne.close();
            outTwo.close();
            bR.close();
        }
    }

    private void correctInvalidNames() throws FileNotFoundException
    {
        Scanner lineGrabber = new Scanner(new File("Data/othernamelist.txt"));

        while(lineGrabber.hasNextLine())
        {
            fixInvalidNames(lineGrabber.nextLine());
        }
    }

    private void fixInvalidNames(String badLine) throws FileNotFoundException
    {
        final Scanner dupeFinder = new Scanner(badLine);
        String namesColumn = "";
        dupeFinder.useDelimiter("\\|");

        if (dupeFinder.hasNext()) dupeFinder.next();
        if (dupeFinder.hasNext()) namesColumn = dupeFinder.next();

        dupeFinder.close();

        for (final String correctName : namesColumn.split(":"))
        {
            System.out.println(correctName);
        }
    }

    /**
     * Copies the CSV file and adds a Tilde (~) at the end of each line
     * in order to help make the BULK INSERT scripts easier to execute.
     * @throws IOException
     * Catches any failed attempts at copying the data.
     */
    private void copyData() throws IOException
    {
        try
        {
            String line;

            //bR.readLine();

            while((line = bR.readLine()) != null)
            {
                sB.append(line + "~" + "\n");
            }

            sB.deleteCharAt(sB.length()-1);

            outOne.write(sB.toString());
        }
        finally
        {
            in.close();
            outOne.close();
            bR.close();
        }
    }
}