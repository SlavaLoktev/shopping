package com.shopping.shopping.service;

import java.util.List;

public interface CommonService<E>{

    List<E> getAll();

    public E save(E object);

    public E get(Long id);

    public void deleteById(Long id);
}
