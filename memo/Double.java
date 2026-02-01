import java.util.*;
public class Main {
	public static void main(String[] args){
		{
			int v1 = (int)1e9;
			int v2 = (int)2e9;
			int v3 = Integer.MAX_VALUE;
			System.out.printf("%,d %,d %,d", v1, v2, v3);
			System.out.println();
		}
		{
			long v1 = (long)1e18;
			long v2 = (long)9e18;
			long v3 = Long.MAX_VALUE;
			System.out.printf("%,d %,d %,d", v1, v2, v3);
			System.out.println();
		}
		{
			double v1 = 0x1p+0;
			double v2 = 0x1p+31;
			double v3 = 0x0.8p+0;
			double v4 = 0x0.1p+0;
			System.out.printf("%f %f %f %f", v1, v2, v3, v4);
			System.out.println();
		}
	}
}
