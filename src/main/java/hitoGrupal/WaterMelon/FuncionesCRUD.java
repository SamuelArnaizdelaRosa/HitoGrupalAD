package hitoGrupal.WaterMelon;

import java.util.ArrayList;
import java.util.Date;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class FuncionesCRUD {

	public static MongoClient primeraconexion() {
		String connectionString = "mongodb://localhost:27017/";
		MongoClient mongoClient = MongoClients.create(connectionString);
		
		MongoDatabase db = mongoClient.getDatabase("Watermelon");
		
		//Date now = new Date();
		//BasicDBObject fechaActual= new BasicDBObject("date",now);
		
		Document clienteInicial = null;
		if(db.getCollection("Clientes").countDocuments()<1) {

			Document llamadaInicial = new Document("fechaLlamda", new Date())
					.append("motivoLlamada", "no funciona la linea")
					.append("problema", "hardware")
					.append("reparacionFisisa", true)
					.append("solucionado", true);
			
			clienteInicial = new Document("nombre","Pepe")
					.append("apellidos", "Pérez Gonzalez")
					.append("telefono", 699223068)
					.append("llamadas",llamadaInicial);
			db.getCollection("Clientes").insertOne(clienteInicial);
		}		
		return mongoClient;
	}
	
	
	public static boolean registroNuevoCliente(MongoDatabase db, Cliente c, Llamada l) {
		
		Document llamadaInicial = new Document("fechaLlamda", new Date())
				.append("motivoLlamada", l.getMotivoLlamada())
				.append("problema", l.getProblema())
				.append("reparacionFisisa", l.isReparacionFisica())
				.append("solucionado", l.isSolucionado());
		
		Document cliente = new Document("nombre",c.getNombre())
				.append("apellidos", c.getApellidos())
				.append("telefono", c.getTelefono())
				.append("llamadas",llamadaInicial);
		db.getCollection("Clientes").insertOne(cliente);
		return true;
	}
	
	public static String encontrarCliente(MongoDatabase db,String campo,String valor) {
		MongoCollection<Document> col = db.getCollection("Clientes");
		Document findDocument = new Document(campo,valor);
		FindIterable<Document> resultDocument = col.find(findDocument);
		
		return resultDocument.first().toJson();
	}
	
}
