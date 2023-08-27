package com.serheev.utils;

import java.time.Duration;
import java.util.Arrays;

public final class TestUtils {

    private TestUtils() {
        throw new java.lang.UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static void executionTime(Runnable method) {
        long startTime = System.nanoTime();
        method.run();
        long endTime = System.nanoTime();

        Duration duration = Duration.ofNanos(endTime - startTime);
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        long millis = duration.toMillisPart();
        long nanos = duration.toNanosPart();

        System.out.println("Executed time: " + minutes + " M, " + seconds + " S, " + millis + " ms, " + nanos + " ns");
    }

    public static void printResult(int[] args) {
        System.out.println("Result: " + Arrays.toString(args));
    }

    public static void printResult(String arg) {
        System.out.println(arg);
    }

    public static void printMethodName() {
        System.out.println(getMethodName());
    }

    private static String getMethodName(){
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }
}
