package com.example.andreaits.dungeonsdragonscharactermanager.Utils;

/**
 * Created by Andrea on 17/03/17.
 */

public class Utils {

    public static int calculateLevel(int exp) {
        if (exp >= 335000) {
            return 20;
        }
        if (exp >= 305000) {
            return 19;
        }

        if (exp >= 265000) {
            return 18;
        }
        if (exp >= 225000) {
            return 17;
        }
        if (exp >= 195000) {
            return 16;
        }
        if (exp >= 165000) {
            return 15;
        }
        if (exp >= 140000) {
            return 14;
        }
        if (exp >= 120000) {
            return 13;
        }
        if (exp >= 100000) {
            return 12;
        }
        if (exp >= 85000) {
            return 11;
        }
        if (exp >= 64000) {
            return 10;
        }
        if (exp >= 48000) {
            return 9;
        }
        if (exp >= 34000) {
            return 8;
        }
        if (exp >= 23000) {
            return 7;
        }
        if (exp >= 14000) {
            return 6;
        }
        if (exp >= 6500) {
            return 5;
        }
        if (exp >= 2700) {
            return 4;
        }
        if (exp >= 900) {
            return 3;
        }
        if (exp >= 300) {
            return 2;
        }
        if (exp >= 0) {
            return 1;
        }
        return 0;
    }

    public static int calculateProficiencyBonus(int level) {
        switch (level) {
            case 1:
            case 2:
            case 3:
            case 4:
                return 2;
            case 5:
            case 6:
            case 7:
            case 8:
                return 3;
            case 9:
            case 10:
            case 11:
            case 12:
                return 4;
            case 13:
            case 14:
            case 15:
            case 16:
                return 5;
            case 17:
            case 18:
            case 19:
            case 20:
                return 6;
            default:
                return -1;
        }
    }

}
