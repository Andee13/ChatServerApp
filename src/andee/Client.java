package andee;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final int PORT = 9001;
    public void start() {
        try (Socket socket = new Socket("localhost", PORT);
             Scanner scanner = new Scanner(socket.getInputStream())) {

            String name = "Andee";


            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.write(name);



            while(true) {
                if(scanner.hasNext()){
                    String line = scanner.nextLine();
                    System.out.println("line: " + line);
                    System.out.println(line.startsWith("SUBMITNAME"));
                    if (line.startsWith("SUBMITNAME")) {
                        printWriter.println(name);
                    } else if (line.startsWith("NAMEACCEPTED")) {
                        System.out.println("first method");
                        System.out.println("Ok");
                    } else if (line.startsWith("MESSAGE")) {
                        System.out.println("this m");
                        System.out.println(line.substring(8) + "\n");
                    }
                }

                Scanner sc = new Scanner(System.in);
                if(sc.hasNext()) {
                    String str = sc.nextLine();
                    System.out.println("Client = " + str);
                    printWriter.write(str);
                }
            }

//            while (true) {
//
//                Scanner sc = new Scanner(System.in);
//                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
//                outputStream.writeBytes(sc.next());
//                //printWriter.write(sc.nextLine());
//            }

        } catch (
                IOException ex) {
            System.out.println(ex);
        }
    }
}