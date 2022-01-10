package baseball;
import nextstep.utils.*;

public class GameConsole {
    private GameConsole() {
    }

    public static String read(int valueLength) {
        while(true) {
            String valueLine = Console.readLine();
            if(isValidSize(valueLine, valueLength) && isNumber(valueLine)){
                return valueLine;
            }
        }
    }

    private static boolean isValidSize(String valueLine, int valueLength) {
        if(valueLine.length() != valueLength) {
            System.out.print("[ERROR] 잘못된 입력의 길이입니다. 다시 입력하세요 :  ");
            return false;
        }
        return true;
    }

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
