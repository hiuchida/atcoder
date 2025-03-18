import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] aa=new int[n];
		int[] ab=new int[n];
		long sum=0;
		for (int i=0; i<n; i++) {
			aa[i]=sc.nextInt();
			ab[i]=sc.nextInt();
			sum+=aa[i];
		}
		long ans=0;
		for (int i=0; i<n; i++) {
			long x=sum-aa[i]+ab[i];
			ans=Math.max(ans, x);
		}
		System.out.println(ans);
	}
}
/*
3
4 10
5 8
2 9

5
1 1
1 1
1 1
1 1
1 1

10
690830957 868532399
741145463 930111470
612846445 948344128
540375785 925723427
723092548 925021315
928915367 973970164
563314352 832796216
562681294 868338948
923012648 954764623
691107436 891127278
*/
