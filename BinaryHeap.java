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
			swapNodes(null, n, p);									 	// From here we work with node i instead.
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



	String remove() {
		Node old_root = root;
		if (root.left == null && root.right == null)
			return root.stringify();

		//Node p = root;
		Node new_root = root;
		while (true) {
			if (new_root.left == null && new_root.right == null)
				break;
			if (new_root.right == null) { // We know that not both are null.
				new_root = new_root.left;
				continue;
			}

			if (new_root.left.children > new_root.right.children) { // Left only if ther's more or left side.
				new_root = new_root.left;
			} else { // Right is right and left has same amount of children (as right can't have the most).
				new_root = new_root.right;
			}
		}
		root = swapNodes(null, root, new_root);

		return "REMOVE: " + old_root.stringify();
	}



	/**
	 * Swap two nodes.
	 * @param a - First node
	 * @param b - Second node
	 */
	Node swapNodes (Node r, Node a, Node b) {
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

		return b;
	}

}

