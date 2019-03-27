import lombok.Data;

import java.util.Date;

@Data
public class Student {
    private String name;
    private String sex;
    private Integer age;
    private Date birthday;
    private Integer isBuy;
 
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", isBuy=" + isBuy +
                '}';
    }
}