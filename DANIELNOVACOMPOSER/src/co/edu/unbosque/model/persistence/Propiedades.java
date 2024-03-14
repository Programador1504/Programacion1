package co.edu.unbosque.model.persistence;

import java.io.*;
import java.util.Properties;

public class Propiedades {
	
	private Properties prop = new Properties();
	private String archivoProp = "C:\\data\\archivo.properties";
	
	public int escribirPropiedadesDeCancion(String directorio, String nombreCancion, int numeroEstrofasCancion, int numeroFrasesPorEstrofaCancion) {
		
		setArchivoProp(directorio + "\\" + nombreCancion + ".properties");
		
		try {
			prop.setProperty("nombreArchivo", nombreCancion + ".properties");
			prop.setProperty("nombreCancion", nombreCancion);
			prop.setProperty("numeroEstrofasCancion", numeroEstrofasCancion + "");
			prop.setProperty("numeroFrasesPorEstrofaCancion", numeroFrasesPorEstrofaCancion + "");
			prop.store(new FileOutputStream(this.archivoProp), null);
		} catch (IOException e) { return -1; }
		return 0;
	}
	
	public void imprimirPropiedadesDeCancion() {
		try {
			prop.load(new FileInputStream(this.archivoProp));
			prop.list(System.out);
		} catch (IOException e) { e.printStackTrace(); }
	}

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public String getArchivoProp() {
		return archivoProp;
	}

	public void setArchivoProp(String archivoProp) {
		this.archivoProp = archivoProp;
	}
}
