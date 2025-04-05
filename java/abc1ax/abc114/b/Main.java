import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int M=753;
		long s=sc.nextLong();
		long ans=Integer.MAX_VALUE;
		while (s>99) {
			long v=s%1000;
			long a=Math.abs(v-M);
			ans=Math.min(ans, a);
//			System.out.println(ans+" "+v+" "+a);
			s/=10;
		}
		System.out.println(ans);
	}
}
/*
1234567876

35753

1111111111
*/
