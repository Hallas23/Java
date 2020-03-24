package aarhusbryghus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainApp {
	static Connection minConnection;
	static Statement stmt;
	static BufferedReader inLine;

	// Opgave 6a
	// Programmet skal lave en oprettelse af et nyt produkt.
	// Programmet skal svare med forståelige og præcise fejlreaktioner,
	// hvis oprettelsen af den ene eller anden grund ikke kan lade sig gøre.
	public static void opretProdukt() {
		try {
			System.out.println("Vi ønsker at oprette et nyt produkt");
			System.out.println("Indtast navnet på produktet");
			String navn = inLine.readLine();
			System.out.println("Indtast ønskede produktgruppe (Produktgruppen skal være oprettet på forhånd)");
			String Produktgruppenavn = inLine.readLine();
			System.out.println("Indtast et ID");
			String id = inLine.readLine();

			String sql = "insert into Produkt values ('" + navn + "', '" + Produktgruppenavn + "', '" + id + "')";
			System.out.println("SQL-streng er " + sql);
			stmt.execute(sql);

			System.out.println("Produktet er nu oprettet");
			if (!minConnection.isClosed())
				minConnection.close();

		} catch (SQLException e) {
			switch (e.getErrorCode())
			// fejl-kode 547 svarer til en foreign key fejl
			{

			case 547: {
				if (e.getMessage().contains("Produktgruppe")) 
					System.out.println("Produktgruppen er ikke oprettet");

				break;
			}

			// fejl-kode 2627 svarer til primary key fejl
			case 2627: {
				System.out.println("Der er allerede registreret et produkt under dette navn");
				break;
			}

			default:
				System.out.println("fejlSQL:  " + e.getMessage());
			}
			;

		} catch (Exception e) {
			System.out.println("fejl:  " + e.getMessage());
		}
	};

	// Opgave 6b
	// Programmet skal vise det samlede salg i kr. på et givet produkt på en given
	// dato. I denne delopgave kræves det ikke, at der kontrolleres for ikke
	// eksisterende produkter eller forkert datoformat.
	public static void samletSalg() {
		try {
			System.out.println("Indtast produkt");
			String produkt = inLine.readLine();
			System.out.println("Indtast dato");
			String dato = inLine.readLine();

			String sql = "exec totalePris where produkt like '" + produkt + "' and where dato like '" + dato + "'";
			System.out.println("SQL-streng er " + sql);
			ResultSet res = stmt.executeQuery(sql);

			while (res.next()) {
				System.out.println(res.getString(1) + "    " + res.getString(2));
			}

			if (!minConnection.isClosed())
				minConnection.close();

		} catch (Exception e) {
			System.out.println("fejl:  " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		try {
			inLine = new BufferedReader(new InputStreamReader(System.in));
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			minConnection = DriverManager.getConnection(
					"jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=AarhusBryghus2;user=sa;password=tarzan;");
			stmt = minConnection.createStatement();

			System.out.println("Indtast  ");
			System.out.println("o for at oprette et nyt produkt");
			System.out.println("s for at se det samlede salg i kr. på et givet produkt på en given dato");
			String in = inLine.readLine();

			switch (in) {
			case "o": {
				opretProdukt();
				break;
			}

			case "s": {
				samletSalg();
				break;
			}

			default:
				System.out.println("ukendt indtastning");
			}

		} catch (Exception e) {
			System.out.println("fejl:  " + e.getMessage());
		}
	}

}
