package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class LabelBank {
	private ArrayList<Label> labelList = new ArrayList<Label>();
    private final String LABEL_FILE_NAME = "label.txt";


    public ArrayList<Label> getLabel() {
        return labelList;
    }
    
    public void setLabel(ArrayList<Label> labList) {
        this.labelList = labList;
    }
    
    @LogAnnotation(className = "Model.LabelBank" , content = "LabelBank : Save() trycatch")
    public void Save(String labelPath) {
        ObjectOutputStream oos = null;
        try {
        	FileOutputStream fos = new FileOutputStream(labelPath);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(labelList);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @LogAnnotation(className = "Model.LabelBank" , content = "LabelBank : Load() trycatch")
    public void Load(String labelPath) {
        ObjectInputStream ois = null;
        labelList = new ArrayList<Label>();
        try {
        	FileInputStream fis = new FileInputStream(labelPath);
            ois = new ObjectInputStream(fis);
            labelList = (ArrayList<Label>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
