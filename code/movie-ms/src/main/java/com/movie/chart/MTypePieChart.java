package com.movie.chart;

import java.util.ArrayList;
import java.util.Map;

import com.movie.service.impl.MmsFilmServiceImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class MTypePieChart extends PieChart {
	
	public static MTypePieChart instance = new MTypePieChart();
	
	MmsFilmServiceImpl mmsFilmService = new MmsFilmServiceImpl();

	public MTypePieChart(){
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
		  getTypeData());
		this.setPrefSize(300, 260);
        this.setData(pieChartData);
	}
	
	public ArrayList<PieChart.Data> getTypeData() {
		ArrayList<Map<String, Object>> list = mmsFilmService.statMType();
		ArrayList<PieChart.Data> list2 = new ArrayList<PieChart.Data>();
		for(int i=0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			String valueOf = String.valueOf(map.get("value"));
			list2.add(new PieChart.Data(String.valueOf(map.get("name")), Integer.parseInt(valueOf)));
		}
		return list2;
	}
}
