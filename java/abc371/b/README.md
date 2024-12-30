# Main
配列に長男フラグを持たせて、falseでMのときにtrueで、フラグをtrueにする。

解説のように数を減らせるが、elseが2行なのであまり変わらんか。
if ("F".equals(b) || flg[a]) System.out.println("No");
else {
	System.out.println("Yes");
	flg[a] = true;
}
