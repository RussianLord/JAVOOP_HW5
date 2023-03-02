import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Server {
    BasicText text = new BasicText();

    public static void main(String[] args) throws IOException {
        Stuff ivan = new Director("Директор", 10000, "Иван", "Петров", 30, 12);
        Stuff oleg = new Accounter("Бухгалтер", 20000, "Олег", "Иванов", 40, "Microsoft");
        Stuff anton = new Worker("Механик", 15000, "Антон", "Сидоров", 27);
        Company businessCo = new Company();
        businessCo.listAdd(ivan);
        businessCo.listAdd(oleg);
        businessCo.listAdd(anton);
        Server serverInfo = new Server();
        try (ServerSocket serverPart = new ServerSocket(666)) {
            System.out.println("Сервер запущен... Ожидаем подключение клиента...");
            Socket serverInterface = serverPart.accept();
            System.out.println("Клиент произвёл подключение...");
            DataOutputStream infoOut = new DataOutputStream(serverInterface.getOutputStream());
            DataInputStream infoIn = new DataInputStream(serverInterface.getInputStream());
            while (true) {
                String clientRequest = infoIn.readUTF();
                if (clientRequest.equals("Стоп")) break;
                if (Integer.parseInt(clientRequest) == 1) {
                    infoOut.writeUTF("Введите ID сотрудника... ");
                    int idNum = Integer.parseInt(infoIn.readUTF());
                    for (Stuff id : businessCo) {
                        if (idNum == id.getId()) {
                            infoOut.writeUTF(id.getInfo() + serverInfo.text.basicPrint());
                        }
                    }
                } else if (Integer.parseInt(clientRequest) == 2) {
                    infoOut.writeUTF("Введите ID сотрудника для удаления... ");
                    int idNum = Integer.parseInt(infoIn.readUTF());
                    for (Stuff it : businessCo) {
                        if (idNum == it.getId()) {
                            infoOut.writeUTF("Сотрудник: " + it.getFirstName() + " " + it.getSecondName() + " удалён." + serverInfo.text.basicPrint());
                        }
                    }
                    businessCo.removeStuff(idNum);
                } else if (Integer.parseInt(clientRequest) == 3) {
                    infoOut.writeUTF("Кого нужно добавить?\n[1] Начальство; [2] Бухгалтерия; [3] Рабочий");
                    serverInfo.addPersonal(infoOut, infoIn, businessCo, serverInfo);
                } else if (Integer.parseInt(clientRequest) == 4) {
                    String listPer = "";
                    for (Stuff it : businessCo) {
                        listPer += it.getInfo() + "\n";
                    }
                    infoOut.writeUTF(listPer + serverInfo.text.basicPrint());
                } else if (Integer.parseInt(clientRequest) == 5) {
                    infoOut.writeUTF("Введите ID сотрудника... ");
                    serverInfo.changePersonal(infoOut,infoIn,businessCo,serverInfo);

                } else {
                    infoOut.writeUTF("Некорректный ввод!!!" + serverInfo.text.basicPrint());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPersonal(DataOutputStream infoOut, DataInputStream infoIn, Company businessCo, Server serverInfo) throws IOException {
        switch (Integer.parseInt(infoIn.readUTF())) {
            case (1):
                infoOut.writeUTF("Введите должность сотрудника");
                String scanPosition = infoIn.readUTF();
                infoOut.writeUTF("Введите имя сотрудника");
                String scanFirstName = infoIn.readUTF();
                infoOut.writeUTF("Введите фамилию сотрудника");
                String scanSecondName = infoIn.readUTF();
                infoOut.writeUTF("Введите зарплату сотрудника");
                int scanSalary = Integer.parseInt(infoIn.readUTF());
                infoOut.writeUTF("Введите возраст сотрудника");
                int scanAge = Integer.parseInt(infoIn.readUTF());
                infoOut.writeUTF("Введите парковочный номер сотрудника");
                int scanParking = Integer.parseInt(infoIn.readUTF());
                businessCo.listAdd(new Director(scanPosition, scanSalary, scanFirstName, scanSecondName, scanAge, scanParking));
                infoOut.writeUTF("Добавлен сотрудник: " + scanFirstName + " " + scanSecondName + serverInfo.text.basicPrint());
                break;
            case (2):
                infoOut.writeUTF("Введите должность сотрудника");
                scanPosition = infoIn.readUTF();
                infoOut.writeUTF("Введите имя сотрудника");
                scanFirstName = infoIn.readUTF();
                infoOut.writeUTF("Введите фамилию сотрудника");
                scanSecondName = infoIn.readUTF();
                infoOut.writeUTF("Введите зарплату сотрудника");
                scanSalary = Integer.parseInt(infoIn.readUTF());
                infoOut.writeUTF("Введите возраст сотрудника");
                scanAge = Integer.parseInt(infoIn.readUTF());
                infoOut.writeUTF("Введите ПО для сотрудника");
                String scanSoft = infoIn.readUTF();
                businessCo.listAdd(new Accounter(scanPosition, scanSalary, scanFirstName, scanSecondName, scanAge, scanSoft));
                infoOut.writeUTF("Добавлен сотрудник: " + scanFirstName + " " + scanSecondName + serverInfo.text.basicPrint());
                break;
            case (3):
                infoOut.writeUTF("Введите должность сотрудника");
                scanPosition = infoIn.readUTF();
                infoOut.writeUTF("Введите имя сотрудника");
                scanFirstName = infoIn.readUTF();
                infoOut.writeUTF("Введите фамилию сотрудника");
                scanSecondName = infoIn.readUTF();
                infoOut.writeUTF("Введите зарплату сотрудника");
                scanSalary = Integer.parseInt(infoIn.readUTF());
                infoOut.writeUTF("Введите возраст сотрудника");
                scanAge = Integer.parseInt(infoIn.readUTF());
                businessCo.listAdd(new Stuff(scanPosition, scanSalary, scanFirstName, scanSecondName, scanAge));
                infoOut.writeUTF("Добавлен сотрудник: " + scanFirstName + " " + scanSecondName + serverInfo.text.basicPrint());
                break;
        }
    }

    public void changePersonal(DataOutputStream infoOut, DataInputStream infoIn, Company businessCo, Server serverInfo) throws IOException {
        int idNum = Integer.parseInt(infoIn.readUTF());
        for (Stuff id : businessCo) {
            if (idNum == id.getId()) {
                infoOut.writeUTF(id.getInfo() + "\nКакие данные изменить?\n[1] Имя; [2] Фамилия" +
                        "; [3] Должность; [4] Зарплата; [5] Возраст;");
                switch (Integer.parseInt(infoIn.readUTF())) {
                    case (1):
                        infoOut.writeUTF("Введите данные... ");
                        id.setFirstName(infoIn.readUTF());
                        infoOut.writeUTF("Теперь сотрудника зовут " + id.getFirstName() + serverInfo.text.basicPrint());
                        break;
                    case (2):
                        infoOut.writeUTF("Введите данные... ");
                        id.setSecondName(infoIn.readUTF());
                        infoOut.writeUTF("Теперь фамилия сотрудника " + id.getFirstName() + ": " + id.getSecondName() + serverInfo.text.basicPrint());
                        break;
                    case (3):
                        infoOut.writeUTF("Введите данные... ");
                        id.setPosition(infoIn.readUTF());
                        infoOut.writeUTF("Теперь должность сотрудника " + id.getFirstName() + ": " + id.getPosition() + serverInfo.text.basicPrint());
                        break;
                    case (4):
                        infoOut.writeUTF("Введите данные... ");
                        id.setSalary(Integer.parseInt(infoIn.readUTF()));
                        infoOut.writeUTF("Теперь зарплата сотрудника " + id.getFirstName() + ": " + id.getSalary() + serverInfo.text.basicPrint());
                        break;
                    case (5):
                        infoOut.writeUTF("Введите данные... ");
                        id.setAge(Integer.parseInt(infoIn.readUTF()));
                        infoOut.writeUTF("Теперь возраст сотрудника " + id.getFirstName() + ": " + id.getAge() + serverInfo.text.basicPrint());
                        break;
                }
                break;
            }
        }
    }
}
