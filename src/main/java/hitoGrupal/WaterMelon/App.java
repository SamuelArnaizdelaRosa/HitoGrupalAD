package hitoGrupal.WaterMelon;

import java.util.Date;

import org.bson.Document;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.filter.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
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
		MongoCollection<Document> col = db.getCollection("Clientes");

		// MENU PRINCIPAL
		int menu, telefono;

		do {
			System.out.println("-----MENÚ PRINCIPAL WATERMELON-----");
			System.out.println("1- Registrar nuevo cliente");
			System.out.println("2- Cliente ya registrado");
			System.out.println("3- Info Llamadas");
			System.out.println("4- Info Llamadas por fecha");
			System.out.println("5- Salir de la aplicación");
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
				Document mostrar = new Document("_id",0)
					.append("nombre", 0)
					.append("apellidos", 0)
					.append("telefono", 0)
				;
				
				
				FindIterable<Document> totales = col.find().projection(mostrar);
				System.out.println("Totales: "+col.countDocuments());
				
				Document condicion_fisica = new Document("llamadas.reparacionFisica",true);
				System.out.println("Necesita reparacion fisica: "+col.countDocuments(condicion_fisica));
				
				for (Document resultado : totales) {
					FuncionesMenu.mostrarJson(resultado);
				}
				
				/*Document condicion_NOfisica = new Document("reparacionFisica",true);
				FindIterable<Document> NOfisica = col.find(condicion_NOfisica).projection(mostrar);
				System.out.println("No necesita reparacion fisica: "+FuncionesMenu.contar(NOfisica));
				
				Document condicion_hardware = new Document("problema","hardware");
				FindIterable<Document> hardware = col.find(condicion_hardware).projection(mostrar);
				System.out.println("Hadware: "+FuncionesMenu.contar(hardware));
				
				Document condicion_software = new Document("problema","software");
				FindIterable<Document> software = col.find(condicion_software).projection(mostrar);
				System.out.println("software: "+FuncionesMenu.contar(software));
				
				Document condicion_solucionado = new Document("reparacionFisica",true);	
				FindIterable<Document> solucionado = col.find(condicion_solucionado).projection(mostrar);
				System.out.println("Solucionados: "+FuncionesMenu.contar(solucionado));
				
				Document condicion_NOsoculcionado = new Document("reparacionFisica",true);
				FindIterable<Document> Nosolucionado = col.find(condicion_NOsoculcionado).projection(mostrar);
				System.out.println("No solucionados: "+FuncionesMenu.contar(Nosolucionado));*/
				
				break;
			case 4: 
				int year = Utilidades.pedirYear("Año: ");
				int mes = Utilidades.pedirMes("Mes: ");
				int dia = Utilidades.pedirDia("Dia: ",mes,year);
				
				Document mostrar_date = new Document("llamadas.fechaLlamada", 
						new Document("$gte", new Date(year-1900,mes-1,dia,0,0,0))
						.append("$lte", new Date(year-1900,mes-1,dia,23,59,59))
				);

				FindIterable<Document> resultDocument2 = col.find(mostrar_date);

				for (Document resultado : resultDocument2) {
					FuncionesMenu.mostrarJson(resultado);
				}
				
				break;
			case 5:
				System.out.println("Saliendo del programa...");
				System.exit(1);
			default:
				System.out.println("Opción incorrecta");
				break;
			}
		} while (menu != 5);
	}
}
