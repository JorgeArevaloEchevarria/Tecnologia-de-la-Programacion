package Practica3.Condition;

import Practica3.LexicalParser;
/**
 * 
 * @author jorge
 *
 */
public class ConditionParser {
	
	private final static Condition[] conditions = {new Equal(null,null,null), new Less(null,null,null),
			new LessEq(null,null,null), new NotEqual(null,null,null)};
	/**
	 * 
	 * @param t1
	 * @param op
	 * @param t2
	 * @param lexParser
	 * @return
	 */
	public static Condition parse(String t1, String op, String t2,  LexicalParser lexParser) {
		
		boolean found = false;
		int i = 0;
		Condition cond = null;
		
		while(i < conditions.length && !found){
			
			cond = conditions[i].parse(t1,op,t2, lexParser);
			if(cond!=null)
				found=true;
			else
				i++;
		}
		return cond;
	}
}
