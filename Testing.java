import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

class Testing {

	public static void main(String[] args) {

		/**
		 * Testing of the two types of lists.
		 */
		/*
		ListOfItemsOne list_one_t = new ListOfItemsOne();

		list_one_t.add(1,11);
		list_one_t.add(2,22);
		list_one_t.add(0,44);
		list_one_t.add(6,33);

		System.out.println(list_one_t.stringify());
		System.out.println(list_one_t.remove());
		System.out.println(list_one_t.stringify());
		System.out.println(list_one_t.remove());
		System.out.println(list_one_t.stringify());
		System.out.println(list_one_t.remove());
		System.out.println(list_one_t.stringify());
		System.out.println(list_one_t.remove());
		System.out.println(list_one_t.stringify());

		System.out.println("-------------------------------------------");

		ListOfItemsTwo list_two_t = new ListOfItemsTwo();

		list_two_t.add(1,11);
		list_two_t.add(2,22);
		list_two_t.add(0,44);
		list_two_t.add(6,33);

		System.out.println(list_two_t.stringify());
		System.out.println(list_two_t.remove());
		System.out.println(list_two_t.remove());
		System.out.println(list_two_t.remove());
		System.out.println(list_two_t.remove());
		System.out.println(list_two_t.stringify());
		*/



		/**
		 * Benchmarking the two types of list. Trough adding and removing n many elements.
		 * 
		 * Worst case:
			Size            List Type 1(us)         List Type 2(us)         Ratio(L1/L2)
			20      &       22      &               21      &               1.02
			40      &       23      &               22      &               1.08
			80      &       29      &               23      &               1.28
			160     &       56      &               26      &               2.18
			320     &       126     &               31      &               4.06
			640     &       423     &               41      &               10.25
			1280    &       1613    &               62      &               26.14

		 */
		/*
		System.out.print("Size\t\tList Type 1(us)\t\tList Type 2(us)\t\tRatio(L1/L2)\n");
		int[] sizes = {10,20,40,80,160,320,640,1280,2560,5120,10240};
		int tries = 10000;
		for (int size : sizes) {
			System.gc();
			double best_list_one = Double.MAX_VALUE;
			double best_list_two = Double.MAX_VALUE;
			double n0, n1, n2;
			ListOfItemsOne list_one;
			ListOfItemsTwo list_two;
			for (int i = 0; i < tries; i++) {
				Integer[] arr = generateDescendingArr(size);
				list_one = new ListOfItemsOne();
				list_two = new ListOfItemsTwo();

				// Warm-up.
				for (Integer x : arr)
					list_one.add(x, x);
				for (Integer x : arr)
					list_one.remove();

				for (Integer x : arr)
					list_two.add(x, x);
				for (Integer x : arr)
					list_two.remove();

				// Test list one.
				n0 = System.nanoTime();
				for (Integer x : arr)
					list_one.add(x, x);
				for (Integer x : arr)
					list_one.remove();
				n1 = System.nanoTime();
				n2 = n1 - n0;
				if (n2 < best_list_one)
					best_list_one = n2;

				// Test list two.
				n0 = System.nanoTime();
				for (Integer x : arr)
					list_two.add(x, x);
				for (Integer x : arr)
					list_two.remove();
				n1 = System.nanoTime();
				n2 = n1 - n0;
				if (n2 < best_list_two)
					best_list_two = n2;

			}
			System.out.printf("%d\t&\t%.0f\t&\t\t%.0f\t&\t\t%.2f\n", size, best_list_one/1000, best_list_two/1000, best_list_one/best_list_two);
		}
		*/

		BinaryHeap tree = new BinaryHeap();
		tree.add(6);
		tree.add(8);
		tree.add(10);
		tree.add(7);
		tree.add(7);
		tree.add(7);
		tree.add(13);
		tree.add(14);
		tree.add(15);
		tree.print();
		System.out.println("\n" + tree.remove() + "\n");
		tree.print();
		System.out.println("\n" + tree.remove() + "\n");
		tree.print();
		System.out.println("\n" + tree.remove() + "\n");
		tree.print();

	}

    static Integer[] generateDescendingArr(int size) {
		Integer[] arr = new Integer[size];
		for (int i = 0; i < size; i++)
			arr[i] = size - i - 1;
		return arr;
    }

    static Integer[] generateShuffledArr(int size) {
		Integer[] arr = new Integer[size];
		for (int i = 0; i < arr.length; i++)
			arr[i] = i;
        Random rn = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int t = arr[i];
            int rn_int = rn.nextInt(arr.length - 1);
            arr[i] = arr[rn_int];
            arr[rn_int] = t;
        }
		return arr;
    }
	
}

