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
			double best_list_one = 0;
			double best_list_two = 0;
			double n0, n1, n2;
			ListOfItemsOne list_one;
			ListOfItemsTwo list_two;
			for (int i = 0; i < tries; i++) {
				Integer[] arr = generateShuffledArr(size);
				//Integer[] arr = generateDescendingArr(size);
				//Integer[] arr = new Integer[size];
				//for (int j = 0; j < size; j++)
				//	arr[j] = (Integer) j;
				list_one = new ListOfItemsOne();
				list_two = new ListOfItemsTwo();

				// Warm-up.
				for (Integer x : arr){
					list_one.add(x, x);
					list_one.remove();}

				for (Integer x : arr){
					list_two.add(x, x);
					list_two.remove();}

				// Test list one.
				n0 = System.nanoTime();
				for (Integer x : arr){
					list_one.add(x, x);
					list_one.remove();}
				n1 = System.nanoTime();
				best_list_one += n1 - n0;

				// Test list two.
				n0 = System.nanoTime();
				for (Integer x : arr){
					list_two.add(x, x);
					list_two.remove();}
				n1 = System.nanoTime();
				best_list_two += n1 - n0;

			}
			best_list_one = best_list_one/tries;
			best_list_two = best_list_two/tries;
			System.out.printf("%d\t&\t%.0f\t&\t\t%.0f\t&\t\t%.2f\n", size, best_list_one/1000, best_list_two/1000, best_list_one/best_list_two);
		}*/
		


		/**
		 * Testing binary heaps.
		 */
		/*
		BinaryHeap tree = new BinaryHeap();
		for (Integer i : generateShuffledArr(10))
			tree.add((int)i);
		tree.print();
		System.out.println("PUSH: " + tree.push(3));
		tree.print();
		for (int i = 0; i < 10; i ++)
			System.out.println(tree.remove());
		/*tree.add(15);
		tree.add(13);
		tree.add(14);
		tree.print();
		System.out.println("\n" + tree.remove() + "\n");
		tree.print();*/
		/* 
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
		System.out.println("---------------------------------");
		System.out.println("\n" + tree.remove() + "\n");
		//System.out.println("---------------------------------");
		System.out.println("\n" + tree.remove() + "\n");
		System.out.println("\n" + tree.remove() + "\n");
		System.out.println("\n" + tree.remove() + "\n");
		System.out.println("\n" + tree.remove() + "\n");
		System.out.println("\n" + tree.remove() + "\n");
		System.out.println("\n" + tree.remove() + "\n");
		System.out.println("\n" + tree.remove() + "\n");
		System.out.println("\n" + tree.remove() + "\n");
		System.out.println("---------------------------------");
		tree.print();
		//tree.print();
		//System.out.println("\n" + tree.remove() + "\n");
		//tree.print();
		//System.out.println("\n" + tree.remove() + "\n");
		//tree.print();*/

		/**
		 * Benchmarking binary heaps.
		 */
		/*
        Random rn = new Random();
		System.out.print("Size\t\tPush\t\tRemove & Add\t\tRatio(Remove & Add/Push)\n");
		int[] sizes = {10,20,40,80,160,320,640,1280,2560,5120,10240};
		int tries = 10000;
		for (int size : sizes) {
			System.gc();
			double best_push = Double.MAX_VALUE;
			double best_remove_add = Double.MAX_VALUE;
			double n0, n1, n2;
			BinaryHeap heap_one;
			BinaryHeap heap_two;
			for (int i = 0; i < tries; i++) {
				Integer[] arr = generateShuffledArr(size);
				heap_one = new BinaryHeap();
				heap_two = new BinaryHeap();

				// Test push.
				n0 = System.nanoTime();
				for (Integer x : arr)
					heap_one.add((int)x);
				for (Integer x : arr)
					heap_one.push(10 + rn.nextInt(91));
				n1 = System.nanoTime();
				n2 = n1 - n0;
				if (n2 < best_push)
					best_push = n2;

				// Test remove and add.
				n0 = System.nanoTime();
				for (Integer x : arr)
					heap_two.add((int)x);
				for (Integer x : arr) {
					BinaryHeap.Node tmp = heap_two.remove();
					tmp.incrPriority(10 + rn.nextInt(91));
					heap_two.add(tmp, heap_two.root, false);
				}
				n1 = System.nanoTime();
				n2 = n1 - n0;
				if (n2 < best_remove_add)
					best_remove_add = n2;

			}
			System.out.printf("%d\t&\t%.0f\t&\t\t%.0f\t&\t\t%.2f\n", size, best_push/1000, best_remove_add/1000, best_push/best_remove_add);
			
		}*/



		/**
		 * Testing array heap.
		 */
		/*
		ArrayHeap heap = new ArrayHeap();
		System.out.println(heap.stringify());

		for (Integer i : generateShuffledArr(64))
			heap.add((int)i);
/*
		heap.add(1);
		heap.add(3);
		heap.add(5);
		heap.add(6);
		heap.add(7);
		heap.add(9);
		heap.add(11);
		System.out.println(heap.stringify());
		heap.add(4);*/

		/*for (Integer i : generateShuffledArr(64))
			System.out.println("REMOVE: " + heap.remove() + " >> " + heap.stringify());
		*/



		/**
		 * Benchmark array heap.
		 * 
		Size            Time (us)       Rate of change
		40      &       2       &       1773.00
		80      &       4       &       2.23
		160     &       9       &       2.28
		320     &       21      &       2.28
		640     &       43      &       2.09
		1280    &       100     &       2.32
		2560    &       217     &       2.17
		5120    &       456     &       2.10
		10240   &       1020    &       2.24
		20480   &       2217    &       2.17
		 */
		System.out.print("Size\t\tTime (us)\tRate of change\t\tTime/Size\n");
		int[] sizes = {40,80,160,320,640,1280,2560,5120,10240,20480};
		int tries = 30000;
		double prev_best = 1;
		for (int size : sizes) {
			System.gc();
			double best = Double.MAX_VALUE;
			double n0, n1, n2;
			ArrayHeap heap;
			for (int i = 0; i < tries; i++) {
				Integer[] arr = generateShuffledArr(size);
				heap = new ArrayHeap();

				// Warm up.
				for (Integer x : arr)
					heap.add(x);
				for (Integer x : arr)
					heap.remove();

				// Test push.
				n0 = System.nanoTime();
				for (Integer x : arr)
					heap.add(x);
				for (Integer x : arr)
					heap.remove();
				n1 = System.nanoTime();
				n2 = n1 - n0;
				if (n2 < best)
					best = n2;

			}
			System.out.printf("%d\t&\t%.0f\t&\t%.2f\t&\t%.2f\n", size, best/1000, best/prev_best, best/size);
			prev_best = best;
			
		}

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

