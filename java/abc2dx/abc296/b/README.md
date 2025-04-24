# Main
n=8を用意し、String[] aryに読み込む。
y,xを0からn回ループし、s=ary[y]、idx=s.indexOf("*")を入れ、
idx>=0の場合、ans="abcdefgh".substring(idx, idx+1)を用意し、
ans+=n-yを入れ、ansを出力して、終了する。
