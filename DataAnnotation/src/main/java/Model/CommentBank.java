package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.springframework.stereotype.Component;



public class CommentBank {
	private ArrayList<Comment> commentList = new ArrayList<Comment>();
    private final String COMMENT_FILE_NAME = "comment.txt";


    public ArrayList<Comment> getComment() {
        return commentList;
    }
    
    @SuppressWarnings("unchecked")
	public void setComment( ArrayList<Comment> cmtList) {
        //this.commentList = cmtList;
    	this.commentList = (ArrayList<Comment>) cmtList.clone();
    }
 
  
    public void Save() {
    	
    	LogAspect.Log("Model.CommentBank", "CommentBank : Save() trycatch");
    	
        ObjectOutputStream oos = null;
        try {
        	FileOutputStream fos = new FileOutputStream(COMMENT_FILE_NAME);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(commentList);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
    public void Load() {
    	
    	LogAspect.Log(COMMENT_FILE_NAME,  "CommentBank : Load() trycatch");
    	
        ObjectInputStream ois = null;
        commentList = new ArrayList<Comment>();
        try {
        	FileInputStream fis = new FileInputStream(COMMENT_FILE_NAME);
            ois = new ObjectInputStream(fis);
            commentList = (ArrayList<Comment>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
