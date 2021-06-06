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
        //this.labelList = labList;
        this.labelList = (ArrayList<Label>) labList.clone();
    }
    
    public void Save(String labelPath) {
    	
    	LogAspect.Log(LABEL_FILE_NAME, "LabelBank : Save() trycatch");
    	
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

    public void Load(String labelPath) {
    	
    	LogAspect.Log("Model.LabelBank", "LabelBank : Load() trycatch");
    	
    	if(labelList.size() > 0) {
    		ArrayList<Label> newLabelList = new ArrayList<Label>();
    		ObjectInputStream ois = null; 
            try {
            	FileInputStream fis = new FileInputStream(labelPath);
                ois = new ObjectInputStream(fis);
                newLabelList = (ArrayList<Label>) ois.readObject();
                
                for(int i=0;i<newLabelList.size();i++) {
                	Label newLabel = newLabelList.get(i);
                	boolean isExist = false;
                	for(int j=0;j<labelList.size();j++) {
                		Label oldLabel = labelList.get(j);
                		if(newLabel.getLabType().equals(oldLabel.getLabType())) { 
                			isExist = true;
                			for(String newChoose:newLabel.getLabChoise()) {
                				boolean isContain = false;
                				for(String oldChoose:oldLabel.getLabChoise()) {
                					if(newChoose.equals(oldChoose)) {
                						isContain = true;
                						break;
                					}               					
                				}
                				if(!isContain) 
                					oldLabel.getLabChoise().add(newChoose);
                			}
                			break;
                		}                		
                	}
                	if(!isExist)
                		labelList.add(newLabel);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
    	}
    	else {
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
    
}







