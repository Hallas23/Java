package passwordOpgave;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Gui extends Application {
	Button btnLogin, btnOpret, btnscene2;
	Label lblbrugernavn, lblPassword, lblBesked;
	Label lblscene2, lblInfoBruger;
	GridPane pane1, pane2;
	Scene scene1, scene2;
	Stage thestage;
	private PasswordField password = new PasswordField();
	private static TextField brugernavn = new TextField();
	static Connection minConnection;
	static Statement stmt;
	private int counter = 0;

	public static void main(String[] args) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			minConnection = DriverManager.getConnection(
					"jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=password;user=sa;password=tarzan;");
			stmt = minConnection.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		thestage = primaryStage;

		btnLogin = new Button("Log in");
		btnOpret = new Button("Opret");
		btnscene2 = new Button("Tilbage til log in");
		btnLogin.setOnAction(e -> ButtonClicked(e));
		btnOpret.setOnAction(e -> ButtonClicked(e));
		btnscene2.setOnAction(e -> ButtonClicked(e));
		lblbrugernavn = new Label("Navn");
		lblPassword = new Label("Password");
		lblBesked = new Label("Hello World");

		lblscene2 = new Label("Du er nu logget ind");
		lblInfoBruger = new Label("Bruger info");

		pane1 = new GridPane();
		pane2 = new GridPane();
		pane1.setVgap(10);
		pane2.setVgap(10);

		pane1.setStyle("-fx-background-color: yellow;-fx-padding: 10px;");
		pane2.setStyle("-fx-background-color: lightgreen;-fx-padding: 10px;");

		pane1.setPadding(new Insets(5));
		pane1.setHgap(10);
		pane1.setVgap(10);

		pane1.add(lblbrugernavn, 0, 0);
		pane1.add(brugernavn, 0, 1, 2, 1);
		pane1.add(lblPassword, 0, 2);
		pane1.add(password, 0, 3, 2, 1);
		pane1.add(btnLogin, 0, 4);
		pane1.add(btnOpret, 1, 4);
		pane1.add(lblBesked, 0, 5);

		pane2.setPadding(new Insets(5));
		pane2.setHgap(10);
		pane2.setVgap(10);

		pane2.add(lblInfoBruger, 0, 0);
		pane2.add(btnscene2, 0, 1);

		scene1 = new Scene(pane1, 200, 200);
		scene2 = new Scene(pane2, 200, 200);

		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(scene1);
		primaryStage.show();
	}

	public void ButtonClicked(ActionEvent e) {
		if (e.getSource() == btnLogin) {
			if (findBruger()) {
				thestage.setScene(scene2);
			} else {
				System.out.println("Ugyldigt");
			}

		} else if (e.getSource() == btnOpret) {
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			random.nextBytes(salt);
			byte[] hash = {};
			try {
				KeySpec spec = new PBEKeySpec(password.getText().toCharArray(), salt, 65536, 128);
				SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
				hash = factory.generateSecret(spec).getEncoded();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String login = brugernavn.getText();
			int userid = getCounter();

			String sql = "insert into bruger values (" + userid + ", '" + login + "', " + 65536 + ", ?, ?)";
			System.out.println("SQL-streng er " + sql);
			try {
				PreparedStatement statement = minConnection.prepareStatement(sql);
				statement.setBytes(1, salt);
				statement.setBytes(2, hash);
				statement.execute();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setCounter();
			password.clear();
			brugernavn.clear();
			System.out.println("Oprettet");
		} else {
			lblBesked.setText("");
			password.clear();
			brugernavn.clear();
			thestage.setScene(scene1);

		}
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter() {
		counter++;
	}

	public boolean findBruger() {
		try {
			String login = brugernavn.getText();

			String sql = "select username, iterations, salt, Hashpassword from bruger where username = '" + login + "'";
			System.out.println("SQL-streng er " + sql);
			ResultSet res = stmt.executeQuery(sql);
			
			if (!res.next()) {
				System.out.println("heej");
			}
			int i = res.getInt("iterations");
			byte[] bsalt = res.getBytes("salt");
			byte[] bhash = res.getBytes("Hashpassword");
			
			try {
				KeySpec spec = new PBEKeySpec(password.getText().toCharArray(), bsalt, i, 128);
				SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
				byte[] hash = factory.generateSecret(spec).getEncoded();
				boolean equals = true;
				for (int j = 0; j < hash.length && j < bhash.length; j++)  {
					if (hash[j] != bhash[j]) {
						equals = false;
						break;
					}
				}
					
				if (equals) 
					return true;
				

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;

	}
}
