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


public class Frag_AboutUs extends Fragment {

    TextView tv1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
       /* tv1.findViewById(R.id.AS);

        tv1.setText(" Fahad Alqattan, Camilo Moreno, and Vanessa Plugues are the brains behind the Brain-Droid App." +
                "\n\n All Students at Florida Atlantic University, coming together to form what many call a break through in APP Development.  " +
                " Fahad Alqattan born and raised in Kuwait, a Senior in his double major in computer engineering/science." +
                " To all the ladies in the class, HE IS SINGLE." +
                "\n\n  Vanessa Plugues born and raised in Florida, a Junior is her double major in computer engineering/science." +
                " Ms. Plugues is a mother of 1 and works as a part time tutor." +
                "\n\n Camilo Moreno born and raised in Colombia, a Senior in his double major in computer engineer/science." +
                " Part time drummer, part time tutor, and full time awesome."   );

        tv1.setTextColor(Color.BLUE);*/

        return inflater.inflate(R.layout.about_us, container, false);
    }


}