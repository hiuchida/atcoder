# Main0
思いついた3つのif文でWA。
	①	if (ab && bc) System.out.println("B");
	②	else if (ac && !bc) System.out.println("C");
	③	else if (!ab && ac) System.out.println("A");

3C3を列挙してみると、if (x && y)とif (!x && !y)の反対が抜けていた。
ABC ①
ACB ②
BAC ③
BCA NOT② if (bc && !ac) System.out.println("C");
CAB NOT③ if (!ac && ab) System.out.println("A");
CBA NOT① if (!bc && !ba) System.out.println("B");

# Main
おそらくよく分からなくなって、先頭にA,B,Cと揃えて、2番3番を比較するようにした。
		if (ab && ac) {
			if (bc) System.out.println("B");
			else System.out.println("C");
		}
		if (bc && !ab) {
			if (ac) System.out.println("A");
			else System.out.println("C");
		}
		if (!ac && !bc) {
			if (ab) System.out.println("A");
			else System.out.println("B");
		}
