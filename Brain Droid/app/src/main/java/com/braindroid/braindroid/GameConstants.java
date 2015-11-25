package com.braindroid.braindroid;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.braindroid.braindroid.R;

import android.content.Context;

public class GameConstants {

	/**
	 *
	 * @function name generateRandomSuccessMessage()
	 *
	 * @description : this function is used to generate random message for the
	 *              Success Dialog Screen when user give right answer
	 *
	 *
	 */

	public static String generateRandomSuccessMessage(Context context) {
		String s;
		List<String> list = Arrays.asList(context.getResources()
				.getStringArray(R.array.success_array));
		int pos = (int) (System.currentTimeMillis() % list.size());
		s = (list).get(pos);

		return s;
	}

	/**
	 *
	 * @function name generateRandomFailMessage()
	 *
	 * @description : this function is used to generate random message for the
	 *              Success Dialog Screen when user give wrong answer
	 *
	 *
	 */

	public static String generateRandomFailMessage(Context context) {
		String s;
		List<String> list = Arrays.asList(context.getResources()
				.getStringArray(R.array.failmsg_array));
		int pos = (int) (System.currentTimeMillis() % list.size());
		s = (list).get(pos);

		return s;
	}

	/**
	 *
	 * @function name generateRandomHappyDrawableResource()
	 *
	 * @description : this function is used to select a random picture for the
	 *              success screen
	 *
	 *              to remove a single of photos u can just delete the whole
	 *              line "drawables.add(R.drawable.happy1);"
	 *
	 *
	 *              to add a photo u can add a line
	 *              "	drawables.add(R.drawable.happy2);"
	 *
	 *              suppose u want to add a photo myphoto.png add it in the
	 *              Game>res>drawable folder
	 *
	 *              and add the line below drawables.add(R.drawable.myphoto);
	 *
	 */

	public static int generateRandomHappyDrawableResource() {
		List<Integer> drawables = new ArrayList<Integer>();

		drawables.add(R.drawable.happy1);
		//	drawables.add(R.drawable.happy2);
		//	drawables.add(R.drawable.happy3);
		//	drawables.add(R.drawable.happy4);
		//	drawables.add(R.drawable.happy5);

		return drawables.get((int) (System.currentTimeMillis() % drawables
				.size()));

	}
	/**
	 *
	 * @function name generateRandomSadDrawableResource()
	 *
	 * @description : this function is used to select a random picture for the
	 *              wrong answer screen
	 *
	 *              to remove a single of photos u can just delete the whole
	 *              line "drawables.add(R.drawable.happy1);"
	 *
	 *
	 *              to add a photo u can add a line
	 *              "	drawables.add(R.drawable.happy2);"
	 *
	 *              suppose u want to add a photo myphoto.png add it in the
	 *              Game>res>drawable folder
	 *
	 *              and add the line below drawables.add(R.drawable.myphoto);
	 *
	 */

	public static int generateRandomSadDrawableResource() {

		List<Integer> drawables = new ArrayList<Integer>();
		drawables.add(R.drawable.sad1);
		//	drawables.add(R.drawable.sad2);
		//	drawables.add(R.drawable.sad3);
		//	drawables.add(R.drawable.sad4);
		//	drawables.add(R.drawable.sad5);

		return drawables.get((int) (System.currentTimeMillis() % drawables
				.size()));

	}

	/**
	 *
	 * @function name generateRandomDrawableResource()
	 *
	 * @description : this function is used to select a random picture for the
	 *              tip screen
	 *
	 *              to remove a single of photos u can just delete the whole
	 *              line "drawables.add(R.drawable.happy1);"
	 *
	 *
	 *              to add a photo u can add a line
	 *              "	drawables.add(R.drawable.happy2);"
	 *
	 *              suppose u want to add a photo myphoto.png add it in the
	 *              Game>res>drawable folder
	 *
	 *              and add the line below drawables.add(R.drawable.myphoto);
	 *
	 */

	public static int generateRandomDrawableResource() {

		List<Integer> drawables = new ArrayList<Integer>();
		drawables.add(R.drawable.happy1);
		//	drawables.add(R.drawable.happy2);
		//	drawables.add(R.drawable.happy3);
		//	drawables.add(R.drawable.happy4);
		//	drawables.add(R.drawable.happy5);
		drawables.add(R.drawable.sad1);
		//	drawables.add(R.drawable.sad2);
		//	drawables.add(R.drawable.sad3);
		//	drawables.add(R.drawable.sad4);
		//	drawables.add(R.drawable.sad5);

		return drawables.get((int) (System.currentTimeMillis() % drawables.size()));

	}

	/**
	 *
	 * @function name generateRandomDrawableResource()
	 *
	 * @description : this function is used to select a random tip for the tip
	 *              screen
	 */

	public static String generateRandomTip(Context context) {
		String s;
		List<String> list;
		list = Arrays.asList(context.getResources()
				.getStringArray(R.array.tips));
		int pos = (int) (System.currentTimeMillis() % list.size());

		return list.get(pos).toString();
	}

}
