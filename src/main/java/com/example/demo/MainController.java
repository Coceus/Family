package com.example.demo;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
  @Autowired // This means to get the bean called userRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private PersonRepository personRepository;

  @PostMapping(path="/add") // Map ONLY POST Requests
  public @ResponseBody String addNewPerson (@RequestParam String name,@RequestParam Optional<List<String>> parents) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    Person n = new Person();
    n.setName(name);
    if(parents.isPresent()) {
    List<Person> parentsDb = personRepository.findByNameIn(parents.get());
    n.setParents(new HashSet<Person>(parentsDb));
    }
    
    personRepository.save(n);
    return "Saved";
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<Person> getAllPeople() {
    // This returns a JSON or XML with the users
    return personRepository.findAll();
  }
}