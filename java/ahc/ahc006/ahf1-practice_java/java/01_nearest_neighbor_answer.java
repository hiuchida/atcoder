/**
 * @file 01_nearest_neighbor_answer.java
 * @brief
 * 今いる点から最も近いレストランに行くことを50回繰り返し、その後今いる点から最も近い目的地に行くことを50回繰り返す解法プログラム
 */

import java.util.*;

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
     * @brief 問題を解く関数（この関数を実装していきます）
     * @param input 入力データ
     * @return 出力データ
     */
    public static Output solve(final Input input) {
        // 貪欲その1
        // 以下を順に実行するプログラム
        // 1.高橋君は最初オフィスから出発する
        // 2.訪問したレストランが50軒に達するまで、今いる場所から一番近いレストランに移動することを繰り返す
        // 3.受けた注文を捌ききるまで、今いる場所から一番近い配達先に移動することを繰り返す
        // 4.オフィスに帰る

        ArrayList<Integer> orders = new ArrayList<Integer>(); // 注文の集合
        ArrayList<Point> route = new ArrayList<Point>();      // 配達ルート

        // 1.オフィスからスタート
        route.add(input.office);
        Point current_position = input.office; // 現在地
        int total_dist = 0;                    // 総移動距離

        // 2.訪問したレストランが50軒に達するまで、今いる場所から一番近いレストランに移動することを繰り返す

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

            for (int j = 0; j < input.order_count; ++j) {
                // 【穴埋め】既に訪れていたらスキップ
                if (visited_restaurant.get(j)) {
                    continue;
                }
    
                // 【穴埋め】最短距離が更新されたら記録
                // 【ヒント】int distance = p0.dist(p1); と書くと、p0とp1のマンハッタン距離が計算できる
                // 【ヒント】nearest_restaurant, min_distの2つを更新する
                int distance = current_position.dist(input.restaurants.get(j));
    
                if (distance < min_dist) {
                    min_dist = distance;
                    nearest_restaurant = j;
                }
            }

            // 最も近いレストラン(nearest_restaurant)に移動する
            // 【穴埋め】現在位置を最も近いレストランの位置に更新
            current_position = input.restaurants.get(nearest_restaurant);

            // 【穴埋め】注文の集合に選んだレストランを追加
            orders.add(nearest_restaurant);

            // 【穴埋め】配達ルートに現在の位置を追加
            route.add(current_position);

            // 【穴埋め】訪問済みレストランの配列にtrueをセット
            visited_restaurant.set(nearest_restaurant, true);

            // 総移動距離の更新
            total_dist += min_dist;

            // デバッグしやすいよう、標準エラー出力にレストランを出力
            // 標準エラー出力はデバッグに有効なので、AHCでは積極的に活用していきましょう
            Point restaurant_pos = input.restaurants.get(nearest_restaurant);
            System.err.println(i + "番目のレストラン: p_" + nearest_restaurant 
                                 + " = (" + restaurant_pos.x + ", " + restaurant_pos.y + ")");
        }

        // 【ヒント】ここまで穴埋めできたら、正しく動くか一度実行してみましょう！

        // 3.受けた注文を捌ききるまで、今いる場所から一番近い配達先に移動することを繰り返す

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
                // 【穴埋め】最短距離が更新されたら記録
                // 【ヒント】nearest_index, nearest_destination, min_distの3つを更新する
                int distance = current_position.dist(input.destinations.get(destinations.get(j)));

                if (distance < min_dist) {
                    min_dist = distance;
                    nearest_index = j;
                    nearest_destination = destinations.get(j);
                }
            }

            // 最も近い配達先(nearest_destination)に移動する
            // 【穴埋め】現在位置を最も近い配達先の位置に更新
            current_position = input.destinations.get(nearest_destination);

            // 【穴埋め】配達ルートに現在の位置を追加
            route.add(current_position);

            // 【穴埋め】配達先のリストから削除
            destinations.remove(nearest_index);

            // 総移動距離の更新
            total_dist += min_dist;

            // デバッグしやすいよう、標準エラー出力に配達先を出力
            Point destination_pos = input.destinations.get(nearest_destination);
            System.err.println(i + "番目の配達先: q_" + nearest_destination 
                                 + " = (" + destination_pos.x + ", " + destination_pos.y + ")");
        }

        // 4.オフィスに戻る
        route.add(input.office);
        total_dist += current_position.dist(input.office);

        // 合計距離を標準エラー出力に出力
        System.err.println("total distance: " + total_dist);

        return new Output(orders, route);
    }

    public static void main(String[] args){
        // 入力データを読み込む
        Input input = Input.read();

        // 問題を解く
        Output output = solve(input);

        // 出力する
        output.print();
    }
}