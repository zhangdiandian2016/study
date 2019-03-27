import lombok.Data;

import java.util.Date;

@Data
public class Person {
    private String name;
    private String sex;
    private int age;
    private Date birthday;
    private Boolean isBuy;

}