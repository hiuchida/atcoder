# Main
位置をpos=0とする。
iを0からn-1までループする。
d<aならばd=a、d>bならばd=bとする。
sが"East"ならばpos+=d、それ以外ならばpos-=dとする。
ループの後、pos>0ならばEast posを出力する。
pos<0ならばWest posを出力する。
pos==0ならば0を出力する。
