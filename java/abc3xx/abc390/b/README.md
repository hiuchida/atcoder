# Main
a=ary\[0\],b=ary\[1\]とし、r=b/aをdouble型で計算すると誤差が出そうなので、
iを2からn-1までループし、c=ary\[i-1\],d=ary\[i\]として、
b/a=d/cよりac==bcの判定をした。
