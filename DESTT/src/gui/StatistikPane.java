package gui;

import java.util.ArrayList;
import java.util.List;

import application.controller.Controller;
import application.model.Prisliste;
import application.model.Salg;
import application.model.Salgslinje;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * 
 * @author Sille, Simon og Michelle
 *
 */

public class StatistikPane extends GridPane {

	private ListView<Salg> lvwProdukter;
	private ListView<Salg> lvwSalg;
	private ListView<String> lvwProdukterFraGaveæsker;
	private ListView<String> lvwSolgteProdukter;
	private ListView<String> lvwLagerstatus;
	private ListView<Salgslinje> lvwSalgslinjer;
	private DatePicker datoFra, datoTil;
	private ComboBox<Prisliste> cbPrislister;
	private Button btnVis;

	public StatistikPane() {
		this.setPadding(new Insets(30));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		Label lblStatistik = new Label("Statistik/salgsoversigt");
		lblStatistik.setFont(Font.font("Deng Xian Light", FontWeight.BOLD, 42));
		Label lblDagensSalg = new Label("Dagens salg");
		lblDagensSalg.setStyle("-fx-font-weight: bold;");
		Label lblGaveæsker = new Label("Produkter solgt fra gaveæsker");
		lblGaveæsker.setStyle("-fx-font-weight: bold;");
		Label lblSituation = new Label("Salgssituation/arrangement");
		lblSituation.setStyle("-fx-font-weight: bold;");
		Label lblLagerstatus = new Label("Lagerstatus");
		lblLagerstatus.setStyle("-fx-font-weight: bold;");
		Label lblDatoFra = new Label("Fra dato:");
		Label lblDatoTil = new Label("Til dato:");
		Label lblCbPrisliste = new Label("Vælg prisliste:");
		Label solgteprodukter = new Label("Procent solgte produkter:");
		solgteprodukter.setStyle("-fx-font-weight: bold;");
		Label salgslinjer = new Label("Salgslinjen for valgte salg:");
		salgslinjer.setStyle("-fx-font-weight: bold;");

		datoFra = new DatePicker();
		datoFra.setMaxWidth(90);
		datoTil = new DatePicker();
		datoTil.setMaxWidth(90);

		cbPrislister = new ComboBox<Prisliste>();
		cbPrislister.setOnAction(event -> prislisteChanged());
		cbPrislister.setMaxWidth(90);
		cbPrislister.getItems().setAll(Controller.getController().getAllPrislister());

		lvwProdukter = new ListView<>();
		lvwProdukter.setMaxHeight(200);
		lvwProdukter.setMaxWidth(350);
		lvwProdukter.getItems().setAll(Controller.getController().getAllSalg());
		lvwProdukter.setOnMouseClicked(event -> this.salgChanged());

		lvwSalg = new ListView<>();
		lvwSalg.setMaxHeight(250);
		lvwSalg.setMaxWidth(250);
		lvwSalg.getItems().setAll(Controller.getController().getDagensSalg());
		lvwSalg.setOnMouseClicked(event -> this.dagensSalgChanged());

		lvwProdukterFraGaveæsker = new ListView<>();
		lvwProdukterFraGaveæsker.setMaxHeight(250);
		lvwProdukterFraGaveæsker.setMaxWidth(250);

		lvwSolgteProdukter = new ListView<>();
		lvwSolgteProdukter.setMaxHeight(250);
		lvwSolgteProdukter.setMaxWidth(250);

		lvwLagerstatus = new ListView<>();
		lvwLagerstatus.setMaxHeight(300);
		lvwLagerstatus.setMaxWidth(350);
		lvwLagerstatus.getItems().setAll(Controller.getController().lagerStatus());

		btnVis = new Button("Vis");
		btnVis.setOnAction(event -> this.prislisteChanged());

		lvwSalgslinjer = new ListView<>();
		lvwSalgslinjer.setMaxHeight(250);
		lvwSalgslinjer.setMaxWidth(250);

		HBox hb1 = new HBox(10, lblDatoFra, datoFra, lblDatoTil, datoTil, btnVis);
		HBox hb2 = new HBox(10, lblCbPrisliste, cbPrislister);
		VBox vb1 = new VBox(10, lblSituation, hb2, hb1, lvwProdukter, lblLagerstatus, lvwLagerstatus);
		VBox vb2 = new VBox(10, lblDagensSalg, lvwSalg, lblGaveæsker, lvwProdukterFraGaveæsker);
		VBox vb3 = new VBox(10, solgteprodukter, lvwSolgteProdukter, salgslinjer, lvwSalgslinjer);

		this.add(lblStatistik, 0, 0);
		this.add(vb1, 0, 1);
		this.add(vb2, 1, 1);
		this.add(vb3, 2, 1);
	}

	// -------------------------------

	/**
	 * ved skift af prisliste vises alle salg sket gennem prislisten i den valgte
	 * periode
	 */
	private void prislisteChanged() {
		try {
			Prisliste ps = cbPrislister.getValue();
			lvwProdukter.getItems().clear();
			lvwProdukter.getItems()
					.setAll(Controller.getController().alleSalgStatistik(ps, datoFra.getValue(), datoTil.getValue()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void salgChanged() {
		Salg selectedSalg = lvwProdukter.getSelectionModel().getSelectedItem();
		List<Salgslinje> salgslinje = new ArrayList<>();
		salgslinje.addAll(selectedSalg.getSalgslinjer());
		lvwSalgslinjer.getItems().setAll(salgslinje);
	}

	private void dagensSalgChanged() {
		Salg selectedSalg = lvwSalg.getSelectionModel().getSelectedItem();
		List<Salgslinje> salgslinje = new ArrayList<>();
		salgslinje.addAll(selectedSalg.getSalgslinjer());
		lvwSalgslinjer.getItems().setAll(salgslinje);
	}

	public void refresh() {
		lvwLagerstatus.getItems().clear();
		lvwProdukter.getItems().clear();
		lvwProdukterFraGaveæsker.getItems().clear();
		lvwSolgteProdukter.getItems().clear();

		Prisliste ps = cbPrislister.getValue();
		lvwLagerstatus.getItems().setAll(Controller.getController().lagerStatus());
		lvwProdukter.getItems()
				.setAll(Controller.getController().alleSalgStatistik(ps, datoFra.getValue(), datoTil.getValue()));
		lvwProdukterFraGaveæsker.getItems().setAll(Controller.getController().ølSolgtFraGaveæsker());
		lvwSalg.getItems().setAll(Controller.getController().getDagensSalg());
		lvwSolgteProdukter.getItems().setAll(Controller.getController().procentSolgteproukter());

	}
}
