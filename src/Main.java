import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	static LinkedNode<Integer> head;
	
	public static void main(String[] args) throws IOException {
		readValues("test.txt");
		displayList(head);
//		Sorting bs = new Sorting();
//		LinkedNode<Integer> headCopy = head;
//		bs.bubbleSort(headCopy);
//		bs.displaySortedList();
		Sorting ss = new Sorting();
		ss.shellSort(head, "test.txt");
		ss.displaySortedList();
//		head = null;
//		readValues("random1000.txt");
//		displayList(head);
//		Sorting ss2 = new Sorting();
//		ss2.shellSort(head, "random1000.txt");
//		ss2.displaySortedList();
//		head = null;
//		readValues("random10000.txt");
//		displayList(head);
//		Sorting ss3 = new Sorting();
//		ss3.shellSort(head, "random10000.txt");
//		ss3.displaySortedList();
	}
	
	public static void readValues(String thePath) throws FileNotFoundException {
		File text = new File(thePath);
        Scanner scnr = new Scanner(text);
        int first = scnr.nextInt();
        LinkedNode<Integer> firstNode = new LinkedNode<Integer>(first);
        head = firstNode;
        while(scnr.hasNextInt()){
        	int value = scnr.nextInt();
        	LinkedNode<Integer> node = new LinkedNode<Integer>(value);
        	firstNode.setNext(node);
            firstNode = firstNode.getNext();
        }   
        scnr.close();
	}
	
	public static void displayList(LinkedNode<Integer> theHead) {
		System.out.println("\nThe following is the list: ");
		LinkedNode<Integer> curr = theHead;
		while (curr != null) {
			System.out.print(curr.getElement() + " ");
			curr = curr.getNext();
		}
		System.out.println();
	}

}
