import java.util.*;
public class Main {
	public static void main(String[] args) {
		int[] ary={ 1, 1, 2, 2, 3, 3 };
		System.out.println(Arrays.toString(ary));
		int len=unique(ary);
		System.out.println(Arrays.toString(ary)+" "+len);
		int[] ary2=Arrays.copyOf(ary, len);
		System.out.println(Arrays.toString(ary2));
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
*/
