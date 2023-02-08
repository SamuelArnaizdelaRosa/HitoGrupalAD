package hitoGrupal.WaterMelon;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;


public class FuncionesCRUD {

	/***
	 * DEVUELVE LA CONEXIÓN CON SERVIDOR Y SI NO EXISTE NINGÚN REGISTRO EN LA
	 * COLECCION INSERTA UNO DE PRUEBA
	 * 
	 * @return CONEXIÓN MONGODB
	 */
	public static MongoClient primeraconexion() {
		String connectionString = "mongodb://localhost:27017";
		MongoClient mongoClient = MongoClients.create(connectionString);

		MongoDatabase db = mongoClient.getDatabase("Watermelon");

		// Date now = new Date();
		// BasicDBObject fechaActual= new BasicDBObject("date",now);

		Document clienteInicial = null;
		if (db.getCollection("Clientes").countDocuments() < 1) {

			Document llamadaInicial = new Document("fechaLlamada", new Date())
					.append("motivoLlamada", "no funciona la linea").append("problema", "hardware")
					.append("reparacionFisica", true).append("solucionado", true);
			
			Document LlamadaInicial2 = new Document("fechaLlamada", new Date())
					.append("motivoLlamada", "Conexión a internet fallida")
					.append("problema", "software").append("reparacionFisica", false)
					.append("solucionado", true);
			
			List<Document> listaLlamadas = new LinkedList<Document>();
			listaLlamadas.add(LlamadaInicial2);
			listaLlamadas.add(llamadaInicial);

			clienteInicial = new Document("nombre", "Pepe").append("apellidos", "Pérez Gonzalez")
					.append("telefono", 699223068).append("llamadas", listaLlamadas);
			db.getCollection("Clientes").insertOne(clienteInicial);
		}
		return mongoClient;
	}

	/***
	 * 
	 * @param Base    de Datos con Mongo
	 * @param Cliente a registrar
	 * @param Llamada a registrar
	 */
	public static void registroNuevoCliente(MongoDatabase db, Cliente c, Llamada l) {

		Document llamadaInicial = new Document("fechaLlamada", new Date())
				.append("motivoLlamada", l.getMotivoLlamada())
				.append("problema", l.getProblema()).append("reparacionFisica", l.isReparacionFisica())
				.append("solucionado", l.isSolucionado());
		
		List<Document> listaLlamadas = new LinkedList<Document>();
		listaLlamadas.add(llamadaInicial);

		Document cliente = new Document("nombre", c.getNombre()).append("apellidos", c.getApellidos())
				.append("telefono", c.getTelefono()).append("llamadas",listaLlamadas);
		db.getCollection("Clientes").insertOne(cliente);
	}

	/***
	 * BUSCA A UN CLIENTE EXISTENTE A TRAVÉS DE SU TELÉFONO
	 * 
	 * @param Base     de datos con Mongo
	 * @param Telefono que buscar
	 * @return DOCUMENTO DEL CLIENTE CON ESE TELEFONO
	 */
	public static Document encontrarClienteTelf(MongoDatabase db, int telefono) {
		MongoCollection<Document> col = db.getCollection("Clientes");
		Document findDocument;
		findDocument = new Document("telefono", telefono);

		FindIterable<Document> resultDocument = col.find(findDocument);

		return resultDocument.first();
	}

	public static void ActualizarCliente(MongoDatabase db, int numeroTelefono, Llamada llamada) {
		Date fecha = new Date();

		Document llamadaNueva = new Document("fechaLlamada", fecha)
				.append("motivoLlamada", llamada.getMotivoLlamada()).append("problema", llamada.getProblema())
				.append("reparacionFisica", llamada.isReparacionFisica())
				.append("solucionado", llamada.isSolucionado());

		db.getCollection("Clientes").updateOne(Filters.eq("telefono", numeroTelefono), Updates.addToSet("llamadas",llamadaNueva));
		System.out.println("\nLlamada añadida correctamente.");
	}
	
	public static void EliminarCliente(MongoDatabase db, int numeroTelefono) {

		System.out.println("--- SISTEMA DE ELIMINACION CLIENTE ---");
		db.getCollection("Clientes").deleteOne(Filters.eq("telefono", numeroTelefono));
	}

}
