package co.edu.unbosque.model;

import java.util.Random;

import co.edu.unbosque.model.persistence.FileManager;
import co.edu.unbosque.model.persistence.Propiedades;

public class Composer {

	private String nombre;
	private int estrofas;
	private int frasesPorEstrofa;
	private String cancion;
	private String[][] frases = {
			{ "Mami", "Bebe", "Plincess", "Pamita", "Nena", "Chica", "Señorita", "Dama", "Niña", "Muñeca", "Reina",
					"Novia", "Hermosura", "Sirena", "Gatita", "Flor", "Mariposa", "Estrella", "Rosa", "Amolcito",
					"Candy", "Chiquitita", "Angelita", "Dulzura", "Pequeñita", "Luna", "Perla", "Miel", "Cariñito",
					"Luz", "Princesita", "Corazoncito", "Dulce niñita", "Mi reina", "Bomboncito", "Solcito", "Nube",
					"Manzana", "Caramelito", "Cuchitita", "Poesía", "Risita", "Delicia", "Sensualidad", "Sabrosura",
					"Chiquilla", "Seducción", "Ternura", "Violeta", "Mirada" },
			{ "yo quiero", "yo puedo", "yo vengo", "voy a", "quiero bailar", "puedo cantar", "vengo a verte",
					"voy a tocarte", "quiero sentir", "puedo besarte", "vengo a entregarte", "voy a abrazarte",
					"quiero adorarte", "puedo enamorarte", "vengo a entregarme", "me provoca", "me domina", "me seduce",
					"me descontrola", "me electrifica", "me envuelve", "me atrapa", "me hipnotiza", "me fascina",
					"me excita", "me motiva", "me inspira", "me deslumbra", "me enloquece", "me estremece",
					"me enciende", "me hace arder", "me hace vibrar", "me hace temblar", "me hace delirar",
					"me lleva al cielo", "me hace soñar", "me hace suspirar", "me hace enloquecer",
					"me hace perder el control", "me pone a volar", "me hace delirar de pasión", "me hace sudar",
					"me hace gemir", "me hace enloquecer de deseo", "me hace perder la razón", "me hace sentir vivo",
					"me hace olvidar el mundo", "me hace perder la noción del tiempo", "me hace sentir único" },
			{ "encenderte", "amarte", "ligarte", "jugarte", "provocarte", "seducirte", "atraparte", "morderte",
					"acercarte", "moverte", "desnudarte", "bailarte", "acariciarte", "sorprenderte", "enamorarte",
					"acompañarte", "descontrolarte", "apoderarte", "hacerte tuyo", "estremecerte", "dominarte",
					"llevarte", "atraparte", "embriagarte", "hipnotizarte", "explorarte", "enseñarte", "invitarte",
					"cantarte", "incitarte", "provocarte", "enloquecerte", "iluminarte", "acompañarte", "desatarte",
					"poseerte", "resplandecerte", "encantarte", "desatarte", "abrazarte", "envolverte", "desbordarte",
					"hacerte vibrar", "liberarte", "acelerarte", "provocarte", "enredarte", "desafiarte", "alimentarte",
					"fascinarte" },
			{ "suave", "lento", "rápido", "fuerte", "ardiente", "seductor", "apasionado", "intenso", "dulce",
					"peligroso", "irresistible", "prohibido", "sensual", "hipnótico", "salvaje", "ardiente", "infame",
					"atrevido", "inolvidable", "explosivo", "seductor", "exótico", "tempestuoso", "atrevido",
					"juguetón", "intenso", "misterioso", "perverso", "ardiente", "desbocado", "incontrolable",
					"insaciable", "pícara", "erótico", "candente", "suculento", "desenfrenado", "delirante",
					"fascinante", "frenético", "embriagador", "voluptuoso", "sobrenatural", "insensato", "obsesivo",
					"inolvidable", "prohibido", "extático", "eterno", "ardoroso" },
			{ "hasta que salga el sol", "toda la noche", "hasta el amanecer", "todo el día", "bajo la luna llena",
					"sin control", "al ritmo del corazón", "sin medida", "sin parar", "bailando sin cesar",
					"en la penumbra", "con pasión", "como un huracán", "sin pudor", "en la oscuridad",
					"sin restricciones", "sin mirar atrás", "sin miedo", "sin final", "en la intimidad", "en secreto",
					"sin límites", "con deseo", "en el silencio", "como un suspiro", "sin remordimientos",
					"como un sueño", "en la fantasía", "con locura", "en el delirio", "en la pasión", "sin frenos",
					"con ternura", "en la entrega", "como un susurro", "en la fiesta", "sin preocupaciones",
					"con dulzura", "sin resistencia", "en la brisa", "en el calor", "sin controlar", "en la tentación",
					"en el deseo", "como un huracán", "en el viento", "sin reglas", "en el éxtasis",
					"como un relámpago", "toda la vida" },
			{ "sin piedad", "sin tregua", "sin compromiso", "con deseo", "sin miedo", "sin anestesia", "con pasión",
					"sin restricciones", "con ternura", "sin control", "sin medidas", "sin reglas", "con dulzura",
					"sin límites", "sin secretos", "con locura", "sin resistencia", "sin condiciones",
					"sin arrepentimientos", "con desenfreno", "sin remilgos", "con fuego", "sin barreras",
					"sin vergüenza", "sin prejuicios", "con adrenalina", "sin excusas", "sin detenerse",
					"con intensidad", "sin tregua", "sin frenos", "con descontrol", "sin pausa", "sin restricciones",
					"sin remilgos", "con ardor", "sin censura", "sin detenerse", "sin misterios", "con emoción",
					"sin secretos", "sin reglas", "sin condiciones", "sin límites", "con deseo", "sin arrepentimientos",
					"sin medidas", "con pasión", "sin remordimientos", "sin control" } };
	private FileManager fm;
	private Propiedades prop;

