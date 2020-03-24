package exercise5.ex5student;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	private GridPane pane;
	private SubFrame frame1, frame2;
	private List<Observer> obervers = new ArrayList<>();

	@Override
	public void start(Stage stage) {
		stage.setTitle("Ex. 5: Observer Pattern");
		this.pane = new GridPane();
		this.initContent(this.pane);

		Scene scene = new Scene(this.pane);
		stage.setScene(scene);
		stage.show();

		this.frame1 = new SubFrame("SubFrame 1", this, stage);
		this.frame2 = new SubFrame("SubFrame 2", this, stage);
	}

	// ------------------------------------------------------------------------

	private void initContent(GridPane pane) {
		this.pane.setPadding(new Insets(20));
		this.pane.setHgap(10);
		this.pane.setVgap(10);

		Label lblNumber = new Label("Number");
		this.pane.add(lblNumber, 0, 0);

		ToggleGroup group = new ToggleGroup();

		RadioButton rbnRed = new RadioButton("Red");
		this.pane.add(rbnRed, 0, 1);
		rbnRed.setToggleGroup(group);
		rbnRed.setOnAction(event -> this.redAction(this.pane));

		RadioButton rbnGreen = new RadioButton("Green");
		this.pane.add(rbnGreen, 0, 2);
		rbnGreen.setToggleGroup(group);
		rbnGreen.setOnAction(event -> this.greenAction(this.pane));

		RadioButton rbnBlue = new RadioButton("Blue");
		this.pane.add(rbnBlue, 0, 3);
		rbnBlue.setToggleGroup(group);
		rbnBlue.setOnAction(event -> this.blueAction(this.pane));

		Button btnShowFrame1 = new Button("Open SubFrame 1");
		this.pane.add(btnShowFrame1, 0, 4);
		btnShowFrame1.setOnAction(event -> this.showFrame1Action());

		Button btnShowFrame2 = new Button("Open SubFrame 2");
		this.pane.add(btnShowFrame2, 0, 5);
		btnShowFrame2.setOnAction(event -> this.showFrame2Action());
	}

	// ------------------------------------------------------------------------

	private String color;

	private void redAction(GridPane pane) {
		this.color = "red";
		pane.setStyle("-fx-background-color: " + this.color);
		notifyObservers();
	}

	private void greenAction(GridPane pane) {
		this.color = "lightgreen";
		pane.setStyle("-fx-background-color: " + this.color);
		notifyObservers();
	}

	private void blueAction(GridPane pane) {
		this.color = "lightskyblue";
		pane.setStyle("-fx-background-color: " + this.color);
		notifyObservers();
	}

	private void showFrame1Action() {
		this.frame1.show();
	}

	private void showFrame2Action() {
		this.frame2.show();
	}

	public void addObserver(Observer o) {
		if (!obervers.contains(o)) {
			obervers.add(o);
		}
	}

	public void removeObserver(Observer o) {
		if (obervers.contains(o)) {
			obervers.remove(o);
		}
	}
	
	public void notifyObservers() {
		for (Observer o: obervers) {
			o.update(color);
		}
	}

	public List<Observer> getObervers() {
		return new ArrayList<>(obervers);
	}
	
	
}
