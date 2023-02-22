package rmiwas.server;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class WasControlImpl extends UnicastRemoteObject implements WasControl {

	static Runtime r = Runtime.getRuntime();
	// �Ʒ� ��η� ��ġ�� tomcat������ "Progam Files" ������ ���⸦ �ν����� ���� ��� ������ �߻���
//	static String tomcatPath = "C:\\Program Files\\apache-tomcat-9.0.71\\bin";
	static String tomcatPath = "C:\\apache-tomcat-9.0.71\\bin";

	protected WasControlImpl() throws RemoteException {
		super();
	}

	@Override
	public String startTomcat() throws RemoteException {
		String ret = "";
		try {
			Process tomcat = r.exec("cmd /c start " + tomcatPath + "/startup.bat run", null, new File(tomcatPath));
			tomcat.waitFor();	// ���μ��� ������ ���������� ��ٸ�
			ret = "Start OK... ";
		} catch(Exception e) {
			System.out.println(">>> [Server] Tomcat Start Fail... " + e);
			ret = "Start Fail... ";
		}
		return ret;
	}

	@Override
	public String stopTomcat() throws RemoteException {
		String ret = "";
		try {
			Process tomcat = r.exec("cmd /c start " + tomcatPath + "/shutdown.bat run", null, new File(tomcatPath));
			tomcat.waitFor();
			ret = "Shutdown OK... ";
		} catch(Exception e) {
			System.out.println(">>> [Server] Tomcat Stop Fail... " + e);
		}
		return ret;
	}
	
	

}
