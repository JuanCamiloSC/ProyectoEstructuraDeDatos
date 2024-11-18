package Proceso.Utils;

public class TaskNode<E> {
    private TaskNode<E> nextNode;
    private E value;

    public TaskNode(E valueNode) {
        this.value = valueNode;
    }

    public TaskNode(E valueNode, TaskNode<E> nextNode, TaskNode<E> previousNode) {
        super();
        this.value = valueNode;
        this.nextNode = nextNode;
    }
    public TaskNode<E> getNextNode() {
        return nextNode;
    }
    public void setNextNode(TaskNode<E> nextNode) {
        this.nextNode = nextNode;
    }
    public E getValue() {
        return value;
    }
}