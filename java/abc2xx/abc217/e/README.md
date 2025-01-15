# Main01,Main02,Main03,Main04
自作MyDequeにremoveFirst()、sort()を追加した。
removeFirst()の中で-1を代入したが、ソートした際に先頭になるため。
しかし、末尾が0なので、sort()で(head,tail)の範囲を指定した。
Main01がTLE2個だったので、Main02でsort()をparallelSort()に変えたがTLE2個。

Main03,Main04でint[] ary;をInteger[] ary;に変えて、内部ソートアルゴリズムを変更したが、
ラッパークラスの逆効果でTLE4個。
