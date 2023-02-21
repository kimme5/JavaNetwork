package rmidbcon.client;

import java.rmi.Naming;
import java.util.ArrayList;

import rmidbcon.common.AnyThing;
import rmidbcon.common.AnyThingImpl;
import rmidbcon.server.RemoteIF;

/* ---------------------------------------------------------------------------------------------
 * RMI클라이언트 - RmiClient 
 * 1) RmiClient 클래스는 RMI서버 Application에서 생성한 원격객체 RemoteObjAndServer를 
 *    remoteObjAndServer라는 이름으로 찾아 원격 메소드 toDo()를 호출하는 일을 수행함
 * 2) RmiClient 클래스는 원격 reference remoteObj를 통해 원격 메소드 toDo()를 호출하기 전에
 *    AnyThingImpl 객체를 생성하고 이 객체를 원격 메소드 toDo() 호출시에 파라미터로 사용함
 * 3) 원격 메소드인 toDo()의 파라미터 타입은 AnyThing 인터페이스이고, 
 *    실제는 이것의 구현체인 AnyThingImpl의 인스턴스가 인자로 전달됨
 * 실행방법
 * 1) 콘솔창을 열어 stub 클래스 생성 
 *  - C:\Users\seong\workspace\JavaNetwork\bin>rmic rmidbcon.server.RemoteObjAndServer 실행
 *  - C:\Users\seong\workspace\JavaNetwork\bin\rmidbcon\server\RemoteObjAndServer_Stub.class 생성여부 확인
 * 2) C:\Users\seong\workspace\JavaNetwork\bin\rmidbcon\server>start rmiregistry 1099 실행
 * 3) eclipse에서 RemoteObjAndServer 선택 후 Run As../Java Application 실행
 * 4) eclipse에서 RmiClient 선택 후 Run As../Java Application 실행
 * 5) Result for Query : SELECT CITY_NAME FROM CITY 쿼리 결과 출력
   --------------------------------------------------------------------------------------------- */
public class RmiClient {

	public static void main(String[] args) {
		
		String rmiString = null;
//		String sql = "select ename from emp";
		// PostgreSQL DB사용
		String sql = "SELECT CITY_NAME FROM CITY";
		try {
			RemoteIF remoteObj = (RemoteIF) Naming.lookup("rmi://localhost:1099/remoteObjAndServer");
			AnyThing anyThing = new AnyThingImpl(sql);
			
			System.out.println("Result for Query : " + sql);
			ArrayList<String> arrayList = (ArrayList<String>) remoteObj.toDo(anyThing);
			
			for(String s : arrayList) {
				System.out.println(s);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
