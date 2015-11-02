package com.braindroid.braindroid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Camilo on 11/2/2015.
 */
public class Frag_AboutApp extends Fragment {
    View view;
    TextView tv2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.about_app, container, false);

        tv2 = (TextView) view.findViewById(R.id.AP);

        return view;
    }

    public void changeText(int color){
        tv2.setText(" The Brain-Droid is an educational mobile app and friendly user that is built in 2015" +
                " by senior students from Florida Atlantic University. Brain-Droid developed to Increase " +
                "math skills, Improve retention ability and productivity.\n"   );

        tv2.setTextColor(Color.BLUE);
    }
}
