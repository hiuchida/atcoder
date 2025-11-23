/**
 * @file 03_simulated_annealing.java
 * @brief
 * 貪欲法で初期解を求めた後、配達先の訪問順序を焼きなまし法で改善する解法プログラム
 */

import java.util.*;
import java.time.*;

/**
 * @brief 2次元座標上の点を表すクラス
 */
class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @brief 2点間のマンハッタン距離を計算する
     * @param p 距離を計算する点
     * @return 2点間のマンハッタン距離
     */
    public int dist(final Point p) {
        return Math.abs(x - p.x) + Math.abs(y - p.y);
    }
}

/**
 * @brief 入力データを表すクラス
 */
class Input {
    /**
     * @brief レストランの数 (=1000)
     */
    public int order_count;
    /**
     * @brief 選択する必要のある注文の数 (=50)
     */
    public int pickup_count;
    /**
     * @brief AtCoderオフィスの座標 (=(400, 400))
     */
    public Point office;
    /**
     * @brief レストランの座標の配列
     */
    public ArrayList<Point> restaurants;
    /**
     * @brief 目的地の座標の配列
     */
    public ArrayList<Point> destinations;

    public Input(
        int order_count,
        int pickup_count,
        Point office,
        ArrayList<Point> restaurants,
        ArrayList<Point> destinations
    ) {
        this.order_count = order_count;
        this.pickup_count = pickup_count;
        this.office = office;
        this.restaurants = restaurants;
        this.destinations = destinations;
    }

    /**
     * @brief 入力データを読み込む
     * @return 読み込んだ入力データ
     */
    public static Input read() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        int order_count = 1000;
        int pickup_count = 50;
        Point office = new Point(400, 400);
        ArrayList<Point> restaurants = new ArrayList<Point>();
        ArrayList<Point> destinations = new ArrayList<Point>();

        for (int i = 0; i < order_count; ++i) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int d = sc.nextInt();
            restaurants.add(new Point(a, b));
            destinations.add(new Point(c, d));
        }

        return new Input(order_count, pickup_count, office, restaurants, destinations);
    }
}

/**
 * @brief 出力データを表すクラス
 */
class Output {
    /**
     * @brief 移動距離の合計
     */
    public int dist_sum;
    /**
     * @brief 選択した注文のリスト
     */
    public ArrayList<Integer> order;
    /**
     * @brief 配達ルート
     */
    public ArrayList<Point> route;

    /**
     * @brief 出力データを構築する
     * @param orders 選択した注文のリスト
     * @param route 配達ルート
     */
    public Output(ArrayList<Integer> orders, ArrayList<Point> route) {
        this.order = orders;
        this.route = route;

        this.dist_sum = 0;
        for (int i = 0; i < route.size() - 1; ++i) {
            this.dist_sum += route.get(i).dist(route.get(i + 1));
        }
    }

    /**
     * @brief 解を出力する
     */
    public void print() {
        // 選択した注文の集合を出力する
        System.out.print(order.size());

        for (int i = 0; i < order.size(); ++i) {
            // 0-indexed -> 1-indexedに変更
            System.out.print(" " + (order.get(i) + 1));
        }

        System.out.println();

        // 配達ルートを出力する
        System.out.print(route.size());

        for (int i = 0; i < route.size(); ++i) {
            System.out.print(" " + route.get(i).x + " " + route.get(i).y);
        }

        System.out.println();
    }
}

