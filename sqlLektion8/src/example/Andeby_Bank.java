package example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Andeby_Bank {

	static Connection minConnection;
	static Statement stmt;
	static BufferedReader inLine;
	
	public static void Opgave1() {
	try {
		// Laver sql-s�tning og f�r den udf�rt
		String sql = "select regnr,navn,adresse,tlfnr from afdeling";
		System.out.println("SQL-streng er "+sql);
		System.out.println("RegNr   Afdeling             Adresse                        tlfNr");
		ResultSet res=stmt.executeQuery(sql);
		// genneml�ber svaret
		while (res.next()) {
			String s1, s2, s3;
			int r;
			r  = res.getInt("regnr");
			s1 = res.getString("navn");
			s2 = res.getString("adresse");
			s3 = res.getString("tlfnr");
			System.out.println(r+"    "+s1+" " + s2 + " " + s3);
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
		System.out.println("Indtast cpr (personen skal v�re oprettet p� forh�nd)");
		String cprstr=inLine.readLine();
		// Laver sql-s�tning og f�r den udf�rt
		String sql = "select cprnr, konto.Ktonr from kundeharkonto, konto "
				   + "where kundeharkonto.ktonr = konto.ktonr AND " + "'" + cprstr + "'" + " = kundeharkonto.cprnr";
		System.out.println("SQL-streng er "+ sql);
		System.out.println("cprNr         Kontonummer");
		ResultSet res= stmt.executeQuery(sql);
		//genneml�ber svaret
		while (res.next()) {
			System.out.println(res.getString(1) + "    " + res.getString(2));
		}
		// p�n lukning
 		if (!minConnection.isClosed()) minConnection.close();
		}
		catch (Exception e) {
			System.out.println("fejl:  "+e.getMessage());
		}
	}
	
	public static void opgave3() {
		try {
			// indl�sning
			System.out.println("Vi vil nu oprette en ny medarbejder i Andeby Bank");
			System.out.println("Indtast cpr");
			String cprstr=inLine.readLine();
			System.out.println("Indtast afdelingens registreringsnummer (Afdeling skal v�re oprettet p� forh�nd)");
			String regstr=inLine.readLine();
			System.out.println("Indtast titel");
			String titelstr=inLine.readLine();
			System.out.println("Indtast Navn");
			String navnstr=inLine.readLine();
			System.out.println("Indtast Adresse");
			String adressestr=inLine.readLine();
			System.out.println("Indtast Postnr (Postnr skal v�re oprettet p� forh�nd");
			String poststr=inLine.readLine();
		
			// sender insert'en til db-serveren
			String sql = "insert into medarbejder" + " values (" + cprstr + "," + regstr + "," + "'" + titelstr + "'" + "," 
			                                                     + "'" + navnstr + "'" + ","+ "'" + adressestr + "'" + "," 
					                                             + poststr + ");" ;
			System.out.println("SQL-streng er "+ sql);
			stmt.execute(sql);
			// p�nt svar til brugeren
			System.out.println("Medarbejderen er nu registreret");
			if (!minConnection.isClosed()) minConnection.close();
		}
		catch (SQLException e) {
			        switch (e.getErrorCode())
			        // fejl-kode 547 svarer til en foreign key fejl
			        { case 547 : {if (e.getMessage().contains("regNr"))
										System.out.println("afdelingen er ikke oprettet");
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			inLine = new BufferedReader(new InputStreamReader(System.in));
			//generel ops�tning
			//via native driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			minConnection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Bank;user=sa;password=tarzan;");
			stmt = minConnection.createStatement();
			//Indl�sning og kald af den rigtige metode
			System.out.println("Indtast  ");
			System.out.println("1 for Opgave 1  ");
			System.out.println("2 for Opgave 2  ");
			System.out.println("3 for Opgave 3  ");
			String in=inLine.readLine();
			switch (in)
			{case "1"  : {Opgave1();break;}
			 case "2" :  {Opgave2();break;}
			 case "3"  : {opgave3();break;}
			default : System.out.println("ukendt indtastning"); 
			}
		}
		catch (Exception e) {
			System.out.println("fejl:  "+e.getMessage());
		}
	}
	

}
