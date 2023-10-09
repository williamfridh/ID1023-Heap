class ListOfItems {

	Node head;

	ListOfItems() {
		head = null;
	}

	protected class Node {

		Integer priority;
		Integer val;
		Node nxt;
		Node prv;

		Node(Integer p, Integer v) {
			priority = p;
			val = v;
			nxt = null;
			prv = null;
		}

	}

	String stringify() {
		if (head == null)
			return null;
		String s = "";
		Node n = head;
		while (n != null) {
			s += "(" + n.priority + ":" + n.val + ") -> ";
			n = n.nxt;
		}
		return s;

	}

}

