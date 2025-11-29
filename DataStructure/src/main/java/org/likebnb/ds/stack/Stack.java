package org.likebnb.ds.stack;

public interface Stack<T> {
	int curSize = 0;
	int maxSize = Integer.MAX_VALUE;
	
	void push(StackNode<T> node) throws OverflowException;
	StackNode<T> pop() throws UnderflowException;
	T peek() throws UnderflowException;
	boolean isEmpty();
	boolean isFull();
	int getCurSize();
	
	public class OverflowException extends Exception {
		private static final long serialVersionUID = 1L;

		public OverflowException() {
			this("스택이 가득 차서 노드를 추가(push)할 수 없습니다.");
		}
		
		public OverflowException(String message) {
			super(message);
		}
	}
	
	public class UnderflowException extends Exception {
		private static final long serialVersionUID = 1L;

		public UnderflowException() {
			this("스택이 비어 있어 삭제(pop)할 수 없습니다.");
		}

		public UnderflowException(String message) {
			super(message);
		}
	}
}
