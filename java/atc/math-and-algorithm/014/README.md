# Main
nを読み込み、StringBuilder sbを用意し、
iを2からi\*i<=nまでループし、
n%i==0の間ループし、sbにiを追加し、n/=iを入れる。
ループ終了したら、n>1の場合、sbにnを追加し、
sb.toString().trim()を出力する。
