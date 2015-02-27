package com.llh.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements SeekBar.OnSeekBarChangeListener {

    private TextView red_tv,
            green_tv,
            blue_tv,
            result;

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

        mList = new ArrayList<SeekBar>();
        mList.add(red_seekbar);
        mList.add(green_seekbar);
        mList.add(blue_seekbar);

        result.getBackground().setAlpha(100);

        red_seekbar.setOnSeekBarChangeListener(this);
        green_seekbar.setOnSeekBarChangeListener(this);
        blue_seekbar.setOnSeekBarChangeListener(this);
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


}
