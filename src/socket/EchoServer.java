package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSock = new ServerSocket(Integer.parseInt(args[0]));
		System.out.println(serverSock + ": 서버 소켓 생성");
		
		while(true) {
			Socket sock = serverSock.accept();
			System.out.println(sock + ": 연결됨");
			
			InputStream inputStream = sock.getInputStream();
			OutputStream outputStream = sock.getOutputStream();
			byte[] buf = new byte[1024];
			int count;
			
			while((count = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, count);
				System.out.write(buf, 0, count);
			}
			outputStream.close();
			System.out.println(sock + ": 연결 종료");
			
		}
	}
	
}
