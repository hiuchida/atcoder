# Main
スキップする数列は配列では探しにくいので、TreeSet<Integer>に入れる。
dp配列に初期値1を入れ、i+1とi+2がスキップ対象でなければ、iの値を加える。
どこでオーバーフローするか分からないので、dp[i]が確定したループの先頭で
Mで割った余りを取る。
