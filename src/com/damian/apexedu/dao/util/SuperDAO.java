package com.damian.apexedu.dao.util;

import com.damian.apexedu.entity.util.SuperEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface SuperDAO <T extends SuperEntity,ID>{
    boolean  add(T t);
    boolean update(T t,ID id);
    boolean delete(ID id);
   Optional<T> search(ID id);
    List <T> getAll();
}
