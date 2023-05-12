package com.movie.pane;

import com.movie.common.BasicConstant;
import com.movie.service.impl.MmsUserServiceImpl;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LoginPane extends GridPane {
	
	public static LoginPane instance = new LoginPane();
	
	MmsUserServiceImpl mmsUserService = new MmsUserServiceImpl();

	public TextField userCodeTf = null;
	public PasswordField pwdField = null;
	public Button loginBtn = null;
	public Button regBtn = null;

	public LoginPane() {

		this.setAlignment(Pos.CENTER);
		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Welcome");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("用户名:");
		this.add(userName, 0, 1);

		userCodeTf = new TextField();
		this.add(userCodeTf, 1, 1);

		Label pw = new Label("用户密码:");
		this.add(pw, 0, 2);

		pwdField = new PasswordField();
		this.add(pwdField, 1, 2);

		FlowPane root = new FlowPane();
		root.setAlignment(Pos.CENTER);

		loginBtn = new Button(BasicConstant.LOGIN);
		regBtn = new Button(BasicConstant.REGISTER);
		HBox hbox = new HBox(10);

		HBox.setHgrow(loginBtn, Priority.ALWAYS);
		HBox.setHgrow(regBtn, Priority.ALWAYS);
		hbox.getChildren().addAll(loginBtn, regBtn);
		hbox.setPrefWidth(160);

		this.add(hbox, 1, 3);
		final Text actiontarget = new Text();
		this.add(actiontarget, 1, 6);
	}
}
