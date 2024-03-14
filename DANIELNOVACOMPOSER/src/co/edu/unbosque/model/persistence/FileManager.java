package co.edu.unbosque.model.persistence;

import java.io.*;

import javax.swing.JFileChooser;

public class FileManager {
	
	private String archivo;
	private JFileChooser selector;
	private File directorio;
	
	public FileManager() {
		selector = new JFileChooser();
		directorio = new File("C:\\data");
		directorio.mkdir();
	}
	
	public int escribirArchivo(String dato, String nombreArchivo) {
		setArchivo(directorio.toString() + "\\" + nombreArchivo + ".txt");
		
		File f = new File(getArchivo());
		
		try {
			FileWriter fw = new FileWriter(f);
			PrintWriter pw = new PrintWriter(fw);
			
			pw.println(dato);
			
			fw.close();
		} catch (IOException e) { return -1; }
		return 0;
	}
	
	public String leerArchivo() {
		String contenido = "", linea = "";
		
		try {
			File f = new File(getArchivo());
			
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			linea = br.readLine();
			while (linea != null) {
				contenido += linea + "\n";
				linea = br.readLine();
			}
			fr.close();
			
		} catch (IOException | NullPointerException e) { return null; }
		return contenido;
	}
	
	public int obtenerArchivo() {
		selector.setDialogTitle("Seleccionar archivo");
		selector.setFileSelectionMode(JFileChooser.FILES_ONLY);
		selector.setCurrentDirectory(getDirectorio());
		if (selector.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			setArchivo(selector.getSelectedFile().toString());
			return 0;
		}
		return -1;
	}
	
	public String seleccionarDirectorio() {
		selector.setDialogTitle("Seleccionar directorio");
		selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		selector.setCurrentDirectory(directorio);
		if (selector.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File directorioSeleccionado = selector.getSelectedFile();
			directorio = directorioSeleccionado;
			return directorioSeleccionado.getAbsolutePath();
		}
		return null;
	}
	
	public String guardarCancion() {
		selector.setDialogTitle("Guardar archivo");
		selector.setCurrentDirectory(directorio);
		if (selector.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File archivoSeleccionado = selector.getSelectedFile();
			try {
				if (archivoSeleccionado.createNewFile()) {
					return archivoSeleccionado.getName();
				}
			} catch (IOException e) {
				return "";
			}
		}
		return "";
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public JFileChooser getSelector() {
		return selector;
	}

	public void setSelector(JFileChooser selector) {
		this.selector = selector;
	}

	public File getDirectorio() {
		return directorio;
	}

	public void setDirectorio(File directorio) {
		this.directorio = directorio;
	}
}
