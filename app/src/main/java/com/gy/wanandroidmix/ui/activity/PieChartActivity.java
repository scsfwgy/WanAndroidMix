package com.gy.wanandroidmix.ui.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.gy.wanandroidmix.R;
import com.gy.wanandroidmix.ui.base.MyBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PieChartActivity extends MyBaseActivity {

    @BindView(R.id.apc_pc_piechart)
    PieChart mApcPcPiechart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        initView();
        initData();
        initAdapter();
        initListener();
        loadData();
    }

    private void initView() {
        initMp();
    }

    private void initMp() {
        // 设置饼图是否接收点击事件，默认为true
        mApcPcPiechart.setTouchEnabled(true);
        //设置饼图是否使用百分比
        mApcPcPiechart.setUsePercentValues(true);
        //设置饼图右下角的文字描述
        Description description = new Description();
        description.setText("图例");
        description.setTextSize(16);
        mApcPcPiechart.setDescription(description);

        //是否显示圆盘中间文字，默认显示
        mApcPcPiechart.setDrawCenterText(false);
        //设置圆盘中间文字
        mApcPcPiechart.setCenterText("我在中间");
        //设置圆盘中间文字的大小
        mApcPcPiechart.setCenterTextSize(20);
        //设置圆盘中间文字的颜色
        mApcPcPiechart.setCenterTextColor(Color.RED);
        //设置圆盘中间文字的字体
        mApcPcPiechart.setCenterTextTypeface(Typeface.DEFAULT);

        //设置中间圆盘的颜色
        mApcPcPiechart.setHoleColor(Color.GREEN);
        //设置中间圆盘的半径,值为所占饼图的百分比
        mApcPcPiechart.setHoleRadius(20);

        //设置中间透明圈的半径,值为所占饼图的百分比
        mApcPcPiechart.setTransparentCircleRadius(40);

        //是否显示饼图中间空白区域，默认显示
        mApcPcPiechart.setDrawHoleEnabled(false);
        //设置圆盘是否转动，默认转动
        mApcPcPiechart.setRotationEnabled(true);
        //设置初始旋转角度
        mApcPcPiechart.setRotationAngle(0);

        //设置比例图
        Legend mLegend = mApcPcPiechart.getLegend();
        //设置比例图显示在饼图的哪个位置
        mLegend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        //设置比例图的形状，默认是方形,可为方形、圆形、线性
        mLegend.setForm(Legend.LegendForm.CIRCLE);
        mLegend.setXEntrySpace(7f);

        //设置X轴动画
        mApcPcPiechart.animateX(1800);
    }

    private void initData() {
    }

    private void initAdapter() {

    }

    private void initListener() {

    }

    private void loadData() {
        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(13f, "红"));
        entries.add(new PieEntry(26.7f, "橙"));
        entries.add(new PieEntry(24.0f, "黄"));
        entries.add(new PieEntry(30.8f, "绿"));

        PieDataSet pieDataSet = new PieDataSet(entries, null);
        List<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.colorAccent));
        colors.add(getResources().getColor(R.color.colorPrimary));
        colors.add(getResources().getColor(R.color.color_fundView_xyTxtColor));
        colors.add(getResources().getColor(R.color.colorPrimaryDark));
        pieDataSet.setColors(colors);

        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter());

        mApcPcPiechart.setData(pieData);
        mApcPcPiechart.invalidate(); // refresh
    }
}
