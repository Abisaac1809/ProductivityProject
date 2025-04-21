package Repositories;

public class Stack<T> {
    private Node topNode;
    private Node bottomNode;
    private int size;
    
    public Stack(){
        topNode=null;
        bottomNode=null;
    }
    
    public void top(T data){
        Node nodo = new Node(data,null);
        if(topNode==null){
            topNode=nodo;
        }else{
            nodo.setNext(topNode);
            topNode=nodo;
        }
        if(bottomNode==null){
            bottomNode=nodo;
        }
        size++;
    }
    
    public T pop(){
        if(topNode==null){
            return null;
        }
        T data = (T) topNode.getData();
        topNode=topNode.getNext();
        size--;
        return data;
    }
    
    public int getSize(){
        return size;
    }
    
}
