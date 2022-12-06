package sg.edu.nus.iss.demoDay1a.model;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  //DAY 2

public class Person {
    private String id;
    private String firstName;
    private String lastName;

    /* generate getter & setter */
    /* generate hash map */

    public Person(String firstName, String lastName) {
        this.id = UUID.randomUUID().toString().substring(0,8);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //----- DAY 2 -----//
    public Person(String id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
