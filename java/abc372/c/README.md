# Main
最初に"ABC"を数えて、クエリーは差分を計算する。
中央を基準に範囲チェック付きのメソッドにしたので、3回呼び出しているが、
ループしてもよい。
			if (isAbc(x-1)) d--;
			if (isAbc(x)) d--;
			if (isAbc(x+1)) d--;