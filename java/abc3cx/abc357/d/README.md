# Main01
nを読み込み、w0=calc(n)を呼び出し、w=w0、ans=nを用意する。
iを1からn-1までループし、a=modmul(n, w)、w=modmul(w, w0)、ans=modadd(ans, a)を更新する。
ansを出力する。
TLE24個。

# Main


