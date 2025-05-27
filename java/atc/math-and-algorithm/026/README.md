# Main
https://github.com/E869120/math-algorithm-book/blob/main/editorial/chap3-4/chap3-4.pdf

N種類中r種類目からr+1種類目を集める確率は、
1+(r/N)^1+(r/N)^2+…=1/(1-(r/N))=1/((N-r)/N)=N/(N-r)
答えはΣN/(N-r)=N/N+N/(N-1)+…+N/1

nを読み込み、ans=0を用意する。
nをnから1までループし、ans+=(double)n/iを加える。

ansを出力する。
