# Main01
n,k,sを読み込み、char[] aryに展開する。
c=0を用意し、iを0からn回ループし、
ary[i]=='o'の場合、c++をカウントし、
i>0 && ary[i-1]=='?'の場合、ary[i-1]='.'を更新する。
i<n-1 && ary[i+1]=='?'の場合、ary[i+1]='.'を更新する。

c0=0を用意し、iを0からn回ループし、
ary[i]=='?'の場合、c0++をカウントする。

c+c0==kの場合、iを0からn回ループし、
ary[i]=='?'の場合、ary[i]='o'を更新する。

ary\[\]から文字列ansを作成し、ansを出力する。
WA23個。

# Main02
Main01を元に、最後に
c==kの場合、iを0からn回ループし、
ary[i]=='?'の場合、ary[i]='.'を更新する。
を追加。
WA13個。

# Main03
Main02を元に、c=0の次に
c2=0、beven=falseを用意し、iを0からn回ループし、
ary[i]=='?'の場合、jをi+1からn-1までループし、ary[j]!='?'の場合中断する。
c1=j-iを入れ、
c1%2==1の場合、c2+=(c1+1)/2を入れ、
それ以外はbeven=trueを入れる。
i=j-1を更新する。
ループ終了したら、
!beven && c+c2==kの場合、iを0からn回ループし、
ary[i]=='?'の場合、jを0からi+j<nまでループし、ary[i+j]!='?'の場合中断する。
j%2==0の場合、ary[i+j]='o'を更新し、
それ以外の場合、ary[i+j]='.'を更新する。
i=j-1を更新する。
ループ終了したら、c+=c2を更新する。
WA11個。

# Main04
Main03を元に、c2のときにSet<Integer> setを用意し、
c1%2==1の場合、setにiを追加する。
!beven && c+c2==k　を　c+c2<=k　に変更し、
ary[i]=='?'　を　ary[i]=='?' && set.contains(i)　に変更する。
WA1個。TLE1個。

# Main05
Main04を元に、
j%2==0の場合、c++を追加する。
c+=c2を削除する。
WA1個。TLE1個。

# Main11
Main05を元に、
Set<Integer> set=new HashSet<>()　の代わりに　boolean[] flag=new boolean[n+1]　に変更する。
WA1個。

# Main

