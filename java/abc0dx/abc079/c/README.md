# Main
sを読み込み、int[] aryに1桁の数値を展開する。
iを0から2^3回ループし、ans=""+ary[0]、x=ary[0]を用意する。
jを0から3回ループし、mask=1<<jを入れ、
(i&mask)>0の場合、ans=ans+"+"+ary[j+1]、x+=ary[j+1]を更新し、
それ以外は、ans=ans+"-"+ary[j+1]、x-=ary[j+1]を更新する。
x==7の場合、ans+"=7"を出力する。
