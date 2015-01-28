package postgresConexion;
/** 
CRUD -> Crear, Leer, Actualizar, Borrar
 **/ 

import java.io.*;
import java.sql.*;
import java.lang.*;
 
public class Crud {
    public static void main(String args[]) {

	String dbUser = "";
	String dbPassword = "";
	try {
	    InputStreamReader inReader = new InputStreamReader(System.in);
	    BufferedReader bReader = new BufferedReader( inReader );
	    System.out.print("Ingrese el usuario de la base de datos: ");
	    dbUser = bReader.readLine();
	    System.out.print("Ingrese su password de la base de datos:");
	    dbPassword = bReader.readLine();
	}
	catch (Exception e) {
	    System.err.println("Error en los datos, vuelva a intentarlo.");
	    e.printStackTrace();
	}

	try {
	    Connection con;
	    Statement stmt;
	    //nos conectamos al host:10.0.0.6, database: prueba
	    String url = 
	       "jdbc:postgresql://10.0.0.6/prueba";
	    // DATABASE CONNECTION
	    Class.forName("org.postgresql.Driver");
	    con = DriverManager.getConnection(url, dbUser, dbPassword);
	    stmt = con.createStatement();

	    /*
	     * student
	     * -name varchar
	     * -id int primary key
	     * -phone varchar
	    */
	    
	    // CREATE
	    stmt.executeUpdate(
		"insert into student values('Barack H. Obama', 2009, '555-1234')");
	    stmt.executeUpdate(
		"insert into student values('George Bush', 2001, '555-1234')");
	    stmt.executeUpdate(
		"insert into student values('George Bush', 1989, '555-1234')");
	    stmt.executeUpdate(
		"insert into student values('William J. Clinton', 1993, '555-1234')");
	    stmt.executeUpdate(
		"insert into student values('George Washington', 1789, '000-0000')");
	    stmt.executeUpdate(
		"insert into student values('John Adams', 1797, '000-0000')");
	    stmt.executeUpdate(
		"insert into student values('John Adams', 1825, '000-0000')");

	    // UPDATE
	    stmt.executeUpdate(
		"update student set name = 'George W. Bush' where id = 2001");
	    stmt.executeUpdate(
		"update student set name = 'George H. W. Bush' where id = 1989");
	    stmt.executeUpdate(
		"update student set name = 'John Q. Adams' where id = 1825");


	    // READ
	    String query = "select name, id, phone from student order by id";
	    System.out.println("Historial de  Estudiantes:");
	    ResultSet rs = stmt.executeQuery(query);
	    while (rs.next()) {
	        String s = rs.getString("name");
	        int i = rs.getInt("id");
	        String p = rs.getString("phone");
	        System.out.println(s + ", " + i + ", " + p);
	    }

	    // DELETE 
	    stmt.executeUpdate("delete from student where phone = '000-0000'");


	    // DATABASE CLOSE/CLEANUP
	    stmt.close();
	    con.close();
	}
	catch(Exception e) {
	    e.printStackTrace();
	}
	finally {

	}
    }
}