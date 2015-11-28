package com.braindroid.braindroid;

import com.parse.ParseObject;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by Camilo on 11/15/2015.
 */
public class User{

    enum Game{MATH, MEMORY, VOCAB};

    private static String id,
                        firstName,
                        lastName,
                        username;
    private static int mathBest,
                        mathRight,
                        mathWrong;
    private static int memoryBest,
                        memoryRight,
                        memoryWrong;
    private static int vocabBest,
                        vocabRight,
                        vocabWrong;
    static DecimalFormat df = new DecimalFormat("#.####");
    static final String TAG = "Test";

    static void userFound(ParseObject po){
        firstName = po.getString("firstname");
        lastName = po.getString("lastname");
        username = po.getString("username");
        mathBest = po.getInt("mathBest");
        mathRight = po.getInt("mathRight");
        mathWrong = po.getInt("mathWrong");
        memoryBest = po.getInt("memoryBest");
        memoryRight = po.getInt("memoryRight");
        memoryWrong = po.getInt("memoryWrong");
        vocabBest = po.getInt("vocabBest");
        vocabRight = po.getInt("vocabRight");
        vocabWrong = po.getInt("vocabWrong");
        id = po.getObjectId();
        df.setRoundingMode(RoundingMode.HALF_UP);
    }

    static void logout(){
        firstName = null;
        lastName = null;
        username = null;
        mathBest = 0;
        mathRight = 0;
        mathWrong = 0;
        memoryBest = 0;
        memoryRight = 0;
        memoryWrong = 0;
        vocabBest = 0;
        vocabRight = 0;
        vocabWrong = 0;
        id = null;
        LogIn.user=null;
    }

    static boolean checkBest (int score, Game game){
        switch(game){
            case MATH:
                LogIn.user.put("mathRight", mathRight);
                LogIn.user.put("mathWrong", mathWrong);
                if(score> mathBest){
                    mathBest = score;
                    LogIn.user.put("mathBest", mathBest);
                    LogIn.user.saveInBackground();
                    return true;
                }
                LogIn.user.saveInBackground();
                return false;
            case MEMORY:
                LogIn.user.put("memoryRight", memoryRight);
                LogIn.user.put("memoryWrong", memoryWrong);
                if(score> memoryBest){
                    memoryBest = score;
                    LogIn.user.put("memoryBest", memoryBest);
                    LogIn.user.saveInBackground();
                    return true;
                }
                LogIn.user.saveInBackground();
                return false;
            case VOCAB:
                return false;
            default:
                return false;
        }

    }

    static int getBest(Game game) {
        switch(game){
            case MATH:
                return mathBest;
            case MEMORY:
                return memoryBest;
            case VOCAB:
                return vocabBest;
            default:
                return 0;
        }
    }

    static int getRight(Game game) {
        switch(game){
            case MATH:
                return mathRight;
            case MEMORY:
                return memoryRight;
            case VOCAB:
                return vocabRight;
            default:
                return 0;
        }
    }

    static int getWrong(Game game) {
        switch(game){
            case MATH:
                return mathWrong;
            case MEMORY:
                return memoryWrong;
            case VOCAB:
                return vocabWrong;
            default:
                return 0;
        }
    }

    static String getRatio(Game game) {
        switch(game){
            case MATH:
                if(mathRight==0 && mathWrong==0){return "N/A";}
                if(mathWrong==0){return df.format(mathRight);}
                return df.format((double)mathRight/(double)mathWrong);
            case MEMORY:
                if(memoryRight==0 && memoryWrong==0){return "N/A";}
                if(memoryWrong==0){return df.format(memoryRight);}
                return df.format((double)memoryRight/(double)memoryWrong);
            case VOCAB:
                if(vocabRight==0 && vocabWrong==0){return "N/A";}
                if(vocabWrong==0){return df.format(vocabRight);}
                return df.format((double)vocabRight/(double)vocabWrong);
            default:
                return "";
        }
    }

    static String getFirstName() {return firstName;}

    static String getFullName() {return firstName + " " + lastName;}

    static void incRight(Game game){
        switch(game){
            case MATH:
                mathRight++;
                break;
            case MEMORY:
                memoryRight++;
                break;
            case VOCAB:
                vocabRight++;
                break;
        }
    }

    static void incWrong(Game game){
        switch(game){
            case MATH:
                mathWrong++;
                break;
            case MEMORY:
                memoryWrong++;
                break;
            case VOCAB:
                vocabWrong++;
                break;
        }
    }
}
