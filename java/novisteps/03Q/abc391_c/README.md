# Main
int\[\] a,bにa\[i\]=i,b\[i\]=1で初期化する。
クエリー1の場合、b\[a\[p\]\]--し、この値が2から1に変わったとき、ans--する。
b\[h\]++し、この値が1から2に変わったとき、ans++する。
a\[p\]=hに変更する。
クエリー2の場合、ansを出力する。
