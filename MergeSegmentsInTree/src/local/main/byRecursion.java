package local.main;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public final class byRecursion {
	private final Set<Segment> tree = new TreeSet<Segment>();
	private List<Segment> toRemove = new LinkedList<Segment>();
	private Random rand;
	private long remCounter = 0;

	@Override
	public String toString() {
		return tree.toString();
	}

	public void add(final Segment seg) {
		add(seg, tree.iterator());
	}

	public void add(final Segment seg, Iterator<Segment> iter) {
		// System.out.println("add " + seg + ": ");

		if (!iter.hasNext()) {
			tree.add(seg);
		}
		while (iter.hasNext()) {
			Segment old = iter.next();
			toRemove.add(old);
			if (old.getA() == seg.getA()) {
				if (old.getB() > seg.getB()) {
					tree.add(seg);
					tree.add(new Segment(seg.getA() + 1, old.getB()));
				} else if (old.getB() < seg.getB()) {
					add(seg, iter);
				} else if (old.getB() == seg.getB()) {
					tree.add(seg);
				}
				break;
			} else if (old.getA() > seg.getA()) {
				if (old.getB() > seg.getB()) {
					tree.add(new Segment(seg.getB() + 1, old.getB()));
					tree.add(seg);
				} else {
					add(seg, iter);
				}
				break;
			} else if (old.getA() < seg.getA() && old.getB() >= seg.getA()) {
				if (old.getB() == seg.getB()) {
					tree.add(new Segment(old.getA(), seg.getA() - 1));
					tree.add(seg);
				} else if (old.getB() < seg.getB()) {
					add(seg, iter);
					tree.add(new Segment(old.getA(), seg.getA() - 1));
				} else if (old.getB() > seg.getB()) {
					tree.add(seg);
					tree.add(new Segment(old.getA(), seg.getA() - 1));
					tree.add(new Segment(seg.getB() + 1, old.getB()));
				}
				break;
			}
			//toRemove.remove(old);
		}
		if (!toRemove.isEmpty()) {
			for (Segment rem : toRemove) {
				tree.remove(rem);
				++remCounter;
			}
			toRemove = new LinkedList<Segment>();
		}
	}

	public void test() {
		int max1 = 100;
		int max2 = max1 * 100;
		long cnt = 0;

		long start = System.nanoTime();
		for (int i = 0; i < max1; ++i) {
			for (int k = i; k < max2; ++k) {
				add(new Segment(i, k));
				++cnt;
			}
		}
		System.out.println((System.nanoTime() - start) / 1000000000.0);
		System.out.println(cnt);
		System.out.println();
	}

	public void test1() {
		// Example assumes these variables have been initialized
		// above, e.g. as method parameters, fields, or otherwise
		// such as: rand = new Random();
		int num = 100;
		int min = 0;
		int max = num * 1000 * 1000;
		long sum = 0;
		rand = new Random(0);
		
		for (int i = 0; i < max; ++i) {
			int randomNum1 = rand.nextInt(max - min) + min;
			int randomNum2 = rand.nextInt(max - randomNum1) + randomNum1;
			// System.out.println(new Segment(randomNum1, randomNum2));
			long start = System.nanoTime();
			add(new Segment(randomNum1, randomNum2));
			sum += System.nanoTime() - start;
		}
		System.out.println(sum / 1000000000.0 / num
				+ " sec for million records");
		System.out.println(tree.size());
		System.out.println("remCounter = " + remCounter);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		byRecursion byRec = new byRecursion();

		// test();
		// test();
		// test();
		byRec.add(new Segment(1, 40));
		byRec.add(new Segment(10, 30));
		byRec.add(new Segment(-1, 50));
		byRec.add(new Segment(3, 4));
		byRec.add(new Segment(2, 90));
		System.out.println(byRec.toString());
		// test1();
		// test1();
		// test1();
	}

}
