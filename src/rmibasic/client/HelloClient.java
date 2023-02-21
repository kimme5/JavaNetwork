package rmibasic.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import rmibasic.server.Hello;

/* ------------------------------------------------------------------------------------------------------------------------
 * RMI 클라이언트 Application : HelloClient
 * 1) 원격 객체를 lookup 메소드를 이용하여 이름으로 찾아낸 후 
 *    해당 객체가 제공하는 원격 메소드를 호출하는 프로그램
 * 2) RMI는 원격 객체에 대한 참조로 stub을 사용함
 *    stub은 파라미터를 skeleton에게 직렬화하여 원격 메소드를 호출하고
 *    결과를 다시 직렬화하여 stub에게 돌려줌
 *    즉, RMI클라이언트 Application에서 사용하는 원격 객체의 reference는
 *    실제로 stub에 대한 reference임
 * 3) RMI클라이언트에서 RMI서버의 메소드 호출 
 *    --> 로컬 stub객체 전달 
 *    --> 원격(Remote Serve)의 skeleton 객체에 전달 
 *    --> 실제 객체의 메소드 호출 순으로 전달
 * 4) Java2 이후부터는 skeleton을 거치지 않고 stub이 원격 객체를 직접 찾아감
 * RMI 실행방법 
 * - 서버 실행용 콘솔창 오픈
 * 1) stub 클래스 생성 : C:\Users\seong\workspace\JavaNetwork\bin> rmic rmiserver.HelloImpl --> HelloImpl_Stub.class 생성
 * 2) rmiregistry 시작 : C:\Users\seong\workspace\JavaNetwork\bin> start rmiregistry 1099
 * 3) rmi서버 구동 : C:\Users\seong\workspace\JavaNetwork\bin> java rmiserver.HelloServer
 * - 클라이언트 실행용 콘솔창 오픈
 * 4) rmi클라이언트 구동 : C:\Users\seong\workspace\JavaNetwork\bin> java rmiclient.HelloClient SeongwooKim
 * ------------------------------------------------------------------------------------------------------------------------ */
public class HelloClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Object obj = Naming.lookup("rmi://localhost:1099/HelloRemote");
			// remoteObj 클래스변수에는 HelloImpl_Stub 클래스 객체가 return됨
			Hello remoteObj = (Hello) obj;
			String message = remoteObj.sayHello(args[0]);
			System.out.println(message);
		} catch(RemoteException ex) {
			System.out.println("Something has gone wrong during remote mothod call... ");
		} catch(MalformedURLException ex) {
			System.out.println("Check URL String... ");
		} catch(NotBoundException ex) {
			System.out.println("Could Not bound... ");
		}
	}

}
