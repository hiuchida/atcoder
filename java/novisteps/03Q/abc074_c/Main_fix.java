import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int c=sc.nextInt();
		int d=sc.nextInt();
		int e=sc.nextInt();
		int f=sc.nextInt();
		int max1=0;
		int max2=0;
		for (int i=0; i*100*a<=f; i++) {
			int w1=i*100*a;
			for (int j=0; w1+j*100*b<=f; j++) {
				int w2=w1+j*100*b;
				for (int g=0; w2+g*c<=f; g++) {
					int s1=g*c;
					int w3=w2+s1;
					for (int h=0; w3+h*d<=f; h++) {
						int s2=s1+h*d;
						int w4=w2+s2;
						if (e*w2/100>=s2) {
//							System.out.println(i+" "+j+" "+g+" "+h+" : "+s2+" "+w2);
							if (max2==0) {
								max1=w4;
								max2=s2;
							} else if (max2*w4<s2*max1) {
								max1=w4;
								max2=s2;
							}
						}
					}
				}
			}
		}
		System.out.println(max1+" "+max2);
	}
}
/*
1 2 10 20 15 200

1 2 1 2 100 1000

17 19 22 26 55 2802
*/
