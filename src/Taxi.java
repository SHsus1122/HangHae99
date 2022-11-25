public class Taxi {
    // 필드
    static final int maxGuest = 4;
    int nowGuest;
    static final int startPrice = 6000;
    static final int taxiNum = 1111;
    int oil = 100;
    int speed = 0;
    static final int basicKm = 20;
    int reach;
    static final int kmPrice = 300;
    String state = "일반";

    public Taxi(int nowGuest, int reach) {
        this.nowGuest = nowGuest;
        this.reach = reach;
        if (nowGuest <= maxGuest) {
            System.out.println("탑승 인원은 " + nowGuest + "명 입니다.");
            System.out.println("택시 번호는 " + taxiNum + " 입니다.");
        } else {
            System.out.println("만원 입니다.");
            nowGuest = 0;
        }
    }

    void setSpeed(int speed) {
        this.speed = speed;
    }

    void getSpeed() {
        if (oil > 0) {
            System.out.println("현재 속도 : " + speed);
        } else {
            System.out.println("주유가 필요합니다.");
        }
    }

    void getState() {
        if (state.equals("일반") && oil >= 10 || nowGuest > 0) {
            state = "주행 중";
            System.out.println("출발합니다.");
            return;
        } else if (oil < 10) {
            state = "탑승 불가";
            System.out.println("주유가 필요합니다.");
        }
    }

    void run() {
        int total1 = 0;
        if (reach > basicKm) {
            total1 = (startPrice + ((reach - basicKm) * kmPrice));
        } else {
            total1 = startPrice;
        }
        if (state.equals("일반") && nowGuest <= 4) {
            state = "주행 중";
            while (true) {
                if (oil > 30 && reach > 0) {
                    System.out.println("주행중 입니다.");
                    System.out.println("현재 속도는 : " + speed + "km/h 입니다.");
                    oil -= 10;
                    speed += 10;
                    reach -= (speed / 5);
                } else if (oil <= 30 && reach > 0) {
                    System.out.println("기름 잔량이 적습니다 현재 남은 량 : " + oil + "L 입니다.");
                    oil -= 10;
                } else if (oil <= 0 && reach > 0) {
                    oil = 0;
                    System.out.println("기름이 부족해서 정차합니다.");
                    return;
                } else if (reach < 0) {
                    System.out.println("목적지에 도착 했습니다.");
                    System.out.println("결제 금액은 " + total1 + "원 입니다.");
                    state = "일반";
                    return;
                }
            }
        }
    }

    void totalPrice() {
        if (reach > basicKm) {
            System.out.println("예상 요금은 " + (startPrice + ((reach - basicKm) * kmPrice)) + "원 입니다.");
            state = "일반";
        } else {
            System.out.println("예상 요금은 " + startPrice + "원 입니다.");
        }
    }

    public static void main(String[] args) {
        Taxi myTaxi = new Taxi(4, 50);
        myTaxi.run();
    }
}
