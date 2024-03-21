/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataPack;

import java.time.LocalDateTime;

/**
 *
 * @author ASUS
 */
  @FunctionalInterface
    public interface TimeIntervalCriteria<T> {
        boolean isWithinInterval(T element, LocalDateTime startTime, LocalDateTime endTime);
    }
