# Main01
idx = line.indexOf("ST")を入れ、idx < 0の場合中断する。
line = line.substring(0, idx) + line.substring(idx+2)を入れる。
TLE3個。

# Main02
Main01を元に、
prev = 0を用意し、idx = line.indexOf("ST", prev)を入れ、
prev = idx-1を入れ、prev < 0の場合prev = 0にする。
TLE3個。

# Main03
Main01を元に、
prev = lineを入れ、line = line.replaceAll("ST", "");を置換する。
prev.equals(line)の場合中断する。
TLE1個。

# Main04
Main03を元に、
prev.length() == line.length()で比較する。
TLE1個。

# Main
cnt = 0、scnt = 0を用意し、ch = line.charAt(i)に入れ、
ch == 'S'の場合、scnt++をカウントする。
ch == 'T'の場合、scnt > 0の場合scnt--、それ以外はcnt++をカウントする。

cnt += scntを入れ、cntを出力する。

# Main\_fix
2018/04/28 r9を適用。

