# Main01
sum = 0を用意し、
lを0からn-1までループし、
rをlからn-1までループし、
min = INT\_MAXを用意し、
iをlからrまでループし、
nums[i] < minの場合、min = nums[i]を入れる。

sum += minを加える。

sumを出力する。
TLE6個。

# Main02
sum = 0を用意し、
lを0からn-1までループし、
min = INT\_MAXを用意し、
rをlからn-1までループし、
nums[r] < minの場合、min = nums[r]を入れる。
sum += minを加える。

sumを出力する。
TLE6個。

# Main03
sum = 0、List<Integer> listを用意する。
lを0からn-1までループし、
min = nums[l]を用意し、
rをlからn-1までループし、
nums[r] < minの場合、min = nums[r]を入れる。
min == 1の場合、sum += n-rを加え、中断する。
min-2<list.size() && min-1 == list.get(min-2)の場合、sum += (n-r) * minを加え、中断する。
sum += minを加える。

listにnums[l]を追加し、ソートする。

sumを出力する。
TLE6個。

# Main
TreeSet<Integer> setを用意し、setに-1、nを追加する。
sum = 0を用意し、
lを0からn-1までループし、
p = idx[i]、l = set.lower(p)、h = set.higher(p)を入れ、
sum += (long)(i+1) * (p-l) * (h-p)を加え、
setにpを追加する。

sumを出力する。

# Main\_rmq01
RMQIndex rmqを用意し、rmq.update(i, v)に更新する。
sum = dfs(0, n-1)を呼び出し、sumを出力する。

dfsの中で、
l > rの場合、0を返す。
v = rmq.query(l, r)を呼び出し、sum = (long)v[0] * (v[1]-l+1) * (r-v[1]+1)を入れる。
sum += dfs(l, v[1]-1)、sum += dfs(v[1]+1, r)を呼び出し、sumを返す。

# Main\_rmq02
Main\_rmq01を元に、
RMQIndexのqueryの中で、末端の場合はメソッド呼び出ししない。
if (a == b) return new int[] { element[a+n-1], index[a+n-1] };

未使用のRMQでも同様に修正。
if (a == b) return element[a+n-1];

# Main\_2ary
int[] idxを用意し、ary\[i\]の添字iを入れる。
int\[\] pre,nxtを用意し、初めて最小値が出現する左端preと、右端nxtを管理する。
pre[i]=i-1、nxt[i]=i+1に初期化する。

ans=0を用意し、
iをnから1までループし、
p=idx[i]を入れ、
ans+=(long)i*(p-pre[p])*(nxt[p]-p)を加えて、
pre[nxt[p]]=pre[p]、nxt[pre[p]]=nxt[p]を入れる。

ansを出力する。

