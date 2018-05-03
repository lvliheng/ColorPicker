package com.llh.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    private static final String TAG = "MainActivity";

    private TextView red_tv,
            green_tv,
            blue_tv,
            result,
            password_tv;

    private RelativeLayout result_layout;
    private List<SeekBar> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_color_by_rgb);
        initView();
    }

    private void initView() {
        ////////////////
        red_tv = (TextView) findViewById(R.id.red_tv);
        SeekBar red_seekbar = (SeekBar) findViewById(R.id.red_seekbar);
        green_tv = (TextView) findViewById(R.id.green_tv);
        SeekBar green_seekbar = (SeekBar) findViewById(R.id.green_seekbar);
        blue_tv = (TextView) findViewById(R.id.blue_tv);
        SeekBar blue_seekbar = (SeekBar) findViewById(R.id.blue_seekbar);
        result_layout = (RelativeLayout) findViewById(R.id.result_layout);
        result = (TextView) findViewById(R.id.result);
        password_tv = (TextView) findViewById(R.id.password_tv);

        mList = new ArrayList<SeekBar>();
        mList.add(red_seekbar);
        mList.add(green_seekbar);
        mList.add(blue_seekbar);

        result.getBackground().setAlpha(100);

        red_seekbar.setOnSeekBarChangeListener(this);
        green_seekbar.setOnSeekBarChangeListener(this);
        blue_seekbar.setOnSeekBarChangeListener(this);
        password_tv.setOnClickListener(this);

        password_tv.setText(giveMeAPwd());
    }

    private void getResult(List<SeekBar> mList, TextView result, RelativeLayout result_layout) {
        String resultStr = String.format("#%02x%02x%02x", mList.get(0).getProgress(), mList.get(1).getProgress(), mList.get(2).getProgress());
        result.setText(resultStr);
        result_layout.setBackgroundColor(Color.parseColor(resultStr));
    }

    @SuppressLint("NewApi")
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        Rect mRect = seekBar.getThumb().getBounds();
        getResult(mList, result, result_layout);
        switch (seekBar.getId()) {
            case R.id.red_seekbar:
                red_tv.setX(mRect.left);
                red_tv.setText("" + progress);
                break;
            case R.id.green_seekbar:
                green_tv.setX(mRect.left);
                green_tv.setText("" + progress);
                break;
            case R.id.blue_seekbar:
                blue_tv.setX(mRect.left);
                blue_tv.setText("" + progress);
                break;

            default:
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private String giveMeAPwd() {
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.password_tv:
                password_tv.setText(giveMeAPwd());
                break;
        }
    }
}
