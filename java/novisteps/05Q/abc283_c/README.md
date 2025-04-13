# Main
sを読み込み、char[] aryに展開し、ans=0を用意する。
iを0からary.length回ループし、
ary[i]!='0'の場合、ans++をカウントする。
i<ary.length-1 && ary[i+1]=='0'の場合、ans++、i++を更新する。（"00"の場合）
それ以外の場合、ans++をカウントする。

ansを出力する。
