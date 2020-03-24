package gui;

import java.util.ArrayList;

import application.controller.Controller;
import application.model.Gaveæske;
import application.model.Produkt;
import application.model.Produktgruppe;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 
 * @author Sille, Simon og Michelle
 *
 */

public class SampakningsVindue extends Stage {
	private ListView<Produkt> lvwFlasker;
	private ListView<Produkt> lvwGlas;
	private ListView<Produkt> lvwProdukter;
	private ArrayList<Produkt> produkter;
	private Produkt produkt;
	private Produkt glas;
	private int produktantal;
	private int glasantal;
	private TextField txfNavn;
	private TextField txfAntal;
	private Label lblError;

	public SampakningsVindue(String title) {
		produkter = new ArrayList<>();

		setTitle(title);
		setResizable(false);
		initModality(Modality.APPLICATION_MODAL);
		initStyle(StageStyle.UTILITY);

		GridPane pane = new GridPane();
		initContent(pane);

		Scene scene = new Scene(pane);
		setScene(scene);
	}

	private void initContent(GridPane pane) {
		pane.setGridLinesVisible(false);

		pane.setPadding(new Insets(20));
		pane.setHgap(10);
		pane.setVgap(10);

		Label lblBO = new Label("Opret sampakning/gaveæske");
		lblBO.setFont(javafx.scene.text.Font.font("Courier", FontWeight.BOLD, 12));
		pane.add(lblBO, 0, 0);

		Label lblBF = new Label("Tilføj flasker");
		pane.add(lblBF, 0, 1);
		Label lblNavn = new Label("Navn");
		pane.add(lblNavn, 5, 1);
		txfNavn = new TextField();
		pane.add(txfNavn, 5, 2);
		Label lblAntal = new Label("Antal");
		pane.add(lblAntal, 5, 3);
		txfAntal = new TextField();
		pane.add(txfAntal, 5, 4);

		lvwFlasker = new ListView<>();
		pane.add(lvwFlasker, 0, 2, 1, 5);
		lvwFlasker.setPrefWidth(200);
		lvwFlasker.setPrefHeight(200);
		lvwFlasker.setOnMouseClicked(event -> this.setProdukt());

		Label lblProdukter = new Label("Tilføj glas");
		pane.add(lblProdukter, 2, 1);

		lvwGlas = new ListView<>();
		pane.add(lvwGlas, 2, 2, 1, 5);
		lvwGlas.setPrefWidth(200);
		lvwGlas.setPrefHeight(200);
		lvwGlas.setOnMouseClicked(event -> this.setGlas());

		lblError = new Label();
		pane.add(lblError, 5, 5);

		Button btnTilføj = new Button("Tilføj");
		btnTilføj.setOnAction(event -> tilføjAction());
		Button btnFjern = new Button("Fjern produkt");
		btnFjern.setOnAction(event -> fjernAction());
		Button btnAnnuller = new Button("Annuller");
		btnAnnuller.setOnAction(event -> annullerAction());
		Button btnOk = new Button("OK");
		btnOk.setOnAction(event -> okAction());
		HBox hbxBtns = new HBox(30, btnTilføj, btnFjern, btnAnnuller, btnOk);

		pane.add(hbxBtns, 0, 8, 4, 1);
		hbxBtns.setAlignment(Pos.BASELINE_CENTER);

		lvwProdukter = new ListView<>();
		pane.add(lvwProdukter, 0, 10, 4, 5);
		lvwProdukter.setPrefWidth(200);
		lvwProdukter.setPrefHeight(200);
		lvwIndsæt();

	}

	private void setGlas() {
		if (produkt != null) {
			produkt = null;
		}
		glas = lvwGlas.getSelectionModel().getSelectedItem();
	}

	private void setProdukt() {
		if (glas != null) {
			glas = null;
		}
		produkt = lvwFlasker.getSelectionModel().getSelectedItem();

	}

	private void lvwIndsæt() {
		for (Produktgruppe p : Controller.getController().getAllProduktgrupper()) {
			if (p.getNavn().equalsIgnoreCase("Flaske")) {
				lvwFlasker.getItems().setAll(p.getProdukter());
			}
			if (p.getNavn().equalsIgnoreCase("Glas")) {
				lvwGlas.getItems().setAll(p.getProdukter());
			}
		}
	}

	private void tilføjAction() {
		if (produkt != null) {
			produkter.add(produkt);
			produkt.setAntalPåLager(produkt.getAntalPåLager() - 1);
			lvwProdukter.getItems().setAll(produkter);
			produktantal++;
		}

		if (glas != null) {
			produkter.add(glas);
			glas.setAntalPåLager(glas.getAntalPåLager() - 1);
			lvwProdukter.getItems().setAll(produkter);
			glasantal++;
		}
	}

	private void fjernAction() {
		Produkt p = lvwProdukter.getSelectionModel().getSelectedItem();
		if (p != null) {
			produkter.remove(p);
			p.setAntalPåLager(p.getAntalPåLager() + 1);
		}

		lvwProdukter.getItems().setAll(produkter);
	}

	private void annullerAction() {
		for (Produkt p : produkter) {
			p.setAntalPåLager(p.getAntalPåLager() + 1);
		}
		close();
	}

	private void okAction() {
		try {
			String navn = txfNavn.getText().trim();
			int antal = Integer.parseInt(txfAntal.getText().trim());
			Gaveæske g = Controller.getController().createGaveæske(navn, antal, glasantal, produktantal,
					Controller.getController().getSampkningsProduktGruppe());
			Controller.getController().addListToGaveæske(g, produkter);
			close();
		}

		catch (Exception e) {
			lblError.setText(e.getMessage());
			lblError.setTextFill(Color.RED);
		}
	}

}