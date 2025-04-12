# Main
sを読み込み、char[] aryに展開する。
lt=0、rt=ary.length-1を用意し、lt<rtの間ループし、
ary[lt]=='0' && ary[rt]=='0'の場合、lt++、rt--し、それ以外は中断する。

lt<rt && ary[rt]=='0'の間ループし、rt--する。

lt>=rtの場合ok。
iをltからrt-1までループし、ary[i]!=ary[rt-i]の場合ng。
ループ終了したらok。
