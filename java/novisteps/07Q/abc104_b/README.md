# Main
sを読み込み、char ary\[\]に展開する。
ary[0]!='A'ならばng。
cnt=0を用意し、iを3からs.length-1までループし、ary\[i-1\]=='C'のときcnt++にカウントする。
ary[i-1]が大文字ならばng。
ループが終わり、cnt==0 || cnt>1ならばng。
	ary[1]が大文字ならばng。
	ary\[s.length-1\]が大文字ならばng。
	