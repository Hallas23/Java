package gui;

import java.util.Optional;

import application.controller.Controller;
import application.model.Betalingsform;
import application.model.Pris;
import application.model.Prisliste;
import application.model.Produktgruppe;
import application.model.Rabat;
import application.model.Salg;
import application.model.Salgslinje;
import application.model.Udlejning;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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

public class ForsidePane extends GridPane {
	private ListView<Produktgruppe> lvwProduktgrupper;
	private ListView<Pris> lvwProdukter;
	private ListView<Salgslinje> lvwIndkøbskurv;
	private ToggleGroup groupRabat, groupSalgUdlej;
	private TextField txfRabat, txfSamletPris, txfSamletPant, txfNavn, txfTlf;
	private MenuButton mbPrislister;
	private DatePicker dpFraDato, dpTilDato;
	private Spinner<Integer> spinner;
	private SpinnerValueFactory<Integer> valueFactory;
	private final int initialValue = 1;
	private Label lblTypeRabat = new Label("");
	private Prisliste prislisteSelected;
	private Salg salg;
	private Label lblFra, lblTil, lblKontaktNavn, lblTlf, lblError;
	private Button btnOK, btnBetalPant;

	public ForsidePane() {
		this.setPadding(new Insets(60));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		salg = Controller.getController().createSalg();

		mbPrislister = new MenuButton("Prisliste");
		opretPrislister();

		Label lblGruppe = new Label("Gruppe");
		lblGruppe.setStyle("-fx-font-weight: bold;");
		Label lblProdukt = new Label("Produkt");
		lblProdukt.setStyle("-fx-font-weight: bold;");
		Label lblIndkøbskurv = new Label("Indkøbskurv");
		lblIndkøbskurv.setStyle("-fx-font-weight: bold;");
		Label lblAntal = new Label("Antal:");
		Label lblOverskrift = new Label("Salg");
		lblOverskrift.setFont(Font.font("Deng Xian Light", FontWeight.BOLD, 42));
		lblFra = new Label("Fra dato: ");
		lblFra.setVisible(false);
		lblTil = new Label("Til dato:  ");
		lblTil.setVisible(false);
		lblKontaktNavn = new Label("Navn:      ");
		lblKontaktNavn.setVisible(false);
		lblTlf = new Label("Tlf:           ");
		lblTlf.setVisible(false);
		Label lblSamletPris = new Label("Samlet pris ");
		lblError = new Label("");
		Label lblSamletPant = new Label("Samlet pant");
		lblError.setTextFill(Color.RED);

		lvwProduktgrupper = new ListView<>();
		lvwProduktgrupper.setMaxWidth(250);
		lvwProduktgrupper.setMaxHeight(300);
		lvwProduktgrupper.getItems().setAll(Controller.getController().getSalgssprodukter());
		lvwProduktgrupper.setOnMouseClicked(event -> this.produktgruppeChanged());

		lvwProdukter = new ListView<>();
		lvwProdukter.setMaxHeight(300);
		lvwProdukter.setMaxWidth(250);
		lvwProdukter.setOnMouseClicked(event -> spinner.setDisable(false));

		lvwIndkøbskurv = new ListView<>();
		lvwIndkøbskurv.setMaxHeight(300);
		lvwIndkøbskurv.setMaxWidth(250);

		txfSamletPris = new TextField();
		txfNavn = new TextField();
		txfNavn.setVisible(false);
		txfTlf = new TextField();
		txfTlf.setVisible(false);
		txfNavn.setPrefWidth(150);
		txfTlf.setPrefWidth(150);
		txfSamletPant = new TextField();
		txfSamletPant.setDisable(false);
		txfRabat = new TextField();
		txfRabat.setMaxWidth(75);
		txfRabat.setVisible(false);
		groupRabat = new ToggleGroup();
		HBox hbRabat = new HBox(10);
		String[] rabatter = { "Ingen rabat", "Procent rabat", "Pris rabat" };
		RadioButton rb;
		for (String element : rabatter) {
			rb = new RadioButton();
			rb.setToggleGroup(groupRabat);
			rb.setText(element);
			rb.setUserData(element);
			hbRabat.getChildren().add(rb);
		}
		groupRabat.getToggles().get(0).setSelected(true);
		groupRabat.selectedToggleProperty().addListener(event -> toggleRadioButtonRabat());

		groupSalgUdlej = new ToggleGroup();
		RadioButton rbSalg = new RadioButton();
		rbSalg.setUserData("Salg");
		RadioButton rbUdlej = new RadioButton();
		rbUdlej.setUserData("Udlejning");
		rbSalg.setToggleGroup(groupSalgUdlej);
		rbSalg.setText("Salg");
		rbUdlej.setToggleGroup(groupSalgUdlej);
		rbUdlej.setText("Udlejning");
		groupSalgUdlej.getToggles().get(0).setSelected(true);
		groupSalgUdlej.selectedToggleProperty().addListener(event -> this.toggleRadioButtonSalgUdlej());

		Button btnTilføj = new Button("Tilføj til kurv");
		btnTilføj.setOnAction(event -> this.tilføjTilIndkøbskurv());
		Button btnFjern = new Button("Fjern fra kurv");
		btnFjern.setOnAction(event -> this.fjernFraIndkøbskurv());
		Button btnBetal = new Button("Betal");
		btnBetal.setMaxWidth(100);
		btnBetal.setOnAction(event -> this.betal());
		Button btnOpretUdlejning = new Button("Opret udlejning");
		btnOpretUdlejning.setOnAction(event -> opretUdlejningAction());
		btnOK = new Button("OK");
		btnOK.setVisible(false);
		btnOK.setPrefWidth(90);
		btnOK.setOnAction(event -> this.okAction());

		spinner = new Spinner<>();
		HBox hbButtom = new HBox(10, lblAntal, spinner, btnTilføj, btnFjern);
		hbRabat.getChildren().addAll(txfRabat, lblTypeRabat);

		valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, initialValue);
		spinner.setPrefWidth(60);
		spinner.setValueFactory(valueFactory);
		spinner.setDisable(true);

