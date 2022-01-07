package baseball;
import java.util.Arrays;
import java.util.InputMismatchException;
import nextstep.utils.*;

public class Application {
    public static void main(String[] args) {
        String inputValue = getValues();
        int [] randomNumber = getRandomNumber();
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

}
