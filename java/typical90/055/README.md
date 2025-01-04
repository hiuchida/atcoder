# Main01
きっとオーバーフローするだろうと投げたら、WA1個、TLE21個。

# Main02
毎回pで余りを取るようにした。TLE21個。
long s1 = s * ary[i] % p;

# Main
TreeSet<Integer> setでは遅いのかと思い、
スタックが欲しいのでDeque<Integer> queでaddLastとremoveLastする。

以下のque.toString()が抑制できないので、コメントアウトする。
DEBUG(que + " " + s + " " + s%p);

# Main_set
Main02でデバッグログを抑止するため、コメントアウトする。
DEBUG(set + " " + s + " " + s%p);

Deque版：793ms
TreeSet版：1728ms
TreeSet版（デバッグログ）：5532ms

インスタンスは、この中でif文がtrueのときのみ、toString()するつもりが、
呼び出し元でtoString()してはダメ。
	static void DEBUG(Object x) {
		if (DEBUG) System.out.println(x);
	}
