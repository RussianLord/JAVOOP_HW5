import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Company implements Iterable<Stuff> {
    private List<Stuff> listPersonal = new ArrayList<>();

    public void listAdd(Stuff person) {
        listPersonal.add(person);
    }

    public void getList() {
        for (Stuff stuff : listPersonal) {
            stuff.getInfo();
        }
    }

    public List<Stuff> removeStuff(int id) {
        for (Stuff stuff : listPersonal) {
            if (id == stuff.getId()) {
                System.out.println("Сотрудник: " + stuff.getFirstName() + " " + stuff.getSecondName() + " удалён.");
                listPersonal.remove(stuff);
                return listPersonal;
            }
        }
        System.out.println("Нет такого сотрудника");
        return null;
    }

    @Override
    public Iterator<Stuff> iterator() {
        return listPersonal.iterator();
    }

    public Stuff checkPerson(int id) {
        for (Stuff stuff : listPersonal) {
            if (id == stuff.getId()) {
                stuff.getInfo();
                return stuff;
            }
        }
        System.out.println("Нет такого сотрудника");
        return null;
    }


}
