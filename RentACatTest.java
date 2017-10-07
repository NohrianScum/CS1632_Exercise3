import java.util.ArrayList;
import static org.junit.Assert.*;

import org.junit.*;

import org.mockito.*;

public class RentACatTest {

    // Re-usable RentACat reference.
    RentACat _r;

    // Create a new RentACat instance before each test.
    @Before
    public void setup() {
	_r = new RentACat();
    }
    
    // Assert that creating a new RentACat instance
    // does not return a null reference
    @Test
    public void testRentACatExists() {
	assertNotNull(_r);
    }
	
	// Assert that returning a cat that has not yet
	// been rented out will return false.
	@Test
	public void returnCatTestFalse() {
		Cat mockCat = Mockito.mock(Cat.class);
		boolean isReturned = _r.returnCat(mockCat);
		assertFalse(isReturned);
	}
	
	
	// Assert that attempting to rent a cat that has
	// already been rented out will return false.
	@Test
	public void rentCatTestFalse() {
		Cat mockCat = Mockito.mock(Cat.class);
		Mockito.when(mockCat.getRented()).thenReturn(true);
		boolean isRented = _r.rentCat(mockCat);
		assertFalse(isRented);
	}
	
	// Assert that listing an array of cats will 
	// return a single String list of cats.
	@Test
	public void listCatsTestString() {
		ArrayList<Cat> cl = new ArrayList<Cat>();
		cl.add(new Cat(1, "Mungojerrie"));
		cl.add(new Cat(2, "Rumpleteazer"));
		String listedCats = _r.listCats(cl);
		assertEquals("ID 1. Mungojerrie\nID 2. Rumpleteazer\n", listedCats);
	}
	
	// Assert that listing an array of cats will
	// NOT list any cats that have been rented.
	@Test
	public void listCatsTestRented() {
		ArrayList<Cat> cl = new ArrayList<Cat>();
		Cat mockCat = Mockito.mock(Cat.class);
		cl.add(new Cat(1, "Mungojerrie"));
		cl.add(new Cat(2, "Rumpleteazer"));
		cl.add(mockCat);
		Mockito.when(mockCat.getRented()).thenReturn(true);
		String listedCats = _r.listCats(cl);
		assertEquals("ID 1. Mungojerrie\nID 2. Rumpleteazer\n", listedCats);
	}
	
	// Assert that attempting to look for the id number
	// of 0 in a list of cats will return false.
	@Test
	public void catExistsTestDefault() {
		ArrayList<Cat> cl = new ArrayList<Cat>();
		Cat mockCat = Mockito.mock(Cat.class);
		int catId = 0;
		cl.add(mockCat);
		boolean doesExist = _r.catExists(catId, cl);
		assertFalse(doesExist);
	}
	
	// Assert that attempting to look for a cat in an
	// empty list of cats will return false.
	@Test
	public void catExistsTestZero() {
		ArrayList<Cat> cl = new ArrayList<Cat>();
		int catId = 1;
		boolean doesExist = _r.catExists(catId, cl);
		assertFalse(doesExist);
	}
	
	// Assert that attempting to check the availability of a cat 
	// with an invalid id number will return false.
	@Test
	public void catAvailableTestInvalid() {
		ArrayList<Cat> cl = new ArrayList<Cat>();
		Cat mockCat = Mockito.mock(Cat.class);
		Cat mockCat2 = Mockito.mock(Cat.class);
		cl.add(mockCat);
		cl.add(mockCat2);
		int catId = 7;
		boolean isAvailable = _r.catAvailable(catId, cl);
		assertFalse(isAvailable);
	}
	
	
	// Assert that a returned cat ID will match the ID that is requested.
	@Test
	public void getCatTest() {
		ArrayList<Cat> cl = new ArrayList<Cat>();
		int catId = 2;
		Cat mockCat = Mockito.mock(Cat.class);
		Mockito.when(mockCat.getId()).thenReturn(1);
		Cat mockCat2 = Mockito.mock(Cat.class);
		Mockito.when(mockCat.getId()).thenReturn(2);
		cl.add(mockCat);
		cl.add(mockCat2);
		Cat testCat = _r.getCat(catId, cl);
		assertEquals(testCat.getId(), catId);
	}
}