package com.movie.pane;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.movie.common.BasicConstant;
import com.movie.entity.MmsFilm;
import com.movie.service.impl.MmsFilmServiceImpl;
import com.movie.util.FileUtil;
import com.movie.util.IdWorker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MManagePane extends BorderPane {

	MmsFilmServiceImpl mmsFilmService = new MmsFilmServiceImpl();

	public Button saveBtn = null;
	public Button cancelBtn = null;
	public Button loadBtn = null;
	public Button loadBtn2 = null;
	public Button deleteBtn = null;
	public String targetPic = "";
	public String targetMv = "";
	public TableView<MmsFilm> table = null;
	ObservableList<MmsFilm> mmsFilmList = null;

	public MManagePane(Stage stage) {

		GridPane gridPane = new GridPane();

		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("电影信息");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		gridPane.add(scenetitle, 0, 0, 2, 1);

		Label filmName = new Label("电影名称:");
		gridPane.add(filmName, 0, 1);

		TextField filmNameTf = new TextField();
		gridPane.add(filmNameTf, 1, 1);

		Label filmDesc = new Label("电影介绍:");
		gridPane.add(filmDesc, 0, 2);

		TextField filmDescField = new TextField();
		gridPane.add(filmDescField, 1, 2);

		Label filmRate = new Label("电影评级:");
		gridPane.add(filmRate, 0, 3);

		TextField filmRateField = new TextField();
		filmRateField.setEditable(false);
		gridPane.add(filmRateField, 1, 3);

		Label filmType = new Label("电影类型:");
		gridPane.add(filmType, 0, 4);

		TextField filmTypeField = new TextField();
		gridPane.add(filmTypeField, 1, 4);

		Label filmPoster = new Label("电影海报:");
		gridPane.add(filmPoster, 0, 5);

		loadBtn = new Button("上传");
		Label loadTxt = new Label();

		HBox hbox1 = new HBox(10);

		HBox.setHgrow(loadBtn, Priority.ALWAYS);
		HBox.setHgrow(loadTxt, Priority.ALWAYS);
		hbox1.getChildren().addAll(loadBtn, loadTxt);
		hbox1.setPrefWidth(160);

		loadBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				FileChooser fileChooser = new FileChooser();
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg",
						"*.gif");
				fileChooser.getExtensionFilters().add(extFilter);
				File file = fileChooser.showOpenDialog(stage);
				loadTxt.setText(file.getAbsolutePath());
				String targetPic = FileUtil.upload(file.getAbsolutePath(), BasicConstant.PIC_PATH);
				setTargetPic(targetPic);
			}
		});
		gridPane.add(hbox1, 1, 5);
		
		
		Label film = new Label("电影视频:");
		gridPane.add(film, 0, 6);

		loadBtn2 = new Button("上传");
		Label loadTxt2 = new Label();

		HBox hbox3 = new HBox(10);

		HBox.setHgrow(loadBtn2, Priority.ALWAYS);
		HBox.setHgrow(loadTxt2, Priority.ALWAYS);
		hbox3.getChildren().addAll(loadBtn2, loadTxt2);
		hbox3.setPrefWidth(160);

		loadBtn2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				FileChooser fileChooser = new FileChooser();
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.avi", "*.mov");
				fileChooser.getExtensionFilters().add(extFilter);
				File file = fileChooser.showOpenDialog(stage);
				loadTxt2.setText(file.getAbsolutePath());
				String targetMv = FileUtil.upload(file.getAbsolutePath(), BasicConstant.MOVIE_PATH);
				setTargetMv(targetMv);
			}
		});
		gridPane.add(hbox3, 1, 6);

		FlowPane root = new FlowPane();
		root.setAlignment(Pos.CENTER);

		saveBtn = new Button(BasicConstant.SAVE);
		cancelBtn = new Button(BasicConstant.CANCEL);
		HBox hbox2 = new HBox(10);

		HBox.setHgrow(saveBtn, Priority.ALWAYS);
		HBox.setHgrow(cancelBtn, Priority.ALWAYS);
		hbox2.getChildren().addAll(saveBtn, cancelBtn);
		hbox2.setPrefWidth(160);
		gridPane.add(hbox2, 1, 7);
		this.setCenter(gridPane);

		// 设置左侧电影列表
		BorderPane borderPane = new BorderPane();
		getTable();
		table.setMaxHeight(500);
		borderPane.setCenter(table);
		this.setLeft(borderPane);

		// 删除
		deleteBtn = new Button(BasicConstant.DELETE);
		borderPane.setBottom(deleteBtn);
		deleteBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ObservableList<MmsFilm> mmsFilmList = getMmsFilmList();
				if (mmsFilmList.size() == 0) {
					JOptionPane.showMessageDialog(null, "表格为空!");
					return;
				}
				MmsFilm mmsFilm = table.getSelectionModel().getSelectedItem();
				int result = mmsFilmService.delete(mmsFilm.getId());
				if(result > 0) {
					//JOptionPane.showMessageDialog(null, "数据删除成功!");
					table.getItems().clear();
					ObservableList<MmsFilm> filmList = getMmsFilmList();
					table.setItems(filmList);
					table.refresh();
				}
			}
		});

		// 保存
		saveBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String name = filmNameTf.getText();
				String description = filmDescField.getText();
				String type = filmTypeField.getText();
				MmsFilm mmsFilm = new MmsFilm(IdWorker.generateId(), name, "", type, description, getTargetPic(),getTargetMv());
				int result = mmsFilmService.save(mmsFilm);
				if (result > 0) {
					// JOptionPane.showMessageDialog(null, "电影信息保存成功!");
					table.getItems().clear();
					ObservableList<MmsFilm> filmList = getMmsFilmList();
					table.setItems(filmList);
					table.refresh();
				}
			}
		});
	}
	
	public void getTable() {
		setTable(getMmsFilmList());
		table.refresh();
	}

	@SuppressWarnings("unchecked")
	public TableView<MmsFilm> setTable(ObservableList<MmsFilm> data) {

		table = new TableView<MmsFilm>();

		TableColumn<MmsFilm, String> idCol = new TableColumn<>("NO");
		TableColumn<MmsFilm, String> nameCol = new TableColumn<>("名称");
		TableColumn<MmsFilm, String> rateCol = new TableColumn<>("评分");
		TableColumn<MmsFilm, String> descCol = new TableColumn<>("介绍");
		TableColumn<MmsFilm, String> typeCol = new TableColumn<>("类型");

		// 将表格的列和类的属性进行绑定
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		rateCol.setCellValueFactory(new PropertyValueFactory<>("rate"));
		descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

		// 设置可编辑
		table.setEditable(true);
		table.getColumns().addAll(idCol, nameCol, rateCol, descCol, typeCol);
		table.setItems(data);
		return table;
	}

	public ObservableList<MmsFilm> getMmsFilmList() {
		ArrayList<MmsFilm> list = mmsFilmService.getMmsFilm(null, null);
		mmsFilmList = FXCollections.observableArrayList(list);
		return mmsFilmList;
	}

	public String getTargetPic() {
		return targetPic;
	}

	public void setTargetPic(String targetPic) {
		this.targetPic = targetPic;
	}

	public String getTargetMv() {
		return targetMv;
	}

	public void setTargetMv(String targetMv) {
		this.targetMv = targetMv;
	}
}
