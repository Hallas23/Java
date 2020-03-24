package gui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import application.controller.Controller;
import application.model.Betalingsform;
import application.model.Rundvisning;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * 
 * @author Sille, Simon og Michelle
 *
 */

public class RundvisningPane extends GridPane {

	private ListView<Rundvisning> lvwRundvisning;
	private DatePicker dpDato;
	private TextField txfNr, txfPris, txfPrisS, txfAntal, txfBeskrivelse;
	private ComboBox<LocalTime> cbxStart;
	private ComboBox<LocalTime> cbxSlut;
	private CheckBox chkStudierabat;
	private Rundvisning rundvisning;
	private Label lblError;

	public RundvisningPane() {
		this.setPadding(new Insets(10, 10, 10, 50));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		Label lblRundvisning = new Label("Rundvisning");
		lblRundvisning.setFont(Font.font("Courier", FontWeight.BOLD, 22));
		this.add(lblRundvisning, 0, 0);

		Label lblOpret = new Label("Opret:");
		this.add(lblOpret, 0, 1);

		Label lblBeskrivelse = new Label("Beskrivelse:");
		this.add(lblBeskrivelse, 0, 2);

		txfBeskrivelse = new TextField();
		this.add(txfBeskrivelse, 1, 2);

		Label lblAntal = new Label("Antal personer");
		this.add(lblAntal, 0, 3);

		txfAntal = new TextField();
		this.add(txfAntal, 1, 3);

		Label lblNr = new Label("Kontaktnummer");
		this.add(lblNr, 0, 4);

		txfNr = new TextField();
		this.add(txfNr, 1, 4);

		Label lblDato = new Label("Dato");
		this.add(lblDato, 0, 5);

		dpDato = new DatePicker();
		this.add(dpDato, 1, 5);

		Label lblStart = new Label("Start tid");
		this.add(lblStart, 0, 6);

		cbxStart = new ComboBox<>();
		this.add(cbxStart, 1, 6);
		cbxStart.getItems().setAll(sætTider(LocalTime.of(8, 0), LocalTime.of(21, 00)));
		cbxStart.setOnAction(event -> tilpasSlutTid(cbxStart.getValue(), LocalTime.of(22, 00)));

		Label lblSlut = new Label("Slut tid");
		this.add(lblSlut, 0, 7);

		cbxSlut = new ComboBox<>();
		this.add(cbxSlut, 1, 7);
		cbxSlut.getItems().setAll(sætTider(LocalTime.of(8, 0), LocalTime.of(22, 00)));

		Label lblCheckbox = new Label("Studierabat ");
		this.add(lblCheckbox, 0, 8);

		chkStudierabat = new CheckBox();
		this.add(chkStudierabat, 1, 8);

		Label lblPris = new Label("Pris pr. person");
		this.add(lblPris, 0, 9);

		txfPris = new TextField();
		this.add(txfPris, 1, 9);

		Label lblAktiv = new Label("Aktive rundvisninger");
		this.add(lblAktiv, 3, 1);

		lvwRundvisning = new ListView<>();
		this.add(lvwRundvisning, 3, 2, 1, 8);
		lvwRundvisning.setMaxHeight(250);
		lvwRundvisning.setPrefWidth(400);
		lvwRundvisning.getItems().setAll(Controller.getController().getAllRundvisningerSorted());
		lvwRundvisning.getSelectionModel().selectedItemProperty().addListener(event -> visAction());

		Button btnOK = new Button("OK");
		btnOK.setOnAction(event -> okAction());
		Button btnAnnuller = new Button("Annuller");
		btnAnnuller.setOnAction(event -> annullerAction());
		Button btnSlet = new Button("Slet");
		btnSlet.setOnAction(event -> sletAction());
		Button btnBetal = new Button("Betal");
		btnBetal.setOnAction(event -> betalAction());

		HBox hbButtom = new HBox(20, btnOK, btnAnnuller, btnSlet, btnBetal);
		this.add(hbButtom, 0, 12, 2, 1);
		hbButtom.setAlignment(Pos.BASELINE_CENTER);

		Label lblPrisS = new Label("Samlet pris ");
		this.add(lblPrisS, 3, 11);

		txfPrisS = new TextField();
		txfPrisS.setMaxWidth(100);
		this.add(txfPrisS, 3, 12);

		lblError = new Label("");
		lblError.setTextFill(Color.RED);
		this.add(lblError, 0, 14);

	}

	/**
	 * tilføjer tider fra starttidspunktet til og med sluttidspunktet
	 * 
	 * @param start
	 * @param slut
	 * @return
	 */
	private List<LocalTime> sætTider(LocalTime start, LocalTime slut) {
		List<LocalTime> tider = new ArrayList<>();
		for (LocalTime t = start; t.isBefore(slut); t = t.plusMinutes(30)) {
			tider.add(t);
		}
		return tider;
	}

