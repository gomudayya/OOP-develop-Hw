import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DoorTest {

	@Test
	void test() {
		Door door = new Door();
		assertEquals(door.getState(), DoorState.CLOSED);
		door.close();
		assertEquals(door.getState(), DoorState.CLOSED);
		door.unlock();
		assertEquals(door.getState(), DoorState.CLOSED);
		door.open();
		assertEquals(door.getState(), DoorState.OPENED);
		door.lock();
		assertEquals(door.getState(), DoorState.OPENED);
		door.unlock();
		assertEquals(door.getState(), DoorState.OPENED);
		door.close();
		assertEquals(door.getState(), DoorState.CLOSED);
		door.lock();
		assertEquals(door.getState(), DoorState.LOCKED);
		door.close();
		assertEquals(door.getState(), DoorState.LOCKED);
		door.open();
		assertEquals(door.getState(), DoorState.LOCKED);
		door.unlock();
		assertEquals(door.getState(), DoorState.CLOSED);
		door.open();
		assertEquals(door.getState(), DoorState.OPENED);
		door.close();
		assertEquals(door.getState(), DoorState.CLOSED);
	}

}
