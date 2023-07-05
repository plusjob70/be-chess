package softeer2nd.chess.utils;

public class StringUtils {

    public static final String NEWLINE = System.getProperty("line.separator");

    private StringUtils() {
    }

    public static String appendNewLine(String string) {
        return string + NEWLINE;
    }

    public static void appendNewLine(StringBuilder stringBuilder) {
        stringBuilder.append(NEWLINE);
    }

}
