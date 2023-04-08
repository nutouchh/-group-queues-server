package ru.rg.studentsqueue.general.types;

/**
 * A functional interface representing a function with two arguments.
 *
 * @param <A1> type of the first argument
 * @param <A2> type of the second argument
 * @param <R>  return value type
 */
public interface Func2Args<A1, A2, R> {
    R apply(A1 arg1, A2 arg2);
}
