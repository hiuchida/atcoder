# Main
ans=1を初期値として、rest=kとする。
空いていないrest<ary[i]の場合、ans++し、rest=kに戻し、i--しもう一度リトライする。
空いている場合、rest-=ary[i]をして空きを減らす。
