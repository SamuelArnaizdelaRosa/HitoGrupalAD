package hitoGrupal.WaterMelon;

import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		
		MongoClient conexion = FuncionesCRUD.primeraconexion();
		
		
		Cliente c1 = new Cliente("Pepe", "Perez", 699223068);
		
		Llamada l1 = new Llamada(new Date(),"quejarse el pesaó" , "Rompió el router", true, true);
		Llamada l2 = new Llamada(new Date(),"Se sigue quejando" , "no ha pasado nada", false, false);
		
		c1.addLlamada(l1);
		c1.addLlamada(l2);
		
		MongoDatabase db = conexion.getDatabase("Watermelon");
		//db.alumnos.find({fechanacimiento:{$gte:new Date(1970,0,1)}})
		
	}
}
