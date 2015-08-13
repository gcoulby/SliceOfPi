package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Model Class handles the calculation of Pi
 * 
 * @author Apalis
 */
public class SliceOfPiModel
{

	/**
	 * calculates pi to the nth decimal place
	 * 
	 * @param count
	 * @param decimals
	 * @return pi as a BigDecimal
	 */
	public BigDecimal getPi(long count, int decimals)
	{

		boolean plus = true;
		BigDecimal base = new BigDecimal(4);
		BigDecimal form = new BigDecimal(2);
		BigDecimal stageOne = new BigDecimal(0);
		BigDecimal stageTwo = new BigDecimal(0);
		BigDecimal one = new BigDecimal(1);
		BigDecimal two = new BigDecimal(2);
		BigDecimal pi = new BigDecimal("3.0");

		for (count = 0; count < decimals; count++)
		{

			if (plus)
			{
				stageOne = form.multiply(form.add(one));
				stageTwo = stageOne.multiply(form.add(two));
				BigDecimal currentCount = base.divide(stageTwo, decimals, RoundingMode.HALF_UP);
				pi = pi.add(currentCount);
			}
			else
			{
				stageOne = form.multiply(form.add(one));
				stageTwo = stageOne.multiply(form.add(two));
				BigDecimal currentCount = base.divide(stageTwo, decimals, RoundingMode.HALF_UP);
				pi = pi.subtract(currentCount);
			}
			plus = !plus;
			form = form.add(two);
		}

		return pi;
	}
}
