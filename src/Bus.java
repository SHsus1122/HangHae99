public class Bus {
    // 필드값
    static final int maxGuest = 20;
    int nowGuest;
    int rideGuest;
    int price = 1800;
    static final int busNum = 5593;
    int oil = 100;
    int speed;

    String state;


    // Get 메소드 (값 단순 조회 목적) =========================================================
    int getBusNum() {
        return busNum;
    }

    int getPrice() {
        return price;
    }

    int getSpeed() {
        return speed;
    }

    int getOil() {
        return oil;
    }

    int getNowGuest() {
        return nowGuest;
    }

    // Set 메소드 (값 변경 목적) =========================================================

    int setRideGuest(int rideGuest) {
        this.rideGuest = rideGuest;
        nowGuest += rideGuest;
        return rideGuest;
    }

    void setOil(int oil) {
        this.oil = oil;
    }

    void setSpeed(int speed) {
        this.speed = speed;
    }

    // 출력 메소드 ========================================================================
    void State() {
        if (oil < 10) {
            System.out.println("기름이 없습니다 주유를 해주세요.");
            System.out.println("현재 상태 : 차고지행");
        } else {
            System.out.println("현재 상태 : 주행중");
        }
    }

    void guestCount() {
        if (oil > 10) {
            if (nowGuest > maxGuest) {
                System.out.println("더 이상 승객을 태울 수 없습니다.");
            } else {
                System.out.println("승객이 " + nowGuest + "명 탑승합니다.");
                System.out.println("잔여 승객의 수는 : " + (maxGuest - nowGuest) + "명 입니다.");
                System.out.println("총 요금은 : " + (price * rideGuest) + "원 입니다.");
            }
        } else {
            return;
        }
    }

    void ride() {
        if (oil > 30) {
            System.out.println("버스가 주행중 입니다.( 현재 oil : " + oil + ")");
            oil -= 10;
        } else if (oil >= 10 && oil <= 30) {
            System.out.println("기름이 부족합니다 주유를 해주세요.( 현재 oil : " + oil + ")");
            oil -= 10;
        } else {
            oil = 0;
            System.out.println("주유가 필요합니다.( 현재 oil : " + oil + ")");
        }
    }

    void speedInfo() {
        System.out.println("현재 속도는 " + speed + "km/h 입니다.");
    }
}
