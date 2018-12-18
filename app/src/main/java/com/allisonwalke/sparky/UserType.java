package com.allisonwalke.sparky;

/**
 * Created by allisonwalke on 5/8/18.
 */

public class UserType {
    public final static int SHELTER = 0;
    public final static int INDIVIDUAL = 1;
    public final static int ERROR = -1;

    public static String toString(int value) {
        switch (value){
            case 0:
                return "shelter";
            case 1:
                return "individual";
            case -1:
                return "error";
        }

        return null;
    }
}
