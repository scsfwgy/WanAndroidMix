package com.gy.wanandroidmix.ui.activity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.gy.wanandroidmix.R;
import com.gy.wanandroidmix.ui.base.MyBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RadarChart2Activity extends MyBaseActivity {

    @BindView(R.id.arc2_rc_radarchart)
    RadarChart mArc2RcRadarchart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_chart2);

        initRadarChart();
        loadRadarChart();
    }

    private void initRadarChart() {
        mArc2RcRadarchart.setTouchEnabled(true);
        Description description = mArc2RcRadarchart.getDescription();
        description.setText("一些描述信息");
        description.setPosition(mArc2RcRadarchart.getX() / 2, mArc2RcRadarchart.getY() - 10);

        Legend legend = mArc2RcRadarchart.getLegend();
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
    }

    private void loadRadarChart() {
        List<String> xVals1 = new ArrayList<>();
        xVals1.add("Party A");
        xVals1.add("Party B");
        xVals1.add("Party C");
        xVals1.add("Party D");
        xVals1.add("Party E");
        xVals1.add("Party F");
        xVals1.add("Party G");
        xVals1.add("Party H");
        xVals1.add("Party I");

        List<RadarEntry> yVals1 = new ArrayList<>();
        List<RadarEntry> yVals2 = new ArrayList<>();
        int mult = 150;
        for (int i = 0; i < xVals1.size(); i++) {
            float v = (float) (Math.random() * mult + mult / 2);
            yVals1.add(new RadarEntry(v, i));
            float v2 = (float) (Math.random() * mult + mult / 2);
            yVals2.add(new RadarEntry(v2, i));
        }

        List<IRadarDataSet> radarDataSetList = new ArrayList<>();

        RadarDataSet radarDataSet1 = new RadarDataSet(yVals1, "数据集合1");
        radarDataSet1.setColor(getResources().getColor(R.color.color_global_red));
        radarDataSet1.setDrawFilled(true);

        RadarDataSet radarDataSet2 = new RadarDataSet(yVals2, "数据集合2");
        radarDataSet2.setColor(getResources().getColor(R.color.color_global_green));
        radarDataSet2.setDrawFilled(true);

        radarDataSetList.add(radarDataSet1);
        radarDataSetList.add(radarDataSet2);
        RadarData radarData = new RadarData(radarDataSetList);
        radarData.setLabels(xVals1);
        radarData.setDrawValues(true);

        mArc2RcRadarchart.setData(radarData);

        mArc2RcRadarchart.invalidate();

    }
}
