package org.xcoderelease;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.opencsv.CSVReader;
import java.util.*;



public class xcodeVersion {
    public static void getXcodeGMRelease(){
        try{
            parseJson.parse();
            String csvFile = "/tmp/files/data.csv";
            ArrayList<String> ar = new ArrayList<String>();
            CSVReader reader = null;
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            int count=1;
            int resultstokeep =1;
            String version = null;
            StringBuffer sb = new StringBuffer();
            StringBuffer firstline = new StringBuffer();
            boolean skipFirstLine = true;
            while(true){
                line = reader.readNext();
                if ( skipFirstLine){ // skip data header
                    skipFirstLine = false; continue;
                }
                if(line == null){
                    break;
                }else{
                    if(line[29].equals("true")) {
                        resultstokeep=resultstokeep+1;
                        sb.append("xcodeversion = " + line[24] +"\n");
                        sb.append("Release date = " + line[2] + "-" + line[0] + "-" + line[1] +"\n");
                        sb.append("DownloadURL = " + line[4] +"\n");
                        sb.append("RequireOS = " + line[27] +"\n");
                        sb.append("Build Number = " + line[25] + "\n");
                        sb.append("**********************************"+"\n");
                    }
                    if(resultstokeep==4){
                        break;
                    }
                }
                count++;
            }
            FileWriter fileWriter = new FileWriter("/tmp/files/xcodeGMreleasedresults.txt");
            fileWriter.write(sb.toString());
            fileWriter.close();
            sendEmail.SendEmail(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void newXcodeVersionReleased() {
        try {
            parseJson.parse();
            String csvFile = "/tmp/files/data.csv";
            ArrayList<String> ar = new ArrayList<String>();
            CSVReader reader = null;
                reader = new CSVReader(new FileReader(csvFile));
                String[] line;
                int count = 1;
                int resultstokeep =1;
                String version = null;
                StringBuffer sb = new StringBuffer();
                //As first line is data header so set to skip
                boolean skipFirstLine = true;
                while (true) {
                    line = reader.readNext();
                    if (skipFirstLine) { // skip data header
                        skipFirstLine = false;
                        continue;
                    }
                    if (line == null) {
                        break;
                    } else {
                        if (count == 1) {
                            version = line[24];
                        }
                        compareXcodeVersion.VersionStr vs1 = new compareXcodeVersion.VersionStr(version);
                        compareXcodeVersion.VersionStr vs2 = new compareXcodeVersion.VersionStr(line[24]);
                        int result = vs2.compareTo(vs1);
                        if (result == 0) {
                            resultstokeep=resultstokeep+1;
                            sb.append("xcodeversion = " + line[24] + "\n");
                            sb.append("Release date = " + line[2] + "-" + line[0] + "-" + line[1] + "\n");
                            if (!line[28].equals("")) {
                                sb.append("Is GM ? = " + line[28] + "\n");
                            }
                            sb.append("DownloadURL = " + line[4] + "\n");
                            sb.append("RequireOS = " + line[27] + "\n");
                            sb.append("Build Number = " + line[25] + "\n");
                        }
                        if(resultstokeep==4){
                            break;
                        }
                    }
                    count++;
                }
                FileWriter fileWriter = new FileWriter("/tmp/files/xcodereleasedresults.txt");
                fileWriter.write(sb.toString());
                fileWriter.close();
                sendEmail.SendEmail(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public static void main(String[] args) throws Exception {
        getXcodeGMRelease();
        newXcodeVersionReleased();
    }
}
