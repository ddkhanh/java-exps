package org.exps.deadlock;

public class Baby extends Thread{
    private static final FruitResources fruits = new FruitResources();
    public static final int SLEEP_TIME = 200;
    private String wantToEat;
    private String eating;

    public Baby(String name, String eating, String wantToEat) {
        super(name);
        this.eating = eating;
        this.wantToEat = wantToEat;
    }

    @Override
    public void run() {

        synchronized (fruits.getFruit(eating)) {
            for(int i=1; i<10; i++) {
                System.out.println(String.format("%s is eating %s, mostly completed %s", getName(), eating, i*10));
                try {
                    sleep(SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(String.format("Now, %s want to eat %s too....", getName(), wantToEat));
            synchronized (fruits.getFruit(wantToEat)) {
                System.out.println(String.format("%s can eat %s", getName(), wantToEat));
            }
        }
    }
}
