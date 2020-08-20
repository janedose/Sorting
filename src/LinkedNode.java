
public class LinkedNode<Integer> {
	
	private LinkedNode<Integer> next;
	private Integer elem;
	
	/**
	 * A constructor that initializes an Empty LinkedNode.
	 * @return
	 */
	public LinkedNode() {
		
	}
	
	/**
	 * A constructor that initializes an Empty LinkedNode with the given element.
	 */
	public LinkedNode(Integer theElem) {
		elem = theElem;
	}
	
	/**
	 * Return the next node.
	 * @return
	 */
	public LinkedNode<Integer> getNext() {
		return next;
	}
	
	/**
	 * Set next to point to the node.
	 * @param node
	 */
	public void setNext(LinkedNode<Integer> theNode) {
		next = theNode;
	}
	
	/**
	 * Return the element.
	 * @return
	 */
	public Integer getElement() {
		return elem;
		
	}
	
	/**
	 * Store the element in current node.
	 * @param element
	 */
	public void setElement(Integer theElem) {
		elem = theElem;
	}

}
