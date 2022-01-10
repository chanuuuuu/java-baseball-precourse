package baseball;

import java.util.Arrays;
import nextstep.utils.Randoms;

public class Pitcher {
    private int ballCounts;
    private int strike;
    private int ball;
    private int[] randomNumber;

    public Pitcher(int ballCounts) {
        this.ballCounts = ballCounts;
        this.randomNumber = new int[ballCounts];
        this.init();
    }

    public void init() {
        this.ball = 0;
        this.strike = 0;
        this.setRandomNumber();
    }

    public boolean round(final String inputValue) {
        this.countRound(inputValue);
        this.printRoundResult();
        return this.checkRoundEnd();
    }

    private void setRandomNumber() {
        for (int i = 0; i < this.ballCounts; i++) {
            String str = Arrays.toString(this.randomNumber).replaceAll("[^0-9]","");
            while(true) {
                int randomNumber = Randoms.pickNumberInRange(1, 9);
                if(!str.contains(Integer.toString(randomNumber))){
                    this.randomNumber[i] = randomNumber;
                    break;
                }
            }
        }
        System.out.println("난수 값은 : " + Arrays.toString(this.randomNumber));
    }

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

    private boolean checkRoundEnd() {
        return this.strike == this.ballCounts;
    }

    private void printRoundResult() {
        if(this.strike == this.ballCounts) {
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
