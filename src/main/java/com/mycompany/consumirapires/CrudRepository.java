/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.consumirapires;

import java.util.List;

/**
 *
 * @author Kiwar
 */
public interface CrudRepository <T>{
    List<T> getAll();
    T getStudent(String id);
    boolean create(T entity);
    boolean update(T entity);
    boolean delete(String id);
}
