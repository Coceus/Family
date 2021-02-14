package com.example.demo;



import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
   List<Person> findByNameIn(Collection<String> parent);

}
