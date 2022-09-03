package storages;

import DTO.CommentDTO;
import beans.Content;
import beans.ContentType;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class CommentStorage {
    private static HashMap<java.lang.Integer, CommentDTO> comments = new HashMap<java.lang.Integer, CommentDTO>();

    private static CommentStorage instance = null;
    public static  CommentStorage getInstance() throws FileNotFoundException {
        if(instance==null){
            instance = new CommentStorage();
        }
        return instance;
    }

    private CommentStorage() throws FileNotFoundException {
        BufferedReader in = null;
        try {
            File file = new File(  "./static/comments.txt");
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
        String line, id="",facilityID="",customerID="",commentText="",available="",isDeleted="",stars="";
        StringTokenizer st;
        try {
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.equals("") || line.indexOf('#') == 0)
                    continue;
                st = new StringTokenizer(line, ";");
                while (st.hasMoreTokens()) {
                    id = st.nextToken().trim();
                    facilityID = st.nextToken().trim();
                    customerID = st.nextToken().trim();
                    commentText = st.nextToken().trim();
                    stars = st.nextToken().trim();
                    available = st.nextToken().trim();
                    isDeleted = st.nextToken().trim();
                }


                CommentDTO flag = new CommentDTO(Integer.parseInt(id),facilityID,customerID,commentText,Integer.parseInt(stars),
                        Integer.parseInt(available),Integer.parseInt(isDeleted));
                comments.put(Integer.parseInt(id), flag);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void AddComment(CommentDTO comment){

        File file = new File("./static/comments.txt");
        Scanner sc = new Scanner(System.in);
        try{
            FileWriter outputfile = new FileWriter(file,true);

            CSVWriter writer = new CSVWriter(outputfile, ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            CommentDTO tempUser = comments.get(comment.getId());
            if(tempUser==null){
                String[] data1 = {Integer.toString(comment.getId()),comment.getFacilityID(),comment.getCustomerID(),
                        comment.getCommentText(),Integer.toString(comment.getStars()),"0","0"};
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
        this.comments.put(comment.getId(),comment);
    }

    public void EditComment(CommentDTO comment){
        int id = comment.getId();
        String facilityId = comment.getFacilityID();
        String customerId = comment.getCustomerID();
        String commentText = comment.getCommentText();
        int stars = comment.getStars();
        int available = comment.getAvailable();
        int isDeleted = comment.getIsDeleted();
        String file = "./static/comments.txt";
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
                    row[1] = facilityId;
                    row[2] = customerId;
                    row[3] = commentText;
                    row[4] = Integer.toString(stars);
                    row[5] = Integer.toString(available);
                    row[6] = Integer.toString(isDeleted);
                    comments.put(id,comment);
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
            File dump = new File ("./static/comments.txt");
            newFile.renameTo(dump);


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Collection<CommentDTO> getValues(){return comments.values();}

    public List<CommentDTO> GetComments(){
        List<CommentDTO> listToReturn =new ArrayList<>(getValues());
        return listToReturn;
    }
}
