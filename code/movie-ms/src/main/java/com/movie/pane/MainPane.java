package com.movie.pane;

import com.movie.entity.MmsUser;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainPane extends BorderPane {
	
	public static MainPane instance = new MainPane();

	public TextField userCodeTf = null;
	public PasswordField pwdField = null;
	public Button loginBtn = null;
	public Button regBtn = null;

	public MainPane() {
		this.setPadding(new Insets(0, 0, 0, 0));
	}
	
	public MainPane(MmsUser mmsUser) {
		this.setPadding(new Insets(0, 0, 0, 0));
		
		FlowPane flowPane = new FlowPane();
		Label desc = new Label("基本信息：" + mmsUser.getUserName() + " " + mmsUser.getNickName() + " " + mmsUser.getAge() + " "+
		mmsUser.getSex() + " " + mmsUser.getAddress());
		desc.setTextFill(Color.web("#FF76a3"));
		desc.setFont(new Font("Arial", 20));
		flowPane.getChildren().add(desc);
		this.setBottom(flowPane);
	}
}
