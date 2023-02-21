package consolechat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/* --------------------------------------------------------------------------------------------
 * ���� ���α׷� ó�� ���� :
 * Ŭ���̾�Ʈ�� ������ ��û�� �� �̸� accept�Ͽ� Ư�� Ŭ���̾�Ʈ�� ����ϴ� Socket�� �����ϰ�
 * �̸� ArrayList�� ������ ��, Ư�� Ŭ���̾�Ʈ�� �޼����� �����ϸ� ArrayList�� ��������
 * �ٸ� ��� Ŭ���̾�Ʈ Socket���� �ش� �޼����� ������
   -------------------------------------------------------------------------------------------- */
public class ConsoleChatServer extends Thread {

	private Socket sock;
	// 5���� Client�� ���� �� �ִ� Socket Ŭ���� ������ ArrayList ��ü ����
	private static ArrayList<Socket> clients = new ArrayList<Socket>(5);

	public ConsoleChatServer(Socket sock) {
		this.sock = sock;
	}
	
	/* -------------------------------------------------------------------
	 * Socket Ŭ���� ������ ArrayList���� Ŭ���Ʈ ������ ������
	 * ������ ���� ���������� ��� �� �� ������ �߻�
	   ------------------------------------------------------------------- */
	public void remove(Socket socket) {
		for(Socket s : ConsoleChatServer.clients) {
			if(socket == s) {
				ConsoleChatServer.clients.remove(socket);
				break;
			}
		}
	}

	// Thread�� �� ��
	@Override
	public void run() {
		try {
			System.out.println(sock + ": �����");
			InputStream fromClient = sock.getInputStream();
			OutputStream toClient = sock.getOutputStream();
			
			byte[] buf = new byte[1024];
			int count;
			
			while((count = fromClient.read(buf)) != -1) {
				for(Socket s : ConsoleChatServer.clients) {
					// ���� ���� Ŭ���̾�Ʈ �ڽſ��Դ� outputstream���� �ʱ� ���� ���� ó��
					if(sock != s) {
						toClient = s.getOutputStream();
						toClient.write(buf, 0, count);
						toClient.flush();
					}
				}
				System.out.write(buf, 0, count);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(sock != null) {
					sock.close();
					// ������ ������� Ŭ���̾�Ʈ�� ��� ArryList���� ����
					remove(sock);
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		try {
			ServerSocket serverSock = new ServerSocket(9999);
			System.out.println(serverSock + ": �������� ����");

			while (true) {
				Socket client = serverSock.accept();
				// Ŭ���̾�Ʈ���� �߻��� ��û�� �޾Ƶ鿩 Socket Ŭ���� ������ ArrayList�� ����
				clients.add(client);

				ConsoleChatServer myServer = new ConsoleChatServer(client);
				myServer.start();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