		dpFraDato = new DatePicker();
		dpFraDato.setVisible(false);
		dpTilDato = new DatePicker();
		dpTilDato.setVisible(false);

		HBox hb1 = new HBox(10, lblKontaktNavn, txfNavn);
		HBox hb2 = new HBox(10, lblTlf, txfTlf);
		HBox hb3 = new HBox(10, lblFra, dpFraDato);
		HBox hb4 = new HBox(10, lblTil, dpTilDato);
		HBox hb5 = new HBox(10, mbPrislister, rbSalg, rbUdlej);
		HBox hb6 = new HBox(10, lblSamletPant, txfSamletPant);

		btnBetalPant = new Button("Betal pant");
		btnBetalPant.setOnAction(event -> this.betalPantAction());

		this.add(lblOverskrift, 0, 0);
		this.add(hb5, 0, 1);
		this.add(lblGruppe, 0, 2);
		this.add(lblProdukt, 1, 2);
		this.add(lblIndkøbskurv, 5, 2);
		this.add(lvwProduktgrupper, 0, 5);
		this.add(lvwProdukter, 1, 5);
		this.add(lvwIndkøbskurv, 5, 5);
		this.add(hbRabat, 0, 6, 2, 1);
		this.add(hbButtom, 0, 7, 2, 1);
		this.add(lblSamletPris, 5, 6);
		this.add(txfSamletPris, 5, 7);
		this.add(btnBetal, 5, 8);
		this.add(btnOpretUdlejning, 5, 9);
		this.add(hb6, 5, 10);
		this.add(hb1, 0, 8);
		this.add(hb2, 0, 9);
		this.add(hb3, 0, 10);
		this.add(hb4, 0, 11);
		this.add(btnOK, 0, 12);
		this.add(lblError, 1, 12);
		this.add(btnBetalPant, 5, 11);

