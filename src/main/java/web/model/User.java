package web.model;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 2, max = 24, message = "Name should be between 2 and 24 characters!")
    @Pattern(regexp = "^[\\p{L}]+(?: [\\p{L}]+)*$", message = "The name can contain only letters and spaces")
    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Min(value = 0, message = "Age should be greater that 0")
    @Column(name = "age")
    private int age;

    public User() {
    }

    public User(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }
}
