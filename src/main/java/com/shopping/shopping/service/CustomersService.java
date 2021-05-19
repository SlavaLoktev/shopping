package com.shopping.shopping.service;

import com.shopping.shopping.entity.Customers;
import com.shopping.shopping.repository.CustomersRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
// все методы класса должны выполниться без ошибки, чтобы транзакция завершилась
// если в методе возникнет исключение - все выполненные операции откатятся (Rollback)
public class CustomersService {

    private final CustomersRepository repository;

    public CustomersService(CustomersRepository repository) {
        this.repository = repository;
    }

    public List<Customers> findAll(){
        return repository.findAll();
    }

    public Customers add(Customers customers){
        return repository.save(customers);
    }

    public Customers update(Customers customers){
        return repository.save(customers);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Customers findById(Long id){
        return repository.findById(id).get();// т.к. возвращается optional - нужно  получить объект методом get()
    }
}
