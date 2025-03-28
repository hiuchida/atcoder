# Main01
Counter cにadd(a, i+1)を追加する。
l,r,xを読み込み、ll=search(l,x)とrr=search(r,x)を呼び出し、rr-llを出力する。
WA15個。

# Main
Main01を元に、searchl()とsearchr()を別々にする。
searchlは、ヒットしない場合は~xを返すが、
searchrは、ヒットしない場合は~x-1を返す。
searchlは、l<l0の場合、l=l0として扱う。0<=xl<=n
searchrは、r<r0の場合、r=r0-1として扱う。-1<=xr<n
このため個数はxr-xl+1を返す。
l=0,r=0の場合、xl=0,xr=-1のため0個。
l=n+1,r=n+1の場合、xl=n,xr=n-1のため0個。

