import java.io.*;
import java.net.*;

public class FileTransferServer {
    public static void main(String[] args) {
        int port = 5000;
        String filePath = "example.txt";  // Path to the file you want to send

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress());

            File file = new File(filePath);
            FileInputStream fileIn = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fileIn);
            OutputStream os = socket.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = bis.read(buffer)) > 0) {
                os.write(buffer, 0, bytesRead);
            }

            os.flush();
            bis.close();
            socket.close();

            System.out.println("File sent successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
