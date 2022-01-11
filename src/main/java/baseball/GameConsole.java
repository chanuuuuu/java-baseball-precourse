package baseball;
import nextstep.utils.*;

/**
 * 제공받은 Console Class를 활용하여 게임에 필요한 입력을 받는 클래스
 */
public class GameConsole {
    private GameConsole() {
    }

    /**
     * 사용자 입력에 대한 유효성을 체크
     * @param valueLength   유효길이, 기본값으로는 3
     * @return  string      검증된 사용자의 입력
     */
    public static String read(int valueLength) {
        while(true) {
            String valueLine = Console.readLine();
            if(isValidSize(valueLine, valueLength) && isNumber(valueLine)){
                return valueLine;
            }
        }
    }

    /**
     * 사용자 입력의 길이에 대한 유효성을 체크
     * @param valueLine     사용자의 입력
     * @param valueLength   유효길이, 기본값으로는 3
     * @return  boolean
     */
    private static boolean isValidSize(String valueLine, int valueLength) {
        if(valueLine.length() != valueLength) {
            System.out.print("[ERROR] 잘못된 입력의 길이입니다. 다시 입력하세요 :  ");
            return false;
        }
        return true;
    }

    /**
     * 사용자 입력의 형식에 대한 유효성을 체크
     * @param valueLine     사용자의 입력
     * @return  boolean
     */
    private static boolean isNumber(String valueLine) {
        try{
            Integer.parseInt(valueLine);
            return true;
        } catch(NumberFormatException e) {
            System.out.print("[ERROR] 형식이 잘못된 입력입니다. 다시 입력하세요 :  ");
        }
        return false;
    }
}
