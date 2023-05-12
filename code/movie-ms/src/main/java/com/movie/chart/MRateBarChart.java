package com.movie.chart;

import java.util.ArrayList;
import java.util.Map;

import com.movie.service.impl.MmsFilmServiceImpl;
import com.movie.service.impl.MmsFilmUserServiceImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

@SuppressWarnings("rawtypes")
public class MRateBarChart extends BarChart {
	
	MmsFilmServiceImpl mmsFilmService = new MmsFilmServiceImpl();
	
	MmsFilmUserServiceImpl mmsFilmUserService = new MmsFilmUserServiceImpl();

	@SuppressWarnings("unchecked")
	public MRateBarChart(Axis xAxis, Axis yAxis) {
		super(xAxis, yAxis);
	
		this.setPrefSize(400, 260);

		xAxis = new CategoryAxis();
		yAxis = new NumberAxis();
		xAxis.setLabel("分布");
		yAxis.setLabel("数量");
		this.setTitle("电影评分分布");
		XYChart.Series series = new XYChart.Series();
		series.setName("评分区段");

		ObservableList<XYChart.Data> xypieChartData = FXCollections.observableArrayList(
				  getTypeData());
		
		series.getData().setAll(xypieChartData);
		this.getData().add(series);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<XYChart.Data> getTypeData() {
		ArrayList<Map<String, Object>> list = mmsFilmUserService.statRate();
		ArrayList<XYChart.Data> list2 = new ArrayList<XYChart.Data>();
		for(int i=0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			String valueOf = String.valueOf(map.get("value"));
			list2.add(new XYChart.Data(String.valueOf(map.get("name")), Integer.parseInt(valueOf)));
		}
		return list2;
	}
}
