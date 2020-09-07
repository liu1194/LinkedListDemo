package cn.sxt.MyCollection;

/**
 * 定义LinkedList节点
 */
public class Node {
    Node previous; //上一个节点地址
    Node next;     //下一个节点地址
    Object element;  //元素数据

    public Node(Node previous, Node next, Node element) {
        this.previous = previous;
        this.next = next;
        this.element = element;
    }

    public Node(Object element) {
        this.element = element;
    }
}
