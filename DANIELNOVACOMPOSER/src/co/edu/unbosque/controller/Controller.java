package co.edu.unbosque.controller;

import co.edu.unbosque.model.Composer;
import co.edu.unbosque.view.View;

public class Controller {
	
	private View gui;
	private Composer composer;
	
	public Controller() {
		gui = new View();
		composer = new Composer();
		
		boolean salir = false;
		while (!salir) {
			switch (gui.obtenerOpcion()) {
				case 1:
					try {
						composer.setEstrofas(gui.pedirNumeroEntero("Digite el número de estrofas para la canción:", "Número de estrofas"));
						composer.setFrasesPorEstrofa(gui.pedirNumeroEntero("Digite el número de frases por estrofa para la canción:", "Numero de frases por estrofa"));
						composer.getFm().setArchivo(null);
					} catch (NullPointerException e) {}
					break;
				case 2:
					gui.mostrarMensaje(composer.generarCancion(), "Creación de canción");
					break;
				case 3:
					gui.mostrarCancion(composer.obtenerCancion(), "Canción");
					break;
				case 4:
					salir = true;
					break;
			}
		}
	}
}
