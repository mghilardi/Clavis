package main;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import demo.CustomNumberEntity;
import demo.NumberFinder;

public class NumberFinderImpTest {

	private final List<CustomNumberEntity> list = new ArrayList<CustomNumberEntity>();
	private final NumberFinder nf = new NumberFinderImp();
	private final JsonReader jsonReader = new JsonReader();
	private final List<CustomNumberEntity> listFromFile = jsonReader.getElementsFromFile("src\\data\\input.json");
	
	@Before
	public void setUp() throws Exception {
		list.add(new CustomNumberEntity("5"));
		list.add(new CustomNumberEntity("12.5"));
		list.add(new CustomNumberEntity("number"));
		list.add(new CustomNumberEntity("22"));
		list.add(new CustomNumberEntity("c"));
	}

	@Test
	public void testContains100() {
		assertTrue(nf.contains(100, listFromFile));
	}
	
	@Test
	public void testContainsMinus3() {
		assertTrue(nf.contains(-3, listFromFile));
	}
	
	@Test (expected = NullPointerException.class)
	public void testContainsNull() {
		assertTrue(nf.contains((Integer) null, listFromFile));
	}
	
	@Test (expected = NumberFormatException.class)
	public void testContainsNFE() {
		assertTrue(nf.contains(Integer.parseInt("s"), listFromFile));
	}
	
	@Ignore
	@Test
	public void testContainsInList() {
		NumberFinder nf = new NumberFinderImp();
		assertTrue(nf.contains(5, list));
		assertTrue(nf.contains(22, list));
		assertFalse(nf.contains(99, list));
		assertFalse(nf.contains(1, list));
	}

	@Test
	public void testReadFromFile() {
		assertNotNull(listFromFile);
		assertTrue(listFromFile.size() == 9);
	}

}
