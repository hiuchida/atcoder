# Main
サイコロを6,5,6,5と出すのが最大値となる。
xを読み込み、ans=x/11、ans*=2、x%=11を計算する。
x>6の場合、ans++、x-=6を入れる。
x>0の場合、ans++、x-=5を入れる。
ansを出力する。
