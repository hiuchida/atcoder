# Main01,Main02,Main03,Main04
自作MyDequeにremoveFirst()、sort()を追加した。
removeFirst()の中で-1を代入したが、ソートした際に先頭になるため。
しかし、末尾が0なので、sort()で(head,tail)の範囲を指定した。
Main01がTLE2個だったので、Main02でsort()をparallelSort()に変えたがTLE2個。

Main03,Main04でint[] ary;をInteger[] ary;に変えて、内部ソートアルゴリズムを変更したが、
ラッパークラスの逆効果でTLE4個。

# Main
c==3のときにソートせずに、PriorityQueue<Integer> pqに追加する。
c==2のとき、pqが空でなければpqの先頭を取り出し、空ならばqueの先頭を取り出す。
AC 755ms

# Main\_fix
MyHeapque_int20250428適用
AC 697ms

# Main\_seg
Main\_fixを元に、
MyDeque queとMyHeapque pqの代わりにSegmentTree stに変更。

SegmentTree_min20250429適用

idx=0、que=1を用意する。
c==1のときst.update(idx, x)、idx++を更新する。
c==2のときx=st.query(0, que)を呼び出し、x==INT\_MAXの場合que++して繰り返す。
v=st.findLeft(0, que, x)を入れ、st.update(v, Integer.MAX_VALUE)を更新する。
c==3のときidx>0の場合que=idxを入れる。
AC 777ms

# Main\_seg2
Main\_seg1を元に、findLeftをfindRightに変更。
AC 808ms

# Main\_fix2
MyHeapque_int20250504適用
AC 712ms

