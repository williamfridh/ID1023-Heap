/**
 * This heap implementation using a doubly linked list
 * it inserts the nodes into the list ordered by priority in a descending
 * order with lower being higher priority.
 */
class ListOfItemsTwo extends ListOfItems{

	/**
	 * Time complexity: O(n)
	 * @param p
	 * @param v
	 */
	void add(Integer p, Integer v) {
		//System.out.println("INSERT: (" + p + ":" + v + ")");
		Node n = new Node(p, v);
		if (head == null) {
			head = n;
		} else {
			Node i = head;
			if (n.priority.compareTo(i.priority) < 0) {
				n.nxt = i;
				i.prv = n;
				head = n;
				return;
			}
			while (i.nxt != null && i.priority.compareTo(p) < 0)
				i = i.nxt;
			i.nxt = n;
			n.prv = i;
		}
	}

	/**
	 * Time complexity: O(1)
	 * @return
	 */
	String remove() {
		if (head == null)
			return null;
		Node n = head;
		head = n.nxt;
		if (head != null)
			head.prv = null;
		return "(" + n.priority + ":" + n.val + ")";
	}

}

