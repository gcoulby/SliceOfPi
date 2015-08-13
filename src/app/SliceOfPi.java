package app;

import javax.swing.SwingUtilities;

import controller.SliceOfPiController;
import model.SliceOfPiModel;
import view.SliceOfPiView;

/**
 * @author Apalis
 * @version 1.0
 *
 */
public class SliceOfPi
{
	public static void main(String[] args)
	{

		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{

			view.SliceOfPiView theView = new SliceOfPiView();

			model.SliceOfPiModel theModel = new SliceOfPiModel();

			new SliceOfPiController(theView, theModel);

			theView.setVisible(true);
			}
		});
	}
}
