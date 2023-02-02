package hitoGrupal.WaterMelon;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		MongoClient conexion = FuncionesCRUD.primeraconexion();

		MongoDatabase db = conexion.getDatabase("Watermelon");
		// db.alumnos.find({fechanacimiento:{$gte:new Date(1970,0,1)}})

		// MENU PRINCIPAL

		System.out.println("MENÚ PRINCIPAL WATERMELON");

		int menu;

		do {
			System.out.println("1- Registrar nuevo cliente");
			System.out.println("2- Cliente ya registrado");
			System.out.println("3- Salir de la aplicación");
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
				Document clienteEncontrado = FuncionesCRUD.encontrarClienteTelf(db,
						Utilidades.pedirEntero("Número de teléfono del cliente:"));
				System.out.println("\n***MOSTRANDO DATOS DEL CLIENTE Y SUS LLAMADAS***");
				FuncionesMenu.mostrarJson(clienteEncontrado);
				
				//EN ESTE MENÚ METED LAS OPERACIONES CON EL CLIENTE
				FuncionesMenu.menuCliente();
				break;
			case 3:
				System.out.println("Saliendo del programa...");
				System.exit(1);
			default:
				System.out.println("Opción incorrecta");
				break;
			}
		} while (menu != 3);
	}
}
