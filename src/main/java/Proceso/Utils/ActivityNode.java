package Proceso.Utils;

public class ActivityNode<E> {
    private ActivityNode<E> nextNode;
    private ActivityNode<E> previousNode;
    private E value;
    private int index;

    public ActivityNode(E valueNode) {
        this.value = valueNode;
    }

    public ActivityNode(E valueNode, ActivityNode<E> nextNode, ActivityNode<E> previousNode) {
        this.value = valueNode;
        this.nextNode = nextNode;
        this.previousNode = previousNode;
    }

    // Getters y Setters
    public ActivityNode<E> getNextNode() {
        return nextNode;
    }

    public void setNextNode(ActivityNode<E> nextNode) {
        this.nextNode = nextNode;
    }

    public ActivityNode<E> getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(ActivityNode<E> previousNode) {
        this.previousNode = previousNode;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}