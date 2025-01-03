# Main01,Main02,Main03
2020年10月に提出してTLE12個。
この当時はUnionFindを知らなかったため、Node[] nodesを行ったり来たり。

# Main
UnionFindを使って作り直し。

04_kill_overflow_0は、きっとTreeMap<Integer, Long> mapがIntegerで値を持っていると、
10^9の差の積み重ねで2^31を越えるのだろう。
