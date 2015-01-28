

<?php
/*COMANDOS DE PostgreSQL NECESARIOS
Creamos un usuario username:jose password:123456
	#create user jose with password '123456';

Creación de la Base de Datos "php_postgresql", indicando que jose es el propietario
	#create database php_postgresql owner jose;

Conexión a la Base de Datos “php_postgresql”
	#\c php_postgresql jose localhost 5432;

Creación de la Tabla “Usuario”:
	#create table usuario(id serial primary key, usuario varchar(20) not null, contrasenia varchar(20) not null);
Inserción de Datos para la Tabla “Usuario”:
	#insert into Usuario (usuario, contrasenia) values ('jose', '123456');
	#insert into Usuario (usuario, contrasenia) values ('hector', '654321');
	#insert into Usuario (usuario, contrasenia) values ('jhenny', '134265');

Verificación de Registros de la Tabla “Usuario”:
	#select * from Usuario;

*/


$user = "jose"; //usuario propietario de la base de datos
$password = "123456";
$dbname = "php_postgresql";
$port = "5432";//puerto del servicio de base de datos
$host = "localhost"; //host del servidor de base datos

//Agregamos todos los datos de conexion a la base de datos a una cadena
$cadenaConexion = "host=$host port=$port dbname=$dbname user=$user password=$password";

//Realizamos al conexion con la base de datos, en donde si da un error no da una respuesta de error
$conexion = pg_connect($cadenaConexion) or die("Error en la Conexión: ".pg_last_error());
echo "<h3>Conexion Exitosa PHP - PostgreSQL</h3><hr><br>";

//lenado de datos a la base de datos a partir de un formulario

$nombre = $_POST['nombre'];
$password_usuario = $_POST['password'];
echo $nombre;



//creamos la consuta a la base de datos 
$query = "select id, usuario, contrasenia from usuario";
$query2 = "insert into Usuario (usuario, contrasenia) values ('".$nombre."', '".$password_usuario."');";
$resultado2 = pg_query($conexion, $query2) or die("Error en insertar los datos");
//Ejecutamos la consulta  creada
$resultado = pg_query($conexion, $query) or die("Error en la Consulta SQL");

//Recuperamos cuantas filas tenemos del resultado de la consulta
$numReg = pg_num_rows($resultado);

//dibuja la tabla con los datos recuperados de la base de datos
if($numReg>0){
	echo "<table border='1' align='center'>
	<tr bgcolor='skyblue'>
	<th>ID</th>
	<th>Usuario</th>
	<th>Contrasena</th></tr>";

	//mientras se tenga filas de la consulta seguira mostrando los datos
	while ($fila=pg_fetch_array($resultado)) {
		echo "<tr><td>".$fila['id']."</td>";
		echo "<td>".$fila['usuario']."</td>";
		echo "<td>".$fila['contrasenia']."</td></tr>";
	}
    echo "</table>";
}
else {
    echo "No hay Registros";
}

pg_close($conexion);

?>