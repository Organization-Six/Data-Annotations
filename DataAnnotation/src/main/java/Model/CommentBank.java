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


    public ArrayList<Comment> getComment() {
        return commentList;
    }
    
    public void setComment(ArrayList<Comment> cmtList) {
        //this.commentList = cmtList;
    	this.commentList = (ArrayList<Comment>) cmtList.clone();
    }

    @LogAnnotation(className = "Model.CommentBank" , content = "CommentBank : Save() trycatch")
    public void Save(String commentPath) {
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

    @LogAnnotation(className = "Model.CommentBank" , content = "CommentBank : Load() trycatch")
    public void Load(String commentPath) {
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
                	if(!isExist)
            			commentList.add(newComment);
                }
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
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
    	}

    }
}
