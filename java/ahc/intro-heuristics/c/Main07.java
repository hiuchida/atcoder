import java.util.*;
public class Main {
	int[] at;
	int[][] aai;
	void addLast(int[] ary, int v) {
		int idx=ary[0];
		ary[idx+1]=v;
		ary[0]++;
	}
	void add(int[] ary, int v) {
		int idx=0;
		for (; idx<ary[0]; idx++) {
			if (ary[idx+1]>v) break;
		}
		add(ary, idx, v);
	}
	void add(int[] ary, int idx, int v) {
		int old=ary[idx+1];
		ary[idx+1]=v;
		while (idx<ary[0]) {
			idx++;
			int tmp=ary[idx+1];
			ary[idx+1]=old;
			old=tmp;
		}
		ary[0]++;
	}
	int indexOf(int[] ary, int v) {
		int idx=0;
		for (; idx<ary[0]; idx++) {
			if (ary[idx+1]==v) break;
		}
		return idx;
	}
	void remove(int[] ary, int v) {
		int idx=ary[0];
		int carry=0;
		while (idx>0) {
			int old=ary[idx];
			ary[idx]=carry;
			carry=old;
			idx--;
			if (carry==v) break;
		}
		ary[0]--;
	}
	void print() {
		System.out.println(Arrays.toString(at));
		for (int i=0; i<6; i++) {
			System.out.println((i+1)+": "+Arrays.toString(aai[i]));
		}
	}
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
		at=new int[d];
		aai=new int[N][d+1];
		for (int i=0; i<d; i++) {
			int t=sc.nextInt();
			at[i]=t;
			addLast(aai[t-1], i+1);
		}
//		print();
		int score=score();
		int m=sc.nextInt();
		for (int k=0; k<m; k++) {
			int i=sc.nextInt();
			int j=sc.nextInt();
			int d1=undoScore(i);
			int d2=redoScore(i, j);
			at[i-1]=j;
//			int v=score();
			int v2=score+d1+d2;
//			if (v!=v2) {
//				System.err.println(v+" "+v2+" "+score+" "+d1+" "+d2);
//			}
			score=v2;
			System.out.println(score);
//			print();
		}
	}
	int undoScore(int i) {
		int j=at[i-1];
		int l1=0;
		int l2=0;
		int v0=as[i-1][j-1];
//		int v=0;
//		System.out.println(v);
//		for (int k=0; k<d; k++) {
//			int t=at[k];
//			if (j==t) {
//				l1=k+1;
//			}
//			if (i-1==k) t=-1;
//			if (j==t) {
//				l2=k+1;
//			}
//			int v1=ac[j-1]*((k+1)-l1);
//			int v2=ac[j-1]*((k+1)-l2);
//			v+=v1-v2;
////			System.out.println(k+" "+v1+" "+v2+" "+(v1-v2)+" "+v);
//		}
		int vv1=score1(j-1);
//		System.out.println(lti.get(j-1));
		remove(aai[j-1], i);
//		System.out.println(lti.get(j-1));
		int vv2=score1(j-1);
		int vv=vv1-vv2;
//		if (v!=vv) {
//			System.out.println("debug "+v+" "+vv+" "+vv1+" "+vv2+" i="+i+" j="+j);
//		}
		return -v0+vv;
	}
	int redoScore(int i, int j) {
		int l1=0;
		int l2=0;
		int v0=as[i-1][j-1];
//		int v=0;
//		System.out.println(v);
//		for (int k=0; k<d; k++) {
//			int t=at[k];
//			if (j==t) {
//				l1=k+1;
//			}
//			if (i-1==k) t=j;
//			if (j==t) {
//				l2=k+1;
//			}
//			int v1=ac[j-1]*((k+1)-l1);
//			int v2=ac[j-1]*((k+1)-l2);
//			v+=v1-v2;
////			System.out.println(k+" "+v1+" "+v2+" "+v);
//		}
		int vv1=score1(j-1);
//		System.out.println(lti.get(j-1));
		add(aai[j-1], i);
//		System.out.println(lti.get(j-1));
		int vv2=score1(j-1);
		int vv=vv1-vv2;
//		if (v!=vv) {
//			System.out.println("debug "+v+" "+vv+" "+vv1+" "+vv2+" i="+i+" j="+j);
//		}
		return v0+vv;
	}
	int score1(int i) {
		int[] ary=aai[i];
		int l=0;
		int v=0;
		for (int idx=0; idx<ary[0]; idx++) {
			int j=ary[idx+1];
			v+=ac[i]*(j-l-1)*(j-l)/2;
			l=j;
		}
		v+=ac[i]*(d-l)*(d-l+1)/2;
		return v;
	}
	int score() {
		int[] al=new int[N];
		int v=0;
		for (int k=0; k<d; k++) {
			int t=at[k];
			v+=as[k][t-1];
			al[t-1]=k+1;
			for (int j=0; j<N; j++) {
				v-=ac[j]*((k+1)-al[j]);
			}
//			System.out.println(v);
		}
		return v;
	}
	final int N=26;
	int d;
	int[] ac;
	int[][] as;
	int[] al;
	void init(Scanner sc) {
		d=sc.nextInt();
		ac=new int[N];
		for (int i=0; i<N; i++) {
			ac[i]=sc.nextInt();
		}
		as=new int[d][N];
		for (int y=0; y<d; y++) {
			for (int x=0; x<N; x++) {
				as[y][x]=sc.nextInt();
			}
		}
		al=new int[N];
//		System.out.println(Arrays.toString(ac));
//		for (int y=0; y<d; y++) {
//			System.out.println(Arrays.toString(as[y]));
//		}
	}
	static Random rand=new Random(42);
	static long start;
	public static void main(String[] args) {
		start = System.currentTimeMillis();
		new Main().solve();
	}
}
/*



*/
