import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] as=new char[3];
		for (int i=0; i<3; i++) {
			String s=sc.next();
			as[i]=s.charAt(0);
		}
		char[] at=new char[3];
		for (int i=0; i<3; i++) {
			String s=sc.next();
			at[i]=s.charAt(0);
		}
//		System.out.println(Arrays.toString(as));
//		System.out.println(Arrays.toString(at));
		int cnt=0;
		if (as[0]!=at[0]) {
			if (as[0]==at[1]) {
				at[1]=at[0];
				at[0]=as[0];
				cnt++;
			} else {
				at[2]=at[0];
				at[0]=as[0];
				cnt++;
			}
		}
//		System.out.println(Arrays.toString(as));
//		System.out.println(Arrays.toString(at));
		if (as[1]!=at[1]) {
			cnt++;
		}
		if (cnt%2==0) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
R G B
R G B
*/
/*
R G B
G R B

R G B
B G R

R G B
B R G
*/
