# Main
nを読み込み、int[] aryに読み込む。
ans=0とList<Point> listを用意する。
iを0からn-1までループする。
list.size()==0のとき、Point(ary\[i\],1)を追加し、ans++する。
listの末尾を得て、p.st!=ary[i]のとき、Point(ary\[i\],1)を追加し、ans++する。
それ以外のとき、p.ed++、ans++し、p.st==p.edとなった場合、listの末尾を取り除き、ans-=p.stする。
毎回ansを出力する。
