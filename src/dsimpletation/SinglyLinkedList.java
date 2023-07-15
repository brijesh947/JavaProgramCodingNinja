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
        if (!isNodePresentInList(value))
            return;
        size--;
        Node t = head;
        Node prev = null;
        while (t != null && t.value != value) {
            prev = t;
            t = t.next;
        }
        if (prev == null)
            head = head.next;
        else
            prev.next = t == null ? null : t.next;

    }

    public boolean isNodePresentInList(int value) {
        Node t = head;
        while (t != null) {
            if (t.value == value)
                return true;
            t = t.next;
        }
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
        linkedList.insertNode(1, 0);
        linkedList.printLinkedList();
        linkedList.insertNode(2, 1);
        linkedList.printLinkedList();
        linkedList.insertNode(3, 1);
        linkedList.printLinkedList();
        linkedList.insertNode(4, 3);
        linkedList.printLinkedList();
        linkedList.insertNode(5, 0);
        linkedList.printLinkedList();
        linkedList.insertNode(6, 1);
        linkedList.printLinkedList();
        linkedList.insertNode(7, 4);
        linkedList.printLinkedList();
        linkedList.deleteNode(4);
        linkedList.printLinkedList();
        linkedList.deleteNode(5);
        linkedList.printLinkedList();
        linkedList.deleteNode(7);
        linkedList.printLinkedList();
        linkedList.deleteNode(1);
        linkedList.printLinkedList();
        linkedList.insertNode(5, 0);
        linkedList.printLinkedList();
        linkedList.deleteNode(7);
        linkedList.printLinkedList();
        linkedList.insertNode(11, 4);
        linkedList.printLinkedList();
        linkedList.deleteNode(11);
        linkedList.printLinkedList();
        linkedList.deleteNode(6);
        linkedList.printLinkedList();
        linkedList.deleteNode(2);
        linkedList.printLinkedList();
        linkedList.deleteNode(3);
        linkedList.printLinkedList();
        linkedList.deleteNode(5);
        linkedList.printLinkedList();
        linkedList.insertNode(6, 1);
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
