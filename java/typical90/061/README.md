# Main0
いつも使っているArrayDeque<Integer>で解けるかと思ったが、
内部は配列Object[] elements;なのに、これとint head;とint tail;を使って
添字int idxでアクセスする術がない。
Object[] toArray()でコピーを作るのもどうかと思ったので、
Iterator<E> iterator()で空回ししたが、TLE2個。

# Main
仕方ないので、使っているaddFirstとaddLastと欲しいgetを持つMyDequeを作る。
内部の配列を拡張するのが面倒なので、最初から200*1000の2倍のサイズを確保し、
head=tailを真ん中の100*1000にする。
addFirstが先にhead--;するので、100*1000回addFirstやaddLastしてもオーバーしないはず。
