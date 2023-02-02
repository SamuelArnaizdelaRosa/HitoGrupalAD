package hitoGrupal.WaterMelon;

import java.time.LocalDate;
import java.util.Scanner;

public class Utilidades {

	/**
	 * 
	 * @param mensaje que se mostrar� por pantalla
	 * @return texto para tratar posteriormente
	 */
	public static String pedirTexto(String mensaje) {

		String texto = "";
		boolean error = false;

		Scanner sc = new Scanner(System.in);

		do {
			error = false;
			try {
				System.out.print(mensaje + " ");
				texto = sc.nextLine();
			} catch (Exception e) {
				error = true;
				System.out.println("Valor incorrecto, introduce de nuevo");
			}
		} while (error);

		return texto;
	}

	/**
	 * 
	 * @param mensaje que se mostrar� por pantalla
	 * @return Char a tratar posteriormente
	 */
	public static char pedirChar(String mensaje) {

		char caracter = ' ';
		boolean error = false;

		Scanner sc = new Scanner(System.in);

		do {
			error = false;
			try {
				System.out.print(mensaje + " ");
				String texto = sc.nextLine();
				caracter = texto.charAt(0);
			} catch (Exception e) {
				error = true;
				System.out.println("Valor incorrecto, introduce de nuevo");
			}
		} while (error);

		return caracter;
	}

	/**
	 * 
	 * @param mensaje que se mostrar� por pantalla
	 * @return Entero para tratar posteriormente
	 */
	public static int pedirEntero(String mensaje) {

		int entero = 0;
		boolean error = false;

		Scanner sc = new Scanner(System.in);

		do {
			error = false;
			try {
				System.out.print(mensaje + " ");
				entero = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				error = true;
				System.out.println("Valor incorrecto, introducelo de nuevo");
			}
		} while (error);

		return entero;
	}

	/**
	 * 
	 * @param mensaje que se mostrar� por pantalla
	 * @return Decimal en formato Double
	 */
	public static double pedirDecimal(String mensaje) {
		double entero = 0;
		boolean error = false;

		Scanner sc = new Scanner(System.in);

		do {
			error = false;
			try {
				System.out.print(mensaje + " ");
				entero = Double.parseDouble(sc.nextLine());
			} catch (Exception e) {
				error = true;
				System.out.println("Valor incorrecto, introducelo de nuevo");
			}
		} while (error);

		return entero;
	}

	/**
	 * 
	 * @param mensaje que se mostrar� por pantalla
	 * @return A�o para tratar posteriormente
	 */
	public static int pedirYear(String mensaje) {
		int year;
		do {
			year = Utilidades.pedirEntero("Dame el a�o: ");
			if (year > LocalDate.now().getYear() || year < 2000) {
				System.out.println("El a�o tiene que estar entre 2000 y el a�o actual");
			}
		} while (year > LocalDate.now().getYear() || year < 2000);
		return year;
	}

	/**
	 * 
	 * @param mensaje que se mostrar� por pantalla
	 * @return Mes para tratar posteriormente
	 */
	public static int pedirMes(String mensaje) {
		int mes;
		do {
			mes = Utilidades.pedirEntero("Dame el mes");
			if (mes > 12 || mes <= 0) {
				System.out.println("El mes tiene que estar entre 1 y 12");
			}
		} while (mes > 12 || mes <= 0);
		return mes;
	}

	/**
	 * 
	 * @param mensaje que se muestra por pantalla
	 * @param mes     (necesario para concretar cuandos dias tiene el mes)
	 * @param year    (necesario para saber si es a�o bisiesto)
	 * @return Dia para tratar posteriormente
	 */
	public static int pedirDia(String mensaje, int mes, int year) {
		int dia, top;
		if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
			top = 31;
		} else if (mes == 2) {
			if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
				top = 29;
			} else
				top = 28;
		} else
			top = 30;

		do {
			dia = Utilidades.pedirEntero(mensaje);
			if (dia > top || dia < 0) {
				System.out.println("El d�a tiene que estar entre 1 y " + top);
			}
		} while (dia > top || dia < 0);
		return dia;
	}

	/***
	 * 
	 * @param mensaje que se mostrará por pantalla añadiendo la opción de S|N
	 * @return boolean
	 */
	public static boolean pedirBoolean(String mensaje) {
		String respuesta;
		do {
			respuesta = Utilidades.pedirTexto(mensaje + " S|N :");
			if(respuesta.trim().equalsIgnoreCase("S") || respuesta.trim().equalsIgnoreCase("N")) {
				
			}else {
				System.out.println("Valor incorrecto, introducelo de nuevo");
			}
		} while (!respuesta.trim().equalsIgnoreCase("S") && !respuesta.trim().equalsIgnoreCase("N"));
		if (respuesta.equalsIgnoreCase("S")) {
			return true;
		} else {
			return false;
		}
	}
}
