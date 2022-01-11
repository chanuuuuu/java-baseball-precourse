package baseball;

import java.util.Arrays;
import nextstep.utils.Randoms;

public class Pitcher {

    /** 게임 내의 맞춰야하는 숫자의 갯수*/
    private int ballCounts;
    /** 라운드에서 값과 위치를 맞춘 숫자의 수 */
    private int strike;
    /** 라운드에서 값을 맞춘 숫자의 수 */
    private int ball;
    /** 라운드를 통해 맞춰야하는 컴퓨팅된 난수 */
    private final int[] randomNumber;

    public Pitcher(int ballCounts) {
        this.ballCounts = ballCounts;
        this.randomNumber = new int[ballCounts];
        this.init();
    }

    /**
     * 라운드 종료 조건에 의해 게임 종료 후, 새로운 게임 시작
     */
    public void init() {
        this.ball = 0;
        this.strike = 0;
        this.setRandomNumber();
    }

    /**
     * 게임 내의 맞춰야하는 숫자의 갯수인 ballCounts에 해당하는 길이의 컴퓨팅된 난수를 생성한다.
     */
    private void setRandomNumber() {
        int[] checkArray = new int[10];
        for (int i = 0; i < this.ballCounts; i++) {
            int uniqueNumber = getUniqueNumber(checkArray);
            this.randomNumber[i] = uniqueNumber;
            checkArray[uniqueNumber] = 1;
        }
        System.out.println("난수 값은 : " + Arrays.toString(this.randomNumber));
    }

    /**
     * 이전까지의 난수중 중복되지 않는 정수값 생성
     * @param checkArray    이전까지의 난수 히스토리
     * @return int          중복되지않은 정수값
     */
    private int getUniqueNumber(final int[] checkArray) {
        while(true) {
            int uniqueNumber = Randoms.pickNumberInRange(1, 9);
            if(checkArray[uniqueNumber] != 1) {
                return uniqueNumber;
            }
        }
    }

    /**
     * 게임 내의 1회의 라운드를 진행한다. 라운드의 동작은 아래와 같다.
     * 1. 스트라이크, 볼을 확인 (countRound)
     * 2. 결과 출력 (printRoundResult)
     * 3. 게임 종료 여부 판별 (checkRoundEnd)
     * @param inputValue 검증된 사용자의 입력값
     * @return boolean  현재 게임의 종료여부
     */
    public boolean round(final String inputValue) {
        this.countRound(inputValue);
        this.printRoundResult();
        return this.checkRoundEnd();
    }

    /**
     * 라운드에서 현재 사용자의 입력값을 통해 스트라이크, 볼을 확인한다.
     * @param inputValue 검증된 사용자의 입력값
     */
    private void countRound(final String inputValue) {
        this.ball = 0;
        this.strike = 0;
        for(int i = 0; i < this.ballCounts; i++){
            int valueIndex = inputValue.indexOf(Integer.toString(this.randomNumber[i]));
            if(valueIndex == i) {
                this.strike++;
            }
            else if(valueIndex != -1) {
                this.ball++;
            }
        }
    }

    /**
     * 라운드의 종료 조건 판별
     * @return  boolean 라운드 종료 여부
     */
    private boolean checkRoundEnd() {
        return this.strike == this.ballCounts;
    }

    /**
     * 현재 라운드의 결과 출력
     */
    private void printRoundResult() {
        if(checkRoundEnd()) {
            System.out.println(this.ballCounts + "스트라이크");
            System.out.println(this.ballCounts + "개의 숫자를 모두 맞히셨습니다! 게임종료");
            return;
        }
        String printString = "";
        if(this.strike > 0) {
            printString += this.strike + "스트라이크 ";
        }
        if(this.ball > 0) {
            printString += this.ball + "볼";
        }
        if(this.strike == 0 && this.ball == 0) {
            printString = "낫싱";
        }
        System.out.println(printString);
    }
}
