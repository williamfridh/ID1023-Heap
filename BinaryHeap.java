class BinaryHeap {

	Node root;



	BinaryHeap () {
		root = null;
	}



    public void print() {
        if (root == null)
            System.out.println("Empty tree.");
        else
        	print("", root, 0);
    }
    public void print(String d, Node n, int side) {
        System.out.print(d);
        if (n != root)
            System.out.print("└───");
		if (side == 0)
    		System.out.print("   ");
		if (side == 1)
    		System.out.print("(L)");
		if (side == 2)
    		System.out.print("(R)");
        System.out.print(n.stringify());
        System.out.println();
        if (n.left != null || n.right != null) {
            if (n.left != null) {
                if (n.right != null) {
                    print(d + "       ", n.left, 1);
                } else {
                    print(d + "       ", n.left, 1);
                }
            }
            if (n.right != null)
                print(d + "       ", n.right, 2);
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
			swapNodes(p, n);									 	// From here we work with node i instead.
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
		if (root.isLeaf()) {
			root = null;
			return old_root.stringify();
		}

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
		sort(root);
		return old_root.stringify();

	}



	Node sort(Node p) {

		if (p.isLeaf() || ((p.left == null || p.priority < p.left.priority) &&( p.right == null || p.priority < p.right.priority)))
			return p;

		Node prev = p;

		if (p.right == null || p.left.priority < p.right.priority) { // Left har higher priority.
			p = p.left;
			swapNodes(prev, p);
			p.left = sort(p.left);
		} else if (p.left.priority == p.right.priority) { // Same priority
			if (p.left.children > p.right.children) { // Left has more children, so go that way
				p = p.left;
				swapNodes(prev, p);
				p.left = sort(p.left);
			} else { // Right has more children, so go that way
				p = p.right;
				swapNodes(prev, p);
				p.right = sort(p.right);
			}
		} else { // Right has lower priority, so go that way
			p = p.right;
			swapNodes(prev, p);
			p.right = sort(p.right);
		}
			
		return p;

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

