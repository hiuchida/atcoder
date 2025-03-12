import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Dice[] dices=new Dice[n];
		for (int i=0; i<n; i++) {
			int k = sc.nextInt();
			dices[i]=new Dice(k);
			for (int j=0; j<k; j++) {
				int a = sc.nextInt();
				dices[i].add(a);
			}
		}
		double ans=0;
		for (int i=0; i<n-1; i++) {
			for (int j=i+1; j<n; j++) {
				Dice d1=dices[i];
				Dice d2=dices[j];
				ans=Math.max(ans, calc(d1, d2));
			}
		}
		System.out.println(ans);
	}
	static double calc(Dice d1, Dice d2) {
		double ans=0;
		for (Integer key : d1.map.keySet()) {
			Integer i1=d1.map.get(key);
			Integer i2=d2.map.get(key);
			if (i2!=null) {
				double r1=(double)i1/d1.k;
				double r2=(double)i2/d2.k;
				ans += r1*r2;
//				System.out.println(i1+" "+d1.k+" "+i2+" "+d2.k+" "+(r1*r2));
			}
		}
		return ans;
	}
	static class Dice {
		int k;
		Map<Integer, Integer> map=new TreeMap<>();
		Dice(int k) {
			this.k=k;
		}
		void add(int a) {
			Integer v=map.get(a);
			if (v==null) {
				map.put(a, 1);
			} else {
				v++;
				map.put(a, v);
			}
		}
	}
}
/*
3
3 1 2 3
4 1 2 2 1
6 1 2 3 4 5 6

3
5 1 1 1 1 1
4 2 2 2 2
3 1 1 2
*/
