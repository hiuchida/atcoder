# Main01,Main02
自分でバイナリーサーチを書き、ミスする。
Main01は、System.out.printlnの消し忘れ。

# Main03,Main
自分でバイナリーサーチし、順位を返す。
Main03は、System.out.printlnの消し忘れ。

Main02
		while (lt+1<rt) {
			int mid=lt+(rt-lt)/2;
			if (ary[mid]<=x) lt=mid;
			else rt=mid;
		}
		return ary.length-1-lt;
Main
		while (lt<=rt) {
			int mid=lt+(rt-lt)/2;
			if (ary[mid]<=x) lt=mid+1;
			else rt=mid-1;
		}
		return ary.length+1-lt;

# Main\_fix
自分でバイナリーサーチし、添字を返し、ary.length+1から引いて順位を出す。
searchは、return lt;を返し、int e=ary.length+1-d;を計算する。

# Main\_bsearch
Collections.binarySearchを使い、挿入ポイントから順位を出す。
