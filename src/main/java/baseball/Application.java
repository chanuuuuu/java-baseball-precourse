package baseball;
import java.util.Arrays;
import java.util.InputMismatchException;
import nextstep.utils.*;

public class Application {
    public static void main(String[] args) {
        int[] 컴퓨터난수 = getRandomNumber();

        while(true) {
            String 사용자입력값 = getValues();
            int[] 볼카운트 = getCounts(사용자입력값, 컴퓨터난수);
            boolean 게임종료 = checkCounts(볼카운트);
            if(게임종료) {
                boolean 게임지속여부 = checkContinue();
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
    public static boolean checkContinue() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요. : ");
        String valueLine = Console.readLine();
        int value = Integer.parseInt(valueLine);
        return value == 1;
    }

    // 사용자 입력을 받는 함수
    public static String getValues() {
        System.out.print("숫자를 입력해 주세요 : ");
        String valueLine;
        int value;
        while(true) {
            valueLine = Console.readLine();
            try{
                // 잘못된 입력의 길이를 확인하는 작업
                checkValueSize(valueLine);

                // 입력받은 라인을 정수로 변환하는 작업
                value = Integer.parseInt(valueLine);
                break;
            } catch(NumberFormatException e) {
                // 실제 숫자 확인
                System.out.print("형식이 잘못된 입력입니다. 다시 입력하세요 :  ");

            } catch(InputMismatchException ime) {
                // 길이 확인
                System.out.print("잘못된 입력의 길이입니다. 다시 입력하세요 :  ");
            }
        }
        return Integer.toString(value);
    }

    // 사용자 입력의 길이를 검증하는 함수
    public static void checkValueSize(String valueLine) {
        if(valueLine.length() != 3) {
            throw new InputMismatchException();
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
            System.out.println("3스트라이크");
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임종료");
            return true;
        }
        if(strike > 0) {
            printString = printString + strike + "스트라이크 ";
        }
        if(ball > 0) {
            printString = printString + ball + "볼";
        }
        if(strike == 0 && ball == 0) {
            printString = "낫싱";
        }
        System.out.println(printString);
        return false;
    }
}
