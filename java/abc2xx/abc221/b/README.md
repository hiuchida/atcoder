# Main01
文字コードが異なる位置を記憶し、隣合う位置で異なった場合にスルーしたが、
実際に入れ替わっているかどうか確認していなかった。
WA3個。

# Main02
文字コードが異なる位置を記憶し、隣合う位置で異なった場合にスルーしたが、
入れ替わっているかどうかの判定がtrue/false反対だった。
WA6個。

# Main02\_fix
文字コードが異なる位置を記憶し、隣合う位置で異なった場合にスルーしたが、
入れ替わっているかどうかの判定を修正した。
また、文字コードが異なった場合、次の文字コードが一致していた場合、okとなっていたのでngとした。
abb
acb

# Main
Main02から大幅に書き直す。
iの位置で文字コードが異なった場合、その場でiとi+1の文字コードを比較する。
