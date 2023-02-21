package rmidbcon.common;

import java.io.Serializable;

/* --------------------------------------------------------------------------
 * AnyThing 인터페이스는 클라이언트/서버에서 공통적으로 사용됨
 * 클라이언트에서는 원격 객체의 원격 메소드인 toDo()를 호출할 때
 * 이에 대한 파라미터 객체로 전달되므로 직렬화(Serializable)가 가능해야 함
 * doThing() 메소드는 원격 메소드는 아니지만, 리턴 값은 원격으로 
 * 보내어 진다는 것을 짐작할 수 있음
   -------------------------------------------------------------------------- */
public interface AnyThing extends Serializable {
	public Serializable doThing();
}
