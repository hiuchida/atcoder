# Main
sを数字として読み込み、lt=s/100、rt=s%100を用意する。
blt=(0<lt && lt<=12)、brt=(0<rt && rt<=12)を入れ、
blt && brtの場合"AMBIGUOUS"
brtの場合"YYMM"
bltの場合"MMYY"
それ以外の場合"NA"
を出力する。
