# Main1
Pair(key,s)にソートキーを追加する。
ソートキーに変換するテーブルを勘違いしていた。
		char[] ary = x.toCharArray();

			for (int j=0; j<s.length(); j++) {
				int ch=s.charAt(j)-'a';
				char k=ary[ch];
				sb.append(k);
			}

# Main
Pair(key,s)にソートキーを追加する。
		char[] ary = new char[26];
		for (int i=0; i<26; i++) {
			ary[x.charAt(i)-'a']=(char)('A'+i);
		}

			for (int j=0; j<s.length(); j++) {
				int ch=s.charAt(j)-'a';
				char k=ary[ch];
				sb.append(k);
			}
