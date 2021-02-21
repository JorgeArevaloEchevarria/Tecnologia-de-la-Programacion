package Practica3.Condition;

import Practica3.LexicalParser;
import Practica3.ByteCode.OneParameter.ConditionalJumps;
import Practica3.Command.Compile;
import Practica3.Excepciones.ArrayException;
import Practica3.Term.Term;
import Practica3.Term.TermParser;
/**
 * 
 * @author jorge
 *
 */
public abstract class Condition {
	
	 private Term t1;
	 private Term t2;
	 protected ConditionalJumps condition; //para la compilación
	 /**
	  * 
	  * @param term1
	  * @param cond
	  * @param term2
	  */
	 public Condition(Term term1,ConditionalJumps cond,Term term2 ){
		 this.t1=term1;
		 this.condition = cond;//sin argumentos por q no se donde salta
		 this.t2=term2;
	 }
	 /**
	  * 
	  */
	 public Condition(){
		 this.t1=null;
		 this.condition = null;
		 this.t2=null;
	 }
	 /**
	  * 
	  * @param t1
	  * @param op
	  * @param t2
	  * @param parser
	  * @return
	  */
	 public Condition parse(String t1, String op, String t2, LexicalParser parser){
		 
		this.t1 = TermParser.parse(t1);
		this.t2 = TermParser.parse(t2);
		this.condition = this.booleanOper(op);
		if(condition == null)
			return null;
		else{
			parser.increaseProgramCounter();
			return this.parseAux(this.t1,this.condition,this.t2);
		}
	 }
	 /**
	  * 
	  * @param compiler
	  * @throws ArrayException
	  */
	 public void compile(Compile compiler) throws ArrayException{
		 compiler.addByteCode(this.t1.compile(compiler));
		 compiler.addByteCode(this.t2.compile(compiler));
		 compiler.addByteCode(this.condition);
	 }
	/**
	 * 
	 * @param jump
	 */
	 public void setJump(int jump){
		this.condition.setJump(jump);
	 }
	 /**
	  * 
	  * @param op
	  * @return
	  */
	 abstract protected ConditionalJumps booleanOper(String op);
	 /**
	  * 
	  * @param term1
	  * @param cond
	  * @param term2
	  * @return
	  */
	 abstract protected Condition parseAux(Term term1,ConditionalJumps cond,Term term2 );
	 
}
