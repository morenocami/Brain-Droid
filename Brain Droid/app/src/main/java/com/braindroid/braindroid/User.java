package com.braindroid.braindroid;

import com.parse.ParseObject;

/**
 * Created by Camilo on 11/15/2015.
 */
public class User{

    private static String id;
    private static int highscore, right, wrong, total;
    private static double ratio;
    private static String firstName, lastName, username;


    static void userFound(ParseObject po){
        firstName = po.getString("firstname");
        lastName = po.getString("lastname");
        username = po.getString("username");
        highscore = po.getInt("highscore");
        right = po.getInt("right");
        wrong = po.getInt("wrong");
        total = po.getInt("total");
        ratio = po.getDouble("ratio");
        id = po.getObjectId();
    }


    static int getTotal() {
        return total;
    }

    static int getRight() {
        return right;
    }

    static int getWrong() {
        return wrong;
    }

    static String getFirstName() {
        return firstName;
    }

    static String getLastName() {
        return lastName;
    }

    static String getUsername() {
        return username;
    }

    static void incRight(){
        right++;
        total++;
    }

    static void incWrong(){
        wrong++;
        total++;
    }
}
