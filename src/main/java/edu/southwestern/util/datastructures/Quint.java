package edu.southwestern.util.datastructures;

/**
 * Tuple of objects
 *
 * @author Devon Fulcher
 * @param <V> Type 1
 * @param <W> Type 2
 * @param <X> Type 3
 * @param <Y> Type 4
 * @param <Z> Type 5
 */
public class Quint<V, W, X, Y, Z> {

	public V t1;
	public W t2;
	public X t3;
	public Y t4;
	public Z t5;

	public Quint(V t1, W t2, X t3, Y t4, Z t5) {
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
		this.t4 = t4;
		this.t5 = t5;
	}

	@Override
	public String toString() {
		return "(" + t1.toString() + "," + t2.toString() + "," + t3.toString() + "," + t4.toString() + "," + t5.toString() + ")";
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Quad) {
			@SuppressWarnings("unchecked")
			Quint<V, W, X, Y, Z> p = (Quint<V, W, X, Y, Z>) other;
			return t1.equals(p.t1) && t2.equals(p.t2) && t3.equals(p.t3) && t4.equals(p.t4) && t5.equals(p.t5);
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 71 * hash + (this.t1 != null ? this.t1.hashCode() : 0);
		hash = 71 * hash + (this.t2 != null ? this.t2.hashCode() : 0);
		hash = 71 * hash + (this.t3 != null ? this.t3.hashCode() : 0);
		hash = 71 * hash + (this.t4 != null ? this.t4.hashCode() : 0);
		hash = 71 * hash + (this.t5 != null ? this.t4.hashCode() : 0);
		return hash;
	}
}