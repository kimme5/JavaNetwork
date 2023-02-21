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
			System.out.println(sock + ": �����");
			
			InputStream fromClient = sock.getInputStream();
			OutputStream toClient = sock.getOutputStream();
			
			byte[] buf = new byte[1024];
			int count;
			
			while((count = fromClient.read(buf)) != -1) {
				toClient.write(buf, 0, count);
				System.out.write(buf, 0, count);
			}
		} catch (IOException ex) {
			System.out.println(sock + ": ���� ����(" + ex + ")");
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {

		try {
			ServerSocket serverSock = new ServerSocket(Integer.parseInt(args[0]));
			System.out.println(serverSock + ": �������� ����");

			while (true) {
				// Ŭ���̾�Ʈ�� ��û�� �޾Ƶ��� ������ ���� ����
				Socket client = serverSock.accept();
				MultiThreadEchoServer myServer = new MultiThreadEchoServer(client);
				// run �޼ҵ� ����
				myServer.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
