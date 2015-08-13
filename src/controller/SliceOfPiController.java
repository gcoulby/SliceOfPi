package controller;

import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.SwingWorker;

import model.SliceOfPiModel;
import view.SliceOfPiView;

/**
 * Controller class
 * 
 * @author Apalis
 *
 */
public class SliceOfPiController
{
	private view.SliceOfPiView theView;
	private model.SliceOfPiModel theModel;
	private ProgressBar progressBar;
	private BigDecimal pi = new BigDecimal(0);
	private int decimals;

	/**
	 * Constructor for controller class
	 * 
	 * @param theView
	 *           : the GUI
	 * @param theModel
	 *           : the functionality
	 */
	public SliceOfPiController(SliceOfPiView theView, SliceOfPiModel theModel)
	{
		this.theView = theView;
		this.theModel = theModel;
		this.theView.addCalculateListener(new CalculateListener());
		this.theView.addCopyListener(new CopyListener());
	}

	/**
	 * Listener for the calculate button
	 *
	 */
	class CalculateListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{
			decimals = theView.getNumberOfDecimals();
			theView.setDecimalFormat(decimals);
			progressBar = new ProgressBar();
			progressBar.execute();
		}
	}

	/**
	 * Custom progress bar Split into incremental sizes depending on size of
	 * calculation
	 *
	 */
	class ProgressBar extends SwingWorker<Void, Void>
	{

		@Override
		protected Void doInBackground() throws Exception
		{
			if (decimals < 4500)
			{
			theView.setjProgressInd(true);
			smallTask();
			}
			else if (decimals >= 4500 && decimals < 10000)
			{
			mediumTask();
			}
			else if (decimals >= 10000 && decimals < 100000)
			{
			largeTask();
			}
			else if (decimals >= 100000 && decimals <= 1000000)
			{
			hugeTask();
			}
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.SwingWorker#done()
		 */
		@Override
		protected void done()
		{

			theView.showDoneMessage();
		}

		/**
		 * method for handling small tasks
		 */
		protected void smallTask()
		{
			pi = theModel.getPi(0, decimals);
			theView.setPi(pi);
			theView.setjProgressInd(false);
		}

		/**
		 * method for handling medium tasks
		 */
		protected void mediumTask()
		{
			int increment = decimals / 10;
			theView.setProgressPercentage(0);
			for (int i = 1; i <= 10; i++)
			{
			theView.setProgressPercentage(i * 10);
			pi = theModel.getPi(i - 1, increment * i);
			theView.setPi(pi);
			}
		}

		/**
		 * method for handling large tasks
		 */
		protected void largeTask()
		{
			int increment = decimals / 100;
			theView.setProgressPercentage(0);
			for (int i = 1; i <= 100; i++)
			{
			theView.setProgressPercentage(i);
			pi = theModel.getPi(i - 1, increment * i);
			theView.setPi(pi);
			}
		}

		/**
		 * method for handling huge tasks
		 */
		protected void hugeTask()
		{
			int increment = decimals / 1000;
			theView.setProgressPercentage(0);
			for (int i = 1; i <= 1000; i++)
			{
			theView.setProgressPercentage(i / 10);
			pi = theModel.getPi(i - 1, increment * i);
			theView.setPi(pi);
			}
		}

	}

	/**
	 * Listener for the copy button
	 *
	 */
	class CopyListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{
			StringSelection cs = new StringSelection(theView.getPi());
			theView.setClip(cs);
		}
	}
}
