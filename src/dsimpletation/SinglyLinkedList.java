package dsimpletation;

public class SinglyLinkedList {

    Node head;
    int size;

    SinglyLinkedList() {
        head = null;
        size = 0;
    }

    public void insertNode(int value, int position) {

        if (position == 0) {
            insertAtHead(value);
            return;
        }
        if (position == size) {
            insertAtEnd(value);
            return;
        }
        if (head == null) {
            size++;
            head = new Node(value);
            return;
        }
        size++;
        Node newNode = new Node(value);
        Node temp = head;
        int count = 0;
        Node prev = null;
        while (temp.next != null && count != position) {
            count++;
            prev = temp;
            temp = temp.next;
        }

        Node t = prev.next;
        prev.next = newNode;
        newNode.next = t;


    }

    private void insertAtHead(int value) {

        size++;
        Node newNode = new Node(value);
        if (head == null)
            head = newNode;
        else {
            newNode.next = head;
            head = newNode;
        }
    }

    private void insertAtEnd(int value) {
        size++;
        Node newNode = new Node(value);
        Node temp = head;
        if (temp == null) {
            head = newNode;
        } else {
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = null;
        }

    }

    public void deleteNode(int value) {

    }

    public boolean isNodePresentInList(int value) {
        return false;
    }

    public void printLinkedList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
          SinglyLinkedList linkedList = new SinglyLinkedList();
          linkedList.insertNode(1,0);
          linkedList.insertNode(2,1);
          linkedList.insertNode(3,1);
          linkedList.insertNode(4,3);
          linkedList.insertNode(5,0);
          linkedList.insertNode(6,1);
          linkedList.insertNode(7,4);
          linkedList.printLinkedList();
    }


    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            next = null;
        }
    }
}
