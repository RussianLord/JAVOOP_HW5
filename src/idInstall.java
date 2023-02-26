public class idInstall {
    private static int id = 1;

    public idInstall(int id) {
        this.id = id;
    }

    public static int getId() {
        return id++;
    }

    public void setId(int id) {
        this.id = id;
    }
}
