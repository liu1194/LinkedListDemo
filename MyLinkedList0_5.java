package cn.sxt.MyCollection;

/**
 * 手工实现LinkedList，自定义链表
 * 增加set方法
 */
public class MyLinkedList0_5<E> {
    public static void main(String[] args) {
        MyLinkedList0_5<String>list = new MyLinkedList0_5<>();
        for (int i=0;i<10;i++){
            list.add("list"+(i+1));
        }

        System.out.println(list);
        System.out.println(list.get(8));
        list.set("bbb",0);
        System.out.println(list);

    }

    private Node first; //第一个节点
    private Node last;  //最后一个节点
    private int size;   //链表长度

    //添加元素['a','b','c']
    public void add(E object){
        Node node = new Node(object);
        if (first == null){
            first = node;
            last = node;
        }else {
            node.previous = last;
            node.next = null;

            last.next = node;
            last = node;
        }
        size++;
    }

    public void add(E obj,int index){
        //判断索引是否越界
        checkIndex(index);
        Node node = new Node(obj);
        Node temp = getNode(index);
        //插入的位置是第一个,first指向新插入节点，first.next为之前的第一个节点

        if (index == 0){
            node.next = first;
            first = node;
            node.next.previous = node;
        }else if (index == size){
            //插入到最后
            temp = getNode(index-1);
            System.out.println("最后"+temp.element);
            node.previous = temp;
            temp.next = node;
//            last.next = node;
//            
            last = node;
            node.next = null;
        }else {
            if (temp!=null){
                //中间插入
                Node up = temp.previous;
                up.next = node;
                node.previous = up;

                node.next = temp;
                temp.previous = node;
            }
        }
        size++;

    }

    //toString

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        //挨个遍历列表中的元素,通过节点的next往下找
        Node temp = first;
        while (temp != null){
//            System.out.print(temp.element);
            sb.append(temp.element+",");
            temp = temp.next;
        }

        //最后一个字符替换为']'
        sb.setCharAt(sb.length()-1,']');
        return sb.toString();
    }

    public void checkIndex(int index){
        if (index < 0 || index > size){
            throw new RuntimeException("索引数字不合法");
        }
    }

    //get方法，去index位置上的element
    public E get(int index){
        checkIndex(index);
        return (E) getNode(index).element;
    }

    //根据index获取节点
    public Node getNode(int index){
        Node temp = null;
        if (index <= (size>>1)) { //size>>1右移一位等于除以二
            temp = first;
            for (int i=0;i<index;i++){
                temp = temp.next;
            }
        }else {
            temp = last;
            for (int i=size;i>index;i--){
                temp = temp.previous;
            }
        }
        return temp;
    }

    //移除index位置上的元素
    public void remove(int index){
        checkIndex(index);
        Node temp = getNode(index);
        if (temp != null){
            Node up = temp.previous;
            Node down = temp.next;
            if (up != null){
                up.next = down;
            }
            if (down != null){
                down.previous = up;
            }
            //被删除的元素是第一个时
            if (index == 0){
                first = down;
            }
            //被删除的元素是最后一个时
            if (index == size-1){
                last = up;
                last.next = null;
            }
            size--;
        }
    }

    public void set(E element,int index){
        checkIndex(index);
        //index =0,=size-1
        Node node = getNode(index);
        node.element = element;
    }

}
