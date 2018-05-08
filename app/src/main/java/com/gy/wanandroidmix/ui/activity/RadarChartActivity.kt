package com.gy.wanandroidmix.ui.activity

import android.os.Bundle
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
import com.gy.wanandroidmix.R
import com.gy.wanandroidmix.ui.base.MyBaseActivity
import kotlinx.android.synthetic.main.activity_radar_chart.*

class RadarChartActivity : MyBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radar_chart)

        initView()
        initData()
        initAdapter()
        initListener()
        loadData()
    }

    private fun initView() {
        arc_rc_radarchart.setTouchEnabled(true)
        val description = arc_rc_radarchart.description
        description.text = "我是描述信息"

        arc_rc_radarchart.webLineWidth = 1.5f
        arc_rc_radarchart.webLineWidthInner = 1.0f
        arc_rc_radarchart.webAlpha = 80

//        arc_rc_radarchart.marker=MarkerView

        val xAxis = arc_rc_radarchart.xAxis
        xAxis.textSize = 12f

        val yAxis = arc_rc_radarchart.yAxis
        yAxis.labelCount = 6
        yAxis.textSize = 15f
        yAxis.setDrawZeroLine(true)

        val legend = arc_rc_radarchart.legend
        legend.position = Legend.LegendPosition.BELOW_CHART_CENTER
        legend.xEntrySpace = 2f
        legend.yEntrySpace = 1f
    }

    private fun initData() {

    }

    private fun initAdapter() {

    }

    private fun initListener() {

    }

    private fun loadData() {
        loadChart()
    }

    private fun loadChart() {
        val arrayOf = arrayOf("Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H", "Party I")

        val yVals1 = ArrayList<RadarEntry>()
        val yVals2 = ArrayList<RadarEntry>()

        val mult = 150

        arrayOf.forEachIndexed { index, s ->
            yVals1.add(
                    RadarEntry(((Math.random() * mult + mult / 2).toFloat()), index.toFloat())
            )
            yVals2.add(
                    RadarEntry(((Math.random() * mult + mult / 2).toFloat()), index.toFloat())
            )
        }

        val xVals1 = ArrayList<String>()

        arrayOf.forEach {
            xVals1.add(it)
        }


        val radarDataSet1 = RadarDataSet(yVals1, "Set 1")
        radarDataSet1.color = resources.getColor(R.color.abc_background_cache_hint_selector_material_dark)
        radarDataSet1.setDrawFilled(true)
        radarDataSet1.lineWidth = 2f

        val radarDataSet2 = RadarDataSet(yVals2, "Set 1")
        radarDataSet2.color = resources.getColor(R.color.abc_background_cache_hint_selector_material_dark)
        radarDataSet2.setDrawFilled(true)
        radarDataSet2.lineWidth = 2f

        val sets = ArrayList<RadarDataSet>()
        sets.add(radarDataSet1)
        sets.add(radarDataSet2)

        val radarData = RadarData(sets as List<IRadarDataSet>?)

        arc_rc_radarchart.data = radarData

        arc_rc_radarchart.invalidate()

    }
}
