package consolechat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/* ------------------------------------------------------------------------------------------------
 * 클라이언트 프로그램 처리 사항 :
 * 수시로 전달되는 메세지 처리를 위해 글을 읽는 부분을 Thread로 빼서 처리함
 * 이렇게 하지 않으면 자신이 전달하고자 하는 메세지를 작성하고 있을 때 다른 클라이언트에서 전달된
 * 메세지를 리얼타임으로 받지 못하고 메세지를 다 작성한 후 받게 되는 문제가 있음
   ------------------------------------------------------------------------------------------------ */
public class ConsoleChatClient {

	public static void main(String[] args) {

		Socket sock = null;
		try {
			sock = new Socket("localhost", 9999);
			System.out.println(sock + ": 연결됨");

			// 다른 클라이언트에서 전달받은 메세지를 처리하기 위한 Thread
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
			System.out.println("연결 종료 (" + ex + ")");
			ex.printStackTrace();
		} finally {
			try {
				if(sock != null) sock.close();
			} catch(IOException ex) {
				System.out.println("연결 종료 (" + ex + ")");
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
			System.out.println("연결 종료 (" + ex + ")");
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
