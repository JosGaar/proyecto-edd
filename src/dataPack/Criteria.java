/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataPack;

/**
 *
 * @author ASUS
 * @param <T>
 */
//Interfaz funcional solo tiene un metodo abstracto
@FunctionalInterface
public interface Criteria<T> {
    boolean match(T value);
}