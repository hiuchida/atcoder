# Main
List<Point>にPoint(p,i+1)を追加し、ソートし、昇順にi+1の値を出力する。

# Main_array
ap[0]からap[n-1]までをソートするので、度数をaq[i]に持たせる。
1baseを0baseにしてap[i]=p-1;へ代入し、
0baseを1baseにしてaq[ap[i]]=i+1;へ代入し、
aq[i]を順番に出力する。
