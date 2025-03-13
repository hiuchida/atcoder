# Main01
List<List<Integer>> groupsにdfsで、既存グループへメンバー追加と、
新規グループ作成を列挙し、ary\[member\]をsumへ合計し、sumをxorでansを求め、
HashSetへ入れてカウントを取る。
TLE3個。

# Main02
List<Long> groupsにdfsで、既存グループの合計と、
新規グループ作成を列挙し、xorでansを求め、
HashSetへ入れてカウントを取る。
TLE3個。

# Main03
Main02からSet<Long> setをList<Long> setに変更して、
最後にソートしユニークを取った。
TLE5個。

# Main
Main03のユニークがset.remove(i);していて、先頭を削除するときにコストがかかるため、
List<Long> set2にユニークなリストを追加した。
