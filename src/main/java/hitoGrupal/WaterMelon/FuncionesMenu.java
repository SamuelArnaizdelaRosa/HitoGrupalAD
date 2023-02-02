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
		 */
	}
	
}
