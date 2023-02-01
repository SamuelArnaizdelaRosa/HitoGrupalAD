package hitoGrupal.WaterMelon;

import java.util.Date;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
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
	
	
	public static boolean insertarDocumento() {
		
		return true;
	}
	
}
