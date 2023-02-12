public class BusExample {
    public static void main(String[] args) {
        Bus myBus = new Bus();

        System.out.println(myBus.getBusNum());
        myBus.State();
        for (int i = 0; i < 12; i++) {
            myBus.ride();
        }
        System.out.println(myBus.getPrice());
        myBus.setSpeed(100);
        myBus.speedInfo();
        myBus.setRideGuest(5);
        myBus.guestCount();
        myBus.setRideGuest(10);
        myBus.guestCount();
        myBus.setRideGuest(20);
        myBus.guestCount();
        System.out.println(myBus.getNowGuest());
        myBus.State();
    }
}
