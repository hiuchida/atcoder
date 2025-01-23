import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] aa = new int[n];
		int[] ab = new int[n];
		double[] ac = new double[n];
		double sum=0;
		for (int i=0; i<n; i++) {
			aa[i] = sc.nextInt();
			ab[i] = sc.nextInt();
			ac[i] = (double)aa[i]/ab[i];
			sum+=ac[i];
		}
//		System.out.println(Arrays.toString(ac));
		sum/=2;
//		System.out.println(sum);
		int len=0;
		double cur=0;
		int i;
		for (i=0; i<n; i++) {
			double nxt = cur+ac[i];
			if (nxt>sum) {
				i--;
				break;
			}
			len+=aa[i];
			cur=nxt;
		}
		sum-=cur;
//		System.out.println(i+" "+len+" "+cur+" "+sum);
		double r=sum*ab[i+1];
		System.out.println(len+r);
	}
}
/*
3
1 1
2 1
3 1

3
1 3
2 2
3 1

5
3 9
1 2
4 6
1 5
5 3
*/
