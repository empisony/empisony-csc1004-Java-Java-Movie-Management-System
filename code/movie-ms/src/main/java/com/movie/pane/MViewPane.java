package com.movie.pane;

import java.util.ArrayList;

import com.movie.common.BasicConstant;
import com.movie.entity.MmsFilm;
import com.movie.entity.MmsFilmUser;
import com.movie.entity.MmsUser;
import com.movie.service.impl.MmsFilmServiceImpl;
import com.movie.service.impl.MmsFilmUserServiceImpl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class MViewPane extends GridPane {
	
	MmsFilmServiceImpl mmsFilmService = new MmsFilmServiceImpl();
	
	MmsFilmUserServiceImpl mmsFilmUserService = new MmsFilmUserServiceImpl();
	
	public MViewPane() {
	}

	public MViewPane(MmsUser mmsUser) {
		ArrayList<MmsFilm> data = getData();
		for(int i = 0; i < data.size(); i++) {
			MmsFilm mmsFilm = data.get(i);
			FlowPane flowPane = new FlowPane();
			flowPane.setPadding(new Insets(5, 5, 5, 5));
			System.out.println(BasicConstant.PIC_PATH + mmsFilm.getPoster());
			
			// 海报
			Image image = new Image("file:" + BasicConstant.PIC_PATH + "\\" + data.get(i).getPoster());
			ImageView imageView = new ImageView(image);
			imageView.setFitWidth(60);
			imageView.setFitHeight(76);
			flowPane.getChildren().add(imageView);
			
			// 设置电影内容
			GridPane gridPane = new GridPane();
			gridPane.setHgap(5);
			gridPane.setVgap(10);
			gridPane.setPadding(new Insets(5, 5, 5, 5));
			Label filmName = new Label("名称: " + mmsFilm.getName());
			gridPane.add(filmName, 0, 1);

			Label filmDesc = new Label("介绍: " + mmsFilm.getDescription());
			gridPane.add(filmDesc, 0, 2);
			
			Label filmRate = new Label("评分: " + mmsFilm.getRate());
			gridPane.add(filmRate, 0, 3);
			
			TextField userFilmRate = new TextField();
			userFilmRate.setPromptText("请输入评分信息");
			gridPane.add(userFilmRate, 2, 1);
			
			TextField comment = new TextField();
			comment.setPromptText("请输入评论内容");
			gridPane.add(comment, 2, 2);
			
			Button saveBtn = new Button(BasicConstant.SAVE);
			Button playBtn = new Button(BasicConstant.PLAY);
			
			HBox hbox1 = new HBox(10);

			HBox.setHgrow(saveBtn, Priority.ALWAYS);
			HBox.setHgrow(playBtn, Priority.ALWAYS);
			hbox1.getChildren().addAll(saveBtn, playBtn);
			hbox1.setPrefWidth(160);
			gridPane.add(hbox1, 2, 3);
			
			saveBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					String filmId = mmsFilm.getId();
					String rate = userFilmRate.getText();
					String comments = comment.getText();
					String userCode = mmsUser.getUserCode();
					MmsFilmUser mmsFilmUser = new MmsFilmUser(filmId,userCode,rate,comments);
					mmsFilmUserService.save(mmsFilmUser);
					System.out.println(filmId + rate + comments + userCode);
				}
			});
			
			/**
			 * 点击播放按钮
			 */
			playBtn.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					String filmId = mmsFilm.getId();
					String rate = userFilmRate.getText();
					String comments = comment.getText();
					String userCode = mmsUser.getUserCode();
					MmsFilmUser mmsFilmUser = new MmsFilmUser(filmId,userCode,rate,comments);
					mmsFilmUserService.save(mmsFilmUser);
					System.out.println(filmId + rate + comments + userCode);
				}
			});
			
			flowPane.getChildren().add(gridPane);
			
			this.add(flowPane, 0, i);
			//this.add(gridPane, 1, i);
		}
	}
	
	public ArrayList<MmsFilm> getData() {
		return mmsFilmService.getMmsFilm(null, null);
	}
}
