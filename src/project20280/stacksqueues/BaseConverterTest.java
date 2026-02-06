package project20280.stacksqueues;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BaseConverterTest {

	@Test
	void Dec23ToBinTest() {
		assertEquals("10111", BaseConverter.convertToBinary(23));
	}
	
	@Test
	void LongToBinTest() {
		assertEquals("111001000000101011000010011101010110110001100010000000000000", BaseConverter.convertToBinary(1027010000000000000L));
	}

}
