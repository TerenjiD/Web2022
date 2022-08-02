package storages;

import beans.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ContentStorage {

    private static HashMap<String, Content> contents = new HashMap<String, Content>();

    private static ContentStorage instance = null;

    public static ContentStorage getInstance() throws FileNotFoundException{
        if(instance==null){
            instance = new ContentStorage();
        }
        return instance;
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


                Content flag = new Content();
                users.put(username, user);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
