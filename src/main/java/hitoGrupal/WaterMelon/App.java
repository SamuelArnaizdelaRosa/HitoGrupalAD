package hitoGrupal.WaterMelon;


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
		
		Llamada l1 = new Llamada("quejarse el pesaó" , "Rompió el router", true, true);
		Llamada l2 = new Llamada("Se sigue quejando" , "no ha pasado nada", false, false);
		
		c1.addLlamada(l1);
		c1.addLlamada(l2);
		
		MongoDatabase db = conexion.getDatabase("Watermelon");
		//db.alumnos.find({fechanacimiento:{$gte:new Date(1970,0,1)}})
		
		//FuncionesCRUD.registroNuevoCliente(db, c1, l2);
		
		System.out.println(FuncionesCRUD.encontrarCliente(db, "nombre","Pepe"));
		
	}
}
