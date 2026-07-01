	import java.util.Iterator;
	import java.util.NoSuchElementException;
/**
 * This is a dynamic implementation using type T to store elements.
 * Default initial capacity given 2.
 * @param <T> type of element in this dynamic array.
 */
public class DynamicArray<T> implements Iterable<T> {
	private static final int INITCAP = 2; //default initial capacity
	private T[] storage;
	private int size;
	/**
	 * constructor default initial capacity INITCAP of 2.
	 */
	@SuppressWarnings("unchecked")
	public DynamicArray() {
		storage = (T[]) new Object[INITCAP];
		size = 0; // starts with zero elements.
	}
	/**
	 * Return the current number of elements.
	 * @return the size of dynamic array.
	 */
	public int size() {
		return size;
	}
	/**
	 * return the capacity of the array.
 	 * @return Return the max number of elements before expansion.
	 */
	public int capacity() {
		return storage.length; //max number of elements.
	}
	/**
	 * Changes the item at the given index to be the given value.
	 * @param index Return the old item at that index.
	 * @param value value the new value to be set at the given index.
	 * @return old value at specific index.
	 * @throws IndexOutOfBoundsException For an invalid index.
	 */
	public T set(int index, T value) {
		if(index<0)
		{
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		} else if (index >= size) {

			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		}
		T oldItem = storage[index]; // change the item to given value.
		storage[index]=value;

		return oldItem;
	}
	/**
	 * Used the exception (and error message) described in set().
	 * @param index the index of the element to retrieve
	 * @return the item at the given index.
	 * @throws IndexOutOfBoundsException if the index is invalid.
	 */
	public T get(int index) {
		if(index<0)
		{
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		} else if (index >= size) {

			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		}
		return storage[index]; //return item in given index.
	}

	/**
	 * Appends a new element to the end of the array. If there is no space, the capacity is doubled.
	 * @param value to append to the array.
	 * @return accepted as valid values.
	 */
	@SuppressWarnings("unchecked")
	public boolean add(T value) {
		if (size == storage.length) {
			T[] newCapacity = (T[]) new Object[storage.length * 2]; // Double the capacity if no space available.

			System.arraycopy(storage, 0, newCapacity, 0, size);
		storage = newCapacity;
	}
		storage[size]=value; // Append the new value to the end of the array
		size++;
		
		return true; //accepted as valid values
	}

	/**
	 * Inserts the given value at the given index, Shifting the necessary elements.
	 * double the capacity if no space available.
	 * @param index to insert at the given index.
	 * @param value to insert at the given index.
	 * @throws IndexOutOfBoundsException if the index id not valid.
	 */
	@SuppressWarnings("unchecked")
	public void add(int index, T value) {
		if(index<0)
		{
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		} else if (index > size) {

			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		}
		if (size == storage.length) {
			T[] newCapacity = (T[]) new Object[storage.length * 2];

            System.arraycopy(storage, 0, newCapacity, 0, size);
			storage = newCapacity;
		}
		for (int i = size; i > index; i--) {
			storage[i] = storage[i - 1];
		}
		storage[index] = value;
		size++;

	}
	/**
	 * Remove and return the element at the given index,if the number of elements falls have storage capacity.
	 *
	 * @param index to index element remove.
	 * @return removed element.
	 * @throws IndexOutOfBoundsException when there is an invalid index.
	 */
	@SuppressWarnings("unchecked")
	public T remove(int index) {
		if(index<0)
		{
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		} else if (index >= size) {

			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		}
		T removedElem = storage[index];
		for(int i =index; i<size-1; i++)
		{
			storage[i] = storage[i+1];
		}
		size--;
		if(size <= storage.length / 3)
		{
			T[] newCapacity = (T[]) new Object[storage.length / 2];
            System.arraycopy(storage, 0, newCapacity, 0, size);
			storage =newCapacity;
		}
		return removedElem;
	}
	/**
	 * Returns an iterator to iterate over the elements.
	 *
	 * @return an iterator for dynamic array.
	 */
	public Iterator<T> iterator() {
		return new Iterator<>() {
			private int iteratorIndex = 0;
			@Override
			public boolean hasNext() {
				return iteratorIndex < size;
			}
			@Override
			public T next() {
				if (hasNext() == false) {
					throw new NoSuchElementException(" There are no elements in the array");
				}
				return storage[iteratorIndex++];
			}

		};
	}
	
	//******************************************************
	//*******     BELOW THIS LINE IS TESTING CODE    *******
	//*******      Edit it as much as you'd like!    *******
	//******************************************************

	/**
	 * Returns a string representation of the dynamic array for testing.
	 *
	 * @return a string representing the contents of the dynamic array
	 */
	public String toString() {
		StringBuilder s = new StringBuilder("Dynamic array with " + size()
			+ " items and a capacity of " + capacity() + ":");
		for (int i = 0; i < size(); i++) {
			s.append("\n  [").append(i).append("]: ").append(get(i));
		}
		return s.toString();
		
	}
	/**
	 * Main method for testing the DynamicArray class.
	 * This is a sample test for the DynamicArray.
	 */
	public static void main(String[] args){
		DynamicArray<Integer> ida = new DynamicArray<>();
		if ((ida.size() == 0) && (ida.capacity() == 2)){
			System.out.println("Yay 1");
		}

		boolean ok = true;
		for (int i=0; i<3;i++)
			ok = ok && ida.add(i*5);
		
		if (ok && ida.size()==3 && ida.get(2) == 10 && ida.capacity() == 4 ){
			System.out.println("Yay 2");
		}
		
		ida.add(1,-10);
		ida.add(4,100);
		if (ida.set(1,-20)==-10 && ida.get(2) == 5 && ida.size() == 5 
			&& ida.capacity() == 8 ){
			System.out.println("Yay 3");
		}
		
		if (ida.remove(0) == 0 && ida.remove(0) == -20 && ida.remove(2) == 100 
			&& ida.size() == 2 && ida.capacity() == 4 ){
			System.out.println("Yay 4");
		}

		System.out.print("Printing values: ");
		for(Integer i : ida) {
			System.out.print(i);
			System.out.print(" ");
		}

	}
}