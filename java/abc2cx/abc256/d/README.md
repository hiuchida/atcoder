# Main01
nを読み込み、List<Point> listに読み込み、ソートする。
List<Point> ansを用意し、listが空になるまでループする。
idx0に末尾の添字を入れ、p0に末尾のPointを入れる。
ed=p0.st-1でバイナリーサーチし、idx<0の場合idx=~idxにする。
p1に添字idxのPointを入れる。
min=INT\_MAXとして、iをidx0からidxまで-1ループし、p.stの最小値を更新する。
ループが終了したら、Point(min,p0.ed)をansに追加する。
ループが終了したら、ansをソートし、ansを出力する。
WA18個。

# Main02
Main01を元に、バイナリーサーチの後に、st-1がヒットしたときのために、idxを補正する。
while (list.get(idx).ed==p0.st-1) idx++;
WA16個。

# Main03
Main02を元に、
				Point p=list.remove(i);
				min=Math.min(min, p.st);
を
				Point p=list.get(i);
				if (p0.st<=p.ed) {
					list.remove(i);
					min=Math.min(min, p.st);
				}
に変える。
おそらく常にif文はtrueとなる。
WA16個。

# Main11
Main03を元に、バイナリーサーチをする必要がないので、idx0-1から-1ループする。
			Point p0=list.remove(idx0);
			int lt=p0.st;
			int rt=p0.ed;
			for (int i=idx0-1; i>=0 && list.get(i).ed>=p0.st; i--) {
				Point p1=list.remove(i);
				lt=Math.min(lt, p1.st);
			}
			ans.add(new Point(lt, rt));
WA16個。

# Main
Main11を元に、
lt=p0.stとして、iをidx0-1からi>=0 && list.get(i).ed>=p0.stまで-1ループし、
ltにp.stの最小値を更新する部分が、ltがp0.stから更新されたら、
list.get(i).ed>=p0.stの判定がlist.get(i).ed>=ltとなり、p0よりも範囲が広がる。

例1では、40で判定すると、(10,20)(20,30)の両方がヒットするが、
3
10 20
20 30
40 50

↓では、40で判定すると、(20,40)しかヒットしないので、(20,40)で確定すると、(10,20)がマージできない。
lt=20に更新されたあとは、20で判定して、(10,20)がヒットし、lt=10となり、次は10で判定する。
3
10 20
20 40
40 50

# Main\_fix
Mainはlistの末尾からマージし、listから削除しているが、ansに追加した後にソートが必要となるため、
listの先頭からマージし、listから削除せずに、ansに追加する。

iを0からn-1までループし、ansが空ならば無条件で追加する。
それ以外ならばansの末尾をp0として現在のpと比較し、p0.ed<p.stならば結合できないので、pをansに追加する。
それ以外は、マージできるので、p0.ed<p.edの場合にp0.edを更新する。
ループが終了したら、ansは昇順となっているので、そのまま出力する。

# Main\_onoff
Lをログイン、Rをログアウトとして扱う。
Point(L,0)とPoint(R,1)の2件をlistに追加し、ソートする。
cnt=0として、ed=0のとき、cnt==0ならばlt=stを保存しcnt++する。
ed=1のとき、cnt--し、cnt==0ならばrt=stとして、ansへPoint(lt,rt)を追加する。

