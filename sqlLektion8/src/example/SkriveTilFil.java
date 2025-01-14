package example;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SkriveTilFil {
	
	public static void main(String[] args) {
		try {
			String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());			
			System.out.println(timeStamp);
			PrintWriter printWriter = 
			new PrintWriter("C:\\Users\\Simon\\Documents\\Datamatiker\\DAOS\\FilSti\\FilSti" 
			+ timeStamp + ".txt");						
			
			Connection minConnection;
			minConnection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=AndeByBank;user=sa;password=tarzan;");

			Statement stmt = minConnection.createStatement();

			ResultSet res = stmt.executeQuery("select * from postdistrikt");
			while (res.next()) {
				printWriter.println(res.getString(1) + "    " + res.getString(2));
				printWriter.flush();				
			}
			printWriter.close();
			if (res != null)
				res.close();
			if (stmt != null)
				stmt.close();
			if (minConnection != null)
				minConnection.close();
		} catch (Exception e) {
			System.out.println("fejl:  " + e.getMessage());
		
		}
	}
}
