package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 * Custom JPanel
 * 
 * @author Apalis
 *		
 */
public class TextPanel extends JPanel
{
   private JTextArea textArea;
   
   public TextPanel(int rows, int columns)
   {
	  textArea = new JTextArea(rows, columns);
	  
	  setLayout(new BorderLayout());
	  textArea.setLineWrap(true);
	  textArea.setWrapStyleWord(true);
	  add(new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
   }
   
   public String getText()
   {
	  String text = textArea.getText();
	  return text;
   }
   
   public void setText(String text)
   {
	  textArea.setText(text);
   }
}
