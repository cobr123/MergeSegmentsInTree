package local.test;

import static org.junit.Assert.*;
import local.main.Segment;
import local.main.byRecursion;
import org.junit.Test;

public class Test1 {

	@Test
	public void test1() {
		byRecursion byRec = new byRecursion();

		byRec.add(new Segment(1, 40));
		
	    assertEquals("Result", "[(1, 40)]", byRec.toString());
	}
	@Test
	public void test2() {
		byRecursion byRec = new byRecursion();

		byRec.add(new Segment(1, 40));
		byRec.add(new Segment(10, 20));
		
	    assertEquals("Result", "[(1, 9), (10, 20), (21, 40)]", byRec.toString());
	}
	@Test
	public void test3() {
		byRecursion byRec = new byRecursion();

		byRec.add(new Segment(1, 40));
		byRec.add(new Segment(10, 20));
		byRec.add(new Segment(-10, 100));
		
	    assertEquals("Result", "[(-10, 100)]", byRec.toString());
	}
	@Test
	public void test4() {
		byRecursion byRec = new byRecursion();

		byRec.add(new Segment(1, 40));
		byRec.add(new Segment(10, 20));
		byRec.add(new Segment(-10, 100));
		byRec.add(new Segment(0, 10));

	    assertEquals("Result", "[(-10, -1), (0, 10), (11, 100)]", byRec.toString());
	}
	@Test
	public void test23() {
		byRecursion byRec = new byRecursion();

		byRec.add(new Segment(1, 40));
		byRec.add(new Segment(10, 30));
		byRec.add(new Segment(-1, 50));
		byRec.add(new Segment(3, 4));
	    assertEquals("Result", "[(-1, 2), (3, 4), (5, 50)]", byRec.toString());
		byRec.add(new Segment(2, 90));		
	    assertEquals("Result", "[(-1, 1), (2, 90)]", byRec.toString());
	}

}
