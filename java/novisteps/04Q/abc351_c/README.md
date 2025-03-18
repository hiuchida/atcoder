# Main
iを0からn-1までループする。
ary[i]をstackへpushする。
stack.size()が2以上の場合、末尾2個をpeekし、
y==zならば2個popし、y+1をpushする。
最後に残ったstack.size()を出力する。
