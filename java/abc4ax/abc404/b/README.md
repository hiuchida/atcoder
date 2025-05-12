# Main
nを読み込み、String[] as,atに読み込み、ans=INT\_MAXを用意する。
rを4回ループし、
x=check(as, at)を呼び出し、ansをr+xの最小値を更新する。
as=rotate(as)を呼び出す。
ansを出力する。

checkの中で、
ans=0を用意し、y,xを0からn回ループし、
as[y].charAt(x)!=at[y].charAt(x)の場合ans++をカウントする。
ansを返す。

rotateの中で、
char[][] aryを用意し、y,xを0からn回ループし、
ary[x][n-1-y]=as[y].charAt(x)を入れる。
String[] ansを用意し、yを0からn回ループし、
ans[y]=new String(ary[y])を入れる。
ansを返す。

# Main\_fix
static String[] rotate(String[] as) { //abc404_b: 時計周りに90度回転する

