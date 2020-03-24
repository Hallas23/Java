package gui;

import java.util.Optional;

import application.controller.Controller;
import application.model.PantProdukt;
import application.model.Pris;
import application.model.Prisliste;
import application.model.Produkt;
import application.model.Produktgruppe;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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

public class OprettelsePane extends GridPane {

	private TextField txfNavnProd, txfNavnPris, txfPris, txfLagerAntal, txfPantpris;
	private ListView<Produktgruppe> lvwProduktgrupper;
	private ListView<Produkt> lvwProdukter;
	private ListView<Pris> lvwPriser;
	private MenuButton mbPrislister;
	private CheckBox cbUdlejningsprdukt, cbSalgsprodukt, cbPantprodukt;
	private Button btnOK1;
	private Button btnOpretGruppe;
	private Button btnOpretProdukt;
	private Label lblPantpris, lblError;
	private Prisliste prislisteSelected;
	private Prisliste nyPrisliste;

	public OprettelsePane() {
		this.setPadding(new Insets(30));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		Label lblOpretGruppeProdukt = new Label("Opret gruppe/produkt");
		lblOpretGruppeProdukt.setFont(Font.font("Deng Xian Light", FontWeight.BOLD, 30));
		Label lblRedigerPrislister = new Label("Rediger prislister");
		lblRedigerPrislister.setFont(Font.font("Deng Xian Light", FontWeight.BOLD, 30));
		Label lblGruppe = new Label("Gruppe");
		lblGruppe.setStyle("-fx-font-weight: bold;");
		Label lblProdukt = new Label("Produkt");
		lblProdukt.setStyle("-fx-font-weight: bold;");
		Label lblNavnPrisliste = new Label("Navn");
		Label lblAngivPris = new Label("Angiv pris");
		Label lblNavnProd = new Label("Navn	              Antal");
		Label lblKroner = new Label("Kr");
		lblPantpris = new Label("Pantpris");
		lblPantpris.setVisible(false);
		lblError = new Label("");
		lblError.setTextFill(Color.RED);

		txfNavnProd = new TextField();
		txfNavnProd.setMaxWidth(90);
		txfLagerAntal = new TextField();
		txfLagerAntal.setMaxWidth(30);
		txfLagerAntal.setDisable(true);
		txfNavnPris = new TextField();
		txfNavnPris.setDisable(true);
		txfPris = new TextField();
		txfPantpris = new TextField();
		txfPantpris.setVisible(false);
		txfPantpris.setMaxWidth(30);

		btnOpretGruppe = new Button("Ny produktgruppe");
		btnOpretGruppe.setOnAction(event -> this.nyProduktGruppeAction());
		btnOpretProdukt = new Button("Nyt produkt");
		btnOpretProdukt.setOnAction(event -> this.nyProduktAction());
		Button btnTilføjProdukt = new Button("Tilføj produkt");
		btnTilføjProdukt.setOnAction(event -> this.tilføjProduktAction());
		Button btnFjernProdukt = new Button("Fjern produkt");
		btnFjernProdukt.setOnAction(event -> this.fjernProduktAction());
		Button btnOpdaterPrisliste = new Button("Opdater");
		btnOpdaterPrisliste.setOnAction(event -> this.opdaterPrislisteAction());
		Button btnSletGruppe = new Button("Slet gruppe");
		btnSletGruppe.setOnAction(event -> this.sletGruppeAction());
		Button btnSletProdukt = new Button("Slet produkt");
		btnSletProdukt.setOnAction(event -> this.sletProduktAction());
		Button btnSletListe = new Button("Slet liste");
		btnSletListe.setOnAction(event -> this.sletPrislisteAction());
		Button btnNyListe = new Button("Ny liste");
		btnNyListe.setOnAction(event -> this.nyPrislisteAction());
		btnOK1 = new Button("OK");
		btnOK1.setDisable(true);
		btnOK1.setOnAction(event -> this.ok1Action());
		Button btnOK2 = new Button("OK");
		btnOK2.setOnAction(event -> this.ok2Action());
		Button btnOpdaterProdukt = new Button("Opdater");
		btnOpdaterProdukt.setOnAction(event -> this.opdaterProduktAction());
		Button btnOpdaterGruppe = new Button("Opdater");
		btnOpdaterGruppe.setOnAction(event -> this.opdaterGruppeAction());
		Button btnOpretSampakning = new Button("Opret en gaveæske");
		btnOpretSampakning.setOnAction(event -> this.opretSampakningAction());
		btnOpretSampakning.setPrefWidth(150);

		mbPrislister = new MenuButton("Prislister");
		opretPrislister();

		lvwProduktgrupper = new ListView<>();
		lvwProduktgrupper.setMaxWidth(250);
		lvwProduktgrupper.setMaxHeight(350);
		lvwProduktgrupper.getItems().setAll(Controller.getController().getAllProduktgrupper());
		lvwProduktgrupper.setOnMouseClicked(event -> this.redigerGruppeAction());

		lvwProdukter = new ListView<>();
		lvwProdukter.setMaxHeight(350);
		lvwProdukter.setMaxWidth(250);
		lvwProdukter.setOnMouseClicked(event -> this.redigerProduktAction());

		lvwPriser = new ListView<>();
		lvwPriser.setMaxHeight(350);
		lvwPriser.setMaxWidth(250);

		cbUdlejningsprdukt = new CheckBox("Udlejningsprodukt");
		cbSalgsprodukt = new CheckBox("Salgsprodukt");
		cbUdlejningsprdukt.setDisable(true);
		cbSalgsprodukt.setDisable(true);
		cbPantprodukt = new CheckBox("Pantprodukt");
		cbPantprodukt.setDisable(true);
		cbPantprodukt.setOnMouseClicked(event -> this.pantSelectedAction());

		// ---------------------------

		this.add(lblOpretGruppeProdukt, 0, 0);
		this.add(lblRedigerPrislister, 4, 0);
		this.add(lblGruppe, 0, 2);
		this.add(lblProdukt, 1, 2);
		this.add(lvwProduktgrupper, 0, 3);
		this.add(lvwProdukter, 1, 3);
		HBox hbox7 = new HBox(10, btnOpretGruppe, btnSletGruppe, btnOpdaterGruppe);
		this.add(hbox7, 0, 4);
		HBox hbox8 = new HBox(10, btnOpretProdukt, btnSletProdukt, btnOpdaterProdukt);
		this.add(hbox8, 1, 4);
		this.add(lblNavnProd, 0, 6);
		HBox hbox6 = new HBox(10, txfNavnProd, txfLagerAntal);
		this.add(hbox6, 0, 7);
		HBox hbox4 = new HBox(10, mbPrislister, btnNyListe);
		this.add(hbox4, 4, 1);
		this.add(lvwPriser, 4, 3);
		HBox hbox1 = new HBox(10, btnTilføjProdukt, btnFjernProdukt, btnOpdaterPrisliste, btnSletListe);
		this.add(hbox1, 4, 4, 1, 3);
		this.add(lblAngivPris, 4, 6);
		HBox hbox2 = new HBox(10, txfPris, lblKroner);
		this.add(hbox2, 4, 7);
		this.add(btnOpretSampakning, 4, 8);
		HBox hbox3 = new HBox(10, lblNavnPrisliste, txfNavnPris, btnOK2);
		this.add(hbox3, 4, 2);
		HBox hbox5 = new HBox(10, cbUdlejningsprdukt, cbSalgsprodukt, cbPantprodukt);
		this.add(hbox5, 0, 8);
		this.add(lblPantpris, 0, 9);
		this.add(txfPantpris, 0, 10);
		this.add(lblError, 0, 11);
		this.add(btnOK1, 0, 12);

	}

