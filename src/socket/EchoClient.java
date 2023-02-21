package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoClient {
	
	public static void main(String[] args) throws IOException {
		
		Socket socket;
		socket = new Socket(args[0], Integer.parseInt(args[1]));
		System.out.println(socket + ": 연결됨");
		
		OutputStream outputStream = socket.getOutputStream();
		InputStream inputStream = socket.getInputStream();
		
		byte[] buf = new byte[1024];
		int count;
		
		// 키보드 입력을 바이트 단위로 읽어 buf라는 바이트배열에 기록 후 읽은 바이트수를 리턴
		// 읽을 것이 없는 경우 read 메소드는 대기하고 Ctrl+c 등을 눌러 종료하면 -1을 리턴함
		// 스트림의 끝인 경우 -1을 리턴함
		while((count = System.in.read(buf)) != -1) {
			outputStream.write(buf, 0, count);
			count = inputStream.read(buf);
			System.out.write(buf, 0, count);
		}
		outputStream.close();
		System.out.println(socket + ": 연결 종료");
		socket.close();
	
	}

}
