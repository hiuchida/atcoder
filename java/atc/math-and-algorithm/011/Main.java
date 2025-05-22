import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		boolean[] flag=new boolean[n+1];
		flag[2]=true;
		for (int i=3; i<=n; i++) {
			boolean bng=false;
			for (int j=2; j<i; j++) {
				if (!flag[j]) continue;
				if (i%j==0) {
					bng=true;
					break;
				}
			}
			if (!bng) {
				flag[i]=true;
			}
		}
//		System.out.println(Arrays.toString(flag));
		StringBuilder sb=new StringBuilder();
		for (int i=2; i<=n; i++) {
			if (flag[i]) {
				sb.append(i).append(" ");
			}
		}
		String ans=sb.toString().trim();
		System.out.println(ans);
	}
}
/*
10
*/
