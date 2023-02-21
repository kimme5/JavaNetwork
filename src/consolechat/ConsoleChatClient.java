package consolechat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/* ------------------------------------------------------------------------------------------------
 * Ŭ���̾�Ʈ ���α׷� ó�� ���� :
 * ���÷� ���޵Ǵ� �޼��� ó���� ���� ���� �д� �κ��� Thread�� ���� ó����
 * �̷��� ���� ������ �ڽ��� �����ϰ��� �ϴ� �޼����� �ۼ��ϰ� ���� �� �ٸ� Ŭ���̾�Ʈ���� ���޵�
 * �޼����� ����Ÿ������ ���� ���ϰ� �޼����� �� �ۼ��� �� �ް� �Ǵ� ������ ����
   ------------------------------------------------------------------------------------------------ */
public class ConsoleChatClient {

	public static void main(String[] args) {

		Socket sock = null;
		try {
			sock = new Socket("localhost", 9999);
			System.out.println(sock + ": �����");

			// �ٸ� Ŭ���̾�Ʈ���� ���޹��� �޼����� ó���ϱ� ���� Thread
			ServerHandler handler = new ServerHandler(sock);
			handler.start();

			OutputStream toServer = sock.getOutputStream();
			byte[] buf = new byte[1024];
			int count;
			
			while((count = System.in.read(buf)) != -1) {
				toServer.write(buf, 0, count);
				toServer.flush();
			}
		} catch (IOException ex) {
			System.out.println("���� ���� (" + ex + ")");
			ex.printStackTrace();
		} finally {
			try {
				if(sock != null) sock.close();
			} catch(IOException ex) {
				System.out.println("���� ���� (" + ex + ")");
			}
		}

	}
}

class ServerHandler extends Thread {
	Socket sock = null;

	public ServerHandler(Socket sock) {
		this.sock = sock;
	}

	public void run() {

		InputStream fromServer = null;
		try {
			fromServer = sock.getInputStream();

			byte[] buf = new byte[1024];
			int count;

			while ((count = fromServer.read(buf)) != -1)
				System.out.write(buf, 0, count);

		} catch (IOException ex) {
			System.out.println("���� ���� (" + ex + ")");
			ex.printStackTrace();
		} finally {
			try {
				if (fromServer != null)
					fromServer.close();
				if (sock != null)
					sock.close();
			} catch (IOException ex) {

			}
		}
	}

}
