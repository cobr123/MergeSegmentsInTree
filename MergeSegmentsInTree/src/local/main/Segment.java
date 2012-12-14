package local.main;

public final class Segment implements Comparable<Segment>{
	private final int a;
	private final int b;
	
	public Segment(final int a, final int b) {
		if(a > b){
			throw new IllegalArgumentException(" онец отрезка должен быть больше или равен началу");
		}
		
		this.a = a;
		this.b = b;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append("(").append(a).append(", ").append(b).append(")").toString();
	}

	@Override
	public int compareTo(Segment aThat) {
		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;

		// this optimization is usually worthwhile, and can
		// always be added
		if (this == aThat)
			return EQUAL;

		if (this.getA() < aThat.getA())
			return BEFORE;
		if (this.getA() > aThat.getA())
			return AFTER;

		if (this.getB() < aThat.getB())
			return BEFORE;
		if (this.getB() > aThat.getB())
			return AFTER;
		
		return EQUAL;
	}

	int getA(){
		return a;
	}
	int getB(){
		return b;
	}
}
