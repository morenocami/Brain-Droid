package com.braindroid.braindroid;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class MyGridView extends GridView {

	Context context;

	public MyGridView(Context context) {
		super(context);
		this.context = context;
	}

	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int heightSpec;

		// if (getLayoutParams().height == LayoutParams.WRAP_CONTENT) {
		// // The great Android "hackatlon", the love, the magic.
		// // The two leftmost bits in the height measure spec have
		// // a special meaning, hence we can't use them to describe height.
		// heightSpec = MeasureSpec.makeMeasureSpec(
		// Integer.MAX_VALUE , MeasureSpec.EXACTLY);
		// }
		// else {
		// // Any other height should be respected as is.
		// heightSpec = heightMeasureSpec;
		// }
		
		//
//		int width = (int) getResources().getDimension(R.dimen.frame_width);
//		int height = (int) getResources().getDimension(R.dimen.frame_height);
		int width=100;
		int height=100;
		widthMeasureSpec = MeasureSpec.makeMeasureSpec(width,
				MeasureSpec.EXACTLY);
		heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,
				MeasureSpec.EXACTLY);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}