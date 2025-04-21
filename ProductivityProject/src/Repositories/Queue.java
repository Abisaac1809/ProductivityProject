package Repositories;

public class Queue<T> {
    
    private Node firstNode;
    private Node lastNode;
    private int size=0;
    
    public Queue(){
        firstNode=null;
        lastNode=null;
    }
    
    public void addNode(T data){
        Node nodo = new Node(data,null);
        if(lastNode==null){
            firstNode=nodo;
        }else{
            lastNode.setNext(nodo);
        }
        lastNode=nodo;
        size++;
    }
    
    public T cutNode(){
        if(firstNode==null){
            return null;
        }
        T data = (T) firstNode.getData();
        firstNode=firstNode.getNext();
        
        if(firstNode==null){
            lastNode=null;
        }
        size--;
        return data;
    }
    
    public int getSize(){
        return size;
    }
    
}
