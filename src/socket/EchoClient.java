package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EchoClient {
	
	public static void main(String[] args) throws IOException {
		
		Socket socket;
		socket = new Socket(args[0], Integer.parseInt(args[1]));
		System.out.println(socket + ": �����");
		
		OutputStream outputStream = socket.getOutputStream();
		InputStream inputStream = socket.getInputStream();
		
		byte[] buf = new byte[1024];
		int count;
		
		// Ű���� �Է��� ����Ʈ ������ �о� buf��� ����Ʈ�迭�� ��� �� ���� ����Ʈ���� ����
		// ���� ���� ���� ��� read �޼ҵ�� ����ϰ� Ctrl+c ���� ���� �����ϸ� -1�� ������
		// ��Ʈ���� ���� ��� -1�� ������
		while((count = System.in.read(buf)) != -1) {
			outputStream.write(buf, 0, count);
			count = inputStream.read(buf);
			System.out.write(buf, 0, count);
		}
		outputStream.close();
		System.out.println(socket + ": ���� ����");
		socket.close();
	
	}

}
