package hitoGrupal.WaterMelon;

import org.bson.Document;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		
		//Quitar log
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
		((ch.qos.logback.classic.Logger) rootLogger).setLevel(Level.OFF);
		
		//Conexion
		MongoClient conexion = FuncionesCRUD.primeraconexion();

		MongoDatabase db = conexion.getDatabase("Watermelon");
		

		// MENU PRINCIPAL

		System.out.println("MENÚ PRINCIPAL WATERMELON");

		int menu, telefono;

		do {
			System.out.println("1- Registrar nuevo cliente");
			System.out.println("2- Cliente ya registrado");
			System.out.println("3- Info Llamadas");
			System.out.println("4- Salir de la aplicación");
			menu = Utilidades.pedirEntero("Opción:");

			switch (menu) {
			case 1:
				Cliente c = FuncionesMenu.crearCliente();
				if(FuncionesCRUD.encontrarClienteTelf(db, c.getTelefono())!=null) {					
					System.out.println("Este cliente ya está registrado, comprueba en la opción 2 del menú principal");
				}else {
					System.out.println("Ahora vas a completar una serie de datos sobre la llamada actual");
					Llamada l = FuncionesMenu.crearLlamada();
					FuncionesCRUD.registroNuevoCliente(db, c, l);
					System.out.println("CLIENTE REGISTRADO CON ÉXITO");
					Document clienteRegistrado = FuncionesCRUD.encontrarClienteTelf(db, c.getTelefono());
					FuncionesMenu.mostrarJson(clienteRegistrado);
				}
				break;
			case 2:
				Document clienteEncontrado = FuncionesCRUD.encontrarClienteTelf(db, telefono = Utilidades.pedirEntero("Número de teléfono del cliente:"));
				
				if (clienteEncontrado != null) {
					
					//Poner bonito el JSON
					FuncionesMenu.mostrarJson(clienteEncontrado);
					//EN ESTE MENÚ METED LAS OPERACIONES CON EL CLIENTE
					FuncionesMenu.menuCliente(db, telefono);
				} else {
					System.out.println("***NO SE HA ENCONTRADO NINGUN RESULTADO***");
				}
				break;
			case 3: 
				/**
				 * AÑADIR AQUI LAS OPCIONES DE BÚSQUEDA PERSONALIZADA DE LLAMADAS
				 * METER FUNCIONES EN LA CLASE "FUNCIONESMENU"
				 * +NUMERO TOTAL DE LLAMADAS RECIBIDAS
				 * +LLAMADAS EN UNA FECHA EN CONCRETO
				 * IMPORTANTE--->db.alumnos.find({fechanacimiento:{$gte:new Date(1970,0,1)}})
				 * FILTROS DE LA LLAMADA-->DEPENDE EL PROBLEMA, DEPENDE REPARACION FISICA , DEPENDE SOLUCIÓN
				 */
				break;
			case 4:
				System.out.println("Saliendo del programa...");
				System.exit(1);
			default:
				System.out.println("Opción incorrecta");
				break;
			}
		} while (menu != 4);
	}
}
