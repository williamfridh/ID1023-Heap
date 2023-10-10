class BinaryHeap {

	Node root;



	BinaryHeap () {
		root = null;
	}



    public void print() {
        if (root == null)
            System.out.println("Empty tree.");
        else
        	print("", root);
    }
    public void print(String d, Node n) {
        System.out.print(d);
        if (n != root)
            System.out.print("└───");
        System.out.print(n.stringify());
        System.out.println();
        if (n.left != null || n.right != null) {
            if (n.left != null) {
                if (n.right != null) {
                    print(d + "    ", n.left);
                } else {
                    print(d + "    ", n.left);
                }
            }
            if (n.right != null)
                print(d + "    ", n.right);
        }
    }



	private class Node {
		Node left, right;
		int children;

		int priority;

		Node (int p) {
			left = null;
			right = null;
			children = 0;
			priority = p;
		}

		String stringify () {
			return "[" + priority + ":" + children + "]";
		}

		void incrementChildren () {
			children++;
		}

		void decrementChildren () {
			children--;
		}

		boolean isLeaf() {
			return left == null && right == null;
		}

	}



	void add(Integer prio) {
		Node n = new Node(prio);
		if (root == null)
			root = n;
		else
			if (n.priority == root.priority)
				add(n, root, true);
			else
				add(n, root, false);
	}
	/**
	 * 
	 * @param n - Node to be inserted.
	 * @param p - Positioning node.
	 */
	Node add(Node n, Node p, boolean forceSkip) {
		if (n.priority <= p.priority && !forceSkip) {
			swapNodes(n, p);									 	// From here we work with node i instead.
			add(p, n, true);
			return n;												// Call the same method but with reversed roles.
		} else {													// Determin which branch to follow.
			p.incrementChildren();
			if (p.left == null) {									// Go left.
				p.left = n;
				return null;
			} else if (p.right == null) {							// Go right.
				p.right = n;
				return null;
			} else if (p.left.children <= p.right.children) {		// Allways go left before right
				if (n.priority <= p.left.priority)
					p.left = add(n, p.left, false);
				else
					add(n, p.left, false);
			} else {
				if (n.priority <= p.right.priority)
					p.right = add(n, p.right, false);
				else
					add(n, p.right, false);
			}
			return null;
		}
	}



	/**
	 * Locate the node at the "end".
	 * Unlink that node and set it as root.
	 * Sort from the root down.
	 */
	String remove() {
		Node old_root = root;
		if (root.isLeaf())
			return root.stringify();

		// Locate the end node, new root.
		Node new_root;
		Node p = root;
		while (true) {
			p.decrementChildren();
			if (p.right == null || p.left.children > p.right.children) { // Left only if ther's more or left side.
				if (p.left.isLeaf()) {
					new_root = p.left;
					p.left = null;
					//System.out.println(new_root.stringify());
					break;
				} else {
					p = p.left;
				}
			} else { // Right is right and left has same amount of children (as right can't have the most).
				if (p.right.isLeaf()) {
					new_root = p.right;
					p.right = null;
					break;
				} else {
					p = p.right;
				}
			}
		}

		// Set the new root.
		swapNodes(root, new_root);

		// Order from the root down.
		Node prev = null;
		p = root;
		while (!p.isLeaf() && (p.priority > p.left.priority || p.priority > p.right.priority)) {
			//System.out.println("P IS " + p.stringify());
			if (p.right == null || p.left.priority < p.right.priority) { // Left har higher priority.
				if (prev != null)
					prev.left = p.left;
				prev = p.left;
				swapNodes(p, p.left);
				//p = p.left;
				print();
				System.out.println("1.Prev = " + prev.stringify());
				System.out.println("1.NEW P: " + p.stringify());
			} else if (p.left.priority == p.right.priority) { // Same priority
				if (p.left.children > p.right.children) { // Left has more children, so go that way
					if (prev != null)
						prev.left = p.left;
					prev = p.left;
					swapNodes(p, p.left);
					//p = p.left;
					print();
					System.out.println("2.Prev = " + prev.stringify());
					System.out.println("2.NEW P: " + p.stringify());
				} else { // Right has more children, so go that way
					if (prev != null)
						prev.right = p.right;
					prev = p.right;
					swapNodes(p, p.right);
					//p = p.right;
					print();
					System.out.println("3.Prev = " + prev.stringify());
					System.out.println("3.NEW P: " + p.stringify());
				}
			} else { // Right has lower priority, so go that way
				//System.out.println(p.stringify() + " - " + p.right.stringify());
				if (prev != null)
					prev.right = p.right;
				prev = p.right;
				print();
				swapNodes(p, p.right);
				//p = p.right;
				print();
				System.out.println("4.Prev = " + prev.stringify());
				System.out.println("4.NEW P: " + p.stringify());
				//break;
			}
			//prev = p;
			//break;
			System.out.println("");
			System.out.println("");
		}
		return "REMOVE: " + old_root.stringify();
	}



	/**
	 * Swap two nodes.
	 * @param a - First node
	 * @param b - Second node
	 */
	void swapNodes (Node a, Node b) {
		Node left_tmp = a.left;
		Node right_tmp = a.right;
		int children_tmp = a.children;

		a.left = b.left;
		a.right = b.right;
		a.children = b.children;

		if (left_tmp == b)
			b.left = a;
		else
			b.left = left_tmp;

		if (right_tmp == b)
			b.right = a;
		else
			b.right = right_tmp;

		b.children = children_tmp;

		if (a == root)
			root = b;
	}

}

