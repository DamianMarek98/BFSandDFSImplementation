import java.util.ArrayList;
import java.util.List;

public class Main {


    public static class Node<T> {
        T data;
        List<Node<T>> childs = new ArrayList<>();
        Node<T> parent = null;
        boolean visited;

        Node(T data) {
            this.data = data;
        }

        Node<T> addChild(Node<T> child) {
            child.setParent(this);
            this.childs.add(child);
            this.visited = false;
            return child;
        }

        void setParent(Node<T> parent) {
            this.parent = parent;
        }

        Node<T> getParent() {
            return this.parent;
        }

        public void setData(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }

        void setVisited(boolean visited) {
            this.visited = visited;
        }

        public boolean getVisited() {
            return this.visited;
        }

        void printTraversion(Node<T> root) {
            System.out.println(root.data.toString());
            for (Node<T> node : root.childs) {
                printTraversion(node);
            }
            if (root.getParent() != null) {
                System.out.println(root.getParent().data.toString());
            }
        }

    }

    public static class QueueElement{
        Node element;
        QueueElement rightSideElement;
        QueueElement leftSideElement;

        public QueueElement getLeftSideElement() {
            return leftSideElement;
        }

        public void setLeftSideElement(QueueElement leftSideElement) {
            this.leftSideElement = leftSideElement;
        }

        QueueElement(Node node){
            this.element = node;
        }

        public QueueElement getRightSideElement() {
            return rightSideElement;
        }

        void setRightSideElement(QueueElement rightSideElement) {
            this.rightSideElement = rightSideElement;
        }
    }


    public static class Queue{
        QueueElement firstElement;
        QueueElement lastElement;

        public Queue(){
            firstElement = null;
            lastElement = null;
        }

        public void setFirstElement(QueueElement firstElement) {
            this.firstElement = firstElement;
        }

        public void setLastElement(QueueElement lastElement) {
            this.lastElement = lastElement;
        }

        public QueueElement getFirstElement() {
            return firstElement;
        }

        public QueueElement getLastElement() {
            return lastElement;
        }

        void popFirst(){
            if(this.firstElement != null) {
                System.out.print(firstElement.element.data.toString()+ " ");
                if(this.firstElement.equals(this.lastElement)){
                    this.lastElement = null;
                    this.firstElement = null;
                }
                else if(lastElement != null) {
                    this.firstElement = this.firstElement.rightSideElement;
                }else{
                    firstElement = null;
                }
            }
        }

        void pushBack(QueueElement element){
            element.rightSideElement = null;
            this.lastElement.setRightSideElement(element);
            element.leftSideElement = this.lastElement;
            this.lastElement = element;

        }


    }


    public static void main(String[] args) {
        //tree i created
//        Node<Integer> node1 = new Node<>(1); //root
//        Node<Integer> node2 = node1.addChild(new Node<>(2));
//        Node<Integer> node3 = node2.addChild(new Node<>(3));
//        Node<Integer> node4 = node2.addChild(new Node<>(4));
//        Node<Integer> node5 = node4.addChild(new Node<>(5));
//
//        Node<Integer> node6 = node1.addChild(new Node<>(6));
//        Node<Integer> node7 = node6.addChild(new Node<>(7));
//        Node<Integer> node10 = node6.addChild(new Node<>(10));
//        Node<Integer> node9 = node7.addChild(new Node<>(9));
//        Node<Integer> node8 = node7.addChild(new Node<>(8));
//        Node<Integer> node11 = node8.addChild(new Node<>(11));

        //node1.printTraversion(node1);

        //graph example form: https://techdifferences.com/difference-between-bfs-and-dfs.html?fbclid=IwAR2CaPpy1lF9I_TDuijehlcHrbcrr8ny85E9Ic_WJOBCJLoVdS0nV0cTJ6s
        //1=A, 2=B, 3=C, 4=D, 5=E, 6=F, 7=G

        Node<Integer> node1 = new Node<>(1); //root
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node4 = new Node<>(4);
        Node<Integer> node5 = new Node<>(5);
        Node<Integer> node6 = new Node<>(6);
        Node<Integer> node7 = new Node<>(7);
        Node<Integer> node;
        //A
        node = node1.addChild(node2);
        node = node1.addChild(node4);
        node = node1.addChild(node7);
        //B
        node = node2.addChild(node1);
        node = node2.addChild(node5);
        node = node2.addChild(node6);
        //C
        node = node3.addChild(node6);
        //D
        node = node4.addChild(node1);
        node = node4.addChild(node6);
        //E
        node = node5.addChild(node2);
        node = node5.addChild(node7);
        //F
        node = node6.addChild(node2);
        node = node6.addChild(node3);
        node = node6.addChild(node4);
        //G
        node = node7.addChild(node1);
        node = node7.addChild(node5);

        performBFS(node1);
        //node1.printTraversion(node1); will only work for binary tree
    }

    static void performBFS(Node root){
        Queue queue = new Queue();
        QueueElement element = new QueueElement(root);
        queue.firstElement = element;
        queue.lastElement = element;
        root.setVisited(true);
        while(queue.firstElement != null || queue.lastElement != null){
            for(Object node : queue.firstElement.element.childs){
                Node elem = (Node) node;
                if(!elem.visited) {
                    queue.pushBack(new QueueElement(elem)); //cast to node
                    elem.setVisited(true);
                }
            }
            queue.popFirst();
        }


    }

}
