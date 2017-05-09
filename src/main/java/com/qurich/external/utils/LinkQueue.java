package com.qurich.external.utils;

import org.springframework.scheduling.config.Task;

/**
 * 队列
 * @author xinhaixin
 *
 * @param <T>
 */
public class LinkQueue<T> {
	// 链的数据结构
	private class Node {
		public T data;
		public Node next;

		// 无参构造函数
		public Node() {
		}

		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	// 队列头指针
	private Node front;
	// 队列尾指针
	private Node rear;
	// 队列长度
	private int size = 0;

	public LinkQueue() {
		Node n = new Node(null, null);
		n.next = null;
		front = rear = n;
	}

	/**
	 * 队列入队算法
	 * 
	 * @param data
	 * @author WWX
	 */
	public void enqueue(T data) {
		// 创建一个节点
		Node s = new Node(data, null);
		// 将队尾指针指向新加入的节点，将s节点插入队尾
		rear.next = s;
		rear = s;
		size++;
	}

	/**
	 * 队列出队算法
	 * 
	 * @return
	 * @author WWX
	 */
	public T dequeue() {
		if (rear == front) {
			try {
				throw new Exception("堆栈为空");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} else {
			// 暂存队头元素
			Node p = front.next;
			T x = p.data;
			// 将队头元素所在节点摘链
			front.next = p.next;
			// 判断出队列长度是否为1
			if (p.next == null)
				rear = front;
			// 删除节点
			p = null;
			size--;
			return x;
		}
	}

	/**
	 * 队列长队
	 * 
	 * @return
	 * @author WWX
	 */
	public int size() {
		return size;
	}

	/**
	 * 判断队列是否为空
	 * 
	 * @return
	 * @author WWX
	 */
	public boolean isEmpty() {
		return size == 0;

	}

	public String toString() {
		if (isEmpty()) {
			return "[]";
		} else {
			StringBuilder sb = new StringBuilder("[");
			for (Node current = front.next; current != null; current = current.next) {
				sb.append(current.data.toString() + ", ");
			}
			int len = sb.length();
			return sb.delete(len - 2, len).append("]").toString();
		}
	}

	public boolean contains(Object obj) {
		if (isEmpty()) {
			return false;
		} else {
			for (Node current = front.next; current != null; current = current.next) {
				if(obj.equals(current.data)){
					return true;
				}
			}
			return false;
		}
	}
	
}
