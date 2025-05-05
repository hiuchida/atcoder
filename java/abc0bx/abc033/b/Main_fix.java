import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		String maxS="";
		int maxP=-1;
		long sum=0;
		for (int i=0; i<n; i++) {
			String s=sc.next();
			int p=sc.nextInt();
			if (maxP<p) {
				maxS=s;
				maxP=p;
			}
			sum+=p;
		}
		if (maxP>sum/2) System.out.println(maxS);
		else System.out.println("atcoder");
	}
}
/*
4
unagi 20
usagi 13
snuke 42
smeke 7

5
a 10
b 20
c 30
d 40
e 100

14
yasuzuka 3340
uragawara 4032
oshima 2249
maki 2614
kakizaki 11484
ogata 10401
kubiki 9746
yoshikawa 5142
joetsu 100000
nakago 4733
itakura 7517
kiyosato 3152
sanwa 6190
nadachi 3169
*/
