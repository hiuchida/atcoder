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

# Main\_retry
前回2025/1/8から2025/3/28まで時間をおいて再挑戦。
文字列を切り出すポイントをp0,p1,p2とする。
p0:k-1番目の'1'の末尾
p1:k番目の'1'の先頭
p2:k番目の'1'の末尾
c=0と初期化し、iを0からs.length-1までループし、'1'を探す。
'1'の位置iからj=i+1からs.length-1までループし、'0'を探す。
j--して'1'の末尾の位置にする。
c++し、c==k-1の場合、p0=jを保存する。
c==kの場合、p1=i、p2=jを保存する。
i=j+1としてループの先頭に戻る。

\[0,p0\]、\[p1,p2\]（すべて'1'）、\[p0+1,p1-1\]（すべて'0'）、\[p2+1,length-1\]を順に出力する。

219ms→804msと遅くなったが、TLEのときはkが大きい場合にメモリが圧迫していたと考えられる。