class Main {
    /**
     * @brief 問題を解く関数
     * @param input 入力データ
     * @return 出力データ
     */
    public static Output solve_greedy(final Input input) {
        // 貪欲その2
        // 以下を順に実行するプログラム
        // 1.オフィスから距離400以下の注文だけを候補にする
        // 2.高橋君は最初オフィスから出発する
        // 3.訪問したレストランが50軒に達するまで、今いる場所から一番近いレストランに移動することを繰り返す
        // 4.受けた注文を捌ききるまで、今いる場所から一番近い配達先に移動することを繰り返す
        // 5.オフィスに帰る

        ArrayList<Integer> candidates = new ArrayList<Integer>(); // 注文の候補
        ArrayList<Integer> orders = new ArrayList<Integer>();     // 注文の集合
        ArrayList<Point> route = new ArrayList<Point>();          // 配達ルート

        // 1. オフィスから距離400以下の注文だけを候補にする
        for (int i = 0; i < input.order_count; i++) {
            if (input.office.dist(input.restaurants.get(i)) <= 400 && input.office.dist(input.destinations.get(i)) <= 400) {
                candidates.add(i);
            }
        }

        // 2.オフィスからスタート
        route.add(input.office);
        Point current_position = input.office; // 現在地
        int total_dist = 0;                    // 総移動距離

        // 3.訪問したレストランが50軒に達するまで、今いる場所から一番近いレストランに移動することを繰り返す

        // 同じレストランを2回訪れてはいけないので、訪問済みのレストランを記録する
        ArrayList<Boolean> visited_restaurant = new ArrayList<Boolean>();
        for (int i = 0; i < input.order_count; ++i) {
            visited_restaurant.add(false);
        }

        // pickup_count(=50)回ループ
        for (int i = 0; i < input.pickup_count; ++i) {
            // レストランを全探索して、最も近いレストランを探す
            int nearest_restaurant = 0;  // レストランの番号
            int min_dist = 1000000;      // 最も近いレストランの距離

            // 候補にした注文だけを調べる
            for (int j : candidates) {
                // 既に訪れていたらスキップ
                if (visited_restaurant.get(j)) {
                    continue;
                }
    
                // 最短距離が更新されたら記録
                int distance = current_position.dist(input.restaurants.get(j));
    
                if (distance < min_dist) {
                    min_dist = distance;
                    nearest_restaurant = j;
                }
            }

            // 最も近いレストラン(nearest_restaurant)に移動する
            current_position = input.restaurants.get(nearest_restaurant);

            // 注文の集合に選んだレストランを追加
            orders.add(nearest_restaurant);

            // 配達ルートに現在の位置を追加
            route.add(current_position);

            // 訪問済みレストランの配列にtrueをセット
            visited_restaurant.set(nearest_restaurant, true);

            // 総移動距離の更新
            total_dist += min_dist;

            // デバッグしやすいよう、標準エラー出力にレストランを出力
            // 標準エラー出力はデバッグに有効なので、AHCでは積極的に活用していきましょう
            Point restaurant_pos = input.restaurants.get(nearest_restaurant);
            System.err.println(i + "番目のレストラン: p_" + nearest_restaurant 
                                 + " = (" + restaurant_pos.x + ", " + restaurant_pos.y + ")");
        }

        // 4.受けた注文を捌ききるまで、今いる場所から一番近い配達先に移動することを繰り返す

        // 行かなければいけない配達先のリスト
        // ordersは最終的に出力しなければならないので、ここでコピーを作成しておく
        // 配達先を訪問したらこのリストから1つずつ削除していく
        ArrayList<Integer> destinations = new ArrayList<Integer>(orders);

        // pickup_count(=50)回ループ
        for (int i = 0; i < input.pickup_count; ++i) {
            // 配達先を全探索して、最も近い配達先を探す
            int nearest_index = 0;                                     // 配達先リストのインデックス
            int nearest_destination = destinations.get(nearest_index); // 配達先の番号
            int min_dist = 1000000;                                    // 最も近い配達先の距離

            // 0～999まで全探索するのではなく、50個のレストランに対応した配達先を全探索することに注意
            for (int j = 0; j < destinations.size(); ++j) {
                // 最短距離が更新されたら記録
                int distance = current_position.dist(input.destinations.get(destinations.get(j)));

                if (distance < min_dist) {
                    min_dist = distance;
                    nearest_index = j;
                    nearest_destination = destinations.get(j);
                }
            }

            // 最も近い配達先(nearest_destination)に移動する
            // 現在位置を最も近い配達先の位置に更新
            current_position = input.destinations.get(nearest_destination);

            // 配達ルートに現在の位置を追加
            route.add(current_position);

            // 配達先のリストから削除
            destinations.remove(nearest_index);

            // 総移動距離の更新
            total_dist += min_dist;

            // デバッグしやすいよう、標準エラー出力に配達先を出力
            Point destination_pos = input.destinations.get(nearest_destination);
            System.err.println(i + "番目の配達先: q_" + nearest_destination 
                                 + " = (" + destination_pos.x + ", " + destination_pos.y + ")");
        }

        // 5.オフィスに戻る
        route.add(input.office);
        total_dist += current_position.dist(input.office);

        // 合計距離を標準エラー出力に出力
        System.err.println("total distance: " + total_dist);

        return new Output(orders, route);
    }

    /**
     * @brief 経路の距離を計算する
     * @param route 経路
     * @return 経路の距離
     */
    public static int get_distance(final ArrayList<Point> route) {
        int dist = 0;
        for (int i = 0; i < route.size() - 1; ++i) {
            dist += route.get(i).dist(route.get(i + 1));
        }
        return dist;
    }

