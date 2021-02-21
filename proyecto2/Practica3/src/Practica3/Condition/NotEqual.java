package Practica3.Condition;

import Practica3.ByteCode.OneParameter.ConditionalJumps;
import Practica3.ByteCode.OneParameter.IfNeq;
import Practica3.Term.Term;

public class NotEqual extends Condition{
	/**
	 * 
	 * @param term1
	 * @param cond
	 * @param term2
	 */
	public NotEqual(Term term1, ConditionalJumps cond, Term term2) {
		super(term1, cond, term2);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ConditionalJumps booleanOper(String op) {
		// TODO Auto-generated method stub
		if(op.equals("!="))
			return new IfNeq();
		else
			return null;
	}

	@Override
	protected Condition parseAux(Term term1, ConditionalJumps cond, Term term2) {
		// TODO Auto-generated method stub
		return new NotEqual(term1,cond,term2);
	}

}
