package storages;

import beans.*;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class ContentStorage {

    private static HashMap<String, Content> contents = new HashMap<String, Content>();

    private static ContentStorage instance = null;

    public static ContentStorage getInstance() throws FileNotFoundException{
        if(instance == null){
            instance = new ContentStorage();
        }
        return instance;
    }

    public Content CheckIfExist(String nameID){
        return contents.get(nameID);
    }

    private ContentStorage() throws FileNotFoundException {
        BufferedReader in = null;
        try {
            File file = new File(  "./static/contentlist.txt");
            System.out.println(file.getCanonicalPath());
            in = new BufferedReader(new FileReader(file));
            readContents(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if ( in != null ) {
                try {
                    in.close();
                }
                catch (Exception e) { }
            }
        }
    }

    private void readContents(BufferedReader in)  {
        String line, nameID="", facilityName="", name="",type="",coach="",logo="",description="",duration="";
        StringTokenizer st;
        try {
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.equals("") || line.indexOf('#') == 0)
                    continue;
                st = new StringTokenizer(line, ";");
                while (st.hasMoreTokens()) {
                    nameID = st.nextToken().trim();
                    facilityName = st.nextToken().trim();
                    name = st.nextToken().trim();
                    type = st.nextToken().trim();
                    coach = st.nextToken().trim();
                    logo = st.nextToken().trim();
                    description = st.nextToken().trim();
                    duration = st.nextToken().trim();
                }
                ContentType flagContent;
                if(type.equals("GROUP_TRAINING")){
                    flagContent = ContentType.GROUP_TRAINING;
                }else if(type.equals("PERSONAL_TRAINING")){
                    flagContent = ContentType.PERSONAL_TRAINING;
                }else{
                    flagContent = ContentType.SAUNA;
                }

                Content flag = new Content(nameID,facilityName,name,flagContent,coach,logo,description,duration);
                contents.put(nameID, flag);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void addContent(Content content){

        File file = new File("./static/contentlist.txt");
        Scanner sc = new Scanner(System.in);
        try{
            FileWriter outputfile = new FileWriter(file,true);

            CSVWriter writer = new CSVWriter(outputfile, ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            Content tempUser = contents.get(content.getNameID());
            if(tempUser==null){
                String[] data1 = {content.getNameID(),content.getFacilityName(),content.getName(),content.getType().toString(),
                content.getCoachID(),content.getLogo(),content.getDescription(),content.getDuration()};
                List<String[]> userList = new ArrayList<>();
                userList.add(data1);
                //userList.add(data2);
                writer.writeAll(userList);

                writer.close();
            }else{
                writer.close();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.contents.put(content.getNameID(),content);
    }

    public Collection<Content> getValues() {return contents.values();}

    public List<Content> GetContents(String facilityName){
        List<Content> listToCheck = new ArrayList<>(getValues());
        List<Content> listToReturn  =  new ArrayList<>();
        for(Content con : listToCheck){
            String flag = con.getFacilityName();
            if (flag.equals(facilityName)){
                listToReturn.add(con);
            }
        }
        return listToReturn;
    }

}
