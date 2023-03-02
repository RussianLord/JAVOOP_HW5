public class Stuff {
    private String position;
    private int salary;
    private int id;
    private String firstName;
    private String secondName;
    private int age;

    public Stuff(String position, int salary, String firstName, String secondName, int age) {
        this.id = idInstall.getId();
        this.position = position;
        this.salary = salary;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {

        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getInfo() {
        return "ID" + id + ". Имя: " + firstName + ". Фамилия: " + secondName + ". Возраст: " + age + "г/л. Должность: " +
                position + ". Зарплата: " + salary;
    }

}
