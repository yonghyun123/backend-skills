package com.example.concurrency.ex07;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class PrimeGenerator implements Runnable {

    private final List<BigInteger> primes = new ArrayList<>();
    private double cancelled = 0;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;

        while (cancelled == 0) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    public void cancel(){
        cancelled = -1;
    }

    public synchronized List<BigInteger> get(){
        return new ArrayList<>(primes);
    }

}

class Main {
    public static void main(String[] args) throws InterruptedException{
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();

        try {
            Thread.sleep(1000);
        } finally {
//            generator.cancel();
        }
        generator.cancel();
        System.out.println(generator.get());
    }
}

