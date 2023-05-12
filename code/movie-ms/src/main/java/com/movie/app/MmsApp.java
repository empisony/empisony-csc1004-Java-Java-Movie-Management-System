package com.movie.app;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

import com.movie.common.BasicConstant;
import com.movie.entity.MmsUser;
import com.movie.pane.LoginPane;
import com.movie.pane.MainPane;
import com.movie.pane.NavgPane;
import com.movie.pane.RegPane;
import com.movie.service.impl.MmsUserServiceImpl;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MmsApp extends Application {

	MmsUserServiceImpl mmsUserService = new MmsUserServiceImpl();

	private MainPane mainPane = null;

	private MmsUser mmsUser;

	@Override
	public void start(final Stage stage) throws Exception {
		@SuppressWarnings("unused")
		VBox box = new VBox();
		stage.setTitle(BasicConstant.T_TITLE);

		final BorderPane root = new BorderPane();
		final Scene scene = new Scene(root, 600, 400, Color.WHITE);

		// 初始化 -> 登录界面
		final LoginPane loginPane = LoginPane.instance;
		root.setCenter(loginPane);
		stage.setScene(scene);
		stage.show();

		// 注册 -> 注册界面
		final RegPane regPane = new RegPane(stage, scene, root, loginPane);
		loginPane.regBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				root.setCenter(regPane);
				stage.setScene(scene);
				stage.show();
			}
		});

		// 登录 -> 主界面
		loginPane.loginBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				String userCode = loginPane.userCodeTf.getText();
				String pwd = loginPane.pwdField.getText();
				MmsUser mmsUser = mmsUserService.getMmsUser(userCode, null);
				setMmsUser(mmsUser);
				
				if (StringUtils.isBlank(mmsUser.getUserCode())) {
					JOptionPane.showMessageDialog(null, "账号不存在!");
				} else {
					if (mmsUser.getPassword().equals(pwd)) {
						Stage mmsOpStage = new Stage();
						mmsOpStage.setTitle(BasicConstant.T_TITLE);
						mmsOpStage.setWidth(800);
						mmsOpStage.setHeight(600);

						// 显示主面板
						mainPane = new MainPane(mmsUser);
						mmsOpStage.setScene(new Scene(mainPane));
						mmsOpStage.show();

						// 隐藏登录或注册
						stage.close();

						NavgPane navgPane = new NavgPane(stage, mainPane, mmsUser);
						navgPane.prefWidthProperty().bind(mmsOpStage.widthProperty());
						mainPane.setTop(navgPane);
					} else {
						JOptionPane.showMessageDialog(null, "密码输入错误!");
					}
				}
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

	public MmsUser getMmsUser() {
		return mmsUser;
	}

	public void setMmsUser(MmsUser mmsUser) {
		this.mmsUser = mmsUser;
	}
}
