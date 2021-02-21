package es.ucm.fdi.tp.launcher;
import org.junit.Test;
import es.ucm.fdi.tp.base.model.GameError;

public class MainTest {
	
	@Test (expected = IllegalArgumentException.class)
	//Test para un numero menor de parametros del main:
	public void testMain1() throws Exception {
		String[] str = {"was", "smart"};
		MainPr4.mainAux(str);
	}
	
	@Test (expected = IllegalArgumentException.class)
	//Test para un numero de mayor de parametros del main:
	public void testMain2() throws Exception {
		String[] str = {"was", "random", "smart", "console"};
		MainPr4.mainAux(str);
	}
	
	@Test (expected = GameError.class)
	//Test para controlar un juego invalido:
	public void testMain3() throws Exception {
		String[] str = {"rummy", "random", "random"};
		MainPr4.mainAux(str);
	}
}