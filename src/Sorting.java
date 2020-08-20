import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Sorting {
	
	private LinkedNode<Integer> head;
	private int myGap;
	private int totalCMP = 0, totalEXCH = 0, totalPass = 0;
    private long time;
    private long totalTime = 0;
    private FileWriter myWriter;
	
    public Sorting() throws IOException {
    	myWriter =  new FileWriter("output.txt", true);
    }
    
	/**
	 * Implement the bubble sort on the linked list.
	 * @param first
	 * @throws IOException 
	 */
	public void bubbleSort(LinkedNode<Integer> first) throws IOException {
		long startTime = System.nanoTime();
		int pass = 0, cmp = 0, exch = 0;
		head = first;
		boolean swapped;
            do {
            	//setting up pointers
            	LinkedNode<Integer> prev = null;
            	LinkedNode<Integer> curr = first;
            	LinkedNode<Integer> next = first.getNext();
            	swapped = false;
                while (next != null) {
                	cmp++;
                    if (curr.getElement() > next.getElement()) {
                    	exch++;
                    	swapped = true;
                        if (prev != null) {
                        	
                        	//swapping
                        	LinkedNode<Integer> temp = next.getNext();
                            prev.setNext(next);
                            next.setNext(curr);
                            curr.setNext(temp);
                        } else {
                        	
                        	//swapping
                        	LinkedNode<Integer> temp = next.getNext();
                            first = next;
                            head = first;
                            next.setNext(curr);
                            curr.setNext(temp);
                        }
                        
                        //updating pointers
                        prev = next;
                        next = curr.getNext();
                    } else { 
                    	
                    	//updating pointers
                        prev = curr;
                        curr = next;
                        next = next.getNext();
                    }
                } 
                pass++;
            } while(swapped);
            long endTime = System.nanoTime();
            totalCMP += cmp;
            totalEXCH += exch;
            totalPass += pass;
            time = (endTime - startTime) / 1000000;
            totalTime += time;
            System.out.print("\t" + myGap + "\t" + pass + "\t" + cmp + "\t\t" + exch + "\t\t" + time + "\n");
            myWriter.append("\n\t" + myGap + "\t" + pass + "\t" + cmp + "\t\t" + exch + "\t\t" + time);
	}

	/**
	 * Implement the shell sort on the linked list.
	 * @param first head node
	 * @throws IOException 
	 */
	public void shellSort(LinkedNode<Integer> first, String file) throws IOException {

		head = first;
		int size = getSize(head);
		int pass = 0, cmp = 0, exch = 0;
		long startTime = System.nanoTime();
		boolean swapped;
		
        System.out.print("\n\na.out < " + file + '\n');
        System.out.println("\tk\tpass\tcmp\t\texch\t\ttime(ms)");
        System.out.println("---------------------------------------------------------------");
        
        myWriter.append("\n\na.out < " + file + '\n');
        myWriter.append("\n\tk\tpass\tcmp\t\texch\t\ttime(ms)");
        myWriter.append("\n---------------------------------------------------------------");
        
		//Calculate the gap
        while (myGap < size/3) {
        	myGap = 3 * myGap + 1; 
        }

		while(myGap > 1) {
        do {
        	
        	//setting up pointers
        	LinkedNode<Integer> prev = null;
        	LinkedNode<Integer> curr = first;
        	LinkedNode<Integer> other = first;
        	LinkedNode<Integer> prevOther = first;
        	for (int i = 0; i < myGap; i++) {
        		other = other.getNext();
        	}
        	for (int i = 0; i < myGap - 1; i++) {
        		prevOther = prevOther.getNext();
        	}
        	swapped = false;
        	
            while (other != null) {
            	cmp++;
                if (curr.getElement() > other.getElement()) {
                	exch++;
                	swapped = true;
                    if (prev != null) {
                    	
                    	//swapping
                        LinkedNode otherNextTemp = other.getNext();
                        other.setNext(curr.getNext());
                        prevOther.setNext(curr);
                        curr.setNext(otherNextTemp);
                        prev.setNext(other);
                        
                        //updating pointers
                        prev = other;
                        other = otherNextTemp;
                        prevOther = curr;
                        curr = prev.getNext();
                        
                    } else {
                    	
                    	//swapping
                        LinkedNode otherNextTemp = other.getNext();
                        first = other;
                        head = first;
                        other.setNext(curr.getNext());
                        prevOther.setNext(curr);
                        curr.setNext(otherNextTemp);
                        
                        //updating pointers
                        prev = other;
                        other = otherNextTemp;
                        prevOther = curr;
                        curr = prev.getNext();
                    }
                } else { 
                	
                	//updating pointers
                    prev = curr;
                    curr = curr.getNext();
                    prevOther = prevOther.getNext();
                    other = other.getNext();
                }
            } 
            pass++;
        } while(swapped);
        
        totalCMP += cmp;
        totalEXCH += exch;
        totalPass += pass;
        long endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000;
        totalTime += time;
        System.out.print("\t" + myGap + "\t" + pass + "\t" + cmp + "\t\t" + exch + "\t\t" + time + "\n");
        myWriter.append("\n\t" + myGap + "\t" + pass + "\t" + cmp + "\t\t" + exch + "\t\t" + time);
        
        //decrease gap
        myGap /= 3;
		}
        
		//if myGap == 1, sort becomes different. Would be faster with just bubblesort
		//because of constraints of singly linked list
        if(myGap == 1) {
        	shellSort2(first);
        }
        System.out.println("---------------------------------------------------------------");
        System.out.print("\tTotal" + "\t" + totalPass + "\t" + totalCMP + "\t\t" + 
        		totalEXCH + "\t\t" + totalTime + "\n");
        myWriter.append("\n---------------------------------------------------------------");
        myWriter.append("\n\tTotal" + "\t" + totalPass + "\t" + totalCMP + "\t\t" + 
        		totalEXCH + "\t\t" + totalTime + "\n");
        
		myWriter.close();
	}
	
	/**
	 * Implement the shell sort with Gap = 1 on the linked list.
	 * @param first
	 * @throws IOException 
	 */
	private void shellSort2(LinkedNode<Integer> first) throws IOException {
		head = first;
		int pass = 0, cmp = 0, exch = 0;
		long startTime = System.nanoTime();
		boolean swapped;
		do {

			//setting up pointers
			LinkedNode<Integer> prevprev = null;
			LinkedNode<Integer> prev = first;
			LinkedNode<Integer> curr = first.getNext();
//			LinkedNode<Integer> next = curr.getNext();
			swapped = false;
			while (curr != null) {
				cmp++;
				if (curr.getElement() < prev.getElement()) {
					exch++;
					swapped = true;
					LinkedNode<Integer> next = curr.getNext();
					
						//swapping
					if (prevprev != null) {
						curr.setNext(prev);
						prev.setNext(next);
						prevprev.setNext(curr);
						
						//updating pointers
						next = prev;
						prev = prevprev;
						
						//updating prevprev pointer
						if (prevprev == first) {
							prevprev = null;
						} else {
							LinkedNode<Integer> temp = first;
							int i = 0;
							prevprev = first;
							
							//hard to implement with singly linked list because no access to prev
							//so have to iterate through the linked list
							while (temp != prev) {
								temp = temp.getNext();
								i++;
							}
							for (int j = 0; j < i - 1; j++) {
								prevprev = prevprev.getNext();
							}
						}
					} else {
						
						//swapping
						curr.setNext(prev);
						prev.setNext(next);
						
						//updating pointers
						first = curr;
						head = first;
						prevprev = first;
						curr = next;
						next = next.getNext();
					}
				} else { 

					//updating pointers
					prevprev = prev;
					prev = curr;
					curr = curr.getNext();
				}
			} 
			pass++;
		} while(swapped);
		
		totalCMP += cmp;
        totalEXCH += exch;
        totalPass += pass;        
        long endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000;
        totalTime += time;
        
        System.out.print("\t" + myGap + "\t" + pass + "\t" + cmp + "\t\t" + exch + "\t\t" + time + "\n");
        myWriter.append("\n\t" + myGap + "\t" + pass + "\t" + cmp + "\t\t" + exch + "\t\t" + time);
	}  
	
	public void displaySortedList() {
		System.out.println("\nThe following is the sorted list: ");
		LinkedNode<Integer> curr = head;
		while (curr != null) {
			System.out.print(curr.getElement() + " ");
			curr = curr.getNext();
		}
		System.out.println();
	}
	
	/**
	 * Returns size of linkedlist.
	 * @param first head node
	 * @return size
	 */
	private int getSize(LinkedNode<Integer> first) {
		LinkedNode<Integer> curr = first;
		int size = 0;
		while (curr != null) {
			size++;
			curr = curr.getNext();
		}
		return size;
	}
	
}
