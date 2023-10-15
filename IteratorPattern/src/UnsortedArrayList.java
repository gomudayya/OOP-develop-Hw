import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2022년도 2학기
 * @author 김상진 
 * 배열 기반 비정렬 범용 리스트
 * 2019136149 박건우 
 */
public class UnsortedArrayList<T> implements Iterable<T> {
	private int capacity = 5;
	@SuppressWarnings("unchecked")
	private T[] items = (T[])(new Object[capacity]);
	private int size = 0;
	
	private class ArrayListIterator implements ListIterator<T>{
		private int cursor = 0;
		private int lastRetIdx = -1;
		@Override public boolean hasNext() {
			return cursor < size;
		}
		@Override public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			lastRetIdx = cursor++;
			return items[lastRetIdx];			
		}
		@Override public boolean hasPrevious() {
			return cursor > 0;
		}
		@Override public T previous() {
			if (!hasPrevious()) throw new NoSuchElementException();
			lastRetIdx = --cursor;
			return items[lastRetIdx];
		}
		@Override public int nextIndex() {
			return cursor;
		}
		@Override public int previousIndex() {
			return cursor-1;
		}
		@Override public void remove() {
			if (lastRetIdx < 0) throw new IllegalStateException();
			
			for (int i = lastRetIdx; i < size-1; i++) {
				items[i] = items[i+1];
			}
			if (cursor-1 == lastRetIdx) cursor--; // 이전에 next()가 호출된상황 
			items[size-1] = null;
			size--;
			
			lastRetIdx = -1;
		}
		@Override public void set(T e) {
			if (lastRetIdx < 0) throw new IllegalStateException();
			items[lastRetIdx] = e;
		}
		@Override public void add(T e) {
			//cursor에서 끝까지 요소들을 오른쪽으로 한칸씩 밀고 현재 cursor의 위치에 add해주어야 함
			if (size == capacity) {
				capacity *= 2;
				items = Arrays.copyOf(items, capacity); //카피할 때 capacity가 items의 크기보다 크면 나머지는 null로 채워짐.
			}
			for (int i = size-1; i >= cursor; i--) {
				items[i+1] = items[i];
			}
			items[cursor++] = e;
			size++;
			
			lastRetIdx = -1;
		}
	}
	
	public boolean isFull(){
		return false;
	}
	public boolean isEmpty(){
		return size==0;
	}
	public int size() {
		return size;
	}
	
	public T peekBack() {
		if(isEmpty()) throw new IllegalStateException();
		return items[size-1];
	}
	
	private void increaseCapacity() {
		capacity *= 2;
		items = Arrays.copyOf(items, capacity); //카피할 때 capacity가 items의 크기보다 크면 나머지는 null로 채워짐.
	}
	
	public void pushBack(T item){
		if(size==capacity) increaseCapacity();
		items[size++] = item;
	}
	
	public T popBack() {
		if(isEmpty()) throw new IllegalStateException();
		return items[--size];
	}
	
	public T get(int index){
		if(index<0 || index>=size) throw new IndexOutOfBoundsException("유효하지 않은 색인 사용");
		return items[index];
	}
	
	public void remove(T item) {
		for(int i=0; i<size; i++)
			if(items[i].equals(item)) {
				for(int j=i+1; j<size; j++)
					items[j-1] = items[j];
				--size;
				break;
			}
	}
	
	@SuppressWarnings("unchecked")
	public void pushBackAll(T... items) {
		for(var item: items) pushBack(item);
	}
	
	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}
	
	public ListIterator<T> listIterator() {
		return new ArrayListIterator();
	}
}
