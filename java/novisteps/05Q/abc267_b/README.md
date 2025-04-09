# Main01
sを読み込み、int[] aryに'1'を数値1にして入れる。
ary[1]>0ならばng。
int[] cntにそれぞれの列の合計を入れる。
		cnt[0]=ary[7];
		cnt[1]=ary[4];
		cnt[2]=ary[2]+ary[8];
		cnt[3]=ary[1]+ary[5];
		cnt[4]=ary[3]+ary[9];
		cnt[5]=ary[6];
		cnt[6]=ary[10];
iを0から5までループし、jをi+2から5までループし、cnt[i]>0 && cnt[j]>0のとき、bhit=falseを用意し、
kをi+1からj-1までループし、cnt[k]>0ならばbhit=trueをセットする。
ループが終了して、bhit==falseならok。
2重ループが終了したらng。
WA2個。

# Main
Main01を元に、
iを0から5までループし、jをi+2から5までループ
↓
iを0から6までループし、jをi+2から6までループ
に修正。

