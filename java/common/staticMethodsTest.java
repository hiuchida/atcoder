import java.util.*;
public class Main {
	public static void main(String[] args) {
		test_binarySearch();
		test_lowerBound();
		test_unique();
		System.out.println("end of main");
	}
	static void test_binarySearch() {
		int[] ary={ 1, 1, 2, 2, 3, 3 };
		System.out.println("test_binarySearch");
		for (int x=0; x<5; x++) {
			int idx=binarySearch(ary, x);
			System.out.println(Arrays.toString(ary)+" x="+x+" idx="+idx);
		}
	}
	static void test_lowerBound() {
		int[] ary={ 1, 1, 2, 2, 3, 3 };
		System.out.println("test_lowerBound");
		for (int x=0; x<5; x++) {
			int idx=lowerBound(ary, x);
			System.out.println(Arrays.toString(ary)+" x="+x+" idx="+idx);
		}
	}
	static void test_unique() {
		int[] ary={ 1, 1, 2, 2, 3, 3 };
		System.out.println("test_unique");
		System.out.println(Arrays.toString(ary));
		int len=unique(ary);
		System.out.println(Arrays.toString(ary)+" len="+len);
		int[] ary2=Arrays.copyOf(ary, len);
		System.out.println(Arrays.toString(ary2));
	}
	//abc077_c: ary[mid]<x条件のバイナリーサーチ
	static int binarySearch(int[] ary, int x) {
		int lt=0;
		int rt=ary.length-1;
		while (lt<=rt) {
			int mid=(lt+rt)/2;
			if (ary[mid]<x) lt=mid+1;
			else rt=mid-1;
		}
		return lt;
	}
	//ソートされた配列int[] aryに対して、ary[i]>=xとなる最小のiを求める関数
	static int lowerBound(int[] ary, int x) {
		int lt=-1;
		int rt=ary.length;
		while (lt+1<rt) {
			int mid=(lt+rt)/2;
			if (ary[mid]>=x) rt=mid;
			else lt=mid;
		}
		return rt;
	}
	//ソート済のary[i]の重複を省く
	static int unique(int[] ary) {
		if (ary.length==0) return 0;
		int idx=1;
		for (int i=1; i<ary.length; i++) {
			if (ary[i-1]!=ary[i]) ary[idx++]=ary[i];
		}
		for (int i=idx; i<ary.length; i++) ary[i]=0;
		return idx;
	}
}
/*
test_binarySearch
[1, 1, 2, 2, 3, 3] x=0 idx=0
[1, 1, 2, 2, 3, 3] x=1 idx=0
[1, 1, 2, 2, 3, 3] x=2 idx=2
[1, 1, 2, 2, 3, 3] x=3 idx=4
[1, 1, 2, 2, 3, 3] x=4 idx=6
test_lowerBound
[1, 1, 2, 2, 3, 3] x=0 idx=0
[1, 1, 2, 2, 3, 3] x=1 idx=0
[1, 1, 2, 2, 3, 3] x=2 idx=2
[1, 1, 2, 2, 3, 3] x=3 idx=4
[1, 1, 2, 2, 3, 3] x=4 idx=6
test_unique
[1, 1, 2, 2, 3, 3]
[1, 2, 3, 0, 0, 0] len=3
[1, 2, 3]
end of main
*/
