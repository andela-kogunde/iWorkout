package com.andela.iworkout.managers;


import com.andela.iworkout.model.Day;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class AnalyticsManager {
    private BarChart mChart;

    public AnalyticsManager(BarChart mChart) {
        this.mChart = mChart;

        if (this.mChart != null) {
            setupChart();
        }
    }

    private void setupChart() {
        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);
        mChart.setDescription("");
        mChart.setMaxVisibleValueCount(60);
        mChart.setPinchZoom(false);
        mChart.setDrawGridBackground(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);

        YAxisValueFormatter custom = new PercentFormatter();

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinValue(0f);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(8, false);
        rightAxis.setValueFormatter(custom);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinValue(0f);

        Legend legend = mChart.getLegend();
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setFormSize(9f);
        legend.setTextSize(11f);
        legend.setXEntrySpace(4f);

        mChart.animateXY(1000, 1000);
    }

    public boolean setData(List<Day> list) {
        int count = list.size();

        if (count > 0) {
            ArrayList<Day> days = new ArrayList<>(list);
            ArrayList<String> chartLabel = new ArrayList<>();
            ArrayList<BarEntry> chartValue = new ArrayList<>();

            for (int i = 0; i < count; i++) {
                chartLabel.add(days.get(i).getThedate());
                chartValue.add(new BarEntry((float) days.get(i).getPushups(), i));
            }

            BarDataSet dataSet = new BarDataSet(chartValue, "PUSH UP");
            dataSet.setBarSpacePercent(35f);
            dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

            ArrayList<IBarDataSet> barDataSets = new ArrayList<>();
            barDataSets.add(dataSet);

            BarData barData = new BarData(chartLabel, barDataSets);
            barData.setValueTextSize(10f);

            mChart.setData(barData);
            return true;
        }

        return false;
    }

    public void displayInstantData(List<Day> list) {
        setData(list);
        mChart.invalidate();
    }
}
