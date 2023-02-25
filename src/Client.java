import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (Socket clientPart = new Socket("localhost",666)){
            DataInputStream infoIn = new DataInputStream(clientPart.getInputStream());
            DataOutputStream infoOut = new DataOutputStream(clientPart.getOutputStream());
            System.out.println("Что требуется сделать?\n[1] Посмотреть сотрудника; [2] Удалить сотрудника;" +
                    "[3] Добавить сотрудника; [4] Посмотреть список сотрудников");
            while (true){
                String idSt = scanner.nextLine();
                infoOut.writeUTF(idSt);
                if(idSt.equals("Стоп")) break;
                System.out.println(infoIn.readUTF());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
