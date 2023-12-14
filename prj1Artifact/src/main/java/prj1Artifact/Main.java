package prj1Artifact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	
	public static Connection connect() {
		
		String url ="jdbc:mysql://localhost:3306/tp_jdbc";
        String username = "root";
        String mdp = "";
        Connection con =null;

        try {
             con= DriverManager.getConnection(url, username, mdp);
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        if(con != null){
            System.out.println("conn reussie");
        }
        else{
            System.out.println("cnx failed");
        }
        
        return con;
		
	}
	public static void getStudents(Connection con) throws SQLException {
		
		String sql = "SELECT * FROM etudiants" ;
		PreparedStatement stm = con.prepareStatement(sql) ;
		ResultSet rs = stm.executeQuery();
		
		while(rs.next()) {
			System.out.println("name " + rs.getString("nom") + "   " + rs.getString("prenom") + "   " + rs.getString("age") );
		}
	}
	
	public static void addStudent(Connection con,String lname,String fname, int age) throws SQLException {
		
		String sql = "insert into etudiants (nom,prenom,age) values(?,?,?)";
		
		PreparedStatement stm = con.prepareStatement(sql) ;
		
		stm.setString(1, lname);
		stm.setString(2, fname);
		stm.setInt(3, age);
		
		int rs = stm.executeUpdate();
		
	}
	
    public static void updateStudent(Connection con,int id ,String lname,String fname, int age) throws SQLException {
		
		String sql = "update  etudiants set nom = ? , prenom = ? , age = ? where id = ? ";
		
		PreparedStatement stm = con.prepareStatement(sql) ;
		
		stm.setString(1, lname);
		stm.setString(2, fname);
		stm.setInt(3, age);
		stm.setInt(4, id);
		
		int rs = stm.executeUpdate();
		
	}
    
    
    public static void deleteStudent(Connection con,int id) throws SQLException {
		
		String sql = "delete from etudiants  where id = ? ";
		
		PreparedStatement stm = con.prepareStatement(sql) ;
		
		stm.setInt(1, id);
		
		int rs = stm.executeUpdate();
		
	}


	public static void main(String[] args) throws SQLException {
		
		Connection con = connect();
		
		System.out.println("Selection");
		
		getStudents(con);
		
		
		//addStudent(con,"zer","aicha",20);
		
       //System.out.println("Selection apres ajout");
		
	    //getStudents(con);
		
		 // updateStudent(con,2,"roooe","jaaane",5);
		
		// System.out.println("Selection apres update");
		
		// getStudents(con);
		
		
		
		deleteStudent(con,2);
		
		System.out.println("Selection apres delete");
		
		getStudents(con);
		
		
		
		
		
		
		
        
        
	}

}
