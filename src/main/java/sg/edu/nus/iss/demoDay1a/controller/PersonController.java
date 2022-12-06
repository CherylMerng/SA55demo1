package sg.edu.nus.iss.demoDay1a.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import sg.edu.nus.iss.demoDay1a.model.Person;
import sg.edu.nus.iss.demoDay1a.service.PersonService;

@Controller
@RequestMapping("/persons") //----- DAY 2 -----//
public class PersonController {
    private List<Person> personList = new ArrayList<Person>();

    /* Connect directly - connect with service */
    @Autowired
    PersonService personService;    

    @Value("${welcome.message}")    /* from application.properties */
    private String welcomeMessage;

    @Value("${person.list.header}") //----- DAY 2 -----//
    private String headerMessage;

    //http://localhost:3000/persons/home
    //http://localhost:3000/persons/index
    @GetMapping(value={"/index", "/home"})
    public String index(Model model){
        model.addAttribute("message", welcomeMessage);
        return "home";  /* view > home.html */
    }

    //----- DAY 2 -----//
    //http://localhost:3000/persons/testRetrieveAllPerson
    //@GetMapping("/testRetrieveAllPerson")
    @GetMapping(value = "/testRetrieveAllPerson", produces = "application/json")
    public @ResponseBody List<Person> getALlPersons(){
        personList = personService.getPersons();

        return personList;
    }
    
    //http://localhost:3000/persons/list
    @GetMapping(value = "/list")
    public String personList(Model model){
        personList = personService.getPersons();
        model.addAttribute("persons", personList);  //data comes from persons
        model.addAttribute("listOfPersons", headerMessage);
        return "personList";
    }

    @PostMapping(value = "/update")
    public String updatePerson(@ModelAttribute(value="per") Person p, Model model){
    //@ModelAttribute(value="per") = data that has been passed in
        model.addAttribute("per", p);
        //add data into a model
        return "personEdit";    
        //data will come from model
    }

    @PostMapping(value = "/updatePerson")
    public String updatePersonRecord(@ModelAttribute(value="person") Person p){
        personService.updatePerson(p);
        return "redirect:/persons/list";
    }
}
