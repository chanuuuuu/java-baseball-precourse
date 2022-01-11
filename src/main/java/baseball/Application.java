package baseball;

public class Application {
    /** 게임 내의 맞춰야하는 숫자의 갯수, 기본값은 3 */
    private final static int BALL_COUNTS = 3;

    public static void main(String[] args) {
        play();
    }

    private static void play() {
        Pitcher pitcher = new Pitcher(BALL_COUNTS);
        while(true) {
            playGame(pitcher);
            boolean 게임지속여부 = checkGameContinue();
            if (게임지속여부) {
                pitcher.init();
            } else {
                System.out.println("게임 끝");
                break;
            }
        }
    }

    /**
     * 라운드를 반복하는 게임을 1회 진행
     * @param pitcher   난수를 가진 컴퓨터 객체
     */
    private static void playGame(final Pitcher pitcher) {
        while(true) {
            System.out.print("숫자를 입력해 주세요 : ");
            String 사용자입력값 = GameConsole.read(BALL_COUNTS);
            boolean 라운드종료여부 = pitcher.round(사용자입력값);
            if(라운드종료여부) {
                break;
            }
        }
    }

    /**
     * 게임의 종료 조건 이후, 다른 게임의 실행 여부를 확인하는 함수
     * @return  boolean 게임 재시작 여부
     */
    private static boolean checkGameContinue() {
        System.out.print("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요. : ");
        while(true) {
            String 사용자입력값 = GameConsole.read(1 );
            if(사용자입력값.equals("1") || 사용자입력값.equals("2")) {
                return 사용자입력값.equals("1");
            }else {
                System.out.print("[ERROR] 올바르지 않은 값의 입력입니다. 다시 입력하세요 : ");
            }
        }
    }
}
