package hitoGrupal.WaterMelon;

import java.util.Arrays;
import java.util.Date;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

public class FuncionesMenu {

	public static void mostrarJson(Document documento) {
		try {
			String resultado = documento.toJson();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonElement je = JsonParser.parseString(resultado);
			String prettyJsonString = gson.toJson(je);
			System.out.println(prettyJsonString);
		}
		catch (Exception e) {
			System.out.println("***Error con el JSON***");
		}
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
	
	public static void menuCliente(MongoDatabase db, int numero) {
		int opcionelegida;
		do {
			System.out.println("-----MENU-----");
			System.out.println("1 - Añadir nueva llamada");
			System.out.println("2 - Eliminar cliente");
			System.out.println("3 - Mostrar llamadas del cliente segun los filtros");
			System.out.println("4 - Mostrar llamadas del cleinte en una fecha");
			System.out.println("5 - Salir del menu");
		
			opcionelegida = Utilidades.pedirEntero("Selecciona una operación: ");
			
			switch (opcionelegida) {
			
			case 1:
				System.out.println("--- SISTEMA DE ACTUALIZACIÓN DE CLIENTE ---");
				FuncionesCRUD.ActualizarCliente(db, numero, crearLlamada());
				break;
			case 2:		
				FuncionesCRUD.EliminarCliente(db, numero);
				System.out.println("Se ha eliminado el cliente con el número de teléfono: "+numero);
				break;
			case 3:	
				Filtros.filtrosLlamadasClientes(db, numero);
				break;
			case 4:	
				int year = Utilidades.pedirYear("Año: ");
				int mes = Utilidades.pedirMes("Mes: ");
				int dia = Utilidades.pedirDia("Dia: ",mes,year);
				
				Filtros.filtrosLlamadasFechaClientes(db,numero, year, mes, dia);
				break;
			}
			
		}while(opcionelegida!=5);	
	}
}
