# Main01
nを読み込み、ans=0を用意する。
n回ループし、x,uを文字列として読み込み、
u=="JPY"の場合、v1=parseInt(x)、v2=v1*Rを入れ、ans+=v2に加える。
それ以外の場合、idx=x.indexOf(".")を入れ、
v1=parseInt(x.substring(0, idx))、v2=parseInt(x.substring(idx+1))、
v3=v1*R+v2、v4=v3*380000を入れ、ans+=v4に加える。
ループが終了したら、ans/=Rし、ansを出力する。
WA1個。

# Main
Main01を元に、最後にans2=(double)ans/Rを計算し、ans2を出力する。
10^8で割って、整数で切り捨てたら、答えが小さい値で誤差が10^-5を越えたと考えられる。

64ビット整数で計算してオーバーフローしない見込み。
R=10^8
M=38\*10^4
JPY: 10^8
BTC: 10^2
N=10
すべてJPYならば、10^8\*10^8\*10=10^17
すべてBTCならば、10^2\*38\*10^4\*10^8=3.8\*10^15

