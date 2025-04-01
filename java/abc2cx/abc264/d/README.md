# Main
sを読み込み、char[] aryに展開し、ans=0を用意する。
moveを呼び出して、外側の文字から移動していく回数を集計する。
		ans=move(ary, ans, 'a', 0);
		ans=move(ary, ans, 'r', 6);
		ans=move(ary, ans, 't', 1);
		ans=move(ary, ans, 'e', 5);
		ans=move(ary, ans, 'c', 2);
		ans=move(ary, ans, 'd', 4);

move内で、find=0を用意し、ary[i]==chとなるときに、find=iを入れる。
idx<findのときは、iをfindからidxまで-1ループし、ary[i]=ary[i-1]を移動し、ans++を加える。
idx>findのときは、iをfindからidxまでループし、ary[i]=ary[i+1]を移動し、ans++を加える。
どちらも最後にary[idx]=chを入れる。
更新されたansを返す。
