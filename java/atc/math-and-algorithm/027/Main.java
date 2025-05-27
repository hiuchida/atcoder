import java.io.*;
import java.util.*;
class Main {
	public static void main(String[] args) throws IOException {
//		Scanner sc = new Scanner(System.in);
//		int n=sc.nextInt();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String line=br.readLine();
		int n=Integer.parseInt(line);
		int[] ary=new int[n+1];
		int[] tmp=new int[n+1];
		line=br.readLine();
		String[] cols=line.split(" ");
		for (int i=1; i<=n; i++) {
//			ary[i]=sc.nextInt();
			ary[i]=Integer.parseInt(cols[i-1]);
		}
		MergeSort(ary, tmp, 1, n+1);
		for (int i=1; i<=n; i++) {
			if (i>1) System.out.print(" ");
			System.out.print(ary[i]);
		}
		System.out.println();
	}
	public static void MergeSort(int[] ary, int[] tmp, int lt, int rt) {
		if (lt+1==rt) return;
		int mid=(lt+rt)/2;
		MergeSort(ary, tmp, lt, mid);
		MergeSort(ary, tmp, mid, rt);
		int c1=lt;
		int c2=mid;
		int cnt=0;
		while (c1!=mid || c2!=rt) {
			if (c1==mid) {
				tmp[cnt]=ary[c2];
				c2++;
			} else if (c2==rt) {
				tmp[cnt]=ary[c1];
				c1++;
			} else {
				if (ary[c1]<=ary[c2]) {
					tmp[cnt]=ary[c1];
					c1++;
				} else {
					tmp[cnt]=ary[c2];
					c2++;
				}
			}
			cnt++;
		}
		for (int i=0; i<cnt; i++) {
			ary[lt+i]=tmp[i];
		}
	}
}
/*
3
3 1 2

10
658 299 47 507 122 969 449 68 513 800
*/
