# Main
t,uを読み込み、iを0からn-lenまでループし、
s=t.substring(i, i+len)を入れ、comp(s, u)の場合ok。
ループ終了したらng。

compの中で、
iを0からs.length()回ループし、s.charAt(i)!='?' && s.charAt(i)!=u.charAt(i)の場合falseを返す。
ループ終了したらtrueを返す。
