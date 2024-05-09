package lab3;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import balloon.Balloon;

public class BalloonTests {

	private Balloon b;

	@Before
	public void setup() {
		b = new Balloon(10);
	}

	@Test
	public void testBlowAfterPop() {
		b.pop();
		assertEquals(0, b.getRadius());
		assertEquals(true, b.isPopped());

		b.blow(5);
		assertEquals(0, b.getRadius()); // error for 1 & 2
		assertEquals(true, b.isPopped());
	}

	@Test
	public void testBlow() {
		b.blow(5);
		assertEquals(5, b.getRadius());
		assertEquals(false, b.isPopped());

		b.blow(5);
		assertEquals(10, b.getRadius()); // error for 3
		assertEquals(false, b.isPopped());
	}

	@Test
	public void testDeflate() {
		b.deflate();
		assertEquals(0, b.getRadius());
		assertEquals(false, b.isPopped()); // error for 4
	}
}
