package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class LabelBank {
	private ArrayList<Label> labelList = new ArrayList<Label>();
    private final String LABEL_FILE_NAME = "label.txt";


    public ArrayList<Label> getLabel() {
        return labelList;
    }
    
    public void setLabel(ArrayList<Label> labList) {
        this.labelList = labList;
    }
    
    public void Save() {
        ObjectOutputStream oos = null;
        try {
        	FileOutputStream fos = new FileOutputStream(LABEL_FILE_NAME);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(labelList);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Load() {
        ObjectInputStream ois = null;
        labelList = new ArrayList<Label>();
        try {
        	FileInputStream fis = new FileInputStream(LABEL_FILE_NAME);
            ois = new ObjectInputStream(fis);
            labelList = (ArrayList<Label>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
