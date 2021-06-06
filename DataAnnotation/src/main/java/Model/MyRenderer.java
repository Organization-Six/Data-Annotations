package Model;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.Border;

public class MyRenderer extends DefaultListCellRenderer{
    private ArrayList<Integer> conflictList;
    private ArrayList<Integer> confirmList;
    
	public MyRenderer(ArrayList<Integer> conflictList, ArrayList<Integer> confirmList) {
        this.conflictList = conflictList;
        this.confirmList = confirmList;
    }
	
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        //DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
	    Border lineBorder = BorderFactory.createLineBorder(new Color(121,121,121),1);
//		JLabel renderer = (JLabel) getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);		    
        this.setBorder(lineBorder);		
        
        if (conflictList!=null) {
        	for (int i = 0; i < conflictList.size(); i++) {
        		if (index == conflictList.get(i)) {
                    setBackground(Color.RED);
                }
            }
        }
        
        if (conflictList!=null) {
        	for (int i = 0; i < confirmList.size(); i++) {
                if (index == confirmList.get(i)) {
                    setBackground(Color.GREEN);
                }
            } 
        }
        return this;
    }

}
