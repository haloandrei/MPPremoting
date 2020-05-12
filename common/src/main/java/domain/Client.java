package domain;

import java.io.Serializable;

public class Client extends BaseEntity<Integer> implements Serializable {
    private String name;
    private int age;

    public Client(Integer Id, String name, int age) {
        this.name = name;
        this.age = age;
        this.setId(Id);
    }

    public Client() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