		hbRabat.setAlignment(Pos.BASELINE_CENTER);
		hbButtom.setAlignment(Pos.BASELINE_CENTER);
		GridPane.setHalignment(txfRabat, HPos.LEFT);
		GridPane.setHalignment(lblTypeRabat, HPos.CENTER);

	}

	private void prislisteChanged(MenuItem item) {
		MenuItem mi = item;
		for (Prisliste pr : Controller.getController().getAllPrislister()) {
			if (pr.getNavn() == mi.getText()
					&& !(groupSalgUdlej.getSelectedToggle().getUserData().toString() == "Udlejning")) {
				prislisteSelected = pr;
				lvwProdukter.getItems().clear();
				lvwProduktgrupper.getItems().setAll(Controller.getController().getSalgssprodukter());
			}

			if (pr.getNavn() == mi.getText()
					&& (groupSalgUdlej.getSelectedToggle().getUserData().toString() == "Udlejning")) {
				prislisteSelected = pr;
				lvwProdukter.getItems().clear();
				lvwProduktgrupper.getItems().setAll(Controller.getController().getUdlejningsprodukter());
			}

			if (pr.getNavn() == mi.getText() && pr.getNavn().equals("Klippekort")
					&& !(groupSalgUdlej.getSelectedToggle().getUserData().toString() == "Udlejning")) {
				prislisteSelected = pr;
				lvwProdukter.getItems().clear();
				lvwProduktgrupper.getItems().setAll(Controller.getController().getKlippekortprodukter());
				;
			}

		}
	}

	private void produktgruppeChanged() {
		lblError.setText("");
		try {
			lvwProdukter.getItems().clear();
			Produktgruppe selectedProduktgruppe = lvwProduktgrupper.getSelectionModel().getSelectedItem();
			for (Pris p : Controller.getController().AllePriserfraPrisliste(prislisteSelected)) {
				if (p.getProdukt().getProduktgruppe() == selectedProduktgruppe) {
					lvwProdukter.getItems().add(p);
				}
			}
		} catch (Exception e) {
			lblError.setText("Der skal vælges en prisliste");
			lblError.setTextFill(Color.RED);
		}

	}

	private void toggleRadioButtonRabat() {
		String s = groupRabat.getSelectedToggle().getUserData().toString();
		if (s.equalsIgnoreCase("Ingen rabat")) {
			txfRabat.setVisible(false);
			lblTypeRabat.setVisible(false);
		} else {
			txfRabat.setVisible(true);
			lblTypeRabat.setVisible(true);
			if (s.equalsIgnoreCase("Procent rabat")) {
				lblTypeRabat.setText("Procent");
			} else {
				lblTypeRabat.setText("Kr");
			}
		}
	}

	private void toggleRadioButtonSalgUdlej() {
		if (groupSalgUdlej.getSelectedToggle().getUserData().toString() == "Udlejning") {
			salg = Controller.getController().createUdlejning();
			this.clearFields();
			lvwProduktgrupper.getItems().setAll(Controller.getController().getUdlejningsprodukter());
		}
		if (groupSalgUdlej.getSelectedToggle().getUserData().toString() == "Salg") {
			salg = Controller.getController().createSalg();
			this.clearFields();
			lvwProduktgrupper.getItems().setAll(Controller.getController().getSalgssprodukter());
		}

	}

	private void tilføjTilIndkøbskurv() {
		lblError.setText("");

		try {
			Pris p = lvwProdukter.getSelectionModel().getSelectedItem();
			int antal = spinner.getValue();
			Rabat rabat;
			RadioButton radioB = (RadioButton) groupRabat.getSelectedToggle();
			String s = radioB.getText();

			if (s.equalsIgnoreCase("Ingen rabat")) {
				rabat = Controller.getController().createIngenRabat();

				Controller.getController().createSalgslinje(salg, antal, rabat, p);

			} else {

				double pris = 0;
				pris = Double.parseDouble(txfRabat.getText());
				if (s.equalsIgnoreCase("Procent rabat")) {
					try {
						rabat = Controller.getController().createProcentRabat(pris);
						Controller.getController().createSalgslinje(salg, antal, rabat, p);

					} catch (Exception e) {
						if (e.getMessage().contains("For input")) {
							lblError.setText("Ugyldig rabat");
						} else {
							lblError.setText(e.getMessage());
						}
					}
				} else {
					try {
						rabat = Controller.getController().createPrisRabat(pris);
						Controller.getController().createSalgslinje(salg, antal, rabat, p);

					} catch (Exception e) {
						if (e.getMessage().contains("For input")) {
							lblError.setText("Ugyldig rabat");
						} else {
							lblError.setText(e.getMessage());
						}
					}

				}
			}

			for (Salgslinje sl : salg.getSalgslinjer()) {
				if (p.getProdukt() == sl.getProdukt()) {
					antal = antal + sl.getAntal();
				}
			}

			valueFactory.setValue(1);
			lvwIndkøbskurv.getItems().setAll(salg.getSalgslinjer());

			txfSamletPris.setText(String.valueOf(salg.udregnSamletPris())); ///

			// alert til hvis der ikke er nok på lager
			if (!Controller.getController().checkAntalPåLager(p.getProdukt(), antal)) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Ikke nok på lager");
				alert.setContentText("Lagerantal: " + p.getProdukt().getAntalPåLager());
				alert.showAndWait();
				return;
			}

		}

		catch (Exception e) {
			lblError.setText("Kan ikke være tom");
			lblError.setTextFill(Color.RED);
		}

	}

	private void betal() {
		lblError.setText("");

		try {

			if (salg instanceof Udlejning) {
				lblError.setText("Du kan ikke købe et udlejningsprodukt");

			}
			if (!(salg instanceof Udlejning)) {
				ChoiceDialog<Betalingsform> dialog = new ChoiceDialog<>();
				dialog.getItems().setAll(Controller.getController().getAllBetalingsformer());
				dialog.setTitle("Betaling for et salg");
				dialog.setHeaderText("Vælg betalingsform");
				dialog.setSelectedItem(dialog.getItems().get(0));
				Optional<Betalingsform> result = dialog.showAndWait();
				if (result.isPresent()) {
					Controller.getController().saveSalg(salg, dialog.getSelectedItem());
					this.clearFields();
				}
			}
		} catch (Exception e) {
			lblError.setText(e.getMessage());
			lvwIndkøbskurv.getItems().clear();
		}

		salg = Controller.getController().createSalg();

	}

	private void betalPantAction() {
		try {
			ChoiceDialog<Betalingsform> dialog = new ChoiceDialog<>();
			dialog.getItems().setAll(Controller.getController().getAllBetalingsformer());
			dialog.setTitle("Betaling for pant");
			dialog.setHeaderText("Vælg betalingsform");
			dialog.setSelectedItem(dialog.getItems().get(0));
			Optional<Betalingsform> result = dialog.showAndWait();
			if (result.isPresent()) {
				Controller.getController().saveSalg(salg, dialog.getSelectedItem());
				txfSamletPant.clear();
			}
		} catch (Exception e) {
			lblError.setText(e.getMessage());
			lblError.setTextFill(Color.RED);
		}
	}

	private void fjernFraIndkøbskurv() {
		Salgslinje sl = lvwIndkøbskurv.getSelectionModel().getSelectedItem();
		salg.removeSalgslinje(sl);
		lvwIndkøbskurv.getItems().setAll(salg.getSalgslinjer());
	}

	private void okAction() {
		try {
			Udlejning u = (Udlejning) salg;
			double pantUdlej = 0;
			pantUdlej = u.udregnSamletPant();

			txfSamletPant.setText("" + pantUdlej);
			u.setNavn(txfNavn.getText().trim());
			u.setTlf(txfTlf.getText().trim());
			u.setUdlejningsDato(dpFraDato.getValue());
			u.setAfleveringsDato(dpTilDato.getValue());
			this.clearFields();

			Controller.getController().saveSalg(u, null);
			for (Salgslinje s : salg.getSalgslinjer()) {
				salg.removeSalgslinje(s);
			}
			this.clearFields();
		} catch (Exception e) {
			lblError.setText(e.getMessage());
		}

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Udlejning oprettet");
		alert.showAndWait();
		return;
	}

	private void opretUdlejningAction() {
		dpFraDato.setVisible(true);
		dpTilDato.setVisible(true);
		txfNavn.setVisible(true);
		txfTlf.setVisible(true);
		lblFra.setVisible(true);
		lblTil.setVisible(true);
		lblKontaktNavn.setVisible(true);
		lblTlf.setVisible(true);
		btnOK.setVisible(true);
	}

	private void clearFields() {
		lblError.setText("");
		txfNavn.clear();
		txfRabat.clear();
		txfTlf.clear();
		txfSamletPris.clear();
		lvwIndkøbskurv.getItems().clear();
		dpFraDato.setValue(null);
		dpTilDato.setValue(null);
		dpFraDato.setVisible(false);
		dpTilDato.setVisible(false);
		txfNavn.setVisible(false);
		txfTlf.setVisible(false);
		lblFra.setVisible(false);
		lblTil.setVisible(false);
		lblKontaktNavn.setVisible(false);
		lblTlf.setVisible(false);
		btnOK.setVisible(false);
	}

	private void opretPrislister() {
		for (Prisliste p : Controller.getController().getAllPrislister()) {
			MenuItem item = new MenuItem(p.getNavn());
			mbPrislister.getItems().add(item);
			item.setOnAction(event -> this.prislisteChanged(item));
		}
	}

	public void refresh() {
		lblError.setText("");
		txfNavn.clear();
		txfRabat.clear();
		txfTlf.clear();
		txfSamletPris.clear();
		lvwIndkøbskurv.getItems().clear();
		mbPrislister.getItems().clear();
		opretPrislister();
		lvwProduktgrupper.getItems().setAll(Controller.getController().getAllProduktgrupper());

	}

}
