# Main
lineを読み込み、iを0からline.length()回ループし、
idx = line.indexOf("dreamera", i)を入れ、
idx == iの場合、i += 5-1を入れ、スキップする。
idx = line.indexOf("dreamer", i)を入れ、
idx == iの場合、i += 7-1を入れ、スキップする。
idx = line.indexOf("dream", i)を入れ、
idx == iの場合、i += 5-1を入れ、スキップする。
idx = line.indexOf("eraser", i)を入れ、
idx == iの場合、i += 6-1を入れ、スキップする。
idx = line.indexOf("erase", i)を入れ、
idx == iの場合、i += 5-1を入れ、スキップする。
それ以外はng。
ループ終了したらok。
AC 142ms

# Main\_fix
sを読み込み、
s=s.replaceAll("eraser", "")
s=s.replaceAll("erase", "")
s=s.replaceAll("dreamer", "")
s=s.replaceAll("dream", "")を入れ、
s.length()==0の場合ok、それ以外はng。
AC 150ms

