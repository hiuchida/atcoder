# Main01
動的計画法を知らない時期にdfsで解こうとした。
TLE24個。

# Main02
Main01を元に、Map<Point,Long> mapを使ってdfs内にキャッシュする。
TLE20個。部分点34点。

# Main03
Main02を元に、n>30の場合は動的計画法を使う。
RE12個。部分点67点。

# Main04
Main03を元に、n<30はdfs、w<=1000はMain03の動的計画法、それ以外は新たな動的計画法を使う。
WA29個。部分点33点（Main03のREがAC）。

# Main05
Main04を元に、n<30をn<=30に修正する。
WA14個。部分点67点。

# Main
Main05を元に、new long[n+1][w+1]をnew long[n+1][n*v+1]に修正する。
pt.y > 1000の場合、Main04で追加した動的計画法を使う。

# Main\_fix01
Mainを元に、long[][] dpをlong[] dpに修正する。
WA12個。

# Main\_fix
Main\_fix01を元に、fill(dp, _longMax)をfill(dp, _intMax)に修正する。

