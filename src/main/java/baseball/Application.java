package baseball;
import java.util.Arrays;
import java.util.InputMismatchException;
import nextstep.utils.*;

public class Application {
    public static void main(String[] args) {
        int[] 컴퓨터난수 = getRandomNumber();

        while(true) {
            System.out.print("숫자를 입력해 주세요 : ");
            String 사용자입력값 = GameConsole.read(3 );
            int[] 볼카운트 = getCounts(사용자입력값, 컴퓨터난수);
            boolean 라운드종료여부 = checkCounts(볼카운트);
            if(라운드종료여부) {
                boolean 게임지속여부 = checkGameContinue();
                if(게임지속여부) {
                    컴퓨터난수 = getRandomNumber();
                } else {
                    System.out.println("게임 끝");
                    break;
                }
            }
        }
    }

    // 게임 지속 여부를 확인하는 함수
    public static boolean checkGameContinue() {
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

    // 난수 3자리 생성
    public static int[] getRandomNumber() {
        int[] randomNumberArray = new int[3];
        for (int i = 0; i < 3; i++) {
            String str = Arrays.toString(randomNumberArray).replaceAll("[^0-9]","");
            while(true) {
                int randomNumber = Randoms.pickNumberInRange(1, 9);
                if(!str.contains(Integer.toString(randomNumber))){
                    randomNumberArray[i] = randomNumber;
                    break;
                }
            }
        }
        System.out.println("난수 값은 : " + Arrays.toString(randomNumberArray));
        return randomNumberArray;
    }

    // 스트라이크, 볼의 갯수를 반환하는 함수
    public static int[] getCounts(String inputValue, int[] randomValue) {
        int[] counts = new int[2];

        for(int i = 0; i < 3; i++){
            int valueIndex = inputValue.indexOf(Integer.toString(randomValue[i]));
            if(valueIndex == i) {
                counts[0]++;
            }
            else if(valueIndex != -1) {
                counts[1]++;
            }
        }

        return counts;
    }

    // 카운트 판별하는 함수, 반환 값은 게임종료
    public static boolean checkCounts(int[] counts) {
        int strike = counts[0];
        int ball = counts[1];
        String printString = "";

        if(strike == 3) {
            System.out.println("3스트라이크 \n3개의 숫자를 모두 맞히셨습니다! 게임종료");
            return true;
        }
        if(strike > 0) {
            printString += strike + "스트라이크 ";
        }
        if(ball > 0) {
            printString += ball + "볼";
        }
        if(strike == 0 && ball == 0) {
            printString = "낫싱";
        }
        System.out.println(printString);
        return false;
    }
}
