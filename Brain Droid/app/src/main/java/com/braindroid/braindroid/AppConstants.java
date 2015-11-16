package com.braindroid.braindroid;

/**
 * @author Vinay Arora 
 * @Dated 12 Sept 2014 Version 1.0
 * @description App Constant is file containing Constants related to the Application
 * 
 * 
 */

public interface AppConstants {

	/**
	 * AD_UNIT_ID is the AD MOB Unit ID you can change the ID as shown below
	 * 
	 * suppose previous id was 123 AD_UNIT_ID="123"
	 * 
	 * now you want to change it to xyz,you can do this by just replacing the
	 * 123 by xyz as shown below
	 * 
	 * AD_UNIT_ID="xyz"
	 * 
	 */
	final String AD_UNIT_ID = "ca-app-pub-1630272783404642/4993887612";
	
	
	/**
	 * INTERSTITIAL_AD_UNIT_ID is the AD MOB Unit ID for INTERSTITIAL AD you can change the ID as shown below
	 * 
	 * suppose previous id was 123 INTERSTITIAL_AD_UNIT_ID="123"
	 * 
	 * now you want to change it to xyz,you can do this by just replacing the
	 * 123 by xyz as shown below
	 * 
	 * INTERSTITIAL_AD_UNIT_ID="xyz"
	 * 
	 */
	
	 final String INTERSTITIAL_AD_UNIT_ID="ca-app-pub-1630272783404642/6470620819";


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
	 * INTERSTITIAL_AD_UNIT_Frequency is the frequency as the number of levels after which ad appears
	 */
	
	final int INTERSTITIAL_AD_UNIT_Frequency = 2;

	
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
