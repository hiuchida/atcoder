# Main
Point[] aryに、Point(前の添字,後ろの添字)を持たせる。
初期値はすべてPoint(0,0)とする。
クエリー１は、ary\[x\].ed=y; ary\[y\].st=x;を設定する。
クエリー２は、ary\[x\].ed=0; ary\[y\].st=0;を設定する。
クエリー３は、ary\[x\].st!=0の間ループして先頭を見つけ、
ary\[x\].ed!=0までループして末尾までList<Integer> listに追加する。
