
public enum DoorState {
	OPENED {
		public void open(Door door) {
			System.out.println("이미 열려 있음");
		}
	
		public void close(Door door) {
			System.out.println("문이 닫힘");
			door.changeToClosedState();
		}
		
		public void lock(Door door) {
			System.out.println("문이 열려 있어 잠글 수 없음");
		}
		
		public void unlock(Door door) {
			System.out.println("문이 잠겨 있지 않음");
		}
	}
	, CLOSED {
		public void open(Door door) {
			System.out.println("문이 열림");
			door.changeToOpenedState();
		}
	
		public void close(Door door) {
			System.out.println("문이 이미 닫혀 있음");
		}
		
		public void lock(Door door) {
			System.out.println("문을 잠금");
			door.changeToLockedState();
		}
		
		public void unlock(Door door) {
			System.out.println("문이 잠겨 있지 않음");
		}
	}
	, LOCKED {
		public void open(Door door) {
			System.out.println("문이 잠겨 있어 열 수 없음");
		}
	
		public void close(Door door) {
			System.out.println("문이 이미 닫혀 있음");
		}
		
		public void lock(Door door) {
			System.out.println("문이 이미 잠겨 있음");
		}
		
		public void unlock(Door door) {
			System.out.println("문의 잠금을 해제함");
			door.changeToClosedState();
		}
	};

	public abstract void open(Door door);
	public abstract void close(Door door);
	public abstract void lock(Door door);
	public abstract void unlock(Door door);
	
}
