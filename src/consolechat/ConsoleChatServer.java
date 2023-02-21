package consolechat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/* --------------------------------------------------------------------------------------------
 * 서버 프로그램 처리 사항 :
 * 클라이언트가 접속을 요청할 때 이를 accept하여 특정 클라이언트를 상대하는 Socket을 생성하고
 * 이를 ArrayList에 저장한 후, 특정 클라이언트가 메세지를 생성하면 ArrayList에 보관중인
 * 다른 모든 클라이언트 Socket에게 해당 메세지를 전달함
   -------------------------------------------------------------------------------------------- */
public class ConsoleChatServer extends Thread {

	private Socket sock;
	// 5개의 Client를 담을 수 있는 Socket 클래스 형태의 ArrayList 객체 생성
	private static ArrayList<Socket> clients = new ArrayList<Socket>(5);

	public ConsoleChatServer(Socket sock) {
		this.sock = sock;
	}
	
	/* -------------------------------------------------------------------
	 * Socket 클래스 형태의 ArrayList에서 클라언트 소켓을 제거함
	 * 접속을 끊고 나가버리는 경우 쓸 때 오류가 발생
	   ------------------------------------------------------------------- */
	public void remove(Socket socket) {
		for(Socket s : ConsoleChatServer.clients) {
			if(socket == s) {
				ConsoleChatServer.clients.remove(socket);
				break;
			}
		}
	}

	// Thread가 할 일
	@Override
	public void run() {
		try {
			System.out.println(sock + ": 연결됨");
			InputStream fromClient = sock.getInputStream();
			OutputStream toClient = sock.getOutputStream();
			
			byte[] buf = new byte[1024];
			int count;
			
			while((count = fromClient.read(buf)) != -1) {
				for(Socket s : ConsoleChatServer.clients) {
					// 글을 보낸 클라이언트 자신에게는 outputstream하지 않기 위한 조건 처리
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
					// 접속을 끊어버린 클라이언트의 경우 ArryList에서 제거
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
			System.out.println(serverSock + ": 서버소켓 생성");

			while (true) {
				Socket client = serverSock.accept();
				// 클라이언트에서 발생한 요청을 받아들여 Socket 클래스 형태의 ArrayList에 담음
				clients.add(client);

				ConsoleChatServer myServer = new ConsoleChatServer(client);
				myServer.start();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
