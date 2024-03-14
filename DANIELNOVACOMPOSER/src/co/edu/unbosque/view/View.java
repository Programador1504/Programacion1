package co.edu.unbosque.view;

import java.awt.Font;

import javax.swing.*;

public class View {
	
	private JTextArea textArea;
	private JScrollPane scrollPane;
	
	public int obtenerOpcion() {
		int opcion = 0;
		boolean datoValido = false;
		while (!datoValido) {
			String mensaje = "Digite una opción: \n";
			mensaje += "1) Definir número de estrofas y número de frases por estrofa.\n";
			mensaje += "2) Crear canción.\n";
			mensaje += "3) Seleccionar canción.\n";
			mensaje += "4) Salir.\n";
			
			String dato = "";
			try {
				dato = pedirDato(mensaje, "Reguetón Composer");
			} catch (NullPointerException e) { return 4; }
			try {
				opcion = Integer.parseInt(dato);
				if (opcion >= 1 && opcion <= 4)
					datoValido = true;
				else 
					mostrarMensaje("El número ingresado está fuera del rango válido (1 - 4)", "Número inválido");
			} catch (NumberFormatException e) {
				mostrarMensaje("El dato ingresado no es válido", "Entrada no válida");
			}
		}
		return opcion;
	}
	
	public int pedirNumeroEntero(String dato, String titulo) {
		int numero = 0;
		boolean datoValido = false;
		while (!datoValido) {
			String entrada = pedirDato(dato, titulo);
			if (entrada == null) throw new NullPointerException();
			try {
				numero = Integer.parseInt(entrada);
				datoValido = true;
			} catch (NumberFormatException e) {
				mostrarMensaje("El dato ingresado no es válido", "Entrada no válida");
			}
		}
		return numero;
	}
	
	public void mostrarMensaje(String mensaje, String titulo) {
		JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public String pedirDato(String mensaje, String titulo) {
		String dato = "";
		while (dato.equals("")) {
			dato = JOptionPane.showInputDialog(null, mensaje, titulo, JOptionPane.QUESTION_MESSAGE);
			if (dato == null) throw new NullPointerException();
		}
		return dato;
	}
	
	public void mostrarCancion(String cancion, String titulo) {
		textArea = new JTextArea(obtenerAlturaTextArea(cancion), obtenerAnchoTextArea(cancion));
		textArea.setWrapStyleWord(true);
		textArea.setCaretPosition(0);
		textArea.setEditable(false);
		textArea.setFont(new Font("sans-serif", Font.BOLD, 15));
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		textArea.setText(cancion);
		if (cancion.contains("Estrofa #"))
			JOptionPane.showMessageDialog(null, scrollPane, titulo, JOptionPane.PLAIN_MESSAGE);
		else
			mostrarMensaje(cancion, titulo);
	}
	
	private int obtenerAlturaTextArea(String cancion) {
		int altura = 0;
		for (char caracter: cancion.toCharArray())
			if (caracter == '\n') 
				altura++;
		if (altura > 30) altura = 30;
		return altura;
	}
	
	private int obtenerAnchoTextArea(String cancion) {
		double anchoPorLinea = 0, maximoAnchoPorLinea = 0;
		for (char caracter: cancion.toCharArray()) {
			anchoPorLinea += 0.65;
			if (caracter == '\n')
				if (maximoAnchoPorLinea < anchoPorLinea)
					maximoAnchoPorLinea = anchoPorLinea;
				anchoPorLinea = 0; 
		}
		return (int) maximoAnchoPorLinea;
	}
}
