package it.unibo.tavernproj.view.form;

//presa dall'esame 2015 primo appello b

/**
 * A functional interface, with a method that takes two elements and combine/aggregate them into one
 * E.g., it could be the sum of the numbers, or concatenation of two strings
 */

public interface Aggregator<X> {
  X aggregate(X one, X two);
}
