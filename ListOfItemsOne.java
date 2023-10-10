/**
 * This heap implementation using a doubly linked list
 * isn't ordered by priority, but instead it finds the correct node with the
 * highest priority in the list upon calling the method remove().
 */
class ListOfItemsOne extends ListOfItems{

	/**
	 * Time complexity: O(1)
	 * @param p
	 * @param v
	 */
	void add(Integer p, Integer v) {
		Node n = new Node(p, v);
		if (head == null) {
			head = n;
		} else {
			head.prv = n;
			n.nxt = head;
			head = n;
		}
	}

	/**
	 * Time complexity: O(n)
	 * @return
	 */
	String remove() {
		if (head == null)
			return null;
		Node res = head;
		Node n = head.nxt;
		while (n != null) {
			if (res.priority.compareTo(n.priority) > 0)
				res = n;
			n = n.nxt;
		}
		if (res == head) {
			head = res.nxt;
			if (head != null)
				head.prv = null;
		} else {
			if (res.nxt != null)
				res.nxt.prv = res.prv;
			res.prv.nxt = res.nxt;
		}
		return "(" + res.priority + ":" + res.val + ")";
	}

}

