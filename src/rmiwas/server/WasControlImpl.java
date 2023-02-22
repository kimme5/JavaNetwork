package rmiwas.server;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class WasControlImpl extends UnicastRemoteObject implements WasControl {

	static Runtime r = Runtime.getRuntime();
	// 아래 경로로 설치한 tomcat에서는 "Progam Files" 사이의 띄어쓰기를 인식하지 못해 경로 에러가 발생함
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
			tomcat.waitFor();	// 프로세스 수행이 끝날때까지 기다림
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
