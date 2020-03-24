package gui;

import java.util.Optional;

import application.controller.Controller;
import application.model.Betalingsform;
import application.model.Udlejning;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * 
 * @author Sille, Simon og Michelle
 *
 */

public class UdlejningPane extends GridPane {

	private ListView<Udlejning> lvwUdlejning;
	private ListView<Udlejning> lvwAfleverede;
	private Udlejning udlejning;
	private Label lblError;

	public UdlejningPane() {
		this.setPadding(new Insets(10, 10, 10, 50));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		Label lblUdlejning = new Label("Oversigt over udlejning");
		lblUdlejning.setFont(Font.font("Deng Xian Light", FontWeight.BOLD, 30));
		Label lblIAB = new Label("Ikke afleveret/betalt");
		lblIAB.setStyle("-fx-font-weight: bold;");
		Label lblAfleverede = new Label("Afleverede");
		lblAfleverede.setStyle("-fx-font-weight: bold;");

		lvwUdlejning = new ListView<>();
		lvwUdlejning.setMaxHeight(400);
		lvwUdlejning.setPrefWidth(300);
		lvwUdlejning.getItems().setAll(Controller.getController().ikkeAfleveredeUdlejninger());

		lvwAfleverede = new ListView<>();
		lvwAfleverede.setMaxHeight(400);
		lvwAfleverede.setPrefWidth(300);
		lvwAfleverede.getItems().setAll(Controller.getController().afleveredeUdlejninger());

		Button btnBetal = new Button("Betal");
		btnBetal.setPrefWidth(90);
		btnBetal.setOnAction(event -> betalAction());

		lblError = new Label("");
		lblError.setTextFill(Color.RED);

		VBox vb1 = new VBox(10, lblIAB, lvwUdlejning, btnBetal);
		VBox vb2 = new VBox(10, lblAfleverede, lvwAfleverede);

		this.add(lblUdlejning, 0, 0);
		this.add(vb1, 0, 1);
		this.add(vb2, 5, 1);
		this.add(lblError, 5, 2);

	}

	private void betalAction() {
		try {
			udlejning = lvwUdlejning.getSelectionModel().getSelectedItem();
			ChoiceDialog<Betalingsform> dialog = new ChoiceDialog<>();
			dialog.getItems().setAll(Controller.getController().getAllBetalingsformer());
			dialog.setTitle("Betaling for en udlejning");
			dialog.setHeaderText("Vælg betalingsform");
			dialog.setSelectedItem(dialog.getItems().get(0));
			Optional<Betalingsform> result = dialog.showAndWait();
			if (result.isPresent()) {
				Controller.getController().betalUdlejning(udlejning, result.get());
				lvwUdlejning.getItems().clear();
				lvwUdlejning.getItems().setAll(Controller.getController().ikkeAfleveredeUdlejninger());
				lvwAfleverede.getItems().clear();
				lvwAfleverede.getItems().setAll(Controller.getController().afleveredeUdlejninger());
			}

		} catch (Exception e) {
			lblError.setText(e.getMessage());
		}
	}

	public void refresh() {
		lvwUdlejning.getItems().clear();
		lvwUdlejning.getItems().setAll(Controller.getController().ikkeAfleveredeUdlejninger());
		lvwAfleverede.getItems().clear();
		lvwAfleverede.getItems().setAll(Controller.getController().afleveredeUdlejninger());
	}
}
