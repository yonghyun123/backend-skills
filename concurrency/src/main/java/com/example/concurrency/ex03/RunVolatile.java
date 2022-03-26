package com.example.concurrency.ex03;

public class RunVolatile {
    public static void main(String[] args) {
        RunVolatile sample = new RunVolatile();
        sample.runVolatileSample();
    }

    public void runVolatileSample(){
        VolatileSample sample = new VolatileSample();
        sample.start();

        try{
            Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("Sleep ended!!");
        sample.setDouble(-1);
        System.out.println("Set value is completed!!");

    }
}

class VolatileSample extends Thread{
    private double instanceVariable = 0;


    public void setDouble(double value){

        this.instanceVariable = value;
    }

    public void run(){
        while(instanceVariable == 0);
        System.out.println(instanceVariable);
    }
}