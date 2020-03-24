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

			String sql = "insert into Produkt(navn, produktgruppenavn, id)" + 
							"values ('" + navn + "', '" + Produktgruppenavn + "', '" + id + "')";
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
				System.out.println("Der er allerede registreret et produkt med dette ID");
				break;
			}

			default:
				System.out.println("fejlSQL:  " + "Der er allerede registreret et produkt under dette navn i den valgte Produktgruppe");
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

			String sql = "select SUM (Belob) AS SamledeSalg\r\n" + 
					"from (\r\n" + 
					"select Produkt.ID, SUM (ISNULL(Salgslinje.aftaltPris,\r\n" + 
					"	CASE\r\n" + 
					"	WHEN Salgslinje.aftaltPris is not null THEN Salgslinje.aftaltpris\r\n" + 
					"	ELSE (Pris.pris * Salgslinje.antal) - (Pris.rabat * Salgslinje.antal)\r\n" + 
					"	end\r\n" + 
					"	)) as Belob\r\n" + 
					"from Salgslinje, Salg, Pris, Produkt\r\n" + 
					"where Salg.SalgslinjeID = Salgslinje.ID AND Salgslinje.PrisID = pris.ID and Pris.produktID = Produkt.id \r\n" + 
					"and Produkt.navn ='" + produkt + "' AND Salg.Dato = '" + dato +"' group by Produkt.id) as Belob";
			ResultSet res= stmt.executeQuery(sql);
			//genneml�ber svaret
			while (res.next()) {
				System.out.println();
				System.out.println("Samlede salg i Kroner for " + produkt + " den " + dato);
				System.out.println(res.getString(1) + " Kroner");
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
