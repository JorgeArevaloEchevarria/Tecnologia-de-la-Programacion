package es.ucm.fdi.TP.P3.comando;

import es.ucm.fdi.TP.P3.excepciones.ComandoIncorrecto;
import es.ucm.fdi.TP.P3.excepciones.ErrorDeInicializacion;
import es.ucm.fdi.TP.P3.excepciones.FormatoNumericoIncorrecto;

/**
 *Clase que se encarga de los parseos
 */
public class ParserComandos {
	
	static private Comando[] comando = {
		new Ayuda(),new Vaciar(),new EliminarCelula(0,0),new Iniciar(),
		new Salir(),new Paso(),new Cargar(""),new Guardar(""),new CrearCelula(0,0),
		new Jugar(null)
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
	 * @throws FormatoNumericoIncorrecto 
	 * @throws ComandoIncorrecto 
	 * @throws ErrorDeInicializacion 
	 */
	static public Comando parseaComando(String[ ] cadenas) throws FormatoNumericoIncorrecto, ComandoIncorrecto, ErrorDeInicializacion{
		
		Comando coman = null;
		
		try{
			
			int i = 0;
	
			while(i<comando.length && coman == null){
				
				coman = comando[i].parsea(cadenas);
				i++;
			}
			
		}catch(ArrayIndexOutOfBoundsException e){
			throw new ComandoIncorrecto("");
		}
		
		
		return coman;
	}
}
