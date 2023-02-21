package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadEchoServer extends Thread {

	private Socket sock = null;

	public MultiThreadEchoServer(Socket sock) {
		this.sock = sock;
	}

	@Override
	public void run() {
		try {
			System.out.println(sock + ": 연결됨");
			
			InputStream fromClient = sock.getInputStream();
			OutputStream toClient = sock.getOutputStream();
			
			byte[] buf = new byte[1024];
			int count;
			
			while((count = fromClient.read(buf)) != -1) {
				toClient.write(buf, 0, count);
				System.out.write(buf, 0, count);
			}
		} catch (IOException ex) {
			System.out.println(sock + ": 연결 종료(" + ex + ")");
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {

		try {
			ServerSocket serverSock = new ServerSocket(Integer.parseInt(args[0]));
			System.out.println(serverSock + ": 서버소켓 생성");

			while (true) {
				// 클라이언트의 요청을 받아들일 서버쪽 소켓 생성
				Socket client = serverSock.accept();
				MultiThreadEchoServer myServer = new MultiThreadEchoServer(client);
				// run 메소드 실행
				myServer.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
