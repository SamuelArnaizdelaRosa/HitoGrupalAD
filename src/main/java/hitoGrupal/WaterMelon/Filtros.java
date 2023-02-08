package hitoGrupal.WaterMelon;

import java.util.Arrays;
import java.util.Date;

import org.bson.Document;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

public class Filtros {
	public static void filtrosLlamadas(MongoDatabase db) {
		MongoCollection<Document> col = db.getCollection("Clientes");
		
		//Filtros Totales
		AggregateIterable<Document> totales = col.aggregate(
		    Arrays.asList(
		        Aggregates.unwind("$llamadas"),
		        Aggregates.count("resultado")
		    )
		);
		for (Document resultado : totales) {
			System.out.println("Llamadas totales: "+resultado.get("resultado"));
		}
		
		//Filtros hardware
		AggregateIterable<Document> hardware = col.aggregate(
		    Arrays.asList(
		        Aggregates.unwind("$llamadas"),
		        Aggregates.match(Filters.eq("llamadas.problema","hardware")),
		        Aggregates.count("resultado")
		    )
		);
		for (Document resultado : hardware) {
			System.out.println("Llamadas por problema de hadrdware: : "+resultado.get("resultado"));
		}
		
		//Filtros SoftWare
		AggregateIterable<Document> software = col.aggregate(
		    Arrays.asList(
		        Aggregates.unwind("$llamadas"),
		        Aggregates.match(Filters.eq("llamadas.problema","software")),
		        Aggregates.count("resultado")
		    )
		);
		for (Document resultado : software) {
			System.out.println("Llamadas por problema de software: "+resultado.get("resultado"));
		}
		
		//Filtros Reparacion Fisica
		AggregateIterable<Document> fisica_si = col.aggregate(
		    Arrays.asList(
		        Aggregates.unwind("$llamadas"),
		        Aggregates.match(Filters.eq("llamadas.reparacionFisica",true)),
		        Aggregates.count("resultado")
		    )
		);
		for (Document resultado : fisica_si) {
			System.out.println("Llamadas por reparacion fisica: "+resultado.get("resultado"));
		}
		
		AggregateIterable<Document> fisica_no = col.aggregate(
		    Arrays.asList(
		        Aggregates.unwind("$llamadas"),
		        Aggregates.match(Filters.eq("llamadas.reparacionFisica",false)),
		        Aggregates.count("resultado")
		    )
		);
		for (Document resultado : fisica_no) {
			System.out.println("Llamadas que no necesitan reparacion fisica: "+resultado.get("resultado"));
		}
		
		//Filtros Solucionado
		AggregateIterable<Document> solucionado_si = col.aggregate(
		    Arrays.asList(
		        Aggregates.unwind("$llamadas"),
		        Aggregates.match(Filters.eq("llamadas.solucionado",true)),
		        Aggregates.count("resultado")
		    )
		);
		
		for (Document resultado : solucionado_si) {
			System.out.println("Llamadas solucionadas: "+resultado.get("resultado"));
		}
		
		AggregateIterable<Document> solucionado_no = col.aggregate(
		    Arrays.asList(
		        Aggregates.unwind("$llamadas"),
		        Aggregates.match(Filters.eq("llamadas.solucionado",false)),
		        Aggregates.count("resultado")
		    )
		);
		
		for (Document resultado : solucionado_no) {
			System.out.println("Llamadas no solucionadas: "+resultado.get("resultado"));
		}
	}
	
	public static void filtrosLlamadasFecha(MongoDatabase db,int year,int mes,int dia) {
		MongoCollection<Document> col = db.getCollection("Clientes");
		
		//Filtros fecha
		AggregateIterable<Document> totales = col.aggregate(
		    Arrays.asList(
		        Aggregates.unwind("$llamadas"),
		        Aggregates.match(Filters.and(
		        		Filters.gte("llamadas.fechaLlamada",new Date(year-1900,mes-1,dia,0,0,0)),
		        		Filters.lte("llamadas.fechaLlamada",new Date(year-1900,mes-1,dia,23,59,59))
		        ))
		    )
		);
		
		for (Document resultado : totales) {
			FuncionesMenu.mostrarJson(resultado);
		}
	}
	
