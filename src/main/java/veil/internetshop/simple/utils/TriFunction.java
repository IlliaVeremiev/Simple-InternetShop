package veil.internetshop.simple.utils;

@FunctionalInterface
public interface TriFunction<F, S, T, A>{

	A apply(F f, S s, T t);
}
