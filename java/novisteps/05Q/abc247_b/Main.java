import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[] as=new String[n];
		String[] at=new String[n];
		for (int i=0; i<n; i++) {
			as[i]=sc.next();
			at[i]=sc.next();
		}
//		System.out.println(Arrays.toString(as));
//		System.out.println(Arrays.toString(at));
		for (int i=0; i<n; i++) {
			String s=as[i];
			String t=at[i];
			boolean bs=false;
			boolean bt=false;
			for (int j=0; j<n; j++) {
				if (i==j) continue;
				if (s.equals(as[j])) bs=true;
				if (s.equals(at[j])) bs=true;
				if (t.equals(as[j])) bt=true;
				if (t.equals(at[j])) bt=true;
			}
			if (bs && bt) {
				System.out.println("No");
				System.exit(0);
			}
		}
		System.out.println("Yes");
	}
}
/*
3
tanaka taro
tanaka jiro
suzuki hanako

3
aaa bbb
xxx aaa
bbb yyy

2
tanaka taro
tanaka taro

3
takahashi chokudai
aoki kensho
snu ke
*/
