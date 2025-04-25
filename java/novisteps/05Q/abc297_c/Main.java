import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int h=sc.nextInt();
		int w=sc.nextInt();
		for (int y=0; y<h; y++) {
			String s=sc.next();
			String ans=s.replaceAll("TT", "PC");
			System.out.println(ans);
		}
	}
}
/*
2 3
TTT
T.T

3 5
TTT..
.TTT.
TTTTT
*/
