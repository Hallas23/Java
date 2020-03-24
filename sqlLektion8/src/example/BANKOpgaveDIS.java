package example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BANKOpgaveDIS {

	static Connection minConnection;
	static Statement stmt;
	static BufferedReader inLine;

	

	public static void Overførsel() {
		try {
			stmt.execute("set transaction isolation level serializable");
			stmt.execute("begin tran");
			// indl�sning
			System.out.println("fraKontoNR");
			String frastr = inLine.readLine();
			Boolean frab = false;
			Boolean tilb = false;
			int saldo = 0;
//			 sender insert'en til db-serveren

			String frasql = "select konto.Ktonr, konto.saldo from konto " + "where konto.ktonr = konto.ktonr AND " + "'"
					+ frastr + "'" + " = konto.ktonr";
			ResultSet frares = stmt.executeQuery(frasql);
			while (frares.next()) {
				frab = true;
				int ktonr;
				ktonr = frares.getInt("ktonr");
				saldo = frares.getInt("saldo");
				System.out.println(ktonr + "    " + saldo);
			}
			if (!frab)
				System.out.println("fra kunde findes ikke");

			if (frab) {
				System.out.println("tilKontoNR");
				String tilstr = inLine.readLine();
				String tilsql = "select konto.Ktonr, konto.saldo from konto " + "where konto.ktonr = konto.ktonr AND "
						+ "'" + tilstr + "'" + " = konto.ktonr";
				ResultSet tilres = stmt.executeQuery(tilsql);
				while (tilres.next()) {
					tilb = true;
					int ktonr;
					int saldo2;
					ktonr = tilres.getInt("ktonr");
					saldo2 = tilres.getInt("saldo");
					System.out.println(ktonr + "    " + saldo2);
				}
				if (tilb) {
					System.out.println("beløb");
					String beløbstr = inLine.readLine();
					int beløbstri = Integer.parseInt(beløbstr);
					if (beløbstri > saldo) {
						System.out.println("Beløbet er over saldoen");
						stmt.execute("rollback tran");
						if (!minConnection.isClosed())
							minConnection.close();
					}

					String updfrasql = "update konto set saldo = saldo - " + "'" + beløbstr + "'"
							+ "where konto.ktonr = konto.ktonr AND " + "'" + frastr + "'" + " = konto.ktonr";
					stmt.execute(updfrasql);
					String tilfrasql = "update konto set saldo = saldo + " + "'" + beløbstr + "'"
							+ "where konto.ktonr = konto.ktonr AND " + "'" + tilstr + "'" + " = konto.ktonr";
					stmt.execute(tilfrasql);
					// Kunder select
					frares = stmt.executeQuery(frasql);
					while (frares.next()) {
						frab = true;
						int ktonr;
						ktonr = frares.getInt("ktonr");
						saldo = frares.getInt("saldo");
						System.out.println(ktonr + "    " + saldo);
					}

					tilres = stmt.executeQuery(tilsql);
					while (tilres.next()) {
						tilb = true;
						int ktonr;
						int saldo2;
						ktonr = tilres.getInt("ktonr");
						saldo2 = tilres.getInt("saldo");
						System.out.println(ktonr + "    " + saldo2);
					}

				}

				if (!tilb)
					System.out.println("til kunde findes ikke");
			}
			stmt.execute("commit tran");
			if (!minConnection.isClosed())
				minConnection.close();
		} catch (SQLException e) {
			switch (e.getErrorCode())
			// fejl-kode 547 svarer til en foreign key fejl
			{
			default:
				System.out.println("fejlSQL:  " + e.getMessage());
			}
			;
		} catch (Exception e) {
			System.out.println("fejl:  " + e.getMessage());
		}
	};

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			inLine = new BufferedReader(new InputStreamReader(System.in));
			// generel ops�tning
			// via native driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			minConnection = DriverManager
					.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=BANK;user=sa;password=tarzan;");
			stmt = minConnection.createStatement();
			// Indl�sning og kald af den rigtige metode
			
				Overførsel();
		} catch (Exception e) {
			System.out.println("fejl:  " + e.getMessage());
		}
	}

}