	public static void filtrosLlamadasClientes(MongoDatabase db, int telefono) {
		MongoCollection<Document> col = db.getCollection("Clientes");
		
		//Filtros Totales
		AggregateIterable<Document> totales = col.aggregate(
		    Arrays.asList(
		        Aggregates.unwind("$llamadas"),
		        Aggregates.match(Filters.eq("telefono",telefono)),
		        Aggregates.count("resultado")
		    )
		);
		for (Document resultado : totales) {
			System.out.println("Llamadas totales: "+resultado.get("resultado"));
		}
		
		//Filtros hardware
		AggregateIterable<Document> hardware = col.aggregate(
		    Arrays.asList(
		        Aggregates.unwind("$llamadas"),
		        Aggregates.match(Filters.and(
		        		Filters.eq("telefono",telefono),
		        		Filters.eq("llamadas.problema","hardware")
		        )),
		        Aggregates.count("resultado")
		    )
		);
		for (Document resultado : hardware) {
			System.out.println("Llamadas por problema de hadrdware: : "+resultado.get("resultado"));
		}
		
		//Filtros SoftWare
		AggregateIterable<Document> software = col.aggregate(
		    Arrays.asList(
		        Aggregates.unwind("$llamadas"),
		        Aggregates.match(Filters.and(
		        		Filters.eq("telefono",telefono),
		        		Filters.eq("llamadas.problema","software")
		        )),
		        Aggregates.count("resultado")
		    )
		);
		for (Document resultado : software) {
			System.out.println("Llamadas por problema de software: "+resultado.get("resultado"));
		}
		
		//Filtros Reparacion Fisica
		AggregateIterable<Document> fisica_si = col.aggregate(
		    Arrays.asList(
		        Aggregates.unwind("$llamadas"),
		        Aggregates.match(Filters.and(
		        		Filters.eq("telefono",telefono),
		        		Filters.eq("llamadas.reparacionFisica",true)
		        )),
		        Aggregates.count("resultado")
		    )
		);
		for (Document resultado : fisica_si) {
			System.out.println("Llamadas por reparacion fisica: "+resultado.get("resultado"));
		}
		
		AggregateIterable<Document> fisica_no = col.aggregate(
		    Arrays.asList(
		        Aggregates.unwind("$llamadas"),
		        Aggregates.match(Filters.and(
		        		Filters.eq("telefono",telefono),
		        		Filters.eq("llamadas.reparacionFisica",false)
		        )),
		        Aggregates.count("resultado")
		    )
		);
		for (Document resultado : fisica_no) {
			System.out.println("Llamadas que no necesitan reparacion fisica: "+resultado.get("resultado"));
		}
		
		//Filtros Solucionado
		AggregateIterable<Document> solucionado_si = col.aggregate(
		    Arrays.asList(
		        Aggregates.unwind("$llamadas"),
		        Aggregates.match(Filters.and(
		        		Filters.eq("telefono",telefono),
		        		Filters.eq("llamadas.solucionado",true)
		        )),
		        Aggregates.count("resultado")
		    )
		);
		
		for (Document resultado : solucionado_si) {
			System.out.println("Llamadas solucionadas: "+resultado.get("resultado"));
		}
		
		AggregateIterable<Document> solucionado_no = col.aggregate(
		    Arrays.asList(
		        Aggregates.unwind("$llamadas"),
		        Aggregates.match(Filters.and(
		        		Filters.eq("telefono",telefono),
		        		Filters.eq("llamadas.solucionado",false)
		        )),
		        Aggregates.count("resultado")
		    )
		);
		
		for (Document resultado : solucionado_no) {
			System.out.println("Llamadas no solucionadas: "+resultado.get("resultado"));
		}
	}
	
	public static void filtrosLlamadasFechaClientes (MongoDatabase db,int telefono,int year,int mes,int dia) {
		MongoCollection<Document> col = db.getCollection("Clientes");
		
		//Filtros Fechas
		AggregateIterable<Document> totales = col.aggregate(
		    Arrays.asList(
		        Aggregates.unwind("$llamadas"),
		        Aggregates.match(Filters.and(
		        		Filters.eq("telefono",telefono),
		        		Filters.gte("llamadas.fechaLlamada",new Date(year-1900,mes-1,dia,0,0,0)),
		        		Filters.lte("llamadas.fechaLlamada",new Date(year-1900,mes-1,dia,23,59,59))
		        ))
		    )
		);
		
		for (Document resultado : totales) {
			FuncionesMenu.mostrarJson(resultado);
		}
	}
}
