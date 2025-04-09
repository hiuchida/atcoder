# Main
a,bを読み込み、lt=0、rt=(long)1e18を用意する。
lt+100<rtの間ループし、mid1=(long)((double)lt*2+(double)rt)/3、mid2=(long)((double)lt+(double)rt*2)/3を入れ、
x1=func(a, b, mid1)、x2=func(a, b, mid2)を呼び出し、
x1>x2の場合lt=mid1、それ以外はrt=mid2を更新する。
ループ終了したら、ans=LONG\_MAXを用意し、iをltからrtまでループし、
x=func(a, b, i)を呼び出し、ansにxの最小値を更新する。
ループ終了したら、ansを出力する。
