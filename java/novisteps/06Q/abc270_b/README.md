# Main
x,y,zを読み込み、
x>=0の場合
x<y || y<0はxを出力する。
y<zは-1を出力する。
z<0のときans=-z、それ以外はans=zを用意し、ans+=x-zして、ansを出力する。
x<0の場合
x>y || y>0は-xを出力する。
y>zは-1を出力する。
z<0のときans=-z、それ以外はans=zを用意し、ans+=z-xして、ansを出力する。
