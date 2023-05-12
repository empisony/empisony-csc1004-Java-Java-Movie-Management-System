package com.movie.pane;

import javax.swing.JOptionPane;

import com.movie.common.BasicConstant;
import com.movie.entity.MmsUser;
import com.movie.service.impl.MmsUserServiceImpl;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegPane extends GridPane {

	public static RegPane instance = new RegPane();

	MmsUserServiceImpl mmsUserService = new MmsUserServiceImpl();

	public Button saveBtn = null;
	public Button cancelBtn = null;

	public TextField userCodeTf = null;
	public TextField userNameTf = null;
	public TextField nickNameTf = null;
	public TextField ageTf = null;
	public ToggleGroup sexTg = null;
	public RadioButton man = null;
	public RadioButton woman = null;
	public TextField addressTf = null;
	public PasswordField pwBox = null;
	public PasswordField rpwBox = null;
	
	private String sexName;

	public RegPane() {

	}

	@SuppressWarnings("static-access")
	public RegPane(Stage stage, Scene scene, BorderPane borderPane, LoginPane loginPane) {

		this.setAlignment(Pos.CENTER);
		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("注册信息");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.add(scenetitle, 0, 0, 2, 1);

		Label userCode = new Label("用户名:");
		this.add(userCode, 0, 1);

		userCodeTf = new TextField();
		this.add(userCodeTf, 1, 1);

		Label userName = new Label("姓名:");
		this.add(userName, 0, 2);

		userNameTf = new TextField();
		this.add(userNameTf, 1, 2);

		Label nickName = new Label("昵称:");
		this.add(nickName, 0, 3);

		nickNameTf = new TextField();
		this.add(nickNameTf, 1, 3);

		Label age = new Label("年龄:");
		this.add(age, 0, 4);

		ageTf = new TextField();
		this.add(ageTf, 1, 4);

		Label sex = new Label("性别:");
		this.add(sex, 0, 5);

		sexTg = new ToggleGroup();
		man = new RadioButton("男");
		man.setToggleGroup(sexTg);
		man.setSelected(true);
		woman = new RadioButton("女");
		woman.setToggleGroup(sexTg);

		HBox hbox = new HBox(10);

		HBox.setHgrow(man, Priority.ALWAYS);
		HBox.setHgrow(woman, Priority.ALWAYS);
		hbox.getChildren().addAll(man, woman);
		hbox.setPrefWidth(160);

		this.add(hbox, 1, 5);

		Label address = new Label("地址:");
		this.add(address, 0, 6);

		addressTf = new TextField();
		this.add(addressTf, 1, 6);

		Label pw = new Label("用户密码:");
		this.add(pw, 0, 7);

		pwBox = new PasswordField();
		this.add(pwBox, 1, 7);

		Label rpw = new Label("用户密码:");
		this.add(rpw, 0, 8);

		rpwBox = new PasswordField();
		this.add(rpwBox, 1, 8);

		FlowPane root = new FlowPane();
		root.setAlignment(Pos.CENTER);

		saveBtn = new Button(BasicConstant.SAVE);
		cancelBtn = new Button(BasicConstant.CANCEL);
		HBox hbox2 = new HBox(10);

		hbox2.setHgrow(saveBtn, Priority.ALWAYS);
		hbox2.setHgrow(cancelBtn, Priority.ALWAYS);
		hbox2.getChildren().addAll(saveBtn, cancelBtn);
		hbox2.setPrefWidth(160);

		this.add(hbox2, 1, 9);
		final Text actiontarget = new Text();
		this.add(actiontarget, 1, 7);

		// 注册保存 -> 登录界面
		this.saveBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				String userCode = userCodeTf.getText();
				String userName = userNameTf.getText();
				String nickName = nickNameTf.getText();
				String age = ageTf.getText();
				String sex = getSexName();
				String address = addressTf.getText();
				String pwd = pwBox.getText();
				String rpwd = rpwBox.getText();

				MmsUser mmsUser = new MmsUser(userCode, userName, nickName, age, sex, address, pwd, rpwd);
				if (pwd.equals(rpwd)) {
					int result = mmsUserService.save(mmsUser);
					if (result < 1) {
						JOptionPane.showMessageDialog(null, "用户注册失败!");
					} else {
						JOptionPane.showMessageDialog(null, "用户注册成功!");
						borderPane.setCenter(loginPane);
						stage.setScene(scene);
						stage.show();
					}
				} else {
					JOptionPane.showMessageDialog(null, "两次密码输入不相同！");
				}
			}
		});

		// 注册取消 -> 登录界面
		this.cancelBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				borderPane.setCenter(loginPane);
				stage.setScene(scene);
				stage.show();
			}
		});

		sexTg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (sexTg.getSelectedToggle() != null) {
					setSexName(sexTg.getSelectedToggle().getUserData().toString());
				}
			}
		});
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
}
