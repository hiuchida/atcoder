import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] ary = new int[m];
		for (int i=0; i<ary.length; i++) {
			ary[i] = sc.nextInt();
		}
		for (int i=1; i<ary.length; i++) {
			if (ary[i-1]+1 != ary[i]) {
				System.out.println("No");
				System.exit(0);
			}
		}
		int m1=ary[0]%7;
		int m2=ary[m-1]%7;
		if (m==7) {
			if (m1!=1 || m2!=0) {
				System.out.println("No");
				System.exit(0);
			}
		} else {
			if (m2==0) m2=7;
			if (m1>m2) {
				System.out.println("No");
				System.exit(0);
			}
		}
		for (int j=1; j<n; j++) {
			for (int i=0; i<ary.length; i++) {
				int b = sc.nextInt();
				if (ary[i]+7 != b) {
					System.out.println("No");
					System.exit(0);
				}
				ary[i]=b;
			}
		}
		System.out.println("Yes");
	}
}
/*
2 3
1 2 3
8 9 10

2 1
1
2

10 4
1346 1347 1348 1349
1353 1354 1355 1356
1360 1361 1362 1363
1367 1368 1369 1370
1374 1375 1376 1377
1381 1382 1383 1384
1388 1389 1390 1391
1395 1396 1397 1398
1402 1403 1404 1405
1409 1410 1411 1412
*/
