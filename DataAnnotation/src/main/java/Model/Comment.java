package Model;

import java.util.ArrayList;

public class Comment implements java.io.Serializable{
	private String cmtText;
	private String cmtWriter;
	private String cmtTime;
	private boolean cmtIsMark;
	
	private ArrayList<String> labelList;
	
	public Comment(String cmtText, String cmtWriter, String cmtTime) {
		this.cmtText = cmtText;
		this.cmtWriter = cmtWriter;
		this.cmtTime = cmtTime;
		this.cmtIsMark = false;
		this.labelList = new ArrayList<String>();
	}
	
	public ArrayList<String> getLabelList() {
		return labelList;
	}

	public void setLabelList(ArrayList<String> labelList) {
		this.labelList = labelList;
	}

	public String getCmtText() {
		return cmtText;
	}
	public void setCmtText(String cmtText) {
		this.cmtText = cmtText;
	}
	public String getCmtWriter() {
		return cmtWriter;
	}
	public void setCmtWriter(String cmtWriter) {
		this.cmtWriter = cmtWriter;
	}
	public boolean isCmtIsMark() {
		return cmtIsMark;
	}
	public void setCmtIsMark(boolean cmtIsMark) {
		this.cmtIsMark = cmtIsMark;
	}
	public String getCmtTime() {
		return cmtTime;
	}
	public void setCmtTime(String cmtTime) {
		this.cmtTime = cmtTime;
	}
}
