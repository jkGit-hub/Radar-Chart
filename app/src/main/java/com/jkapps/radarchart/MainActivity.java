package com.jkapps.radarchart;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.renderer.AxisRenderer;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;

// 2019-12-29

public class MainActivity extends AppCompatActivity {

    public static final float MIN = 0f, MAX = 4f;
    static RadarChart radarChart;
    String[] sixRules = {"Meditation", "Generosity", "Ethics", "Patience", "Effort", "Wisdom" };
    String[] ratings = {"", "25%", "50%", "75%", "100% Clear"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radarChart = findViewById(R.id.radarChart);
        radarChart.setBackgroundColor(Color.rgb(60, 60, 60));
        radarChart.getLegend().setEnabled(true);
        radarChart.getLegend().setTextColor(Color.WHITE);
        radarChart.getLegend().setTextSize(12f);
        //radarChart.getLegend().;  //how to align position? top-right?
        radarChart.getDescription().setEnabled(false);
        radarChart.setWebLineWidth(1f);
        radarChart.setWebColor(Color.WHITE);
        radarChart.setWebColorInner(Color.WHITE);
        radarChart.setWebAlpha(200);
        radarChart.setRotationAngle(60);
        //radarChart.animateXY(1400, 1400, Easing.EaseInOutQuad, Easing.EaseInOutQuad);

        XAxis xAxis = radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(sixRules));
        xAxis.setTextSize(15f);

        xAxis.setTextColor(Color.WHITE);
        xAxis.setXOffset(0);
        xAxis.setYOffset(0);

        YAxis yAxis = radarChart.getYAxis();
        yAxis.setValueFormatter(new IndexAxisValueFormatter(ratings));
        yAxis.setAxisMinimum(MIN);
        yAxis.setAxisMaximum(MAX);
        yAxis.setGranularity(1f);
        yAxis.setTextSize(10f);
        yAxis.setTextColor(Color.WHITE);
        yAxis.setLabelCount(5, true);
        yAxis.setDrawLabels(true);

        setData();
    }

    private void setData() {
        ArrayList<RadarEntry> today = new ArrayList<>();
        ArrayList<RadarEntry> lifetime = new ArrayList<>();

        today.add(new RadarEntry(4f));
        today.add(new RadarEntry(4f));
        today.add(new RadarEntry(1f));
        today.add(new RadarEntry(3f));
        today.add(new RadarEntry(2f));
        today.add(new RadarEntry(4f));

        lifetime.add(new RadarEntry(1));
        lifetime.add(new RadarEntry(2));
        lifetime.add(new RadarEntry(3));
        lifetime.add(new RadarEntry(4));
        lifetime.add(new RadarEntry(4));
        lifetime.add(new RadarEntry(2));

        RadarDataSet dataSet1 = new RadarDataSet(today, "RECENT 3 MONTHS");
        dataSet1.setColor(Color.RED);
        //dataSet1.setValueTextColor(Color.WHITE);
        //dataSet1.setValueTextSize(16f);
        dataSet1.setDrawValues(false);
        dataSet1.setFillAlpha(200);
        dataSet1.setLineWidth(2f);
        dataSet1.setDrawHighlightIndicators(false);
        dataSet1.setDrawHighlightCircleEnabled(true);
        dataSet1.setDrawFilled(true);
        if (Utils.getSDKInt() >= 18) {
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
            dataSet1.setFillDrawable(drawable);
        }
        else {
            dataSet1.setFillColor(Color.BLACK);
        }

        RadarDataSet dataSet2 = new RadarDataSet(lifetime, "LIFETIME");
        dataSet2.setColor(Color.GREEN);
        //dataSet2.setValueTextSize(14f);
        dataSet2.setDrawValues(false);
        dataSet2.setDrawFilled(true);
        if (Utils.getSDKInt() >= 18) {
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_green);
            dataSet2.setFillDrawable(drawable);
        }
        else {
            dataSet2.setFillColor(Color.YELLOW);
        }

        RadarData data = new RadarData();
        data.addDataSet(dataSet1);
        data.addDataSet(dataSet2);


        radarChart.setData(data);
        radarChart.invalidate(); //refresh
    }
}