package graph;

import java.util.Collection;
import java.util.HashSet;

public class Vertices<T> extends HashSet<T> {
    public Vertices(Collection<? extends T> c) {
        super(c);
    }
}
