package com.llh.myapplication;

import android.util.SparseArray;

import java.util.Random;

/**
 * Created by lvliheng on 2018/6/28 at 18:08.
 */
public class PasswordUtils {

    public static String giveMeAPwd() {
        StringBuilder localStringBuffer = new StringBuilder();

        int count = 0;
        int letterCount = 0;
        int upperLetterCount = 0;
        int maxCount = 14;
        int maxNumCount = 10;
        int maxLetterCount = 26;
        int letterA = 97;

        Random random = new Random();

        SparseArray<String> numberMap = new SparseArray<>();
        SparseArray<String> letterMap = new SparseArray<>();
        SparseArray<String> finalMap = new SparseArray<>();

        int randomNum;
        String randomLetter;
        for (int i = 0; i < maxCount; i++) {
            if (i == 4 || i == 9) {
                finalMap.put(i, "_");
                continue;
            }
            randomNum = random.nextInt(maxNumCount);
            if (randomNum % 2 == 0) {
                numberMap.put(i, String.valueOf(randomNum));
                finalMap.put(i, String.valueOf(randomNum));
            } else {
                randomNum = random.nextInt(maxLetterCount);
                randomLetter = String.valueOf((char) (randomNum + letterA));
                if (randomNum % 2 == 0) {
                    randomLetter = randomLetter.toUpperCase();
                    upperLetterCount++;
                }
                letterMap.put(i, randomLetter);
                finalMap.put(i, randomLetter);
                letterCount++;
            }
        }

        if (letterCount == 0) {
            randomNum = random.nextInt(numberMap.size());
            randomLetter = String.valueOf((char) (randomNum + letterA));
            numberMap.put(randomNum, randomLetter.toUpperCase());
            finalMap.put(randomNum, randomLetter.toUpperCase());
        } else if (upperLetterCount == 0) {
            randomNum = random.nextInt(letterMap.size());
            randomLetter = String.valueOf((char) (randomNum + letterA));
            letterMap.put(randomNum, randomLetter.toUpperCase());
            finalMap.put(randomNum, randomLetter.toUpperCase());
        }

        for (int i = 0; i < maxCount; i++) {
            if (finalMap.get(i) != null) {
                localStringBuffer.append(finalMap.get(i));
            }
        }

        return localStringBuffer.toString();
    }
}
