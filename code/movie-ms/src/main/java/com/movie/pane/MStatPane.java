package com.movie.pane;

import com.movie.chart.MAuRateBarChart;
import com.movie.chart.MRateBarChart;
import com.movie.chart.MTypePieChart;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.FlowPane;

public class MStatPane extends FlowPane{

	public static MStatPane instance = new MStatPane();

	public MStatPane() {
		// 电影类型
		MTypePieChart instance = new MTypePieChart();
		this.getChildren().add(instance);
		// 评分分布
		MRateBarChart mRateBarChart = new MRateBarChart(new CategoryAxis(),new NumberAxis());
		this.getChildren().add(mRateBarChart);
		// 收视率
		MAuRateBarChart mAuRateBarChart = new MAuRateBarChart(new CategoryAxis(),new NumberAxis());
		this.getChildren().add(mAuRateBarChart);
	}
}
