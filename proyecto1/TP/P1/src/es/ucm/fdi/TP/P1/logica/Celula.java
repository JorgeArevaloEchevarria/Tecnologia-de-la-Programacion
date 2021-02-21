package es.ucm.fdi.TP.P1.logica;

public class Celula {
		
	
	static final int MAX_PASOS_SIN_MOVER=1;
	static final int PASOS_REPRODUCCION=2;

	private int numPasos;
	private int numPasosQuieto;
	
	public Celula(){//Constructor de celula
		numPasos = 0;
		numPasosQuieto = 0;
	}
	
	public int getPasosCelula(){//devuelve el numero de pasos que tiene la celula
		return numPasos;
	}
	
	public int getPasosQuieto(){//devuelve el numero de pasos que lleva quieta la celula
		return numPasosQuieto;
	}
	
	public void setPasosCelula(int pasos){
		numPasos = pasos;
	}
	
	public void setPasosQuieto(int quieto){
		numPasosQuieto = quieto;
	}
	
	public void contadorPasos(){//incrementa los pasos dados
		numPasos++;
	}
	public void contadorQuieto(){//incrementa los pasos que ha estado quieto
		numPasosQuieto++;
	}
	
	public boolean morir(){ //devuelve si la celula tiene que morir
		return (numPasosQuieto == MAX_PASOS_SIN_MOVER); 
	 }

	public boolean reproducir(){//devuelve si la celula tiene que reproducirse
		return (numPasos == PASOS_REPRODUCCION);
	}
	
	public String toString(){//muestra los valores de las celulas
		
		String cadena = "";
		cadena += (MAX_PASOS_SIN_MOVER - numPasosQuieto) + "-" + (PASOS_REPRODUCCION - numPasos);
				
		return cadena;
	}
}
