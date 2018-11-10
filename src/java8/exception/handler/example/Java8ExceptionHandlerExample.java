/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java8.exception.handler.example;

import java.util.function.BiConsumer;

/**
 *
 * @author Momir Sarac
 */
public class Java8ExceptionHandlerExample {

    // array of ints
    static int[] listOfIntegers = {465, 23, 63, 235, 642};
    // declare a variable x as 10
    static int x = 10;
    // declare a variable y as 0
    static int y = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("Division by 10");
        processing(listOfIntegers, x, genericBiConsumerWrapper((v, k) -> System.out.println(v / k), ArithmeticException.class));
        System.out.println("Division by 0");
        processing(listOfIntegers, y, genericBiConsumerWrapper((v, k) -> System.out.println(v / k), ArithmeticException.class));

    }

    private static void processing(int[] listOfIntegers, int number, BiConsumer<Integer, Integer> biConsumer) {
        for (int i : listOfIntegers) {
            biConsumer.accept(i, number);
        }
    }

    /**
     * Generic method that handles lambda expressions for Functional Interface
     * of type BiConsumer.This method takes two arguments, the lambda expression
     * and the type of Exception to be caught.
     *
     * @param <T> generic object
     * @param <V> generic object
     * @param <E> generic object of type Exception
     * @param biConsumer Biconsumer lambda expression
     * @param exceptionClass parameter of type Exception
     * @return
     */
    public static <T, V, E extends Exception> BiConsumer<T, V> genericBiConsumerWrapper(BiConsumer<T, V> biConsumer, Class<E> exceptionClass) {

        return (T v, V k) -> {
            try {
                biConsumer.accept(v, k);

            } catch (Exception ex) {
                try {
                    E exCast = exceptionClass.cast(ex);
                    System.err.println("Exception occured : " + exCast.getMessage());
                } catch (ClassCastException exception) {
                    throw ex;
                }
            }
        };
    }

}
