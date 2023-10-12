import java.util.Arrays;

class ArrayHeap {

	private int[] arr;
	private int end;



	ArrayHeap() {
		arr = new int[4];
		end = -1;
	}



	/**
	 * Print the queue for debugging.
	 * 
	 * @return string displaying queue.
	 */
	public String stringify() {
		if (end < 0)
			return "[]";
		String s = "[";
		for (int i = 0; i <= end; i++)
			s += arr[i] + ", ";
		s = s.substring(0, s.length() - 2);
		return s + "]";
	}


	
	public void add(int item) {
		if (end + 1 > arr.length - 1)
			resizeArr(true);
		arr[++end] = item;
		addHeapify(end);
	}

	private void addHeapify(int i) {
		if (end == 0)
			return;
		int p;
		if (i % 2 == 0) // Even.
			p = (i - 2) / 2;
		else // Odd.
			p = (i - 1) / 2;
		if (p > -1 && arr[i] < arr[p]) {
			swap(i, p);
			addHeapify(p);
		}
	}

	private void swap(int a, int b) {
		int t = arr[a];
		arr[a] = arr[b];
		arr[b] = t;
	}


	public Integer remove() {

		if (end < 0)
			return null;

		if (end - 1 <= arr.length / 4 && arr.length > 4)
			resizeArr(false);

		int old_root = arr[0];

		arr[0] = arr[end--];

		removeHeapify();

		return old_root;
	}

	private void removeHeapify() {
		removeHeapify(0);
	}
	private void removeHeapify(int i) {
		int left = i*2 + 1;
		int right = left + 1;

		if (left > end || (arr[left] > arr[i] && (right > end || arr[right] > arr[i])))
			return;

		if (right > end || arr[left] < arr[right]) { // Left.
			swap(i, left);
			removeHeapify(left);
		} else { // Right.
			swap(i, right);
			removeHeapify(right);
		}

	}



	/**
	 * Adjust array size.
	 */
	private void resizeArr(boolean grow) {
		int len;
		if (grow)
			len = arr.length * 2;
		else
			len = arr.length / 2;
		int[] newArr = new int[len];
		for (int i = 0; i <= end; i++)
			newArr[i] = arr[i];
		arr = newArr;
		/*if (grow)
			System.out.println("# Increased arr size from " + arr.length / 2 + " to " + arr.length);
		else 
			System.out.println("# Decreased arr size from " + arr.length * 2 + " to " + arr.length);*/
	}

}