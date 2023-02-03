package hitoGrupal.WaterMelon;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class FuncionesMenu {

	public static void mostrarJson(Document documento) {
		String resultado = documento.toJson();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonElement je = JsonParser.parseString(resultado);
		String prettyJsonString = gson.toJson(je);
		
		System.out.println(prettyJsonString);
	}
	
	public static Cliente crearCliente() {
		return new Cliente(Utilidades.pedirTexto("Dame el nombre del nuevo cliente:"),
				Utilidades.pedirTexto("Dame los apellidos del cliente:"),
				Utilidades.pedirEntero("Dame el número del cliente:"));
	}
	
	public static Llamada crearLlamada() {
		return new Llamada(Utilidades.pedirTexto("Dame el motivo de la llamada:"),
				Utilidades.pedirTexto("Dame el problema que expone el cliente:"),
				Utilidades.pedirBoolean("¿Ha necesitado reparación física?"),
				Utilidades.pedirBoolean("¿Se ha solucionado el problema?"));
	}
	
	public static void menuCliente() {
		
		/*
		 * METED AQUI LAS OPCIONES DENTRO DEL MENU CLIENTE Y LLAMAD A LAS FUNCIONES CRUD
		 * -AÑADIR NUEVA LLAMADA
		 * -ELIMINAR CLIENTE
		 * -MOSTRAR LLAMADAS DEL CLIENTE SEGÚN FILTRO
		 */
		int opcionelegida;
		do {
			System.out.println("-----MENU-----");
			System.out.println("1 - Añadir nueva llamada");
			System.out.println("2 - Eliminar cliente");
			System.out.println("3 - Mostrar llamadas del cliente segun filtro");
			System.out.println("4 - Salir del menu");
		
			opcionelegida = Utilidades.pedirEntero("Selecciona una operación: ");
			
			switch (opcionelegida) {
			
			case 1:
				
				
				
				
				
				break;
			case 2:
			
				System.out.println("--- SISTEMA DE ELIMINACION CLIENTE ---");
				
				String numero = Utilidades.pedirTexto("Ingresa numero de telefono que identifique al cliente");
				
				//db.getCollection("Clientes").deleteOne(new Document ("telefono", numero));
				
				
				break;
			case 3:
		
				
				break;
			}
			
			
		}while(opcionelegida!=4);
		
	}
	
}
