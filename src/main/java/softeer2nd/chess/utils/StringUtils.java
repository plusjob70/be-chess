package softeer2nd.chess.utils;

/**
 * 문자열 생성 관련 유틸리티
 */
public class StringUtils {
    /**
     * 줄바꿈 문자열
     */
    public static final String NEWLINE = System.getProperty("line.separator");

    /**
     * 생성자를 통해서 해당 인스턴스를 생성할 수 없다.
     */
    private StringUtils() {
    }

    /**
     * 인자로 받은 문자열에 줄바꿈하여 반환한다.
     * @param string 문자열
     * @return 줄바꿈된 문자열
     */
    public static String appendNewLine(String string) {
        return string + NEWLINE;
    }

    /**
     * StringBuilder 객체에 줄바꿈을 추가한다.
     * @param stringBuilder
     */
    public static void appendNewLine(StringBuilder stringBuilder) {
        stringBuilder.append(NEWLINE);
    }
}