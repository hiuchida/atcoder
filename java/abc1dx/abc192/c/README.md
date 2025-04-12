# Main
nを文字列として読み込み、kを読み込む。
iをk回ループし、nn=calc(n)を呼び出し、n=nnを入れる。
ループ終了したら、nを出力する。

calcの中で、sをchar[] aryに展開し、ソートする。
s1にary\[\]の文字列を作成し、i1にs1をparseIntする。
iを0からary.length/2回ループし、ary[i]とary\[length-1-i]をスワップする。
s2にary\[\]の文字列を作成し、i2にs2をparseIntする。
i3=i2-i1を入れ、s3にi3の文字列を作成する。
s3を返す。
