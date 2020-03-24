package gui;

import application.controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 
 * @author Sille, Simon og Michelle
 *
 */

public class MainApp extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void init() {
		Controller.getController().initStorage();
	}

	@Override
	public void stop() {
		Controller.getController().saveStorage();
	}

	@Override
	public void start(Stage stage) {
		stage.setTitle("Kasseapparat");

		BorderPane pane = new BorderPane();
		this.initContent(pane);

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.show();
	}

	// ---------------------------------------------------

	private void initContent(BorderPane pane) {
		TabPane tabPane = new TabPane();
		this.initTabPane(tabPane);
		pane.setCenter(tabPane);

		String path = "http://aarhusbryghus.dk/wp-content/uploads/2016/09/cropped-logo2.png";
		Image image1 = new Image(path);
		ImageView logo = new ImageView();
		logo.setImage(image1);
		logo.setFitHeight(90);
		logo.setFitWidth(90);
		pane.getChildren().add(logo);
		logo.relocate(900, 700);
	}

	private void initTabPane(TabPane tabPane) {

		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		// ---------
		Tab tabForside = new Tab("Salg");
		tabPane.getTabs().add(tabForside);

		ForsidePane forsidePane = new ForsidePane();
		tabForside.setOnSelectionChanged(event -> forsidePane.refresh());

		forsidePane.setBackground(new Background(new BackgroundFill(Color.HONEYDEW, CornerRadii.EMPTY, Insets.EMPTY)));
		tabForside.setContent(forsidePane);

		// ---------
		Tab tabOprettelse = new Tab("Oprettelse");
		tabPane.getTabs().add(tabOprettelse);

		OprettelsePane oprettelsePane = new OprettelsePane();
		oprettelsePane
				.setBackground(new Background(new BackgroundFill(Color.HONEYDEW, CornerRadii.EMPTY, Insets.EMPTY)));
		tabOprettelse.setContent(oprettelsePane);

		// --------
		Tab tabStatistik = new Tab("Statistik");
		tabPane.getTabs().add(tabStatistik);

		StatistikPane statistikPane = new StatistikPane();
		tabStatistik.setOnSelectionChanged(event -> statistikPane.refresh());
		statistikPane
				.setBackground(new Background(new BackgroundFill(Color.HONEYDEW, CornerRadii.EMPTY, Insets.EMPTY)));
		tabStatistik.setContent(statistikPane);

		// ---------
		Tab tabKlippekort = new Tab("Klippekort");
		tabPane.getTabs().add(tabKlippekort);

		KlippekortPane klippekortPane = new KlippekortPane();
		tabKlippekort.setOnSelectionChanged(event -> klippekortPane.refresh());
		klippekortPane
				.setBackground(new Background(new BackgroundFill(Color.HONEYDEW, CornerRadii.EMPTY, Insets.EMPTY)));
		tabStatistik.setContent(statistikPane);
		tabKlippekort.setContent(klippekortPane);

		// --------
		Tab tabUdlejning = new Tab("Udlejning");
		tabPane.getTabs().add(tabUdlejning);

		UdlejningPane udlejningPane = new UdlejningPane();
		tabUdlejning.setOnSelectionChanged(event -> udlejningPane.refresh());
		udlejningPane
				.setBackground(new Background(new BackgroundFill(Color.HONEYDEW, CornerRadii.EMPTY, Insets.EMPTY)));
		tabUdlejning.setContent(udlejningPane);

		// ---------
		Tab tabRundvisning = new Tab("Rundvisning");
		tabPane.getTabs().add(tabRundvisning);

		RundvisningPane rundvisningPane = new RundvisningPane();
		rundvisningPane
				.setBackground(new Background(new BackgroundFill(Color.HONEYDEW, CornerRadii.EMPTY, Insets.EMPTY)));
		tabRundvisning.setContent(rundvisningPane);

	}

}
