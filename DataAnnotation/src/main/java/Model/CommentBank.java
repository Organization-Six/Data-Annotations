package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class CommentBank {
	private ArrayList<Comment> commentList = new ArrayList<Comment>();
    private final String COMMENT_FILE_NAME = "comment.txt";
    public static ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();


    public ArrayList<Comment> getComment() {
        return commentList;
    }
    
    public void setComment(ArrayList<Comment> cmtList) {
        //this.commentList = cmtList;
    	this.commentList = (ArrayList<Comment>) cmtList.clone();
    }

    
    public void Save(String commentPath) {
    	LogAspect.Log("Model.CommentBank", "CommentBank : Save() trycatch");
        ObjectOutputStream oos = null;
        try {
        	FileOutputStream fos = new FileOutputStream(commentPath);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(commentList);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void Load(String commentPath) {
    	LogAspect.Log("Model.CommentBank", "CommentBank : Load() trycatch");
    	if(commentList.size() > 0) {
    		ArrayList<Comment> newCommentList = new ArrayList<Comment>();
    		ObjectInputStream ois = null; 
//    		System.out.println("youshuju");
            try {
            	FileInputStream fis = new FileInputStream(commentPath);
                ois = new ObjectInputStream(fis);
                newCommentList = (ArrayList<Comment>) ois.readObject();
                
                for(int i=0;i<newCommentList.size();i++) {
                	Comment newComment = newCommentList.get(i);
                	boolean isExist = false;
                	for(int j=0;j<commentList.size();j++) {
                		Comment oldComment = commentList.get(j);
                		if(newComment.getCmtText().equals(oldComment.getCmtText()) && newComment.getCmtWriter().equals(oldComment.getCmtWriter())) { 
                			isExist = true;
                			for(String newLabel:newComment.getLabelList()) {
                				table.get(j).add(newLabel);
                				boolean isContain = false;
                				for(String oldLabel:oldComment.getLabelList()) {
                					if(newLabel.split("-")[0].equals(oldLabel.split("-")[0])) {
                						isContain = true;
                						if(!(newLabel.split("-")[1].equals(oldLabel.split("-")[1]))) {
                							oldComment.setIsConflict(true);
                							commentList.set(j, oldComment);
                						}
                						break;
                					}               					
                				}
                				if(!isContain) 
                					oldComment.getLabelList().add(newLabel);
                			}
                			break;
                		}                		
                	}
                	if(!isExist) {
            			commentList.add(newComment);
            			table.add(new ArrayList<String>());
                	}
                }
                System.out.println(table);
            }catch (Exception e) {
                e.printStackTrace();
            }
    	}
    	else {
		    ObjectInputStream ois = null;
		    commentList = new ArrayList<Comment>();
		    try {
		    	FileInputStream fis = new FileInputStream(commentPath);
		        ois = new ObjectInputStream(fis);
		        commentList = (ArrayList<Comment>) ois.readObject();
		        for(int i = 0; i < commentList.size(); i++) {
		        	ArrayList<String> colList = new ArrayList<String>();
		        	for(String choose: commentList.get(i).getLabelList()) {
		        		colList.add(choose);
		        	}
		        	table.add(colList);
		        }
		        System.out.println(table);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
    	}

    }
}
