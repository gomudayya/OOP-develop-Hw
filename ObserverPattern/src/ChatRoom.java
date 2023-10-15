import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2022년도 2학기 
 * @author 김상진 
 * @student 2019136149 박건우
 * @file ChatRoom.java
 * 관찰자 패턴: subject
 * 사용자 목록과 채팅 메시지 목록 유지
 * 채팅룸 목록, 사용자 목록 유지
 * 
 * 1. 이 실습에서는 관찰자 패턴을 사용하여 구현하고 있다. 특히, 채팅방을 관찰 대상, 채팅방의 참여자를 관
 * 찰자로 모델링하였다. 관찰자 패턴은 크게 push 또는 pull 방법으로 구현할 수 있다. 현재 제시된 소스는
 * 어느 방식으로 구현되어 있는지 설명하시오.
 * 
 * push 방식이다. ChatServer 클래스의 forwardMessage 함수에
 * receiver.update(roomName, message); 부분을 보면 알 수 있다.
 * update 함수의 인자로 roomName과 message를 유저에게 직접 전달해주는 구조이다.
 * 
 * 만약 pull 방식이었으면 update함수에는 인자가 없는 상태로, 주제클래스는(ChatServer) 상태가 바뀌었음만 알려주고,
 * User클래스의 update 메소드에서 자신이 원하는 정보를 가져와야한다.
 * 
 * 
 * 2. 관찰자인 참여자는 하나만 관찰하는 형태가 아니다. 여러 개의 채팅방을 관찰하고 있을 수 있다. 종
 * 류는 같지만 관찰 대상이 복수일 수 있는 구조이다. 관찰자는 관찰 대상을 어떻게 구분하는지 간단히
 * 설명하시오
 * 
 * User클래스에 roomLogs라는 해시맵 타입의 멤버변수에서 roomName을 이용하여 채팅창 별 메시지를 구분할 수 있도록 하였다.
 * 또한 chatServer의 forwardMessage 메소드에서도 Message와 roomName를 함께 전달해주기에 가능하다.
 * 
 * 
 * 3. 실제 코드를 보면 이 두 객체 외에 관찰 대상과 관찰자로 모델링하는 것이 있다. 그것이 무엇인지 제시
 * 하시오
 * 
 * ChatWindow클래스가 User클래스를 관찰하고 있다.
 * User의 notifyView 메소드를 보면 chatWindow.update(roomName); 라는 코드가 있다.
 * 이 코드를 통해 User클래스가 chatWindow 클래스에게 해당 roomName의 상태가 변경되었음을 알려주고,
 * chatWindow에서는 변경된 상태를 업데이트 한다. (updateChatArea)
 * 
 */
public class ChatRoom{
	private String roomName;
	private List<ChatMessage> roomLog = new ArrayList<>();
	// Map<사용자ID, 마지막 받은 메시지 색인>
	// 관찰자 목록
	private Map<String, Integer> userList = new HashMap<>();
	public ChatRoom(String name) {
		roomName = Objects.requireNonNull(name);
	}
	public String getRoomName() {
		return roomName;
	}
	
	// 관찰자 추가
	public boolean addUser(String userID) {
		// 완성하시오.
		if(userList.containsKey(Objects.requireNonNull(userID)))
			return false;
		
		int theLatestMessageIdx = roomLog.size()-1;
		userList.put(userID, theLatestMessageIdx);
		return true;
	}
	// 관찰자 삭제
	public boolean deleteUser(String userID) {
		if(!userList.containsKey(Objects.requireNonNull(userID)))
			return false;
		userList.remove(userID);
		return true;
	}
	
	public void newMessage(ChatMessage message) {
		if(!userList.containsKey(Objects.requireNonNull(message).getSenderID()))
			throw new IllegalArgumentException("존재하지 않는 전송자");
		if(message.getContent() == null)
			throw new IllegalArgumentException("메시지의 내용이 존재하지 않음.");
		
		message.setIndex(roomLog.size());
		roomLog.add(message);
		updateUsers();
	}
	
	// 관찰자 패턴에서 notifyObservers에 해당
	public void updateUsers() {
		userList.keySet().forEach(key->updateUser(key));
		//for(var key: userList.keySet()) updateUser(key);
	}
	public void updateUser(String userID) {
		ChatServer chatServer = ChatServer.getServer();		
		
		User user = chatServer.getUser(userID);
		if( !user.isOnline()) return;
		
		int theLatestMessageIdx = roomLog.size() -1;
		int updateIndex; // 서버의 메시지를 유저별로 어디까지 update했는지 표시하는 변수
		
		while( (updateIndex = userList.get(userID) ) < theLatestMessageIdx) {
			if (!roomLog.get(updateIndex+1).isDeleted()) // 지워지지 않은 메시지만 forward)
				chatServer.forwardMessage(userID, roomName, roomLog.get(updateIndex+1));
			
			userList.put(userID, updateIndex+1);// 메시지가 지워졌더라도 update색인은 더해준다.
		}
	}
	
	public void deleteMessage(int index) {
		// 완성하시오. 
		roomLog.get(index).setDeleteFlag();	//딜리트 플래그가 true인거는 업데이트를 안하도록
	}
}
