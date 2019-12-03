package tests;

import active_manager.*;
import org.junit.Before;
import org.junit.Test;

public class Testagm {
	private GameDBImpl DB;
	private ActiveManager AGM;
	
	@Before
	public void SetUp() throws Exception{
		DB = new GameDBImpl();
		AGM = new ActiveManager(DB);
	}
	
	@Test
	public void testAddNewGame() {
		
	}
	
}
