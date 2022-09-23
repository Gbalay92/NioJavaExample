package com.liceolapaz.des.gbl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Principal {
	private static HashMap<Integer, Factura> facturas = new HashMap<>();
	public static void main(String[] args) {
		do {
			escribirMenu();
			int opcion=leerEntero();
			switch(opcion) {
			case 0:
				System.exit(0);
				break;
			case 1:
				//leerFacturas
				pedirRuta();
				String ruta = leerString();
				leerFacturas(ruta);
				break;
			case 2:
				//añadir facturas
				Factura factura= crearFactura();
				if(factura != null) {
					if(facturas.containsKey(factura.getIdFactura())) {
						System.err.println("ya existe un empleado con este dni");
						continue;
					}
					facturas.put(factura.getIdFactura(), factura);
					}
				//System.out.println(facturas);
				break;
			case 3:
				//eliminar factura
				pedirDato("numero de factura: ");
				int idFactura= leerEntero();
				if(!facturas.containsKey(idFactura)) {
					System.err.println("la factura no existe");
					continue;
				}
				facturas.remove(idFactura);
				break;
			case 4:
				//modificar factura
				Factura facturaModificada = crearFactura();
				if(facturas.containsKey(facturaModificada.getIdFactura())) {
					System.err.println("no existe un empleado con este dni");
					continue;
				}
				facturas.replace(facturaModificada.getIdFactura(), facturaModificada);
				break;
			case 5:
				//exportar facturas
				pedirRuta();
				ruta = leerString();
				exportarFacturas(ruta);
				
				break;
			default:
				System.out.println("Opción no valida");
				break;
				
				
			}
			
			
			
			
			
			
			
			
		}while(true);
		
	}
	private static void exportarFacturas(String ruta) {
		try {
			FileWriter fw = new FileWriter(ruta);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			String linea= "";
			linea="LINEA DE FACTURAS";
			pw.println(linea);
			linea="********************";
			pw.println(linea);
			Date fechah = new Date();
			String fecha = new SimpleDateFormat("dd/MM/yyyy").format(fechah);
			linea="Fecha: " + fecha;
			pw.println(linea);
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Date date = new Date();
			linea = "Hora actual: " + dateFormat.format(date);
			pw.println(linea);
			int i=0;
			double importeTotal=0;
			for(Factura factura: facturas.values()) {
				i++;
				importeTotal+=factura.getImporte();
				
			}
			linea = "Numero de facturas: "+ i;
			pw.println(linea);
			DecimalFormat df = new DecimalFormat("#.00");
			linea = "Importe total: " + df.format(importeTotal);
			pw.print(linea);
			linea = "\n********************";
			pw.println(linea);
			i=0;
			for(Factura factura: facturas.values()) {
				i++;
				linea = "********************";
				pw.println(linea);
				linea="FACTURA" + i;
				pw.println(linea);
				linea = "********************";
				pw.println(linea);
				linea ="Numero de factura: "+ factura.getIdFactura();
				pw.println(linea);
				fechah = factura.getFecha();
				fecha = new SimpleDateFormat("dd/MM/yyyy").format(fechah);
				linea = "Fecha: " + fecha;
				pw.println(linea);
				linea= "Numero de cliente: " + factura.getNumeroCliente();
				pw.println(linea);
				linea = "Importe: " +df.format(factura.getImporte());
				pw.println(linea);
				linea ="Forma de pago: " + factura.getPago();

				
			}
			pw.close();
			bw.close();
			fw.close();
			
		} catch (IOException e) {
			System.err.println("Error al escribir fichero");
		}
	}
	private static double leerDouble() {
		Scanner scaner=new Scanner(System.in);
		return scaner.nextDouble();
	}
	private static Factura crearFactura() {
		Factura factura = null;
		pedirDato("Numero de factura: ");
		int idFactura=leerEntero();
		pedirDato("fecha de factura: ");
		String fechaF= leerString();
		Date fecha=null;
		try {
			fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaF);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		pedirDato("numero de cliente: ");
		int numeroCliente=leerEntero();
		pedirDato("importe de factura: ");
		double importe= leerDouble();
		pedirDato("forma de pago: ");
		String pago=leerString();
		factura= new Factura(idFactura, fecha, numeroCliente, importe, pago);
		return factura;
	}
	private static void pedirDato(String dato) {
		System.out.print("escriba " + dato);
		
	}
	private static void leerFacturas(String ruta) {
			File fichero = new File(ruta);
			if (fichero.isFile()) {
				try {
					Scanner escaner = new Scanner(fichero);
				if(escaner.hasNext()) {
					escaner.nextLine();
					escaner.nextLine();
					escaner.nextLine();
					escaner.nextLine();
					escaner.nextLine();
					escaner.nextLine();
					escaner.nextLine();
					escaner.nextLine();
					escaner.nextLine();
					escaner.nextLine();
					String linea;
					String []partes; 
					while(escaner.hasNext()) {
						linea=escaner.nextLine();
						partes = linea.split(": ");
						int idFactura = Integer.parseInt(partes[1]);
						linea=escaner.nextLine();
						partes = linea.split(": ");
						Date fecha= new SimpleDateFormat("dd/MM/yyyy").parse(partes[1]);
						linea=escaner.nextLine();
						partes = linea.split(": ");
						int numeroCliente= Integer.parseInt(partes[1]);
						linea=escaner.nextLine();
						partes = linea.split(": ");
						double importe=Double.parseDouble(partes[1].substring(0,partes[1].length()-6));
						String[] dinero = partes[1].split(",");
						importe+= (Double.parseDouble(dinero[1].substring(0, dinero[1].length()-3)))/100;
						linea=escaner.nextLine();
						partes = linea.split(": ");
						String pago= partes[1];
						Factura factura = new Factura(idFactura, fecha, numeroCliente, importe, pago);
						facturas.put(idFactura, factura);
						if(escaner.hasNext()) {
						escaner.nextLine();
						escaner.nextLine();
						escaner.nextLine();}
						
						
					}
					//System.out.println(facturas);
				}

				} catch (FileNotFoundException e) {
				System.out.println("la ruta no es valida");
				} catch (ParseException e) {
				System.err.println("el formato de fecha no es válido");
				}
				}

	}
		
	private static String leerString() {
		Scanner scaner = new Scanner(System.in);
		return scaner.nextLine();
	}

	private static void pedirRuta() {
		System.out.print("Indique una ruta de fichero: ");
	}
	private static int leerEntero() {
		Scanner scaner=new Scanner(System.in);
		return scaner.nextInt();
	}

	private static void escribirMenu() {
		System.out.print("GESTOR DE FACTURAS\n"
				+ "1. Leer facturas\n"
				+ "2. Añadir Facturas\n"
				+ "3. Eliminar Facturas\n"
				+ "4. Modificar Facturas\n"
				+ "5. Exportar Facturas\n"
				+ "0. Salir\n"
				+ "Escoja una opción: ");
		
	}
}
