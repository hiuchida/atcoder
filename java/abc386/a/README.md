# Main
解説の1-13の1枚を加えて判定は、判定が面倒そうなので、4枚のままで判定した。
ソートして、[1-3], [3-1], [2-2]の3パターンを判定した。
1枚加えて5枚だと、[2-3], [3-2]の2パターンしかないのか。

全部同じ[4]はNGなので、隣合う3ヵ所の1つのみが異なるというのは確かにその通りだった。

# Main_array
度数も思いついたが、スカスカの配列からソートして探さないといけないと思ったが、
度数の度数を取ればよかった。

こちらの方が条件分岐の意味が分かりやすいか。
		if (ary2[3] == 1 && ary2[1] == 1)
			System.out.println("Yes");
		else if (ary2[2] == 2)
			System.out.println("Yes");
		else
			System.out.println("No");