	/**
	 * gør så sluttidspunktet minimum kan sættes en time efter // starttidspunktet
	 * 
	 * @param start
	 * @param slut
	 */
	private void tilpasSlutTid(LocalTime start, LocalTime slut) {
		if (start != null) {
			cbxSlut.getItems().setAll(sætTider(start.plusHours(1), slut));
			cbxSlut.getSelectionModel().select(0);
		}
	}

	/**
	 * Opretter en ny rundvisning
	 */
	private void okAction() {
		try {
			String beskrivelse = txfBeskrivelse.getText().trim();
			LocalTime start = cbxStart.getValue();
			LocalTime slut = cbxSlut.getValue();
			int kontaktNr = Integer.parseInt(txfNr.getText());
			double prisPrPerson = Double.parseDouble(txfPris.getText());
			int antalPersoner = Integer.parseInt(txfAntal.getText());
			LocalDate dato = dpDato.getValue();
			Betalingsform betalingsform = null;
			boolean studieRabat = chkStudierabat.isSelected();

			if (rundvisning != null) {
				Controller.getController().updateRundvisning(rundvisning, beskrivelse, start, slut, dato, prisPrPerson,
						kontaktNr, betalingsform, antalPersoner, studieRabat);
			} else {

				Controller.getController().createRundvisning(beskrivelse, start, slut, dato, prisPrPerson, kontaktNr,
						betalingsform, antalPersoner, studieRabat);
			}
		} catch (Exception e) {
			if (e.getMessage().contains("For input string")) {
				lblError.setText("Manglende information");
			} else {
				lblError.setText(e.getMessage());
			}
		}

		lvwRundvisning.getItems().setAll(Controller.getController().getAllRundvisningerSorted());
		clearFields();
	}

	/**
	 * Rydder alle felter
	 */
	private void clearFields() {
		txfBeskrivelse.clear();
		txfAntal.clear();
		txfNr.clear();
		txfPris.clear();
		txfPrisS.clear();
		dpDato.setValue(null);
		cbxStart.setValue(null);
		cbxSlut.setValue(null);
		chkStudierabat.setSelected(false);
	}

	/**
	 * Viser information omkring rundvisningen
	 */
	private void visAction() {
		rundvisning = lvwRundvisning.getSelectionModel().getSelectedItem();
		if (rundvisning != null) {
			txfBeskrivelse.setText("" + rundvisning.getBeskrivelse());
			txfAntal.setText("" + rundvisning.getAntalPersoner());
			txfNr.setText("" + rundvisning.getKontaktNr());
			txfPris.setText("" + rundvisning.getPrisPrPerson());
			txfPrisS.setText("" + rundvisning.getSamletPris());
			dpDato.setValue(rundvisning.getDato());
			cbxStart.setValue(rundvisning.getStartTid());
			cbxSlut.setValue(rundvisning.getSlutTid());
			chkStudierabat.setSelected(rundvisning.isStudieRabat());

		}
	}

	/**
	 * Rydder felterne ved annullering af oprettelse
	 */
	private void annullerAction() {
		txfBeskrivelse.clear();
		txfAntal.clear();
		txfNr.clear();
		txfPris.clear();
		txfPrisS.clear();
		dpDato.setValue(null);
		cbxStart.setValue(null);
		cbxSlut.setValue(null);
	}

	/**
	 * Sletter en rundvisning
	 */
	private void sletAction() {
		Rundvisning r = lvwRundvisning.getSelectionModel().getSelectedItem();

		if (r == null) {
			return;
		}

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Slet rundvisning");
		alert.setHeaderText("Du er ved at slette en rundvisning");
		alert.setContentText("Er du sikker på du vil slette rundvisningen?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.isPresent() && (result.get() == ButtonType.OK)) {
			Controller.getController().deleteRundvisning(r);
			lvwRundvisning.getItems().setAll(Controller.getController().getAllRundvisningerSorted());
			clearFields();
		}

	}

	/**
	 * Viser en choice dialog med mulighed for at betale for en rundvisning
	 */
	private void betalAction() {
		ChoiceDialog<Betalingsform> dialog = new ChoiceDialog<>();
		dialog.getItems().setAll(Controller.getController().rundvisningsBetalingsformer());
		dialog.setTitle("Betaling for en rundvisning");
		dialog.setHeaderText("Vælg betalingsform");
		dialog.setSelectedItem(dialog.getItems().get(0));
		Optional<Betalingsform> result = dialog.showAndWait();
		if (result.isPresent()) {
			Controller.getController().betalRundvisning(rundvisning, result.get());
		}

		lvwRundvisning.getItems().setAll(Controller.getController().getAllRundvisningerSorted());

	}

}
