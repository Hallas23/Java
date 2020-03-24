package andebyBank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class JAVAexamples {

	static Connection minConnection;
	static Statement stmt;
	static BufferedReader inLine;
	
	public static void Opgave1() {
	try {
		// Laver sql-s�tning og f�r den udf�rt
		String sql = "select regnr,navn, adresse, tlfnr from Afdeling";
		System.out.println("SQL-streng er "+sql);
		ResultSet res=stmt.executeQuery(sql);
		// genneml�ber svaret
		while (res.next()) {
			System.out.println(res.getString(1) + "    " + res.getString(2) + " " + res.getString(3) + "    " + res.getString(4));
		}
		// p�n lukning
 		if (!minConnection.isClosed()) minConnection.close();
		}
		catch (Exception e) {
			System.out.println("fejl:  "+e.getMessage());
		}
	}
	
	public static void Opgave2() {
	try {
		// Indl�ser s�gestreng
		System.out.println("Indtast kundeCprNr");
		String inString = inLine.readLine();
		// Laver sql-s�tning og f�r den udf�rt
		String sql = "SELECT Kunde.navn, Konto.ktoNr, Konto.saldo\r\n" + 
				"from Konto, Kunde, KundeHarKonto\r\n" + 
				"WHERE " + inString + " = Kunde.cprNr AND " + inString + " = KundeHarKonto.cprNr AND KundeHarKonto.ktonr = Konto.ktoNr;";
		System.out.println("SQL-streng er "+ sql);
		ResultSet res=stmt.executeQuery(sql);
		//genneml�ber svaret
		while (res.next()) {
			System.out.println(res.getString(1) + "    " + res.getString(2) + "       "  + res.getString(3));
		}
		// p�n lukning
 		if (!minConnection.isClosed()) minConnection.close();
		}
		catch (Exception e) {
			System.out.println("fejl:  "+e.getMessage());
		}
	}
	
	
	
	public static void Opgave3() {
		try {
			// indl�sning
			System.out.println("Vi vil nu oprette en ny medarbejder");
			System.out.println("Indtast cpr");
			String cprstr=inLine.readLine();
			System.out.println("Indtast afdeling");
			String afdelingstr=inLine.readLine();
			System.out.println("Indtast titel");
			String titelstr=inLine.readLine();
			System.out.println("Indtast navn");
			String navnstr=inLine.readLine();
			System.out.println("Indtast adresse");
			String adressestr=inLine.readLine();
			System.out.println("Indtast postNr");
			String postnrstr=inLine.readLine();

			
			// sender insert'en til db-serveren
			String sql = "insert into medarbejder values ('" + cprstr + "', '" + afdelingstr + "', '" + titelstr + "', '" + 
			navnstr + "','" + adressestr + "', '" + postnrstr + "')";
			System.out.println("SQL-streng er "+ sql);
			stmt.execute(sql);
			// p�nt svar til brugeren
			System.out.println("Medarbejderen er nu registreret");
			if (!minConnection.isClosed()) minConnection.close();
		}
		catch (SQLException e) {
			        switch (e.getErrorCode())
			        // fejl-kode 547 svarer til en foreign key fejl
			        { case 547 : {if (e.getMessage().contains("cprforeign"))
										System.out.println("cpr-nummer er ikke oprettet");
								  if (e.getMessage().contains("postNr"))
										System.out.println("Postnummeret er ikke oprettet");
								  break;
			        			}
			        // fejl-kode 2627 svarer til primary key fejl
			          case 2627: {System.out.println("den p�g�ldende medarbejder er allerede oprettet");
			          	          break;
			         			 }
			          default: System.out.println("fejlSQL:  "+e.getMessage());
				};
		}
		catch (Exception e) {
			System.out.println("fejl:  "+e.getMessage());
		}
	};
	
	public static void Opgave4() {
		try {
			// indl�sning
			System.out.println("Indtast ktonr (konto skal v�re oprettet p� forh�nd");
			String kontostr=inLine.readLine();
			
			String sql = "select regnr, ktonr, dato,tekst, beløb\r\n" + "from Transaktion\r\n" + 
			"WHERE " + kontostr + " = Transaktion.ktonr;";
	
			
			ResultSet res=stmt.executeQuery(sql);
			ArrayList<Transaktion> trans = new ArrayList<>();
			
			// genneml�ber svaret
			while (res.next()) {
				Transaktion t = new Transaktion(res.getInt(1), res.getInt(2), res.getDate(3), res.getString(4), res.getDouble(5));
				trans.add(t);
			}
			for (Transaktion t: trans) {
				System.out.println(t.toString());
			}
			// p�n lukning
	 		if (!minConnection.isClosed()) minConnection.close();
			}
			catch (Exception e) {
				System.out.println("fejl:  "+e.getMessage());
			}
		}
		
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			inLine = new BufferedReader(new InputStreamReader(System.in));
			//generel ops�tning
			//via native driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			minConnection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=AndeByBank;user=sa;password=tarzan;");
			stmt = minConnection.createStatement();
			//Indl�sning og kald af den rigtige metode
			System.out.println("Indtast  ");
			System.out.println("1 for Opgave 1  ");
			System.out.println("2 for Opgave 2");
			System.out.println("3 for Opgave 3");
			System.out.println("4 for Opgave 4");
			String in=inLine.readLine();
			switch (in)
			{case "1"  : {Opgave1();break;}
			 case "2" : {Opgave2();break;}
			 case "3"  : {Opgave3();break;}
			 case "4"  : {Opgave4();break;}
			default : System.out.println("ukendt indtastning"); 
			}
		}
		catch (Exception e) {
			System.out.println("fejl:  "+e.getMessage());
		}
	}
	

}
