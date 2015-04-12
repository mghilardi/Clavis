package demo;

import java.util.Random;

public final class FastestComparator {

	/**
	 * Get an int and CustomNumberEntity values as input and compare them as a int numbers Time needed to make the
	 * comparison will be between 5 and 10 seconds
	 * 
	 * @param firstValue
	 * @param secondValue
	 * @return 0 if both values are equal, > 0 if first value is greater < 0 if second value is greater
	 */
	public int compare(int firstValue, CustomNumberEntity secondValue) {
		Random random = new Random();
		int mSeconds = (random.nextInt(5) + 6) * 1000; // milliseconds
		int secondValueAsNumber = Integer.parseInt(secondValue.getNumber());
		try {
			Thread.sleep(mSeconds);
		} catch (InterruptedException e) {
			// error while sleeping. Do nothing.
		}
		return firstValue - secondValueAsNumber;
	}
}
