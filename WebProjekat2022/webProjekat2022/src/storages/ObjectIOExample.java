package storages;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class ObjectIOExample {
    String filepath;
    public void setPath(String filepath){
        this.filepath=filepath;
    }

    public void WriteObjectToFile(Object serObj) {

        try {

            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
