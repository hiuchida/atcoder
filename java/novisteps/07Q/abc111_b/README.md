# Main
nを読み込み、iをnから無限ループする。
check(i)を呼び出し、trueならばiを出力して終了する。

checkの中で、a=n/100、b=n/10%10、c=n%10を計算し、
a==b && b==cを返す。
