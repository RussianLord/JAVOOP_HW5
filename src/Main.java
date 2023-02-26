import java.util.Scanner;
/*
Создать информационную систему позволяющую работать с сотрудниками некой компании \ студентами вуза \
учениками школы - добавлять, удалять, изменять данные о сотруднике, выводить информацию о них.
**Сделать клиент серверную версию, чтобы клиент вводил данные о сотруднике \ студенте \ ученике.
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stuff ivan = new Director("Директор",10000,"Иван","Петров",30,2);
        Stuff oleg = new Accounter("Бухгалтер",20000,"Олег","Иванов",40,"Microsoft");
        Stuff anton = new Worker("Механик",15000,"Антон","Сидоров",27);

        Company businessCo = new Company();
        businessCo.listAdd(ivan);
        businessCo.listAdd(oleg);
        businessCo.listAdd(anton);
        businessCo.getList();

        System.out.println("Введи id сотрудника для удаления...");
        int idSt = scanner.nextInt();
        businessCo.removeStuff(idSt);
        businessCo.getList();
//        System.out.println("");
//        ivan.setPosition("Генеральный директор");
//        System.out.println(ivan.getInfo());






    }
}