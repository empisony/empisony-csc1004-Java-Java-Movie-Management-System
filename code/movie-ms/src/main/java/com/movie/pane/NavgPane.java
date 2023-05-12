package com.movie.pane;

import com.movie.common.BasicConstant;
import com.movie.entity.MmsUser;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class NavgPane extends FlowPane {
	
	public static NavgPane instance = new NavgPane();

	public NavgPane() {
		
	}
	
	public NavgPane(Stage stage, MainPane mainPane, MmsUser mmsUser){
		Button view = new Button(BasicConstant.T_VIEW);
		this.getChildren().add(view);

		Button manager = new Button(BasicConstant.T_MANAGER);

		Button stat = new Button(BasicConstant.T_STAT);

		// 管理员用户可以查看
		if (BasicConstant.YES.equals(mmsUser.getIsAdmin())) {
			this.getChildren().add(manager);
			this.getChildren().add(stat);
		}

		// 预览电影
		view.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//JOptionPane.showMessageDialog(null, "电影预览");
				ScrollPane scrollPane = new ScrollPane();
				scrollPane.setPrefSize(400, 500);
				MViewPane mViewPane = new MViewPane(mmsUser);
				scrollPane.setContent(mViewPane);
				mainPane.setCenter(scrollPane);
			}
		});

		// 电影管理
		manager.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// JOptionPane.showMessageDialog(null, "电影管理");
				MManagePane mManagePane = new MManagePane(stage);
				mainPane.setCenter(mManagePane);
			}
		});

		// 电影统计
		stat.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//JOptionPane.showMessageDialog(null, "电影统计");
				MStatPane mStatPane = new MStatPane();
				mainPane.setCenter(mStatPane);
			}
		});
	}
}
