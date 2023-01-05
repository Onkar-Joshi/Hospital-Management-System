package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInterface {
	private static Connection con;
	
	public static void DbConnect() 
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver"); //jdbc drivers loaded dynamically
		
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", 
									"root", "");			
		
	}
	
	public static void DbDisconnect() throws SQLException 
			{
		con.close();
	}
	
	public static String getAll() throws SQLException {
		String query = "select * from hospital";
		
		Statement stat = con.createStatement();
		
		ResultSet rs = stat.executeQuery(query);
		
		String output = "";
		output += "\n\n-----------------------------------------------------------------------------------------------------------------------------------------------------\n";
		output += "|ID\t|Patient Name\t|Disease Type\t|Doctor's Name\t|No.of Days Admitted\t|One Day charge\t|Final bill\n";
		output += "---------------------------------------------------------------------------------------------------------------------------------------------------------\n";
		
		while(rs.next()) {
			output += "|" + rs.getInt("patient_id") + 
					"\t|" + rs.getString(2) + 
					"\t\t|" + rs.getString(3) + 
					"\t\t|" + rs.getString(4) + 
					"\t\t|" + rs.getInt(5) + 
					"\t\t|" + rs.getInt(6) + 
					"\t\t|" + rs.getInt(7) + "\t\n";
					
		}
		output += "---------------------------------------------------------------------------------------------------------------------------------------------------------\n";
		
		return output;
	}

	public static String getById(int id) throws SQLException {
		String query = "select * from hospital where patient_id=?";
		
		String output = "";
		
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			output += "\n\n---------------------------------------------------------------------------------------------\n";
			output += "|ID\t|Patient Name\t|Disease Type\t|Doctor's Name\t|No.of Days Admitted\t|One Day charge\t|Final bill\n";
			output += "-------------------------------------------------------------------------------------------------\n";
			
			
			output += "|" + rs.getInt("patient_id") + 
					"\t|" + rs.getString(2) + 
					"\t\t|" + rs.getString(3) + 
					"\t\t|" + rs.getString(4) + 
					"\t\t|" + rs.getInt(5) + 
					"\t\t|" + rs.getInt(6) + 
					"\t\t|" + rs.getInt(7) + "\t\n";
						
			
			output += "--------------------------------------------------------------------------------------------------\n";
		}		
		else
			output += "\n\n **** No Record Found **** \n\n";
		
		return output;
	}

	public static String add(int id, String name, String Diseasetype, String Dname,int da, int od, int fb) 
				throws SQLException {
		String query = "insert into hospital values(?,?,?,?,?,?,?)";
		
		String output = "";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id); //7 que marks
		ps.setString(2, name);
		ps.setString(3, Diseasetype);
		ps.setString(4, Dname);
		ps.setInt(5, da);
		ps.setInt(6, od);
		ps.setInt(7, fb);
		
		int i = ps.executeUpdate();//no.of rows affected
		System.out.println(i);//return 1
		
		if(i>0)
			output += "Insert Successful";
		else
			output += "Insertion Failed";
		
		return output;
	}

	public static String delete(int id) throws SQLException {
		String query = "delete from hospital where patient_id = ?";
		
		String output = "";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		
		int i = ps.executeUpdate(); 
		
		
		if(i>0)
			output += "Delete Successful";
		else 
			output += "Delete Failed";
		
		return output;
	}

	public static String updatePName(int id, String pname) throws SQLException {
		String query = "update hospital set patient_name = ? where patient_id = ?";
		
		String output = "";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, pname);// on 1st que
		ps.setInt(2, id);
		
		int i = ps.executeUpdate();
		
		if(i>0)
			output += "Update Successful";
		else 
			output += "Update Failed";
		
		return output;
	}

	public static String updateda(int id, int da) throws SQLException {
		String query = "update hospital set Days_Admitted = ? where patient_id = ?";
		
		String output = "";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, da);
		ps.setInt(2, id);
		
		int i = ps.executeUpdate();
		
		if(i>0)
			output += "Update Successful";
		else 
			output += "Update Failed";
		
		return output;
	}
	
	public static String updateod(int id, int od) throws SQLException {
		String query = "update hospital set One Day Charge = ? where patient_id = ?";
		
		String output = "";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, od);
		ps.setInt(2, id);
		
		int i = ps.executeUpdate();
		
		if(i>0)
			output += "Update Successful";
		else 
			output += "Update Failed";
		
		return output;
	}
	
	

	public static String updateDt(int id, String Diseasetype) throws SQLException {
String query = "update hospital set Diseasetype = ? where patient_id = ?";
		
		String output = "";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, Diseasetype);
		ps.setInt(2, id);
		
		int i = ps.executeUpdate();
		
		if(i>0)
			output += "Update Successful";
		else 
			output += "Update Failed";
		
		return output;
	}

	public static String updateDname(int id, String Dname) throws SQLException {
String query = "update hospital set Dname = ? where patient_id = ?";
		
		String output = "";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, Dname);
		ps.setInt(2, id);
		
		int i = ps.executeUpdate();
		
		if(i>0)
			output += "Update Successful";
		else 
			output += "Update Failed";
		
		return output;
	}

	public static String getByName(String pname) throws SQLException {
		String query = "select * from hospital where patient_name = ?";
		
		String output = "";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, pname);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			output += "\n\n-------------------------------------------------------------------------------------------------------------------------------------------------\n";
			output += "|ID\t|Patient Name\t|Disease Type\t|Doctor's Name\t|No.of Days Admitted\t|One Day charge\t|Final bill\n";
			output += "-----------------------------------------------------------------------------------------------------------------------------------------------------\n";
			
			
			output += "|" + rs.getInt("patient_id") + 
					"\t|" + rs.getString(2) + 
					"\t\t|" + rs.getString(3) + 
					"\t\t|" + rs.getString(4) + 
					"\t\t|" + rs.getInt(5) + 
					"\t\t|" + rs.getInt(6) + 
					"\t\t|" + rs.getInt(7) + "\t\n";
						
			
			output += "-----------------------------------------------------------------------------------------------------------------------------------------------------\n";
		}		
		else
			output += "\n\n **** No Record Found **** \n\n";
		
		return output;
	}
}
