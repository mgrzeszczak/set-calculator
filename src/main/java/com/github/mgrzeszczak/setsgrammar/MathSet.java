package com.github.mgrzeszczak.setsgrammar;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MathSet<T> {

    private final Set<T> data;

    private MathSet(Set<T> data) {
        this.data = data;
    }

    public static <S> MathSet<S> of(Set<S> data) {
        return new MathSet<>(data);
    }

    public MathSet<T> union(MathSet<T> other) {
        return Stream.concat(
                data.stream(),
                other.data.stream()
        ).collect(Collectors.collectingAndThen(Collectors.toSet(), MathSet::of));
    }

    public MathSet<T> intersection(MathSet<T> other) {
        return MathSet.of(data.stream().filter(other.data::contains).collect(Collectors.toSet()));
    }

    public MathSet<T> difference(MathSet<T> other) {
        return MathSet.of(data.stream().filter(i -> !other.data.contains(i)).collect(Collectors.toSet()));
    }

    @Override
    public String toString() {
        return "{" + data.stream().map(Object::toString).collect(Collectors.joining(",")) + "}";
    }
}
