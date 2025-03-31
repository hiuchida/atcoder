# Main
s,tを読み込み、char[] cs,ctに展開する。
is=0、it=0を用意し、is<cs.length && it<ct.lengthの間ループする。
chs=cs[is]、cht=ct[it]を用意し、chs!=chtならばng。
js=is+1とし、js<cs.lengthかつcs[js]==chsの間js++する。
同様にjt=it+1とし、ct[jt]==chtの間jt++する。
lens=js-is、lent=jt-itを計算し、
lens!=lentの場合、lens==1 || lens>lentのときにng。
is,itにjs,jtを入れる。
ループが終了したら、is==cs.length && it==ct.lengthのときにok。
それ以外はng。
