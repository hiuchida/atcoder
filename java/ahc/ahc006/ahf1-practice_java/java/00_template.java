/**
 * @file 00_template.java
 * @brief AtCoder Heuristic First-step Vol.1のJava向けテンプレートファイル
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
        // サンプル解法
        // 以下を順に実行するプログラム
        // 1.高橋君は最初オフィスから出発する
        // 2.0番目のレストラン, 0番目の配達先, ..., 49番目のレストラン, 49番目の配達先の順に移動する
        // 3.オフィスに帰る

        ArrayList<Integer> orders = new ArrayList<Integer>(); // 注文の集合
        ArrayList<Point> route = new ArrayList<Point>();      // 配達ルート

        // 1.オフィスからスタート
        route.add(input.office);
        Point current_position = input.office; // 現在地
        int total_dist = 0;                    // 総移動距離

        // 2.レストランと配達先を50箇所（pickup_count）ずつ巡る
        for (int i = 0; i < input.pickup_count; ++i) {
            // 次のレストランに移動する
            // 注文の集合にi番目のレストランを追加
            orders.add(i);

            // 配達ルートにi番目のレストランの位置を追加
            route.add(input.restaurants.get(i));

            // 総移動距離の更新
            total_dist += current_position.dist(input.restaurants.get(i));

            // 現在位置をi番目のレストランの位置に更新
            current_position = input.restaurants.get(i);

            // 次の配達先に移動する
            // 配達ルートにi番目の配達先の位置を追加
            route.add(input.destinations.get(i));

            // 総移動距離の更新
            total_dist += current_position.dist(input.destinations.get(i));

            // 現在位置を最も近い配達先の位置に更新
            current_position = input.destinations.get(i);
        }

        // 3.オフィスに戻る
        route.add(input.office);
        total_dist += current_position.dist(input.office);

        // 合計距離を標準エラー出力に出力
        // 標準エラー出力はデバッグに有効なので、AHCでは積極的に活用していきましょう
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