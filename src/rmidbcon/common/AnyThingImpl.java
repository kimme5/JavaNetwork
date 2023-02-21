package rmidbcon.common;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AnyThingImpl implements AnyThing {

	private String sql;
	private Connection con;
	private ArrayList<String> arrayList = new ArrayList<String>();

	public AnyThingImpl(String sql) {
		this.sql = sql;
	}

	@Override
	public Serializable doThing() {
		// PostgreSQL Database Á¢¼Ó
		String jdbcURL = "jdbc:postgresql://localhost:5432/Retail";
		String userName = "postgres";
		String password = "tjddn!3862";

		try {
			con = DriverManager.getConnection(jdbcURL, userName, password);
			System.out.println("Connected to PostgreSQL server");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(this.sql);
			
			while(rs.next()) {
				arrayList.add(rs.getString("CITY_NAME"));
			}
		} catch (SQLException e) {
			System.err.println("Error in connecting to PostgreSQL server");
			e.printStackTrace();
		} finally {
			try {
				if(con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return arrayList;
	}

}
