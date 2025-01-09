# Main
3C3を列挙してみる。
ABC ①
ACB ②
BAC ③
BCA NOT② if (bc && !ac) System.out.println("C");
CAB NOT③ if (!ac && ab) System.out.println("A");
CBA NOT① if (!bc && !ba) System.out.println("B");
		