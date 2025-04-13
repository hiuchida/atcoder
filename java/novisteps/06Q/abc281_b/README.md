# Main01
sを読み込み、n=s.length()を入れる。
ch=s.charAt(0)を入れ、!checkUpper(ch)ならばng。
iを1からn-1回ループし、ch=s.charAt(i)を入れ、!checkDigit(ch)ならばng。
ss=s.substring(1, n-1)、v=Integer.parseInt(ss)を入れ、v<100000 || 999999<vならばng。
ch=s.charAt(n-1)を入れ、!checkUpper(ch)ならばng。
それ以外はok。
WA1個。RE1個。

# Main02
Main01を元に、
int v=Integer.parseInt(ss);
を
long v=Long.parseLong(ss);
に修正。
WA1個。RE1個。

# Main03
Main02を元に、
n=s.length()を入れた後に、n<8ならばng。　を追加
WA1個。

# Main
Main03を元に、
n<8　を　n!=8　に修正

okなのは8文字固定なのに、1<=len<=10のsが渡されるので、
長さのチェックを先にすればよかった。
