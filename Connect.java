package movies;

import java.sql.Connection;
import java.sql.DriverManager;  
import java.sql.SQLException;
import java.sql.Statement;  
import java.sql.ResultSet; 


public class Connect {  
	//connecting to database
	public static void connect() {  
		Connection conn = null;  
		try {  
			// db parameters  
			String url = "jdbc:sqlite:C:/sqlite/MULESOFT.db";  
			// create a connection to the database  
			conn = DriverManager.getConnection(url);
			
			System.out.println("Connection Established");  
             
		} catch (SQLException e) {  
			System.out.println(e.getMessage());  
		}
	}  
	
	//creating table
	public static void createTable() {
		//database
		String url = "jdbc:sqlite:C://sqlite/MULESOFT.db";
		//SQL Query
		String sql = "CREATE TABLE IF NOT EXISTS movies(\n"
				+"id integer PRIMARY KEY,\n"
				+"Name text NOT NULL,\n"
				+"Actor text NOT NULL,\n"
				+"Actress text NOT NULL,\n"
				+"Year integer NOT NULL,\n"
				+"Director text NOT NULL\n"
				+");";
		try {
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			System.out.println("Movie Table Created Successfully");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void insertRecord(int id,String name, String actor, String actress, int year, String director) {
		//database 
		String url = "jdbc:sqlite:C://sqlite/MULESOFT.db";
		//SQL Query
		String sql = "INSERT INTO movies(id, Name, Actor, Actress, Year, Director) \n"
				+"VALUES ("+id+",'"+name+"','"+actor+"','"+actress+"',"+year+",'"+director+"');";
//		System.out.println(sql);
		try {
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			System.out.println("Inserted 1 Record in Movie Table");
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
	}

	public static void selectRecord() {
		//database
		String url = "jdbc:sqlite:C://sqlite/MULESOFT.db";
		//sql
		String sql = "SELECT * FROM movies";
		try {
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("id \t Name \t\t Actor \t\t Actress \t\t Year \t Director");
			while(rs.next()) {
				System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getString("Actor")+"\t"+rs.getString("Actress")+"\t"+rs.getInt("Year")+"\t"+rs.getString("Director"));
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void selectRecord(String actor) {
		//database
				String url = "jdbc:sqlite:C://sqlite/MULESOFT.db";
				//sql
				String sql = "SELECT * FROM movies WHERE Actor='"+actor+"';";
				try {
					Connection conn = DriverManager.getConnection(url);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					System.out.println("id \t Name \t\t Actor \t\t Actress \t\t Year \t Director");
					while(rs.next()) {
						System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getString("Actor")+"\t"+rs.getString("Actress")+"\t"+rs.getInt("Year")+"\t"+rs.getString("Director"));
					}
				}catch(SQLException e) {
					System.out.println(e.getMessage());
				}
	}
	
	public static void main(String[] args) {  
		connect();  
		createTable();
		insertRecord(1,"Red Notice", "Dwayne Johnsan", "Gal Gadot", 2021, "Rawson Marshall Thurber");
		insertRecord(2,"Catch Me If You Can", "Leonardo DiCaprio", "Amy Adams", 2002, "Steven Spielberg");
		insertRecord(3,"Andhadhun", "Ayushman Khurrana", "Tabu", 2018, "Sriram Raghavan");
		insertRecord(4,"Partner", "Salman Khan", "Lara Dutta", 2007, "David Dhawan");
		insertRecord(5,"PK", "Amir Khan", "Anushka Sharma", 2014, "Rajkumar Hirani");
		selectRecord();
		selectRecord("Salman Khan");
	}  
}  


