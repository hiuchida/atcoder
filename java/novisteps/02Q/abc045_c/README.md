# Main
lineを読み込み、len = line.length()を用意する。
len == 1の場合、lineを出力し、終了する。

sum = 0、max = 1 << (len-1)を入れる。max=2^(len-1)
iを0からmax回ループし、mask = max、StringBuilder sbを用意し、
sb.append(line.charAt(0))を追加する。
jを1からlen-1までループし、mask >>= 1を入れ、
(i & mask) == maskの場合、sum += Long.parseLong(sb.toString())を入れる。
sb.append(line.charAt(j))を追加する。
sum += Long.parseLong(sb.toString())を入れる。
sumを出力する。
AC 108ms

# Main\_fix
書き直す。

sを読み込み、char[] aryに展開する。
n=s.length()、ans=0を用意し、
iを0から2^(n-1)回ループし、val=ary[0]-'0'、sum=0を用意し、
jを0からn-1回ループし、mask=1 << jを用意し、
(i&mask)>0の場合、sum+=val、val=ary[j+1]-'0'を入れ、
それ以外は、val*=10、val+=ary[j+1]-'0'を入れる。

sum+=val、ans+=sumを入れる。

ansを出力する。
AC 72ms
