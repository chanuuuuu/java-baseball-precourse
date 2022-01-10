package baseball;

public class Application {
    private final static int BALL_COUNTS = 3;

    public static void main(String[] args) {
        playGame();
    }

    public static void playGame() {
        Pitcher pitcher = new Pitcher(BALL_COUNTS);
        while(true) {
            System.out.print("숫자를 입력해 주세요 : ");
            String 사용자입력값 = GameConsole.read(BALL_COUNTS);
            boolean 라운드종료여부 = pitcher.round(사용자입력값);
            if(라운드종료여부) {
                boolean 게임지속여부 = checkGameContinue();
                if(게임지속여부) {
                    pitcher.init();
                } else {
                    System.out.println("게임 끝");
                    break;
                }
            }
        }
    }

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
