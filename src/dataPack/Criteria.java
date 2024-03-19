/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataPack;

/**
 *
 * @author ASUS
 */
//Interfaz funcional solo tiene un metodo abstracto
@FunctionalInterface
interface Criteria<T> {
    boolean match(T value);
}