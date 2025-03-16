# Main01
手で列挙しても法則が見つからず、dfsで列挙することにした。TLE5個。

# Main02
すべてstrs.add(s0);を追加してから、後から!is(s0)でフィルターする。TLE5個。

# Main03
Main02を元に、HashSetを使わず、ArrayListに追加し、ソートしユニークにする。TLE5個。

# Main04
Main03を元に、TreeSet<String> strsを使わず、List<String> strsに追加し、ソートしユニークにする。TLE24個。

# Main05
Main01を元に、TreeSet<String> strsを使わず、List<String> strsに追加し、ソートしユニークにする。TLE10個。

# Main06
Main01を元に、dfs(String s)の代わりにdfs(char\[\] buf, int len)に変更する。TLE5個。

# Main07
Main06を元に、TreeSet<Integer> setの代わりにboolean\[\] flagに変更する。TLE2個。

# Main07b
Main07を元に、Set<String> strs = new HashSet<>(440640);を指定する。TLE2個。

# Main07c
Main07bを元に、if (buf[i+j] == buf[i+k-1-j]) {がelseのときbreak;で中断する。TLE2個。

# Main07d
Main07cを元に、Set<String> uniq = new HashSet<>(3628800);で重複を事前に省く。TLE2個。

# Main07e
Main07dを元に、calc(char[] buf)で10文字の文字列をlong値に変換し、Set<Long> uniq = new HashSet<>(3628800);で重複チェックする。TLE2個。

# Main08
Main07eを元に、List<String> strs = new ArrayList<>(3628800);に追加し、ソートしユニークにする。TLE2個。

# Main09
Main08を元に、Set<Long> strs = new HashSet<>(3628800);に変更する。TLE2個。

# Main10
Main09を元に、Set<Long> strs = new HashSet<>(3628800);を廃止し、long ansにカウントする。TLE1個。

# Main11
Main10を元に、void dfs(char[] buf, int len, long v)として、calc(char[] buf)を廃止。TLE1個。2075ms→2019ms。

# Main
Main11を元に、冒頭のArrays.sort(ary);を廃止。WA。2019ms→1998ms。

# Main\_fix
Mainのソースをリファクタリング。

# Main\_final
Main\_fixを元に、char[] aryとint[] flagから同じ文字の個数を管理し、Set<Long> uniqを廃止。WA。1998ms→247ms。
