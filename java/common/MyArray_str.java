	static class MyArray { //MyArray_str20250505
		String[] array;
		int size=0;
		MyArray() {
			this(100);
		}
		MyArray(int n) {
			this.array = new String[n + 1];
		}
		int size() {
			return size;
		}
		String get(int idx) {
			return array[idx];
		}
		String peek() {
			return array[size-1];
		}
		void add(String v) {
			if (array.length == size) array = Arrays.copyOf(array, size * 2);
			array[size++] = v;
		}
		String remove() {
			if (size == 0) return null;
			String x=array[--size];
			array[size]=null;
			return x;
		}
		void sort() {
			Arrays.sort(array, 0, size);
		}
		void unique() {
			if (size==0) return;
			int idx=1;
			for (int i=1; i<size; i++) {
				if (!array[i-1].equals(array[i])) array[idx++]=array[i];
			}
			for (int i=idx; i<size; i++) array[i]=null;
			size=idx;
		}
		int binarySearch(String key) {
			return Arrays.binarySearch(array, 0, size, key);
		}
		void trimToSize() {
			if (size < array.length) array = Arrays.copyOf(array, size);
		}
		String[] toArray() {
			String[] ans=new String[size];
			System.arraycopy(array, 0, ans, 0, size);
			return ans;
		}
		String join(String delimiter) {
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<size; i++) {
				if (i>0) sb.append(delimiter);
				sb.append(array[i]);
			}
			return sb.toString();
		}
		@Override
		public String toString() {
			return Arrays.toString(array) + " " + size;
		}
	}
