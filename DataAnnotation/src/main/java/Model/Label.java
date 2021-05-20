package Model;

import java.util.ArrayList;

public class Label implements java.io.Serializable{
	private String labType;
	private ArrayList<String> labChoise;
	
	public Label(String labType, ArrayList<String> labChoise) {
		this.labType = labType;
		this.labChoise = labChoise;
	}

	public String getLabType() {
		return labType;
	}

	public void setLabType(String labType) {
		this.labType = labType;
	}

	public ArrayList<String> getLabChoise() {
		return labChoise;
	}

	public void setLabChoise(ArrayList<String> labChoise) {
		this.labChoise = labChoise;
	}
}
