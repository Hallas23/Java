package gui;

import java.util.ArrayList;
import java.util.List;

import application.controller.Controller;
import application.model.Salg;
import application.model.Salgslinje;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * 
 * @author Sille, Simon og Michelle
 *
 */

public class KlippekortPane extends GridPane {

	private ListView<Salg> lvwSalg, lvwSalgMedKlip;
	private ListView<Salgslinje> lvwSalgslinje;
	private DatePicker dpFra, dpTil;
	private TextField txfAnvendt, txfSolgt, txfPeriodeOmløb, txfOmløb;
	private Button btnVis;
	private Label lblError;

	public KlippekortPane() {
		this.setPadding(new Insets(60));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		Label lblOverskrift = new Label("Klippekort");
		lblOverskrift.setFont(Font.font("Deng Xian Light", FontWeight.BOLD, 42));
		Label lblSalg = new Label("Salg betalt med klippekort");
		lblSalg.setStyle("-fx-font-weight: bold;");
		Label lblProdukter = new Label("Salg af klippekort");
		lblProdukter.setStyle("-fx-font-weight: bold;");
		Label lblFra = new Label("Fra");
		Label lblTil = new Label("Til  ");
		Label lblKlipAnvendt = new Label("Klip anvendt:            ");
		Label lblKlipSolgt = new Label("Klip solgt:                ");
		Label lblOmløbPeriode = new Label("Klip i omløb (periode):");
		Label lblOmløb = new Label("Klip i omløb:             ");

		dpFra = new DatePicker();
		dpTil = new DatePicker();

		txfAnvendt = new TextField();
		txfSolgt = new TextField();
		txfPeriodeOmløb = new TextField();
		txfOmløb = new TextField();

		lvwSalgMedKlip = new ListView<>();
		lvwSalgMedKlip.setMaxHeight(300);
		lvwSalgMedKlip.setMaxWidth(250);
		lvwSalgMedKlip.setOnMouseClicked(event -> this.salgMedKlipChanged());

		lvwSalg = new ListView<>();
		lvwSalg.setMaxHeight(300);
		lvwSalg.setMaxWidth(300);
		lvwSalg.setOnMouseClicked(event -> this.salgAfKlipChanged());

		btnVis = new Button("Vis");
		btnVis.setOnAction(event -> this.datoChanged());

		lblError = new Label();
		lblError.setTextFill(Color.RED);

		lvwSalgslinje = new ListView<>();
		lvwSalgslinje.setMaxHeight(200);
		lvwSalgslinje.setMaxWidth(500);

		HBox hbox1 = new HBox(20, lblFra, dpFra);
		HBox hbox2 = new HBox(20, lblTil, dpTil);
		HBox hbox3 = new HBox(20, btnVis);
		HBox hbox4 = new HBox(20, lblKlipAnvendt, txfAnvendt);
		HBox hbox5 = new HBox(20, lblKlipSolgt, txfSolgt);
		HBox hbox6 = new HBox(20, lblOmløbPeriode, txfPeriodeOmløb);
		HBox hbox7 = new HBox(20, lblOmløb, txfOmløb);
		HBox hbox8 = new HBox(20, lblError);
		VBox vbox1 = new VBox(20, hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7, hbox8);

		this.add(lblOverskrift, 0, 0);
		this.add(lblProdukter, 0, 1);
		this.add(lvwSalgMedKlip, 1, 2);
		this.add(lblSalg, 1, 1);
		this.add(lvwSalg, 0, 2);
		this.add(vbox1, 2, 2);
		this.add(lvwSalgslinje, 1, 3);

	}

	/**
	 * Ved ændring af dato fra og dato til vises alle informationer omkring
	 * klippekort mellem de to datoer
	 */
	private void datoChanged() {
		try {
			if (dpTil.getValue() != null && dpFra.getValue() != null) {
				txfSolgt.setText("" + Controller.getController().antalKlipiAlt(dpFra.getValue(), dpTil.getValue()));
				txfAnvendt.setText("" + Controller.getController().antalBrugteKlip(dpFra.getValue(), dpTil.getValue()));
				txfPeriodeOmløb
						.setText("" + Controller.getController().getKlipIOmløb(dpFra.getValue(), dpTil.getValue()));
				lvwSalg.getItems()
						.setAll(Controller.getController().getSalgafklippekort(dpFra.getValue(), dpTil.getValue()));
				lvwSalgMedKlip.getItems().setAll(
						Controller.getController().getBetalteSalgmedKlippekort(dpFra.getValue(), dpTil.getValue()));
				lvwSalgslinje.getItems().clear();
			}
		} catch (Exception e) {
			lblError.setText(e.getMessage());
		}
	}

	/**
	 * Viser salgslinjer for det valgte salg med klippekort
	 */
	private void salgMedKlipChanged() {
		Salg selectedSalg = lvwSalgMedKlip.getSelectionModel().getSelectedItem();
		List<Salgslinje> salgslinje = new ArrayList<>();
		salgslinje.addAll(selectedSalg.getSalgslinjer());
		lvwSalgslinje.getItems().setAll(salgslinje);
	}

	private void salgAfKlipChanged() {
		Salg selectedSalg = lvwSalg.getSelectionModel().getSelectedItem();
		List<Salgslinje> salgslinje = new ArrayList<>();
		salgslinje.addAll(selectedSalg.getSalgslinjer());
		lvwSalgslinje.getItems().setAll(salgslinje);
	}

	public void refresh() {
		txfAnvendt.clear();
		txfSolgt.clear();
		txfPeriodeOmløb.clear();
		txfOmløb.clear();
		txfOmløb.setText("" + Controller.getController().getAlleklipIomløb());
		lvwSalg.getItems().clear();
		lvwSalgMedKlip.getItems().clear();
		lvwSalgslinje.getItems().clear();
		dpFra.setValue(null);
		dpTil.setValue(null);
	}

}
