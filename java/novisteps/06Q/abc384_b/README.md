# Main
d==1とd==2と異なる条件で判定する。

} else {が} else if (d == 2) {を省略したもののため、
if (d == 1 && (1600 <= r && r <= 2799)) r += a;
else if (d == 2 && (1200 <= r && r <= 2399)) r += a;
と書き換えられるが、1行に収めるメリットがあまりない。

} else {のまま、
else if (1200 <= r && r <= 2399) r += a;
とすると、d == 1 && 1200 <= r && r <= 1599が紛れ込む。
