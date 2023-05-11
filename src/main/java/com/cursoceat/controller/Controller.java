package com.cursoceat.controller;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.Scanner;

import com.cursoceat.xml.schema.Temperaturas;
import com.cursoceat.xml.schema.Temperaturas.Temperatura;
public class Controller {
static Scanner entrada = new Scanner(System.in);
	
	public static void main(String[] args) throws JAXBException {
		// TODO Auto-generated method stub

		System.out.println("************ MENU ************");
		System.out.println("1- Registrar nueva temperatura");
		System.out.println("2- Mostrar historial de registros");
		System.out.println("3- Salir");
		int opcion = entrada.nextInt();
		switch(opcion) {
		case 1: escribirXML();
		break;
		case 2: leerXML();
		break;
		case 3:{ System.out.println("Hasta luego");
		break;
		}
		}
		
		
	entrada.close();	
	}

	public static void leerXML() throws JAXBException{
		
	File miArchivo= new File("C:\\Users\\PROGRAMACION\\Documentos\\JAVA\\Temperaturas\\registro.xml");
	
	JAXBContext miContexto = JAXBContext.newInstance(Temperaturas.class);
	Unmarshaller miLectura= miContexto.createUnmarshaller();
	
	Temperaturas temperatura =(Temperaturas)miLectura.unmarshal(miArchivo);
	for (Temperatura t: temperatura.getTemperatura()) {
				
		System.out.println(t.getFecha());
		System.out.println(t.getTemperaturaMax());
		System.out.println(t.getTemperaturaMin());
	}
		
	}
	
	public static void escribirXML() throws JAXBException{
		
		Temperaturas miTemperatura = new Temperaturas();
		//temperatura 1
		Temperatura temperatura1 = new Temperatura();
		temperatura1.setFecha("15-01-2020");
		temperatura1.setTemperaturaMax(12);
		temperatura1.setTemperaturaMin(-1);
		miTemperatura.getTemperatura().add(temperatura1);
		
		//temperatura 2
		Temperatura temperatura2 = new Temperatura();
		temperatura2.setFecha("16-01-2020");
		temperatura2.setTemperaturaMax(15);
		temperatura2.setTemperaturaMin(2);
		miTemperatura.getTemperatura().add(temperatura2);
		
		//pregunto datos
		
		System.out.println("Escriba la fecha");
		String fecha= entrada.next();
		System.out.println("Escriba la temperatura máxima");
		int tempMax= entrada.nextInt();
		System.out.println("Escriba la temperatura mínima");
		int tempMin= entrada.nextInt();
		
		int i=3;
		i++;
		Temperatura temperatura3 = new Temperatura();
		temperatura3.setFecha(fecha);
		temperatura3.setTemperaturaMax(tempMax);
		temperatura3.setTemperaturaMin(tempMin);
		miTemperatura.getTemperatura().add(temperatura3);
		
		JAXBContext miContexto = JAXBContext.newInstance(Temperaturas.class);
		Marshaller miLectura = miContexto.createMarshaller();
		miLectura.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
	  //miLectura.marshal(miTemperatura, System.out);
		miLectura.marshal(miTemperatura, new File("C:\\Users\\PROGRAMACION\\Documentos\\JAVA\\Temperaturas\\registro.xml"));
		
	}
}
