package com.revature.repository;

import java.util.List;

public interface DAO <T> {

    void create(T t); //Create DB record of selected object

    T getById(int id); //Search DB for record by PK ID

   // void update(T t);//Edit selected record

    //void deleteById(int id);// remove selected record
}
