/**
 * 
 */
package postgresConexion;

import java.sql.*;
/**
 * @author jose luis
 *
 */
public class JBDPostgreSQL {

	/*Objeto para conexion con la base de datos*/
	Connection conexion = null;
	/*Objeto para ejecucion de consultas sql, y retorno de resultados que estas produzcan*/
	Statement sentencia;
	/*Objeto para obtener las filas que retorne la ejecucion de la consulta sql*/
	ResultSet resultado;
	
	/*URL de la localizacion de la base de datos (jdbc:postgresql://host/nombredelabasededatos)*/
	String url = "jdbc:postgresql://10.0.0.6/postgres";
	
	/*Usuario que se conectara con la base de datos*/
    String usuario = "postgres";
    
    /*Contrasena del usuario*/
    String contrasena = "postgres";
	

	public void ProbarConexion(){
		try {
			/*Cargando el controlador postgresql y registrandolo al JDBC*/
			Class.forName("org.postgresql.Driver");
			
			/*obteniendo una instancia del JDBC y conectando a la base de datos*/
			conexion = DriverManager.getConnection(url, usuario, contrasena);
			
			/*Iniciando el objeto para realizar consultas a la base de datos*/
			sentencia = conexion.createStatement();
			
			/*Realizando la consulta, y obteniendo las filas con los resultados que esta produzca*/
			resultado = sentencia.executeQuery("SELECT VERSION()");
			
			/*Si la consulta no produjo resultados, no se entrara al while*/
			while (resultado.next()) {
				System.out.print(resultado.getString(1));
            }
			
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		} finally {
			/*Cerrando resultados, sentencias y la conexion a la base de datos*/
            try {
                if (resultado != null) {
                	resultado.close();
                }
                if (sentencia != null) {
                	sentencia.close();
                }
                if (conexion != null) {
                	conexion.close();
                }

            } catch (SQLException ex) {
            	ex.printStackTrace();
            }
		}
	}
	
	
	/**
	 *Funcion Principal 
	 */
	public static void main(String[] args) {
		JBDPostgreSQL jbdps = new JBDPostgreSQL();
		jbdps.ProbarConexion();
	}
	

}
