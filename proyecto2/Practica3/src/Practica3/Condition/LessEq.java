package Practica3.Condition;

import Practica3.ByteCode.OneParameter.ConditionalJumps;
import Practica3.ByteCode.OneParameter.IfLeq;
import Practica3.Term.Term;
/**
 * 
 * @author jorge
 *
 */
public class LessEq extends Condition{
	/**
	 * 
	 * @param term1
	 * @param cond
	 * @param term2
	 */
	public LessEq(Term term1, ConditionalJumps cond, Term term2) {
		super(term1, cond, term2);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ConditionalJumps booleanOper(String op) {
		// TODO Auto-generated method stub
		if(op.equals("<="))
			return new IfLeq();
		else
			return null;
	}

	@Override
	protected Condition parseAux(Term term1, ConditionalJumps cond, Term term2) {
		// TODO Auto-generated method stub
		return new LessEq(term1,cond,term2);
	}

}
