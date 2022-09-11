package storages;

import DTO.ChangeContentDTO;
import DTO.TrainingHistoryDTO;
import DTO.TrainingSearchDTO;
import beans.*;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class ContentStorage {


    private static HashMap<String, Content> contents = new HashMap<String, Content>();

    private FacilityStorage facilities = FacilityStorage.getInstance();

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
        String line, nameID="", facilityName="", name="",type="",coach="",logo="",description="",duration="",
                startTime="",endTime="",isDeleted="";
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
                    startTime = st.nextToken().trim();
                    endTime = st.nextToken().trim();
                    isDeleted = st.nextToken().trim();
                }
                ContentType flagContent;
                if(type.equals("GROUP_TRAINING")){
                    flagContent = ContentType.GROUP_TRAINING;
                }else if(type.equals("PERSONAL_TRAINING")){
                    flagContent = ContentType.PERSONAL_TRAINING;
                }else{
                    flagContent = ContentType.SAUNA;
                }

                Content flag = new Content(nameID,facilityName,name,flagContent,coach,logo,
                        description,duration,startTime,endTime,Integer.parseInt(isDeleted));
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
                        content.getCoachID(),content.getLogo(),content.getDescription(),content.getDuration(),content.getStartTime(),
                        content.getEndTime(),"0"};
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
            int isDel = con.getIsDeleted();
            if (flag.equals(facilityName) && isDel == 0){
                listToReturn.add(con);
            }
        }
        return listToReturn;
    }


    public List<Content> GetTrainingsForCoach(String username){
        String user="",name="",surname="";
        List<Content> listToCheck = new ArrayList<>(getValues());
        List<Content> listToReturn  =  new ArrayList<>();
        StringTokenizer st;
        for(Content con : listToCheck){
            String flag = con.getCoachID();
            int isDel = con.getIsDeleted();
            if(flag.equals("nema")){
                continue;
            }
            st = new StringTokenizer(flag," ");
            while (st.hasMoreTokens()) {
                user = st.nextToken().trim();
                name = st.nextToken().trim();
                surname = st.nextToken().trim();
            }
            if (user.equals(username) && isDel == 0){
                listToReturn.add(con);
            }
        }
        return listToReturn;
    }

    public void ChangeCoach(Coach coach,Content content){
        String coachNameAndSurname = coach.getUsername()+" "+coach.getName()+" "+coach.getLastName();
        String nameID = content.getNameID();
        String file = "./static/contentlist.txt";
        File oldFile = new File(file);
        File newFile = new File("./static/temp.txt");
        BufferedReader reader = null;
        String line = "";
        List<String[]> rows = new ArrayList<>();
        try{
            FileWriter outputfile = new FileWriter("./static/temp.txt",true);
            reader = new BufferedReader(new FileReader(file));
            while((line=reader.readLine()) != null){
                String[] row = line.split(";");
                if(row[0].equals(nameID)){
                    row[4] = coachNameAndSurname;
                    ContentType flagContent;
                    if(row[3].equals("GROUP_TRAINING")){
                        flagContent = ContentType.GROUP_TRAINING;
                    }else if(row[3].equals("PERSONAL_TRAINING")){
                        flagContent = ContentType.PERSONAL_TRAINING;
                    }else{
                        flagContent = ContentType.SAUNA;
                    }
                    Content flagContent1 = new Content(row[0],row[1],row[2],flagContent,
                            row[4],row[5],row[6],row[7],row[8],row[9],Integer.parseInt(row[10]));
                    contents.put(row[0],flagContent1);
                }
                rows.add(row);

            }
            reader.close();

            CSVWriter writer = new CSVWriter(outputfile, ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            writer.writeAll(rows);
            writer.close();
            oldFile.delete();
            File dump = new File ("./static/contentlist.txt");
            newFile.renameTo(dump);


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void ChangeContent(Content content){
        String nameID = content.getNameID();
        String name = content.getName();
        ContentType typeToSend = content.getType();
        String type = content.getType().toString();
        String logo = content.getLogo();
        String description = content.getDescription();
        String duration = content.getDuration();
        String file = "./static/contentlist.txt";
        File oldFile = new File(file);
        File newFile = new File("./static/temp.txt");
        BufferedReader reader = null;
        String line = "";
        List<String[]> rows = new ArrayList<>();
        try{
            FileWriter outputfile = new FileWriter("./static/temp.txt",true);
            reader = new BufferedReader(new FileReader(file));
            while((line=reader.readLine()) != null){
                String[] row = line.split(";");
                if(row[0].equals(nameID)){
                    row[2]=name;
                    row[3]=type;
                    row[5]=logo;
                    row[6]=description;
                    row[7]=duration;
                    Content flagContent1 = new Content(row[0],row[1],row[2],typeToSend,row[4],
                            row[5],row[6],row[7],row[8],row[9],Integer.parseInt(row[10]));
                    contents.put(row[0],flagContent1);
                }
                rows.add(row);

            }
            reader.close();

            CSVWriter writer = new CSVWriter(outputfile, ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            writer.writeAll(rows);
            writer.close();
            oldFile.delete();
            File dump = new File ("./static/contentlist.txt");
            newFile.renameTo(dump);


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void CancelContent(Content training){
        String nameID = training.getNameID();
        String name = training.getName();
        ContentType typeToSend = training.getType();
        String type = training.getType().toString();
        String logo = training.getLogo();
        String description = training.getDescription();
        String duration = training.getDuration();
        String file = "./static/contentlist.txt";
        File oldFile = new File(file);
        File newFile = new File("./static/temp.txt");
        BufferedReader reader = null;
        String line = "";
        List<String[]> rows = new ArrayList<>();
        try{
            FileWriter outputfile = new FileWriter("./static/temp.txt",true);
            reader = new BufferedReader(new FileReader(file));
            while((line=reader.readLine()) != null){
                String[] row = line.split(";");
                if(row[0].equals(nameID)){
                    row[2]=name;
                    row[3]=type;
                    row[5]=logo;
                    row[6]=description;
                    row[7]=duration;
                    Content flagContent1 = new Content(row[0],row[1],row[2],typeToSend,row[4],
                            row[5],row[6],row[7],row[8],row[9],1);
                    contents.put(row[0],flagContent1);
                }
                rows.add(row);

            }
            reader.close();

            CSVWriter writer = new CSVWriter(outputfile, ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            writer.writeAll(rows);
            writer.close();
            oldFile.delete();
            File dump = new File ("./static/contentlist.txt");
            newFile.renameTo(dump);


        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public List<Content> getSearchedTrainingsCoach(TrainingSearchDTO search, List<Content> list) {
        Collection<Content> temp = new ArrayList<Content>();
        temp = searchByName(search.getName(),list);
        temp = searchByDate(search.getDate(),temp);
        if(search.getSortBy() != null && search.getSortType() != null){
            temp=sortList(search.getSortBy(),search.getSortType(),temp);
        }
        if(search.getFilterBy() != null){
            temp = filterList(search.getFilterBy(),temp);
        }
        List<Content> listRet = new ArrayList<Content>(temp);
        return listRet;
    }

    public Collection<Content> searchByName(String name,List<Content> list){
        Collection<Content> temp = new ArrayList<Content>();
        if (name == null || name == ""){
            return list;
        }else{
            for (Content u : list) {
                if (u.getFacilityName().toUpperCase().contains(name.toUpperCase()))
                    temp.add(u);
            }
        }
        List<Content> listRet = new ArrayList<Content>(temp);
        return listRet;
    }

    private Collection<Content> searchByDate(String date, Collection<Content> starter) {
        Collection<Content> temp = new ArrayList<Content>();
        if (date == "" || date == null){
            temp = starter;
        }else{
            for (Content u : starter) {
                if (u.getDuration().toUpperCase().contains(date.toUpperCase()))
                    temp.add(u);
            }
        }
        List<Content> list = new ArrayList<Content>(temp);
        return list;
    }

    public Collection<Content> sortList(String criterium,String type,Collection<Content> starter){
        List<Content> list = new ArrayList<Content>(starter);
        List<Content> temp = new ArrayList<Content>();
        if(type.equals("1")){
            if (criterium.equals("1")) {
                Collections.sort(list,new TrainingComparatorByNameCoach());
            }else {
                Collections.sort(list, new TrainingComparatorByDateCoach());
            }
        }else{
            if (criterium.equals("1")) {
                Collections.sort(list,new TrainingComparatorByNameCoach().reversed());
            }else {
                Collections.sort(list, new TrainingComparatorByDateCoach().reversed());
            }
        }
        return list;
    }

    public Collection<Content> filterList(String type,Collection<Content> starter){
        Collection<Content> temp = new ArrayList<Content>();
        if(type.equals("1")){
            for (Content f:starter){
                if(facilities.GetByIdFacility(f.getFacilityName()).getFacilityType().toString().contains("GYM")){
                    temp.add(f);
                }
            }
        }else if(type.equals("2")){
            for (Content f:starter){
                if(facilities.GetByIdFacility(f.getFacilityName()).getFacilityType().toString().contains("POOL")){
                    temp.add(f);
                }
            }
        }else if(type.equals("3")){
            for (Content f:starter){
                if(facilities.GetByIdFacility(f.getFacilityName()).getFacilityType().toString().contains("SPORT")){
                    temp.add(f);
                }
            }
        }else if(type.equals("4")){
            for (Content f:starter){
                if(facilities.GetByIdFacility(f.getFacilityName()).getFacilityType().toString().contains("DANCE")){
                    temp.add(f);
                }
            }
        }else if(type.equals("5")){
            for (Content f:starter){
                if(f.getType().toString().contains("GROUP")){
                    temp.add(f);
                }
            }
        }else if(type.equals("6")){
            for (Content f:starter){
                if(f.getType().toString().contains("PERSONAL")){
                    temp.add(f);
                }
            }
        }else{
            for (Content f:starter){
                if(f.getType().toString().contains("GYM")){
                    temp.add(f);
                }
            }
        }
        List<Content> list = new ArrayList<Content>(temp);
        return list;
    }

}
