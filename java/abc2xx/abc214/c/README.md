# Main
1つ前のt\[i-1\]とans\[i-1\]の最小値にs\[i-1\]を加えた値とt\[i\]の最小値で更新する。
0からn-1までループした後、n-1から0への更新の可能性があるため、2セットループする。
ans\[\]を初期化せず0のままで正解していた。

# Main\_fix
ans\[\]をMAX\_INTで初期化する。
1つ前のt\[i-1\]とans\[i-1\]の最小値にs\[i-1\]を加えた値とt\[i\]の最小値で更新する。
0からn-1までループした後、n-1から0への更新の可能性があるため、2セットループする。

# Main_PriorityQueue
PriorityQueueに確定したansとiを登録する。
queから取り出したansが更新する場合、次のque(ans+s\[i\],i+1)を登録する。
