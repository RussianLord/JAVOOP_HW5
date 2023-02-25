public class Accounter extends Stuff{
    private String soft;

    public Accounter(int id, String position, int salary, String firstName, String secondName, int age, String soft) {
        super(id, position, salary, firstName, secondName, age);
        this.soft = soft;
    }

    public String getSoft() {
        return soft;
    }

    public void setSoft(String soft) {
        this.soft = soft;
    }
}
