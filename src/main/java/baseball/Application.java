package baseball;

public class Application {
    /** 게임 내의 맞춰야하는 숫자의 갯수, 기본값은 3 */
    private final static int BALL_COUNTS = 3;

    public static void main(String[] args) {
        playGame();
    }

    private static void playGame() {
        Pitcher pitcher = new Pitcher(BALL_COUNTS);
        while(true) {
            System.out.print("숫자를 입력해 주세요 : ");
            String 사용자입력값 = GameConsole.read(BALL_COUNTS);
            boolean 게임지속여부 = playRound(pitcher, 사용자입력값);
            if(게임지속여부) {
                pitcher.init();
            } else {
                System.out.println("게임 끝");
                break;
            }
        }
    }

    /**
     * 사용자 입력값을 통해 라운드를 진행하고 라운드 종료 조건시에 게임지속여부를 판단한다.
     * @param pitcher       게임을 진행할 난수를 가진 컴퓨터
     * @param 사용자입력값     검증된 사용자의 입력값
     * @return  boolean     게임 지속 여부
     */
    private static boolean playRound(final Pitcher pitcher, final String 사용자입력값) {
        boolean 라운드종료여부 = pitcher.round(사용자입력값);
        if(라운드종료여부) {
            return  checkGameContinue();
        }
        return false;
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
