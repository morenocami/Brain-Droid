package com.braindroid.braindroid;



public interface AppConstants {


	/**
	 * progress_duration is the number of milliseconds for which the characters
	 * in the cells are shown suppose previously it was setted to 50000 as shown
	 * below
	 *
	 * progress_duration= 5*1000
	 *
	 * and now we wan to set it to 4000 it can be done as shown below
	 *
	 * progress_duration= 4*1000
	 */

	final long progress_duration = 5 * 1000; // 5000 milliseconds

	/**
	 * number of lifes is the total number of lifes and must remain same Changes
	 * must be done at the developer level
	 */

	int num_of_lifes = 5;


	/**
	 * progress_tick is the numbe of milliseconds seconds after which the time
	 * remaining bar refreshes itself
	 */

	final long progress_tick = 2;

	/**
	 * Level key and Score key is used for internal purpose Please donot change
	 * until required. Change may introduce bugs in the application
	 */
	final String levelkey = "level_key";
	final String scorekey = "scorekey";
	// final long questiontimer = 4000;
	// final long answertimer=5000;
	// final long interval = 1000;


}
