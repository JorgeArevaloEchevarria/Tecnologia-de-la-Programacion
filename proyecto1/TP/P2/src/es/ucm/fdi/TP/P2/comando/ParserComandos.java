package es.ucm.fdi.TP.P2.comando;

public class ParserComandos {
	
	static private Comando[] comando = {
		new Ayuda(),new Vaciar(),new EliminarCelula(0,0),new Iniciar(),
		new CrearCelulaSimple(0,0),new Salir(),new CrearCelulaCompleja(0,0),new Paso()
		};
	
	/**
	 * muestra los texto ayuda de todos los comandos
	 * @return help
	 */
	static public String AyudaComandos(){
		
		String help = "";
		
		for(int i = 0;i<comando.length;i++)
			help += comando[i].textoAyuda() + '\n';
		
		return help;
	}
	
	/**
	 * devuelve el comando que corresponda con la cadena
	 * @param cadenas
	 * @return coman
	 */
	static public Comando parseaComando(String[ ] cadenas){
		
		Comando coman = null;
		int i = 0;

		while(i<comando.length && coman == null){
			
			coman = comando[i].parsea(cadenas);
			i++;
		}
		
		return coman;
	}
}
