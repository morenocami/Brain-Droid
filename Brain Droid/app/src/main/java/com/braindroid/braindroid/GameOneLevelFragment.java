package com.braindroid.braindroid;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class GameOneLevelFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

	public View root;
	public FragmentActivity context;
	public GridView grid;
	public GameOneGridAdapter adapter;
	public List<String> list;
	private TextView selecttext, score_textview, level_textview,
			right_dia_text, wrong_dia_text, tip_text;
	CountDownTimer question_timer;
	private List<String> list2, anslist, numofcolumns, numofcells;
	Resources res;
	int score, level;
	boolean gridclickable;
	private ProgressBar progressBar;
	private ImageView life_imgview, wrong_dia_img, right_dia_img;
	static int num_life;
	LinearLayout wronganslayout, rightanslayout,mainlayout;
	private ImageButton rightdialogbutton, wrongdialogbutton,
			wrongdialogtrybutton;
	List<Integer> life_resource;
	private Animation animationFadeIn;
	FrameLayout frame;

	static boolean newBest;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		root = inflater.inflate(R.layout.gameonelevelfragment, container, false); // create the size of the game grid view
		context = getActivity();
		res = getResources();
		score = 0;
		level = 1;

		num_life = GameOneAppConstants.num_of_lifes;
		life_resource = new ArrayList<Integer>();
		life_resource.add(R.drawable.life1);
		life_resource.add(R.drawable.life2);
		life_resource.add(R.drawable.life3);
		life_resource.add(R.drawable.life4);
		life_resource.add(R.drawable.lifes);
		grid = (GridView) root.findViewById(R.id.grid);
		progressBar = (ProgressBar) getActivity().findViewById(
				R.id.time_progress);
		progressBar.setMax((int) GameOneAppConstants.progress_duration);
		animationFadeIn = AnimationUtils.loadAnimation(getActivity(),
				R.anim.fadein);
		list = new ArrayList<String>();
		list = new GameOneHelperUtils(context)
				.loadMasterDataFromString(R.array.characters);
		selecttext = (TextView) context.findViewById(R.id.selectCharacter);
		level_textview = (TextView) context.findViewById(R.id.leveltextview);
		score_textview = (TextView) context.findViewById(R.id.score);
		life_imgview = (ImageView) context.findViewById(R.id.life_image);
		right_dia_text = (TextView) context.findViewById(R.id.right_dia_text);
		wrong_dia_text = (TextView) context.findViewById(R.id.wrong_dia_text);
		wrong_dia_img = (ImageView) context.findViewById(R.id.wrong_dia_img);
		right_dia_img = (ImageView) context.findViewById(R.id.right_dia_img);
		mainlayout=(LinearLayout) context.findViewById(R.id.main_layout);
		numofcells = Arrays.asList(res.getStringArray(R.array.numberofcells));
		tip_text = (TextView) context.findViewById(R.id.tip_textview);
		gridclickable = false;

		frame=(FrameLayout) context.findViewById(R.id.container);
		rightanslayout = (LinearLayout) context
				.findViewById(R.id.rightanslinlayout);
		wronganslayout = (LinearLayout) context
				.findViewById(R.id.wronganslinlayout);
		rightdialogbutton = (ImageButton) context
				.findViewById(R.id.rightdialogbutton);
		wrongdialogbutton = (ImageButton) context
				.findViewById(R.id.wrongdialogbutton);
		wrongdialogtrybutton = (ImageButton) context
				.findViewById(R.id.wrongdialogtrybutton);
		right_dia_text = (TextView) context.findViewById(R.id.right_dia_text);
		numofcolumns = Arrays
				.asList(res.getStringArray(R.array.numberofcolums));
		updatescore(score);
		life_imgview.setImageResource(life_resource.get(num_life - 1));
		grid.setNumColumns(25);
		grid.setScrollContainer(false);
		setMyList(list, getnumberofcells());
		grid.setOnItemClickListener(this);
		rightdialogbutton.setOnClickListener(this);
		wrongdialogbutton.setOnClickListener(this);
		wrongdialogtrybutton.setOnClickListener(this);
		newBest=false;
		return root;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		intialiseTimer();
		gridclickable = false;
		question_timer.start();
	}

	private void intialiseTimer() {
		if (question_timer != null)
			question_timer = null;
		question_timer = new CountDownTimer(GameOneAppConstants.progress_duration,
				GameOneAppConstants.progress_tick) {
			@Override
			public void onTick(long millisUntilFinished) {
				progressBar.setProgress((int) millisUntilFinished);
				progressBar.setVisibility(View.VISIBLE);
				selecttext.setVisibility(View.INVISIBLE);
			}

			@Override
			public void onFinish() {

				gridclickable = true;
				grid.setAdapter(new GameOneGridAdapter(context, getTempList()));
				selecttext.setText(context.getString(R.string.select,list.get(generateRandom(anslist.size()))));
				progressBar.setVisibility(View.GONE);
				selecttext.setVisibility(View.VISIBLE);
				fadeText(selecttext);

			}
		};
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {

		checkanswer(position);

	}

	private void checkanswer(int position) {
		if (!isgridviewClickable()) {}
		else {
			if (anslist.get(position).equalsIgnoreCase("" + selecttext.getText().toString().charAt(selecttext.length() - 1))) {
				final int temp = score +10;
				User.incRight(User.Game.MEMORY);
				if(User.checkBest(temp,User.Game.MEMORY) && !newBest){
					Toast.makeText(context, "New personal best!", Toast.LENGTH_SHORT).show();
					newBest=true;
				}
				right_dia_text.setText(GameOneGameConstants.generateRandomSuccessMessage(context));
				rightanslayout.setVisibility(View.VISIBLE);
				right_dia_img.setImageResource(GameOneGameConstants.generateRandomHappyDrawableResource());
				tip_text.setText(GameOneGameConstants.generateRandomTip(getActivity()));
				grid.setVisibility(View.GONE);
				frame.setVisibility(View.GONE);
				selecttext.setVisibility(View.GONE);
			}
			else {
				User.incWrong(User.Game.MEMORY);
				decrementlife();
			}
		}
	}

	private void updatescore(int i) {
		score = score + i;
		score_textview.setText(res.getString(R.string.score, score));
		setLevel();

	}

	private void setLevel() {
		level_textview.setText(res.getString(R.string.level,
				((level - 1) / 5) + 1));
	}

	private void setNextQuestion(int i) {

		if (i > numofcolumns.size()) {

			Intent intent = new Intent(context, GameOneResultActivity.class)
					.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
							| Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra(GameOneAppConstants.levelkey, level);
			intent.putExtra(GameOneAppConstants.scorekey, score);
			startActivity(intent);
			getActivity().finish();


		} else {
			setMyList(list, i);
			intialiseTimer();
			question_timer.start();
		}
	}

	protected List<String> getTempList() {

		if (list2 == null || list2.size() != getnumberofcells()) {
			list2 = new ArrayList<String>();
			for (int i = 0; i < getnumberofcells(); i++) {
				list2.add(i, " ");

			}
		}
		return list2;
	}

	public int generateRandom(int size) {

		Random r = new Random();
		return (r.nextInt(size));
	}

	private void setMyList(List<String> list3, int size) {
		selecttext.setText("");
		anslist = new ArrayList<String>();
		Collections.shuffle(list3);
		anslist = list3.subList(0, size);
		adapter = new GameOneGridAdapter(context, anslist);
		grid.setNumColumns(getnumofColums());
		grid.setAdapter(adapter);

	}

	int getnumberofcells() {
		return Integer.parseInt(numofcells.get(level - 1));
	}

	int getnumofColums() {
		return Integer.parseInt(numofcolumns.get(level - 1));
	}

	private boolean isgridviewClickable() {
		return gridclickable;
	}

	private void fadeText(TextView text2) {

		text2.startAnimation(animationFadeIn);

	}

	private void decrementlife() {
		num_life--;
		if (num_life > 0) {

			life_imgview.setImageResource(life_resource.get(num_life - 1));
			wrong_dia_text.setText(GameOneGameConstants.generateRandomFailMessage(context));
			wrong_dia_img.setImageResource(GameOneGameConstants.generateRandomSadDrawableResource());
			grid.setVisibility(View.GONE);
			selecttext.setVisibility(View.INVISIBLE);
			frame.setVisibility(View.GONE);
			wronganslayout.setVisibility(View.VISIBLE);

		} else {

			Intent intent = new Intent(context, GameOneResultActivity.class)
					.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
							| Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra(GameOneAppConstants.levelkey, level);
			intent.putExtra(GameOneAppConstants.scorekey, score);
			startActivity(intent);
			getActivity().finish();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		question_timer.cancel();
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

			case R.id.rightdialogbutton:
				grid.setVisibility(View.VISIBLE);
				frame.setVisibility(View.VISIBLE);
				rightanslayout.setVisibility(View.GONE);
				gridclickable = false;
				level++;
				updatescore(10);
				question_timer.cancel();
				if (level < numofcells.size())
					setNextQuestion(getnumberofcells());
				break;

			case R.id.wrongdialogbutton:
				gridclickable = false;
				grid.setVisibility(View.VISIBLE);
				wronganslayout.setVisibility(View.GONE);
				frame.setVisibility(View.VISIBLE);
				selecttext.setVisibility(View.VISIBLE);
				level++;
				updatescore(0);
				question_timer.cancel();
				if (level < numofcells.size())
					setNextQuestion(getnumberofcells());
				break;

			case R.id.wrongdialogtrybutton:
				grid.setVisibility(View.VISIBLE);
				wronganslayout.setVisibility(View.GONE);
				frame.setVisibility(View.VISIBLE);
				selecttext.setVisibility(View.VISIBLE);
				break;

			default:
				break;
		}
	}
}
