import java.util.ArrayList;

public class Tree<T> {
	private Node<T> root;

	public Tree(T rootData) {
		root = new Node<T>();
		addNode(null, rootData);
	}

	public void addNode(Node<T> parent, T data) {
		Node<T> node = new Node<T>();
		node.setParent(parent);
		node.setData(data);
		if (parent != null)
			parent.children.add(node);

		node.children = new ArrayList<Node<T>>();
	}

	public Node<T> getRoot() {
		return root;
	}

	public static class Node<T> {
		private T data = null;
		private Node<T> parent = null;
		private ArrayList<Node<T>> children = null;

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node<T> getParent() {
			return parent;
		}

		public void setParent(Node<T> parent) {
			this.parent = parent;
		}
	}
}