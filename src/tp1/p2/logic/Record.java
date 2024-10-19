package tp1.p2.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import tp1.p2.control.Level;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.RecordException;
import tp1.p2.view.Messages;

public class Record {
	private Level level;
	private int points;
	
	private Record(Level l, int p) throws GameException{
		this.level = l;
		this.points = p;
		createFile(); //Crea el file si no existe
		
	}
	
	public static Record loadRecord(Level level) throws GameException{
		Record r = new Record(level, 0);
		r.readRecord();
		return r;
	}
	
	private void readRecord() throws GameException{
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\luisf\\eclipse-workspace\\PvZ\\src\\record.txt"))) {
			String linea;
		    while ((linea = br.readLine()) != null) { // Mientras lea algo
		        String[] split = linea.split(":"); // Dividimos la l�nea a partir de los ":" en dos partes, nivel y puntuaci�n
		        if (split.length == 2 && split[0].equals(level.name())) { // Verificamos que haya dos partes y el nivel del juego corresponda al escrito en la l�nea
		            points = Integer.parseInt(split[1]); // Modificamos el valor de la puntuaci�n de nuestro r�cord con el del archivo
		            break; // Salimos del bucle una vez encontrado el r�cord correspondiente al nivel
		        }
		    }
		} catch (IOException ioe) {
		    throw new RecordException(Messages.RECORD_READ_ERROR); // Si hay un error al leer el archivo, lanzamos la excepci�n
		}

	}
	
	
	public void createFile() {
		File file = new File("C:\\Users\\luisf\\eclipse-workspace\\PvZ\\src\\record.txt");
		try {
			if (!file.exists()) { // Si no existe el archivo lo crea
				file.createNewFile();
			}
		} catch (IOException e) {
		// Manejar la excepci�n o propagarla si es necesario
		}
	}
	
	public void writeRecord(int puntuacion) throws GameException {
	    String[] records = new String[5];
	    boolean exist = false;
	    points = puntuacion;
	    int index = 0;

	    try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\luisf\\eclipse-workspace\\PvZ\\src\\record.txt"))) {
	        String linea = br.readLine();
	        while (linea != null) {
	            String[] split = linea.split(":");
	            if (split[0].equals(level.name())) {
	                linea = level.name() + ":" + points;
	                exist = true;
	            }
	            records[index++] = linea;
	            linea = br.readLine();
	        }
	        if (!exist) {
	            records[index++] = level.name() + ":" + points;
	        }
	    } catch (IOException ioe) {
	        throw new RecordException(Messages.RECORD_WRITE_ERROR);
	    }

	    try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\luisf\\eclipse-workspace\\PvZ\\src\\record.txt"));
	         PrintWriter pw = new PrintWriter(bw)) {
	        for (int i = 0; i < index; i++) {
	            pw.println(records[i]);
	        }
	    } catch (IOException ioe) {
	        throw new RecordException(Messages.RECORD_WRITE_ERROR);
	    }
	}

	public int getPoints() {
		return this.points;
	}


	
}
