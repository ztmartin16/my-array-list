import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * The class MyArrayList implements the List211 interface given online. It
 * contains an initial capacity of 10, an array of type E named data, and an
 * initial size of 0.
 * 
 * @author ZacharyMartin
 * @param <E>
 */

public class MyArrayList<E> implements List211<E>, Iterable<E> {
	private static final int INITIAL_CAPACITY = 10;
	private E[] data;
	private int size = 0;
	private int capacity;

	/**
	 * Creates the iterator for the ArrayList class, and implements a few
	 * methods to help the iterator be more effective.
	 * 
	 * @author ZacharyMartin
	 *
	 */
	public class ArrayListIterator implements ListIterator<E> {
		private int index = 0;

		@Override
		/**
		 * hasNext shows if there is an item at the next index.
		 * 
		 * @return false if the index is greater than the size or less than 0.
		 * @return true if index if within the array size.
		 */
		public boolean hasNext() {
			if (index >= size || index < 0) {
				return false;
			} else {
				return true;
			}
		}

		@Override
		/**
		 * next returns the next value in the array.
		 * 
		 * @return the index if there is a next element in the array.
		 * @exception element exception if there is no such element next in the
		 *            array.
		 */
		public E next() {
			if (hasNext()) {
				return get(index++);
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		/**
		 * The following are methods not needed in the assignment.
		 */
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public E previous() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}

		@Override
		public void set(E e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void add(E e) {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * Sorts the list.
	 * 
	 * @param compare
	 */
	public void insertionSort(Comparator<? super E> compare) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < size; i++) {
			E temp = data[i];
			int j = i;
			while (j != 0 && compare.compare(temp, data[j - 1]) < 0) {
				data[j] = data[j - 1];
				j--;
			}
			data[j] = temp;
		}
		// for (int i = 0; i < size; i++) {
		// sb.append(data[i]);
		// sb.append(",");
		// System.out.println(sb.toString());
		// }
	}

	/**
	 * Sorts the list.
	 * 
	 * @param compare
	 */
	public void bubbleSort(Comparator<? super E> compare) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size - 1; j++) {
				if (compare.compare(data[j], data[j + 1]) > 0) {
					E temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
				}
			}
		}
		// for (int i = 0; i < size; i++) {
		// sb.append(data[i]);
		// sb.append(",");
		// System.out.println(sb.toString());
		// }
	}

	/**
	 * Sorts the list
	 * 
	 * @param compare
	 */
	public void selectionSort(Comparator<? super E> compare) {
		StringBuilder sb = new StringBuilder();
		long selectionStart = System.nanoTime();
		for (int i = 0; i < size - 1; i++) {
			int posMin = i;
			for (int j = i + 1; j < size; j++) {
				if (compare.compare(data[j], data[posMin]) < 0) {
					posMin = j;
				}
			}
			E temp = data[i];
			data[i] = data[posMin];
			data[posMin] = temp;
		}
		// for (int i = 0; i < size; i++) {
		// sb.append(data[i]);
		// sb.append(",");
		// System.out.println(sb.toString());
		// }
	}

	/**
	 * This is the constructor of MyArrayList.
	 */
	public MyArrayList() {
		capacity = INITIAL_CAPACITY;
		this.data = (E[]) new Object[capacity];
		this.size = 0;
	}

	/**
	 * This method 'E remove' takes an index as the input, then first insures
	 * that the given index is not out of bounds. After, it stores the index in
	 * returnValue, and shifts the other list items so that there are no gaps,
	 * and decrements its size. Finally, it returns the index taken out.
	 * 
	 * @param index to remove at the given index.
	 */
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		E returnValue = data[index];
		for (int i = index + 1; i < size; i++) {
			data[i - 1] = data[i];
		}
		size--;
		return returnValue;
	}

	/**
	 * The add method adds the given parameter into the list at the end.
	 * 
	 * @param e to add at the end of the list.
	 * @return true when the method is successful.
	 */
	public boolean add(E e) {
		if (size == capacity) {
			reallocate();
		}
		data[size] = e;
		size++;
		return true;
	}

	/**
	 * The add method adds an element inside the given index.
	 * 
	 * @param index where the element will be placed
	 * @param element
	 */
	public void add(int index, E element) {
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		if (size == data.length) {
			reallocate();
		}
		for (int i = size; i > index; i--) {
			data[i] = data[i - 1];
		}
		data[index] = element;
		size++;
	}

	/**
	 * Gets the index. Getter method.
	 * 
	 * @param index that the method needs to get.
	 * @return data[index]
	 */
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		return data[index];
	}

	/**
	 * Setter. Sets the index and element.
	 * 
	 * @param index
	 * @param element
	 * @return old
	 */
	public E set(int index, E element) {
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		E old = data[index];
		data[index] = element;
		return old;
	}

	/**
	 * returns the size of the array.
	 * 
	 * @return size
	 */
	public int size() {
		return size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	/**
	 * @return sb.toString()
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(data[i]);
			sb.append(",");
			sb.append('\n');
		}
		return sb.toString();
	}

	/**
	 * Increases array by double to make room for list.
	 */
	public void reallocate() {
		capacity = data.length * 2;
		data = Arrays.copyOf(data, capacity);
	}

	@Override
	/**
	 * The Iterator method
	 * 
	 * @return the ArrayListIterator method.
	 */
	public Iterator<E> iterator() {
		return new ArrayListIterator();
	}

}
