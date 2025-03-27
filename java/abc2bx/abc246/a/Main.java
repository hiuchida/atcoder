import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] ax=new int[3];
		int[] ay=new int[3];
		for (int i=0; i<3; i++) {
			ax[i]=sc.nextInt();
			ay[i]=sc.nextInt();
		}
		Arrays.sort(ax);
		Arrays.sort(ay);
		int x=0;
		int y=0;
		if (ax[0]==ax[1]) x=ax[2];
		else x=ax[0];
		if (ay[0]==ay[1]) y=ay[2];
		else y=ay[0];
		System.out.println(x+" "+y);
	}
}
/*
-1 -1
-1 2
3 2

-60 -40
-60 -80
-20 -80
*/
