/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dataPack;

/**
 *
 * @author ASUS
 */
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
