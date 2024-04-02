/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

/**
 *
 * @author ASUS
 */
public class Node<T> {

T  value;    
    
Node<T> next;

    public Node(T value) {
        this.value = value;
        this.next = null;
    }

    public Node(T value, Node next) {
        this.value = value;
        this.next = next;
    }
}
