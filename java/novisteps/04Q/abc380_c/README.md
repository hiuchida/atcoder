# Main01,02,03
TLE2個を取るために試行錯誤。

# Main11
TLE2個を取るために試行錯誤。
substringからStringBuilderに書き換え。

# Main12
TLE2個を取るために試行錯誤。
List<Integer>のラッパークラスが重いと予想して、int[]に書き換え。

# Main13
TLE2個を取るために試行錯誤。
StringBuilderのsetCharAtで変更部分のみ。

# Main21
TLE2個を取るために試行錯誤。
AtCoderLibraryForJavaを適用。

# Main
他人の正解を見て、ようやく正解。
List<Integer>やint[]が2つあり、おそらく最大の5*10^5確保すると、2200msになる。
List<Point>に変えて1つにすると、190ms程度になる。

Pointにするため、st,edを同時に決定するためループは短くなったが、あまり時間に違いが出るとは。。。
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1') {
				int j=i;
				for (; j < s.length(); j++) {
					if (s.charAt(j) == '0') break;
				}
				Point p=new Point(i, j-1);
				l1.add(p);
				i=j-1;
			} else {
			}
		}

TLEとなったループ。
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '0') {
				for (int j = i; j < s.length(); j++) {
					if (s.charAt(j) == '1') {
						l1.add(j);
						i = j - 1;
						break;
					}
				}
			} else {
				for (int j = i; j < s.length(); j++) {
					if (s.charAt(j) == '0') {
						l2.add(j);
						i = j - 1;
						break;
					}
				}
			}
		}

ローカル環境で最大サイズのデータで計測しても、どちらも180ms程度しかかからない。

# Data1,Data2,Data3,Data4
テストデータ作成ツール。
