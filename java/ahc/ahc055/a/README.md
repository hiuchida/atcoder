# Main00
すべて素手
0:1
150 770位タイ

# Main01
サンプル：bの武器でb+1を攻撃
0:2820
435030 751位タイ

## Main01b
リファクタリング：init,hand,weapon

# Main02
minhから素手、minh->maxaに武器と素手で攻撃
0:39749
6172349 670位PeaQaタイ

# Main03
minhから素手、minh->mintに武器と素手で攻撃
0:35413
5355829 700位

# Main04
Main03より
minhから素手、minh->mintに武器と素手で攻撃、残数があれば他にも武器で攻撃
0:37195
5704562 691位

# Main04b
minhから素手、minh->mintに武器と素手で攻撃、残数があれば他にも武器で攻撃
que.offer(next);のタイミング誤りFIX
0:37049
5705602 691位

# Main05
Main02より
minhから素手、minh->maxaに武器と素手で攻撃、残数があれば他にも武器で攻撃
0:43453
6823053 635位

# Main06
Main05より
minhから素手、minh->maxaに武器のみで攻撃、残数があれば武器のみで攻撃
0:51572
7954097 457位konnnahinaタイ

## Main06b
リファクタリング：while (ac[mini]>0) {に統合

# Main07
Main06より
maxaの判定をmin(ah[b], aa[w][b]);
0:51876
7967155 447位

# Main08
Main07より
weaponを1打ごと
0:51477
8028109 387位saito1417タイ P1403

# Main

