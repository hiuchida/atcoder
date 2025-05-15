import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int n4=0;
		int n2=0;
		int n1=0;
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			if (a%4==0) n4++;
			else if (a%2==0) n2++;
			else n1++;
		}
//		System.out.println(n4+" "+n2+" "+n1);
		n2%=2;
		if (n4*2>=n2+n1) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
3
1 10 100

4
1 2 3 4


3
1 4 1

2
1 1

6
2 7 1 8 2 8
*/
/*
5
2 7 1 8 2
*/
