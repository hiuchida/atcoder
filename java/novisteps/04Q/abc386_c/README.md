# Main
長さが同じなら、1文字づつ同じ添字で比較すればよい。
長さが2以上違う場合はNG。
長さが1違う場合、s>tの場合、ひっくり返した。

int i,j;で比較して一回だけ長い文字列のj=i+1する。
i--;して、j++;i++;でリトライさせている。