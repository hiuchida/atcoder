import java.util.*;
public class Main {
	public static void main(String[] args) {
		int ary[] = { 1, 3, 5, };
		for (int x=0; x<7; x++) {
			int rc = Arrays.binarySearch(ary, x);
			System.out.println("x=" + x + " rc=" + rc);
		}
		System.out.println();

		for (int x=0; x<7; x++) {
			int rc = Arrays.binarySearch(ary, x);
			if (rc<0) rc=-(rc+1);
			System.out.println("x=" + x + " rc=" + rc);
		}
		System.out.println();

		for (int x=0; x<7; x++) {
			int rc = Arrays.binarySearch(ary, x);
			if (rc<0) rc=-(rc+1)-1;
			System.out.println("x=" + x + " rc=" + rc);
		}
	}
}
/*
x=0 rc=-1
x=1 rc=0
x=2 rc=-2
x=3 rc=1
x=4 rc=-3
x=5 rc=2
x=6 rc=-4

if (rc<0) rc=-(rc+1);
x=0 rc=0
x=1 rc=0
x=2 rc=1
x=3 rc=1
x=4 rc=2
x=5 rc=2
x=6 rc=3 (ArrayIndexOutOfBoundsException)

if (rc<0) rc=-(rc+1)-1;
x=0 rc=-1 (ArrayIndexOutOfBoundsException)
x=1 rc=0
x=2 rc=0
x=3 rc=1
x=4 rc=1
x=5 rc=2
x=6 rc=2
*/
