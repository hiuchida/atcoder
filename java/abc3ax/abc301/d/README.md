# Main01
s,nを読み込み、char[] aryに展開する。TreeSet<Long> setを用意する。
iを0からary.length回ループし、
ary[i]=='?'の場合、ary[i]='1'に置き換えてx=calc(ary)を呼び出し、set.add(x)に追加する。ary[i]='0'に置き換える。
x=calc(ary)を呼び出し、set.add(x)を追加する。

ans=set.lower(n)を入れる。
ans==nullの場合-1、それ以外はansを出力する。

calcの中で、	ans=0を用意し、
	iを0からary.length回ループし、
	v=0を用意し、ary[i]=='?'の場合v=1、それ以外はv=ary[i]-'0'を入れる。
	ans*=2、ans+=vを更新する。
WA25個。
	
# Main02
Main01を元に、
set.lower(n)をset.floor(n)に変更。
WA21個。

# Main
Main02を元に、
max=calcMax(ary)、min=calcMin(ary)を呼び出し、min>nの場合、ary[i]='0'を置き換える。

x=calc(ary)を呼び出し、x<=nの場合、ans=xに置き換える。

calcMaxの中で、ary[i]=='?'の場合v=1を入れる。

calcMinの中で、ary[i]=='?'の場合v=0を入れる。

# Main\_fix
Mainを元に、
calcMax()はcalc()と同じで、max=calcMax(ary)は未使用のため削除する。