	// ---------------------------------------------

	/**
	 * Opretter enten en produktgruppe eller et produkt
	 */
	private void ok1Action() {
		/**
		 * Opretter en produktgruppe
		 */
		if (cbPantprodukt.isDisable()) {
			// Viser en alert hvis man har valgt for meget eller for lidt i checkboksene
			// salg
			// og udlejning
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Produktypen er både salgs og udlejningsprodukt");
			alert.setContentText("Skal produktetypen være salg eller udlejning?");
			ButtonType btnSale = new ButtonType("Salgsprodukt");
			ButtonType btnUdlej = new ButtonType("Udlejningsprodukt");
			alert.getButtonTypes().setAll(btnSale, btnUdlej);
			try {
				String navn = txfNavnProd.getText().trim();
				boolean udlejningsprodukt = false;
				if (cbUdlejningsprdukt.isSelected() && !cbSalgsprodukt.isSelected()) {
					udlejningsprodukt = true;
				} else if (cbSalgsprodukt.isSelected() && !cbUdlejningsprdukt.isSelected()) {
					udlejningsprodukt = false;
				} else if (cbUdlejningsprdukt.isSelected() && cbSalgsprodukt.isSelected()) {
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == btnSale) {
						cbSalgsprodukt.setSelected(true);
						cbUdlejningsprdukt.setSelected(false);
						udlejningsprodukt = false;
					} else {
						cbUdlejningsprdukt.setSelected(true);
						cbSalgsprodukt.setSelected(false);
						udlejningsprodukt = true;
					}
				} else if (!cbSalgsprodukt.isSelected() && !cbUdlejningsprdukt.isSelected()) {
					alert.setHeaderText("Produktypen er hverken salgs og udlejningsprodukt");
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == btnSale) {
						cbSalgsprodukt.setSelected(true);
						cbUdlejningsprdukt.setSelected(false);
					} else {
						cbUdlejningsprdukt.setSelected(true);
						cbSalgsprodukt.setSelected(false);
					}
				}
				Controller.getController().createProduktgruppe(navn, udlejningsprodukt);
				lvwProduktgrupper.getItems().setAll(Controller.getController().getAllProduktgrupper());
				this.clearAll();

			} catch (Exception e) {
				if (e.getMessage().contains("For input string") || e.getMessage().contains("empty String")) {
					lblError.setText("Manglende information");
				} else {
					lblError.setText(e.getMessage());
				}
			}
			/**
			 * Opretter et produkt
			 */
		} else if (!cbPantprodukt.isDisable()) {
			try {
				Produktgruppe selectedPD = lvwProduktgrupper.getSelectionModel().getSelectedItem();
				String navn = txfNavnProd.getText().trim();
				int lagerAntal = Integer.parseInt(txfLagerAntal.getText());
				if (selectedPD == null) {
					lblError.setText("Vælg en produktgruppe");
				}
				if (!cbPantprodukt.isSelected()) {
					Controller.getController().createProdukt(selectedPD, navn, lagerAntal);
				}
				if (cbPantprodukt.isSelected()) {
					double pantpris = Double.valueOf(txfPantpris.getText());
					Controller.getController().createPantProdukt(selectedPD, navn, lagerAntal, pantpris);
				}
				this.clearAll();
				lvwProdukter.getItems().setAll(Controller.getController().getAllProdukter());
			} catch (Exception e) {
				if (e.getMessage().contains("For input string") || e.getMessage().contains("empty String")) {
					lblError.setText("Manglende information");
				} else {
					lblError.setText(e.getMessage());
				}
			}
		}
	}

	/**
	 * Tilpasser felterne til når man har valgt checkboksen pantprodukt
	 */
	private void pantSelectedAction() {
		if (cbPantprodukt.isSelected()) {
			lblPantpris.setVisible(true);
			txfPantpris.setVisible(true);
		} else {
			lblPantpris.setVisible(false);
			txfPantpris.setVisible(false);
		}

	}

	/**
	 * Opretter prislisterne i MenuButton og sætter hvert item til at kunne vælges
	 */
	private void opretPrislister() {
		for (Prisliste p : Controller.getController().getAllPrislister()) {
			MenuItem item = new MenuItem(p.getNavn());
			mbPrislister.getItems().add(item);
			item.setOnAction(event -> this.prislisteChanged(item));
		}
	}

	/**
	 * Opdaterer information om hvilken prisliste der er valgt
	 * 
	 * @param item
	 */
	private void prislisteChanged(MenuItem item) {
		MenuItem mi = item;
		for (Prisliste pr : Controller.getController().getAllPrislister()) {
			if (pr.getNavn() == mi.getText()) {
				lvwPriser.getItems().setAll(pr.getPriser());
				prislisteSelected = pr;
			}
		}
		txfNavnPris.setDisable(false);
	}

	/**
	 * Tilpasser felter til oprettelse af prisliste
	 */
	private void nyPrislisteAction() {
		this.clearAll();
		txfNavnPris.setDisable(false);
		lvwPriser.getItems().clear();
	}

	/**
	 * Opretter en prisliste efter indtastning af korrekt information
	 */
	private void ok2Action() {
		try {
			String navn = txfNavnPris.getText().trim();
			nyPrisliste = Controller.getController().createPrisliste(navn);
			prislisteSelected = nyPrisliste;
			txfNavnPris.clear();
			mbPrislister.getItems().clear();
			opretPrislister();
			txfNavnPris.setDisable(true);
		} catch (Exception e) {
			if (e.getMessage().contains("For input string") || e.getMessage().contains("empty String")) {
				lblError.setText("Manglende information");
			} else {
				lblError.setText(e.getMessage());
			}
		}
		this.clearAll();
	}

	/**
	 * Tilføjer et produkt til en prisliste efter indtastning af korrekt information
	 * i prisfeltet
	 */
	private void tilføjProduktAction() {
		try {
			Produkt selectedProdukt = lvwProdukter.getSelectionModel().getSelectedItem();
			double pris = Double.valueOf(txfPris.getText());
			prislisteSelected.CreatePris(pris, selectedProdukt);
			lvwPriser.getItems().setAll(Controller.getController().AllePriserfraPrisliste(prislisteSelected));
		} catch (Exception e) {
			if (e.getMessage().contains("empty String")) {
				lblError.setText("Produktet skal have en pris");
			} else {
				lblError.setText(e.getMessage());
			}
		}

		this.clearAll();
	}

	/**
	 * Fjerner et produkt
	 */
	private void fjernProduktAction() {
		Pris pris = lvwPriser.getSelectionModel().getSelectedItem();
		prislisteSelected.removePris(pris);// Controller? delete?
		lvwPriser.getItems().setAll(Controller.getController().AllePriserfraPrisliste(prislisteSelected));
	}

	/**
	 * Opdaterer en produkt efter indtastning af korrekt information i felterne
	 */
	private void opdaterProduktAction() {
		try {
			Produkt produkt = lvwProdukter.getSelectionModel().getSelectedItem();
			String navn = txfNavnProd.getText().trim();
			int antalPålager = Integer.parseInt(txfLagerAntal.getText());
			Produktgruppe ptype = lvwProduktgrupper.getSelectionModel().getSelectedItem();
			if (produkt instanceof PantProdukt) {
				lblPantpris.setVisible(true);
				txfPantpris.setVisible(true);
				double pantpris = Double.valueOf(txfPantpris.getText());
				Controller.getController().updatePantProdukt((PantProdukt) produkt, navn, antalPålager, ptype,
						pantpris);

			} else {
				Controller.getController().updateProdukt(produkt, navn, antalPålager, ptype);
			}
			this.clearAll();
			lvwProdukter.getItems().setAll(Controller.getController().getAllProdukter());
		} catch (Exception e) {
			if (e.getMessage().contains("For input string")) {
				lblError.setText("Manglende information");
			} else {
				lblError.setText(e.getMessage());
			}
		}
	}

	/**
	 * Opdaterer en produktgruppe efter indtastning af korrekt information i
	 * felterne
	 */
	private void opdaterGruppeAction() {
		try {
			Produktgruppe produktgruppe = lvwProduktgrupper.getSelectionModel().getSelectedItem();
			String navn = txfNavnProd.getText();
			boolean udlejningsprodukt;
			if (cbSalgsprodukt.isSelected()) {
				udlejningsprodukt = false;
			} else {
				udlejningsprodukt = true;
			}
			Controller.getController().updateProduktgruppe(produktgruppe, navn, udlejningsprodukt);
			lvwProduktgrupper.getItems().setAll(Controller.getController().getAllProduktgrupper());
			this.clearAll();
		} catch (Exception e) {
			if (e.getMessage().contains("For input string")) {
				lblError.setText("Manglende information");
			} else {
				lblError.setText(e.getMessage());
			}
		}

	}

	/**
	 * Opdaterer en prisliste efter indtastning af korrekt information i felterne
	 */
	private void opdaterPrislisteAction() {
		try {
			txfNavnPris.setDisable(false);
			String navn = txfNavnPris.getText();
			for (MenuItem mi : mbPrislister.getItems()) {
				if (mi.getText() == prislisteSelected.getNavn()) {
					mi.setText(txfNavnPris.getText());
				}
			}
			Controller.getController().updatePrisliste(prislisteSelected, navn);
			txfNavnPris.clear();
			mbPrislister.getItems().clear();
			opretPrislister();
		} catch (Exception e) {
			if (e.getMessage().contains("For input string")) {
				lblError.setText("Manglende information");
			} else {
				lblError.setText(e.getMessage());
			}
		}
	}

	/**
	 * Sletter en prisliste Viser en alert for at informere om valget
	 */
	private void sletPrislisteAction() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Slet prisliste");
		alert.setHeaderText("Bekræft sletning");
		alert.setContentText("Vil du fjerne prislisten: " + prislisteSelected.getNavn() + "?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Controller.getController().deletePrisliste(prislisteSelected);
			lvwPriser.getItems().clear();
			mbPrislister.getItems().clear();
			opretPrislister();

		}
	}

	/**
	 * Sletter en produktgruppe
	 */
	private void sletGruppeAction() {
		Produktgruppe selected = lvwProduktgrupper.getSelectionModel().getSelectedItem();
		Controller.getController().deleteProduktgruppe(selected);
		lvwProduktgrupper.getItems().setAll(Controller.getController().getAllProduktgrupper());
	}

	/**
	 * Sletter et produkt
	 */
	private void sletProduktAction() {
		Produkt selected = lvwProdukter.getSelectionModel().getSelectedItem();
		Controller.getController().deleteProdukt(selected);
		lvwProdukter.getItems().setAll(Controller.getController().getAllProdukter());
	}

	/**
	 * Åbner et vindue til oprettelse af en gaveæske/sampakning
	 */
	private void opretSampakningAction() {
		this.clearAll();
		SampakningsVindue sv = new SampakningsVindue("Opret en sampakning");
		lvwProdukter.getItems().clear();
		sv.showAndWait();
	}

	private void redigerGruppeAction() {
		this.clearAll();
		txfNavnProd.setDisable(false);
		cbUdlejningsprdukt.setDisable(false);
		cbSalgsprodukt.setDisable(false);
		btnOK1.setDisable(true);
		cbPantprodukt.setDisable(true);
		Produktgruppe produktgruppe = lvwProduktgrupper.getSelectionModel().getSelectedItem();
		lvwProdukter.getItems().setAll(produktgruppe.getProdukter());
	}

	/**
	 * Tilpasser adgang til felter og choiceboxes til oprettelse af en produktgruppe
	 */
	private void nyProduktGruppeAction() {
		this.clearAll();
		txfNavnProd.setDisable(false);
		txfLagerAntal.setDisable(true);
		cbUdlejningsprdukt.setDisable(false);
		cbSalgsprodukt.setDisable(false);
		btnOK1.setDisable(false);
		cbPantprodukt.setDisable(true);
		btnOK1.setDisable(false);
	}

	/**
	 * Tilpasser adgang til felter og choiceboxes til oprettelse af et produkt
	 */
	private void nyProduktAction() {
		this.clearAll();
		txfNavnProd.setDisable(false);
		cbUdlejningsprdukt.setDisable(true);
		cbSalgsprodukt.setDisable(true);
		btnOK1.setDisable(false);
		txfLagerAntal.setDisable(false);
		cbPantprodukt.setDisable(false);
		btnOK1.setDisable(false);
	}

	/**
	 * Tilpasser adgang til felter og choiceboxes til når man trykker på et produkt
	 * på listen
	 */
	private void redigerProduktAction() {
		this.clearAll();
		txfNavnProd.setDisable(false);
		cbUdlejningsprdukt.setDisable(true);
		cbSalgsprodukt.setDisable(true);
		btnOK1.setDisable(true);
		txfLagerAntal.setDisable(false);
		cbPantprodukt.setDisable(true);
	}

	/**
	 * Rydder alle felterne
	 */
	private void clearAll() {
		txfLagerAntal.clear();
		txfNavnPris.clear();
		txfNavnProd.clear();
		txfPantpris.clear();
		txfPris.clear();
		cbSalgsprodukt.setSelected(false);
		cbUdlejningsprdukt.setSelected(false);
		cbPantprodukt.setSelected(false);
		lblError.setText("");
		lblPantpris.setVisible(false);
		txfPantpris.setVisible(false);
	}
}
