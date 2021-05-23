package com.shopping.shopping.service;

import java.util.List;

public interface CommonService<E>{

    List<E> getAll();

    E save(E object);

    E get(Long id);

    void deleteById(Long id);
}
