package storages;

import DTO.CommentDTO;
import beans.Promocode;
import com.opencsv.CSVWriter;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PromocodeStorage {
    private HashMap<java.lang.Integer, Promocode> promocodes = new HashMap<java.lang.Integer, Promocode>();

    private static PromocodeStorage instance = null;
    public static PromocodeStorage getInstance() throws FileNotFoundException {
        if(instance==null){
            instance = new PromocodeStorage();
        }
        return instance;
    }

    private PromocodeStorage() throws FileNotFoundException {
        BufferedReader in = null;
        try {
            File file = new File(  "./static/promocodes.txt");
            System.out.println(file.getCanonicalPath());
            in = new BufferedReader(new FileReader(file));
            readPromocodes(in);
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

    private void readPromocodes(BufferedReader in)  {
        String line, id="",name="",startTime="",endTime="",numberOfCode="",percent="",isDeleted="";
        StringTokenizer st;
        try {
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.equals("") || line.indexOf('#') == 0)
                    continue;
                st = new StringTokenizer(line, ";");
                while (st.hasMoreTokens()) {
                    id = st.nextToken().trim();
                    name = st.nextToken().trim();
                    startTime = st.nextToken().trim();
                    endTime = st.nextToken().trim();
                    numberOfCode = st.nextToken().trim();
                    percent = st.nextToken().trim();
                    isDeleted = st.nextToken().trim();
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate flagStartTime =  LocalDate.parse(startTime,formatter);
                LocalDate flagEndTime =  LocalDate.parse(endTime,formatter);
                Promocode flag = new Promocode(Integer.parseInt(id),name, flagStartTime,flagEndTime,
                        Integer.parseInt(numberOfCode),Integer.parseInt(percent),Integer.parseInt(isDeleted));
                promocodes.put(Integer.parseInt(id), flag);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void addPromocode(Promocode promocode){

        File file = new File("./static/promocodes.txt");
        Scanner sc = new Scanner(System.in);
        try{
            FileWriter outputfile = new FileWriter(file,true);

            CSVWriter writer = new CSVWriter(outputfile, ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            Promocode tempUser = promocodes.get(promocode.getId());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            if(tempUser==null){
                LocalDate startTime = promocode.getStartTime();
                String formattedStartTime = startTime.format(formatter);
                LocalDate endDate = promocode.getEndTime();
                String formattedEndDate = endDate.format(formatter);
                String[] data1 = {Integer.toString(promocode.getId()),promocode.getName(),formattedStartTime,
                        formattedEndDate,Integer.toString(promocode.getNumberOfCode()),Integer.toString(promocode.getPercent()),"0"};
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
        this.promocodes.put(promocode.getId(),promocode);
    }

    public void editPromocode(Promocode promocode){
        int id = promocode.getId();
        String name = promocode.getName();
        int numberOfCode = promocode.getNumberOfCode();
        int percent = promocode.getPercent();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startTimeFlag = promocode.getStartTime();
        String formattedStartTime = startTimeFlag.format(formatter);
        LocalDate endDateFag = promocode.getEndTime();
        String formattedEndDate = endDateFag.format(formatter);
        int isDeleted = promocode.getIsDeleted();
        String file = "./static/promocodes.txt";
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
                if(row[0].equals(Integer.toString(id))){
                    row[1] = name;
                    row[2] = formattedStartTime;
                    row[3] = formattedEndDate;
                    row[4] = Integer.toString(numberOfCode);
                    row[5] = Integer.toString(percent);
                    row[6] = Integer.toString(isDeleted);
                    promocodes.remove(id);
                    promocodes.put(id,promocode);
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
            File dump = new File ("./static/promocodes.txt");
            newFile.renameTo(dump);


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private Collection<Promocode> getValues(){
        return promocodes.values();
    }

    public List<Promocode> getAllPromocodes(){
        List<Promocode> listToReturn = new ArrayList<>(getValues());
        return listToReturn;
    }

    public int getId(){
        return promocodes.size();
    }

    public Promocode getByName(String name){
        List<Promocode> listToIterate = new ArrayList<>(getValues());
        Promocode promocodeFlag = new Promocode();
        for (Promocode promocode:listToIterate
             ) {
            String flag = promocode.getName();
            if(flag.equals(name)){
                promocodeFlag = promocode;
                break;
            }
        }
        return promocodeFlag;
    }

}
