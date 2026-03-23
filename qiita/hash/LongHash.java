import java.util.*;
/*
 */
public class LongHash {
	HashSet<Long> set=new HashSet<>();
	int bit;
	int rcnt;
	int size;
	long[] ary;
	long lmask;
	long hmask;
	LongHash(int bit, int rcnt) {
		this.bit = bit;
		this.rcnt = rcnt;
		size = 1 << bit;
		ary=new long[size];
		lmask = size - 1;
		hmask =~ lmask;
	}
	boolean contains(long key) {
		int kidx = (int)(key & lmask);
		for (int retry = 0; retry <= rcnt; retry++) {
			long val = ary[kidx];
			if ((val & 1) == 0) return false;
			long kval = (key & hmask) | (retry << 1) | 1;
			if (val == kval) return true;
			//隣を探す
			kidx = (int)((kidx + 1) & lmask);
		}
		return false;
	}
	boolean add(long key) {
		int kidx = (int)(key & lmask);
		int retry;
		for (retry = 0; retry <= rcnt; retry++) {
			long val = ary[kidx];
			if ((val & 1) == 0) break;
			long kval = (key & hmask) | (retry << 1) | 1;
			if (val == kval) break;
			//隣を探す
			kidx = (int)((kidx + 1) & lmask);
		}
		if (retry <= rcnt) {
			long kval = (key & hmask) | (retry << 1) | 1;
			ary[kidx] = kval;
			return true;
		}
		return false;
	}
	int size() {
		int cnt=0;
		for (int i=0; i<ary.length; i++) {
			if ((ary[i] & 1) == 1) cnt++;
		}
		return cnt;
	}
	void test() {
		long v1=0;
		long v2=0 | size;
		long v3=1 | size;
		boolean b11 = contains(v1);
		boolean b12 = add(v1);
		System.out.printf("1=%s %s %x %x %x\n",
				b11, b12, ary[0], ary[1], ary[2]);
		boolean b21 = contains(v2);
		boolean b22 = add(v2);
		System.out.printf("2=%s %s %x %x %x\n",
				b21, b22, ary[0], ary[1], ary[2]);
		boolean b31 = contains(v3);
		boolean b32 = add(v3);
		System.out.printf("3=%s %s %x %x %x\n",
				b31, b32, ary[0], ary[1], ary[2]);
	}
	long[] same=new long[1000];
	int sameidx=0;
	long nextLong(Random rand) {
		if (sameidx<same.length) {
			long v=rand.nextLong();
			same[sameidx++]=v;
			return v;
		}
		if (rand.nextBoolean()) {
			int idx=rand.nextInt(same.length);
			long v=same[idx];
			return v;
		}
		long v=rand.nextLong();
		return v;
	}
	void test(int cnt) {
		Random rand=new Random(0);
		long st=System.currentTimeMillis();
		for (int i=0; i<cnt; i++) {
			long v=nextLong(rand);
			if (!contains(v)) {
				if (!add(v)) set.add(v);
			}
		}
		long ed=System.currentTimeMillis();
		System.out.println((ed-st)+"ms");
		int s1=size();
		int s2=set.size();
		System.out.println("bit="+bit+" rcnt="+rcnt+" bufsize="+size+" s1="+s1+" s2="+s2+" s="+(s1+s2)+" cnt="+cnt
				+" use%="+(100.0*s1/size)
				+" err%="+(100.0*s2/(s1+s2)));
	}
	void test0(int cnt) {
		Random rand=new Random(0);
		long st=System.currentTimeMillis();
		for (int i=0; i<cnt; i++) {
			long v=nextLong(rand);
			if (!set.contains(v)) set.add(v);
		}
		long ed=System.currentTimeMillis();
		System.out.println((ed-st)+"ms");
		int s=set.size();
		System.out.println("s="+s+" cnt="+cnt);
	}
	public static void main(String[] args) {
		int bit=8;
		int rcnt=1;
		new LongHash(bit, rcnt).test();
		bit=20;
		
		int cnt=2*1000*1000;
		for (rcnt = 0; rcnt <= 3; rcnt++) {
			new LongHash(bit-1, rcnt).test(cnt);
			new LongHash(bit, rcnt).test(cnt);
			new LongHash(bit+1, rcnt).test(cnt);
		}
		new LongHash(bit, rcnt).test0(cnt);
		bit=24;
		cnt=20*1000*1000;
		for (rcnt = 0; rcnt <= 3; rcnt++) {
			new LongHash(bit-1, rcnt).test(cnt);
			new LongHash(bit, rcnt).test(cnt);
			new LongHash(bit+1, rcnt).test(cnt);
		}
		new LongHash(bit, rcnt).test0(cnt);
	}
}
