# Main
sx,sy,gx,gyを読み込み、sx>gxの場合、sx,gxとsy,gyをスワップする。
sy=-sy、grad=(double)(gy-sy)/(gx-sx)、inte=sy-grad*sxを計算する。
-1*inte/gradを出力する。

ax+b=yよりy=0を解くと、x=-b/a。
