# Main
ローカルテストの入力例1でWAとなり、s += "X";の番兵を加えた

# Main\_list
2重ループを1重ループにして、List<Integer> listに'O'の個数を追加し、listのループで答えを出す

# Main2
Mainを番兵なし。
内側のループが'O'で終わった際に最後の値を足し、外側のループを終了させる。
内側のループが'X'で中断する際に、okをクリアする。

# Main\_list2
Main\_listを番兵なし。
最後が'O'のとき、最後のlist.add()が抜けるのでループ後にok>0のときに追加する。
