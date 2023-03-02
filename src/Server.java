import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Server implements BasicText{
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
                            infoOut.writeUTF(id.getInfo()+serverInfo.printBasic());
                        }
                    }
                } else if (Integer.parseInt(clientRequest) == 2) {
                    infoOut.writeUTF("Введите ID сотрудника для удаления... ");
                    int idNum = Integer.parseInt(infoIn.readUTF());
                    for (Stuff it : businessCo) {
                        if (idNum == it.getId()) {
                            infoOut.writeUTF("Сотрудник: " + it.getFirstName() + " " + it.getSecondName() + " удалён." +serverInfo.printBasic());
                        }
                    }
                    businessCo.removeStuff(idNum);
                } else if (Integer.parseInt(clientRequest) == 3) {
                    infoOut.writeUTF("Кого нужно добавить? [1] Директор;[2] Бухгалтер;[3] Рабочий");
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
                            infoOut.writeUTF("Добавлен сотрудник: " + scanFirstName + " " + scanSecondName + serverInfo.printBasic());
                            continue;
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
                            infoOut.writeUTF("Добавлен сотрудник: " + scanFirstName + " " + scanSecondName + serverInfo.printBasic());
                            continue;
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
                            infoOut.writeUTF("Добавлен сотрудник: " + scanFirstName + " " + scanSecondName + serverInfo.printBasic());
                    }
                } else if (Integer.parseInt(clientRequest) == 4) {
                    String listPer = "";
                    for (Stuff it : businessCo) {
                        listPer += it.getInfo() + "\n";
                    }
                    infoOut.writeUTF(listPer + serverInfo.printBasic());
                } else {
                    infoOut.writeUTF("Некорректный ввод!!!" + serverInfo.printBasic());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String printBasic() {
        return "\nЧто требуется сделать?\n[1] Посмотреть сотрудника; [2] Удалить сотрудника;" +
                "[3] Добавить сотрудника; [4] Посмотреть список сотрудников";
    }
    public void addPersonal(){

    }
}