    /**
     * @brief 配達先の訪問順序を焼きなまし法で改善する関数（この関数を実装していきます）
     * @param input 入力データ
     * @param output_greedy 貪欲法で求めた出力データ
     * @return 出力データ
     */
    public static Output solve_simulated_annealing(final Input input, final Output output_greedy) {
        // 焼きなまし法
        // 「ある1つの配達先を訪問する順序を、別の場所に入れ替える」操作を繰り返すことで、経路を改善する

        // 貪欲法で求めた解をコピー(これを初期解とする)
        ArrayList<Integer> orders = new ArrayList<Integer>(output_greedy.order);
        ArrayList<Point> route = new ArrayList<Point>(output_greedy.route);

        // 現在の経路の距離を計算
        int current_dist = get_distance(route);

        // 乱数生成器を用意
        // 乱数のシード値は固定のものにしておくと、デバッグがしやすくなります
        Random rand = new Random(42);

        // 焼きなまし法の開始時刻を取得
        Instant start_time = Instant.now();

        // 制限時間(1.7秒)
        // 2秒ちょうどまでやるとTLEになるので、1.7秒程度にしておくとよい
        final int time_limit = 1700;

        // 開始温度と終了温度
        final double start_temperature = 2e2;
        final double end_temperature = 1e0;

        // 現在の温度
        double current_temperature = start_temperature;

        // 試行回数
        int iteration = 0;

        // 焼きなまし法の本体
        for (int kk=0; kk<1000*1000; kk++) {
//        while (true) {
            // 現在時刻を取得
            Instant current_time = Instant.now();

            // 制限時間になったら終了
            if (Duration.between(start_time, current_time).toMillis() >= time_limit) {
                break;
            }

            // 訪問先が配達先であるようなインデックスの中から、
            // 「i番目の訪問先をj番目に移動」する操作をランダムに選ぶことで、
            // ある配達先を訪れる順序を他の配達先の間に変える
            // 貪欲法で求めた解では、配達先の訪問順序は0-indexedで51番目～100番目であることに注意
            // (AtCoderオフィス、レストラン50軒、配達先50軒、AtCoderオフィスの順に並んでいる)

            // 訪問先が配達先であるようなインデックスの中から i, j をランダムに選ぶ
            int i = rand.nextInt(input.pickup_count) + input.pickup_count + 1;
            int j = rand.nextInt(input.pickup_count) + input.pickup_count + 1;

            // i番目の訪問先をj番目に移動する操作を行う
            Point point_to_move = route.remove(i);
            route.add(j, point_to_move);

            // 操作後の経路の距離を計算
            int new_dist = get_distance(route);

            // 【穴埋め】操作後の距離が操作前以下なら採用する
            // 【穴埋め】操作前より悪化していても、確率で採用する(悪化度合いが小さく、温度が高いほど採用されやすい)
            // 【ヒント】採用確率(0.0以上1.0未満)は Math.exp((current_dist - new_dist) / current_temperature) で計算できる
            // 【ヒント】rand.nextFloat() と書くと、0.0以上1.0未満の乱数が得られる
            if (new_dist<=current_dist || rand.nextFloat()<=Math.exp((current_dist - new_dist) / current_temperature)) {
                current_dist = new_dist;
            } else {
                // 採用されなかったら元に戻す
                route.remove(j);
                route.add(i, point_to_move);
            }

            // 試行回数のカウントを増やす
            // 進行状況を可視化するため、一定回数ごとに現在の試行回数と合計距離を標準エラー出力に出力
            iteration++;
            if (iteration % 100000 == 0) {
                System.err.println("iteration: " + iteration + ", total distance: " + current_dist);
            }

            // 現在の経過時間の割合を計算する
            double progress = (double) Duration.between(start_time, current_time).toMillis() / time_limit;
            // 【穴埋め】温度の更新
            // 【ヒント】現在の経過時間の割合に対する温度は Math.pow(start_temperature, 1.0 - progress) * Math.pow(end_temperature, progress) で計算できる
            current_temperature=Math.pow(start_temperature, 1.0 - progress) * Math.pow(end_temperature, progress);

            // ここまで穴埋めして実行できるようになったら、
            // 開始温度(start_temperature)と終了温度(end_temperature)を変えてみて、実行結果がどう変わるかを確認してみましょう
        }

        // 試行回数と合計距離を標準エラー出力に出力
        System.err.println("--- Result ---");
        System.err.println("iteration     : " + iteration);
        System.err.println("total distance: " + current_dist);

        return new Output(orders, route);
    }

    public static void main(String[] args){
        // 入力データを読み込む
        Input input = Input.read();

        // 問題を解く
        Output output_greedy = solve_greedy(input);
        Output output = solve_simulated_annealing(input, output_greedy);

        // 出力する
        output.print();
    }
}