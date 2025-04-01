import java.util.*;
public class Main {
	static int n;
	static int x;
	static int y;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		x = sc.nextInt();
		y = sc.nextInt();
		long[] red=new long[n+1];
		long[] blue=new long[n+1];
		red[n]=1;
		dfs(n, red, blue);
		System.out.println(blue[1]);
	}
	static void dfs(int i, long[] red, long[] blue) {
//		System.out.println(i+" r:"+Arrays.toString(red));
//		System.out.println(i+" b:"+Arrays.toString(blue));
		if (i==1) return;
		if (red[i]==0 && blue[i]==0) {
			dfs(i-1, red, blue);
		}
		if (red[i]>0) {
//			long r1=red[i-1];
//			long r0=red[i];
//			long b0=blue[i];
			red[i-1]+=red[i];
			blue[i]+=red[i]*x;
			red[i]=0;
			dfs(i, red, blue);
//			red[i-1]=r1;
//			red[i]=r0;
//			blue[i]=b0;
		}
		if (blue[i]>0) {
//			long r1=red[i-1];
//			long b1=blue[i-1];
//			long b0=blue[i];
			red[i-1]+=blue[i];
			blue[i-1]+=blue[i]*y;
			blue[i]=0;
			dfs(i, red, blue);
//			red[i-1]=r1;
//			blue[i-1]=b1;
//			blue[i]=b0;
		}
	}
}
/*
2 3 4

1 5 5

10 5 5
*/