	public Composer() {
		fm = new FileManager();
		prop = new Propiedades();
	}

	public String generarCancion() {
		if (fm.leerArchivo() == null) {
			if (estrofas != 0 && frasesPorEstrofa != 0) { 
				String directorio = fm.guardarCancion();
				if (directorio == null)
					return "La canción no ha sido creada";
				else if (!directorio.contains(".txt"))
					return "La extensión del archivo de la canción debe ser .txt";
				setNombre(directorio.replace(".txt", ""));
				
				String cancion = "Nombre de la canción: " + getNombre() + "\n\n";

				Random random = new Random();

				for (int i = 0; i < estrofas; i++) {
					cancion += "Estrofa #" + (i + 1) + "\n";
					for (int j = 0; j < frasesPorEstrofa; j++) {
						for (int k = 0; k < frases.length; k++) {
							cancion += frases[k][random.nextInt(0, frases[0].length)];
							if (k != frases.length - 1)  cancion += " ";
						}
						if (random.nextInt(0, 1) == 1) cancion += "yeah";
						cancion += (j == frasesPorEstrofa - 1)? ".\n": ",\n";
					}
					cancion += "\n";
				}
				setCancion(cancion);
				
				fm.escribirArchivo(getCancion(), getNombre());
				gestionarPropiedades();
				return "Canción creada exitosamente en el directorio: " + fm.getDirectorio().toString();
			}
			return "No se ha especificado el número de estrofas y frases por estrofa para la canción";
		}
		return "La canción ya ha sido creada en el directorio: " + fm.getDirectorio().toString();
	}

	public void gestionarPropiedades() {
		prop.escribirPropiedadesDeCancion(fm.getDirectorio().toString(), this.nombre, this.estrofas, this.frasesPorEstrofa);
		prop.imprimirPropiedadesDeCancion();
	}

	public String obtenerCancion() {
		String cancion = "";
		if (fm.obtenerArchivo() == 0) {
			cancion = fm.leerArchivo();
			if (cancion == null)
				return "La selección de canción es inválida";
			return cancion;
		}
		return "No se ha seleccionado ninguna canción";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEstrofas() {
		return estrofas;
	}

	public void setEstrofas(int estrofas) {
		this.estrofas = estrofas;
	}

	public int getFrasesPorEstrofa() {
		return frasesPorEstrofa;
	}

	public void setFrasesPorEstrofa(int frasesPorEstrofa) {
		this.frasesPorEstrofa = frasesPorEstrofa;
	}

	public String getCancion() {
		return cancion;
	}

	public void setCancion(String cancion) {
		this.cancion = cancion;
	}

	public String[][] getFrases() {
		return frases;
	}

	public void setFrases(String[][] frases) {
		this.frases = frases;
	}

	public FileManager getFm() {
		return fm;
	}

	public void setFm(FileManager fm) {
		this.fm = fm;
	}

	public Propiedades getProp() {
		return prop;
	}

	public void setProp(Propiedades prop) {
		this.prop = prop;
	}
}
