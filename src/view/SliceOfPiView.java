package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 * @author Apalis
 *		
 */
@SuppressWarnings("serial")
public class SliceOfPiView extends JFrame implements ActionListener
{
   
   // Layout objects
   private JLabel titleLabel = new JLabel("Find 'Pi' to the \"nth\" decimal place.");
   private TextPanel textArea = new TextPanel(15, 7);
   private JLabel nthLabel = new JLabel("Enter number of decimal places");
   private SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 1000000000, 1);
   private JSpinner nthInput = new JSpinner(spinnerModel);
   private JButton calculate = new JButton("Calculate");
   private JButton copy = new JButton("copy");
   private JLabel count = new JLabel("0 decimal places.");
   private JProgressBar jProgress = new JProgressBar();
   private Clipboard clip = getToolkit().getSystemClipboard();
   private JLabel logo = new JLabel(new ImageIcon(SliceOfPiView.class.getResource("/res/SliceOfPi.png")));
  
   
   // private JOptionPane doneMessage = new JOptionPane();
   private DecimalFormat df;
   
   /**
    * Constructor for view class initialises Swing
    */
   public SliceOfPiView()
   {
	  super("Slice of Pi!");
	  
	  setMinimumSize(new Dimension(400, 600));
	  setResizable(true);
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close program when
													  // swing frame closed.
	  setLocation(400, 200);
	  
	  this.df = new DecimalFormat();
	  df.setRoundingMode(RoundingMode.HALF_UP);
	  layoutObjects();
	  pack();
	  setVisible(true); // display swing frame.
   }
   
   /**
    * Set Layout for the GUI
    */
   public void layoutObjects()
   {
	  
	  setLayout(new GridBagLayout());
	  GridBagConstraints gc = new GridBagConstraints();
	  
	  // =================First Row=================//
	  
	  gc.gridy = 0;
	  gc.gridx = 0;
	  
	  gc.gridwidth = 2;
	  
	  gc.fill = GridBagConstraints.BOTH;
	  
	  add(logo,gc);

	  // =================Next Row=================//
	  
	  gc.gridy++;
	  
	  gc.gridheight = 1;
	  gc.gridwidth = 2;
	  
	  gc.gridx = 0;
	  
	  titleLabel.setHorizontalAlignment(titleLabel.CENTER);
	  Font titleFont = titleLabel.getFont().deriveFont(Font.PLAIN, 20f);
	  titleLabel.setFont(titleFont);
	  
	  gc.gridx = 0;
	  
	  gc.fill = GridBagConstraints.BOTH;
	  gc.anchor = GridBagConstraints.LINE_END;
	  gc.insets = new Insets(0, 0, 0, 0);
	  
	  add(titleLabel, gc);
	  
	  // =================Next Row=================//
	  
	  gc.gridy++;
	  
	  gc.gridwidth = 1;
	  
	  gc.anchor = GridBagConstraints.LINE_END;
	  gc.insets = new Insets(20, 0, 10, 0);
	  add(nthLabel, gc);
	  
	  gc.gridx++;
	  
	  gc.anchor = GridBagConstraints.LINE_START;
	  gc.insets = new Insets(20, 10, 10, 0);
	  add(nthInput, gc);
	  
	  // =================Next Row=================//
	  
	  gc.gridy++;
	  
	  gc.fill = GridBagConstraints.NONE;
	  gc.gridwidth = 2;
	  gc.gridx = 0;
	  
	  gc.anchor = GridBagConstraints.LINE_START;
	  gc.insets = new Insets(0, 135, 10, 0);
	  add(calculate, gc);
	  
	  // =================Next Row=================//
	  
	  gc.gridy++;
	  
	  gc.fill = GridBagConstraints.BOTH;
	  gc.gridwidth = 2;
	  gc.gridx = 0;
	  
	  gc.anchor = GridBagConstraints.LINE_START;
	  gc.insets = new Insets(0, 0, 10, 0);
	  add(jProgress, gc);
	  
	  // =================Next Row=================//
	  
	  gc.gridy++;
	  
	  gc.fill = GridBagConstraints.BOTH;
	  gc.gridwidth = 2;
	  gc.gridx = 0;
	  
	  gc.anchor = GridBagConstraints.LINE_START;
	  gc.insets = new Insets(0, 0, 10, 0);
	  add(textArea, gc);
	  
	  // =================Next Row=================//
	  
	  gc.gridy++;
	  
	  gc.fill = GridBagConstraints.NONE;
	  gc.gridwidth = 1;
	  gc.gridx = 0;
	  
	  gc.anchor = GridBagConstraints.LINE_START;
	  gc.insets = new Insets(0, 0, 10, 0);
	  add(count, gc);
	  
	  gc.gridx++;
	  
	  gc.anchor = GridBagConstraints.LINE_START;
	  gc.insets = new Insets(0, 115, 10, 0);
	  add(copy, gc);
   }
   
   // ===========Methods for Controller=============//
   
   /**
    * returns pi as a string
    * 
    * @return the textArea
    */
   public String getPi()
   {
	  
	  return textArea.getText();
   }
   
   /**
    * set's pi
    * 
    * @param textArea
    *           the textArea to set
    */
   public void setPi(BigDecimal pi)
   {
	  df.format(pi);
	  String piAsString = pi.toString();
	  this.textArea.setText(piAsString);
	  count.setText((piAsString.length() - 2) + " decimal places.");
   }
   
   /**
    * get's the number of decimals in which to calculate pi
    * 
    * @return the nthInput
    */
   public int getNumberOfDecimals()
   {
	  return (int) nthInput.getValue();
   }
   
   /**
    * sets the decimal format (number of decimal places)
    * 
    * @param df
    *           the decimalFormat to set
    */
   public void setDecimalFormat(int max)
   {
	  df.setMaximumFractionDigits(max);
   }
   
   /**
    * Listener for calculate button
    * 
    * @param listenerForCalcButton
    */
   public void addCalculateListener(ActionListener listenerForCalcButton)
   {
	  calculate.addActionListener(listenerForCalcButton);
   }
   
   /**
    * listener for copy button
    * 
    * @param listenerForCopyButton
    */
   public void addCopyListener(ActionListener listenerForCopyButton)
   {
	  copy.addActionListener(listenerForCopyButton);
   }
   
   @Override
   public void actionPerformed(ActionEvent arg0)
   {
	  // TODO Auto-generated method stub
	  
   }
   
   /**
    * sets progress bar to value
    * 
    * @param jProgress
    *           set Indeterminate (b)
    */
   public void setjProgressInd(boolean b)
   {
	  this.jProgress.setIndeterminate(b);
   }
   
   /**
    * changes the percentage of progress bar
    * 
    * @param jProgress
    *           set percentage (b)
    */
   public void setProgressPercentage(int percentage)
   {
	  this.jProgress.setValue(percentage);
   }
   
   /**
    * shows a message when the task is completed
    * 
    * @param doneMessage
    *           the doneMessage to set
    */
   public void showDoneMessage()
   {
	  JOptionPane.showMessageDialog(null, "Task Completed");
   }
   
   /**
    * copies the input string to the clipboard
    * 
    * @param clip
    *           the clip to set
    */
   public void setClip(StringSelection clip)
   {
	  this.clip.setContents(clip, clip);
   }
   
}
