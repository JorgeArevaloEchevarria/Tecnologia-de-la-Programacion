package tp.pr0;

public class Matematica {
	
	public static int factorial(int n){
		
		long res;
		
		if(n<0)
			res = 0;
		else if(n == 0 || n == 1)
			res = 1;
		else{
			
			res = 1;
			for(int i = 0; i < n; i++)
				res = res*(n-i);
		}	
		
		return (int)res;
		
	}
	
	public static int combinatorio(int n,int k){
		
		int res = Matematica.factorial(n)/((Matematica.factorial(k))*(Matematica.factorial(n-k)));
		
		return res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j <= i;j++)
				System.out.print(Matematica.combinatorio(i, j) + " ");
			System.out.println();
		}

	}

}
