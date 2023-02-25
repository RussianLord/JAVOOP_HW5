import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        Stuff ivan = new Director(1,"Директор",10000,"Иван","Петров",30,12);
        Stuff oleg = new Accounter(2,"Бухгалтер",20000,"Олег","Иванов",40,"Microsoft");
        Stuff anton = new Worker(3,"Механик",15000,"Антон","Сидоров",27);
        Company businessCo = new Company();
        businessCo.listAdd(ivan);
        businessCo.listAdd(oleg);
        businessCo.listAdd(anton);
        Scanner scanner = new Scanner(System.in);
        try (ServerSocket serverPart = new ServerSocket(666)){
            System.out.println("Сервер запущен... Ожидаем подключение клиента...");
            Socket serverInterface = serverPart.accept();
            System.out.println("Клиент произвёл подключение...");
            DataOutputStream infoOut = new DataOutputStream(serverInterface.getOutputStream());
            DataInputStream infoIn = new DataInputStream(serverInterface.getInputStream());

            while (true){
                int clientRequest = infoIn.read();
                System.out.print("Что требуется сделать?");
                System.out.print("[1] Посмотреть сотрудника; [2] Удалить сотрудника; " +
                        "[3] Добавить сотрудника; [4] Посмотреть список сотрудников");
//                if(clientRequest.equals("Стоп")) break;
                if(clientRequest == 1){
                    infoOut.writeUTF("Введите ID сотрудника... ");
                    int idNum = infoIn.read();
                    for (Stuff id: businessCo) {
                        if(idNum==id.getId()){
                            infoOut.writeUTF(id.getInfo());

                        }
                    }
                } else if (clientRequest == 2) {
                    infoOut.writeUTF("Введите ID сотрудника для удаления... ");
                    int idNum = infoIn.read();
                    for (Stuff id: businessCo) {
                        if(idNum==id.getId()){
                            businessCo.removeStuff(idNum);
                            infoOut.writeUTF("Сотрудник: " + id.getFirstName() + " " + id.getSecondName() + " удалён.");

                        }
                    }
                } else if (clientRequest == 3) {
                    int scanID = scanner.nextInt();
                    int scanSalary = scanner.nextInt();
                    int scanAge = scanner.nextInt();
                    String scanPosition = scanner.next();
                    String scanFirstName = scanner.next();
                    String scanSecondName = scanner.next();
                    Stuff nameScan = new Stuff(scanID,scanPosition,scanSalary,scanFirstName,scanSecondName,scanAge);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
