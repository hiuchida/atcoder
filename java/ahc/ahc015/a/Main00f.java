import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		init(sc);
//		System.out.println(Arrays.toString(af));
		for (int k=0; k<N; k++) {
			int pos=sc.nextInt();
			System.out.println("F");
			System.out.flush();
		}
	}
	static int N=100;
	static int[] af;
	static void init(Scanner sc) {
		af=new int[N];
		for (int i=0; i<N; i++) {
			int f=sc.nextInt();
			af[i]=f;
		}
	}
}
/*



*/
