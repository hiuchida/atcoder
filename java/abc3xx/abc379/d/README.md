# Main
重複するのでList<Long> listに管理する。
クエリ2では、base += tする。
クエリ1では、baseを踏まえて、list.add(-base);する。
クエリ3では、ソートし、大きい方からlist.get(j)>=h-baseをカウントし、取り除く。

# Main_linkedlist
listは常に降順に並ぶので、毎回ソートを止め、LinkedList<Long> listに変更して、
先頭からlist.peekFirst()>=h-baseをカウントし、取り除く。
