# Main
nを読み込み、Counter cntnを用意し、n回ループし、cntnにカウントする。
mを読み込み、Counter cntmを用意し、n回ループし、cntmにカウントする。
ans=0を用意し、cntnの要素sをループし、
a=cntn.get(s)、b=cntm.get(s)を入れ、ansにa-bの最大値を更新する。
ループ終了したら、ansを出力する。
