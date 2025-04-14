# Main
l,n1,n2を読み込み、int[] av1,av2、long[] al1,al2に読み込む。
ans=0、i1=0、i2=0、lt1=0、lt2=0、rt1=lt1+al1[i1]-1、rt2=lt2+al2[i2]-1を用意する。
無限ループし、
av1[i1]==av2[i2]の場合、lt3=max(lt1, lt2)、rt3=min(rt1, rt2)を入れ、ans+=rt3-lt3+1に加える。
i1==n1-1 && i2==n2-1の場合、中断する。（whileでこの条件を書くと、最後のansへの加算ができない）
rt1==rt2とrt1<rt2の場合、i1<n1-1の場合、lt1=rt1+1、i1++、rt1=lt1+al1[i1]-1を更新する。
それ以外の場合、lt2=rt2+1、i2++、rt2=lt2+al2[i2]-1を更新する。
rt1>rt2の場合、先にi2<n2-1の判定と更新、それ以外はi1<n1-1の判定と更新する。

ansを出力する。
