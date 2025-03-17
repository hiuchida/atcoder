# Main
sを受け取り、大文字に変換する。
iを0からs.length-1までループし、
idx=0からs.charAt(i)==t.charAt(idx)の場合idx++する。
idx==3となった時点でYes。
ループが終了し、idx==2でtの末尾が'X'の場合Yes。
それ以外はNo。
