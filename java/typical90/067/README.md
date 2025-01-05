# Main0
n=8^20=2^60なので64bit longに取り込めると思っていたらRE。
一時的に10進数で取ろうとするので、10^20>2^66なので無理。

# Main
仕方ないので8進数は文字列で扱う。

nを数値で10進数に変換した場合
			long v = 0;
			long w = 1;
			while (n > 0) {
				int a=(int)(n%10);
				v+=a*w;
				w*=8;
				n/=10;
			}

sを文字列で10進数に変換した場合
			long v = 0;
			long w = 1;
			for (int j=s.length()-1; j>=0; j--) {
				char ch = s.charAt(j);
				int a=(int)(ch-'0');
				v+=a*w;
				w*=8;
			}

まあ似たようなものか。

vを数値で9進数に変換した場合
			w = 1;
			v = 0;
			while (n > 0) {
				int a=(int)(n%9);
				if (a==8) a=5;
				v+=a*w;
				w*=10;
				n/=9;
			}

sを文字列で9進数に変換した場合
			List<String> list = new ArrayList<>();
			while (n > 0) {
				int a=(int)(n%9);
				if (a==8) a=5;
				list.add(""+a);
				n/=9;
			}
			Collections.reverse(list);
			if (list.size() == 0) list.add(""+0);
			s = String.join("", list);

何気にList<Integer>よりList<String>の方が文字列にするのが楽だった。

# Main_final
解説を読んでいて、C言語のときは文字列を前から追加していくか、
固定の配列の末尾から追加するが、
javaなのでList<String>なんか使わなくても、s=a+s;だけでよかった。

			s = "";
			while (n > 0) {
				int a=(int)(n%9);
				if (a==8) a=5;
				s=a+s;
				n/=9;
			}
			if (s.length() == 0) s="0";
