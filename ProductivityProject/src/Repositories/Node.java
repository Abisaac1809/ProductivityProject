package Repositories;

public class Node<T> {
    private T data;
    private Node nextNode;
    private Node previusNode;
    
    public Node(T element, Node next){
        data=element;
        nextNode=next;
    }
    
    //setters
    
    public void setPrevius(Node element){
        previusNode = element;
    }
    
    public void setNext(Node element){
        nextNode = element;
    }
    
    //getters
    
    public T getData(){
        return data;
    }
    
    public Node getNext(){
        return nextNode;
    }
    
    public Node getPrevius(){
        return previusNode;
    }
    
}
