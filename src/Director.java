public class Director extends Stuff{
    private int parkingNumber;

    public Director(String position, int salary, String firstName, String secondName, int age, int parkingNumber) {
        super(position, salary, firstName, secondName, age);
        this.parkingNumber = parkingNumber;
    }

    public int getParkingNumber() {
        return parkingNumber;
    }

    public void setParkingNumber(int parkingNumber) {
        this.parkingNumber = parkingNumber;
    }
}
