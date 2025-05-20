package util;

public class ConsoleStyle {
    public static final String RESET = "\033[0m";

    // 기본 색상 예시
    public static final String OKGREEN = "\033[92m";
    public static final String WARNING = "\033[93m";
    public static final String FAIL    = "\033[91m";

    // 256색 텍스트 스타일 (foreground)
    public static final String LIGHT_PURPLE = "\033[38;5;183m";
    public static final String ORANGE       = "\033[38;5;208m";
    public static final String MINT         = "\033[38;5;121m";
    public static final String HOT_PINK     = "\033[38;5;197m";
    public static final String SKY_BLUE     = "\033[38;5;117m";
    public static final String EWHA_GREEN   = "\033[38;5;22m";
    public static final String LOGO_TEST    = "\033[38;5;24m";
    
    // 256색 배경 스타일 (background)
    public static final String BG_PURPLE    = "\033[48;5;7m";

    // 강조 스타일
    public static final String BOLD = "\033[1m";
    public static final String UNDERLINE = "\033[4m";

    

    
    // ✅ 글자색만 적용
    public static String apply(String textColor, String text) {
        return textColor + text + RESET;
    }

    // ✅ 글자색 + 배경색 적용
    public static String apply(String textColor, String bgColor, String text) {
        return bgColor + textColor + text + RESET;
    }

    // 카테고리별 전용 색상
    public static final String KOREANcolor      = "\033[38;5;28m";  // 갈색 계열
    public static final String JAPANESEcolor    = "\033[38;5;25m";   // 딥 블루
    public static final String CHINESEcolor     = "\033[38;5;88m";  // 강렬한 빨강
    public static final String WESTERNcolor     = "\033[38;5;57m";  // 주황
    public static final String DESSERTcolor     = "\033[38;5;165m";  // 핑크
    public static final String OTHERcolor       = "\033[38;5;240m";  // 회색

    public static String getCategoryColor(String category) {
        return switch (category) {
            case "한식" -> KOREANcolor;
            case "일식" -> JAPANESEcolor;
            case "양식" -> WESTERNcolor;
            case "중식" -> CHINESEcolor;
            case "디저트" -> DESSERTcolor;
            case "기타" -> OTHERcolor;
            default -> OTHERcolor;
        };
    }
    public static String stripAnsi(String input) {
        return input.replaceAll("\033\\[[;\\d]*m", "");
    }

    public static int getVisualWidth(String s) {
        int width = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 한글, 한자, 일본어 등 wide char: 2칸
            if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.HANGUL_SYLLABLES ||
                Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS ||
                Character.UnicodeBlock.of(c) == Character.UnicodeBlock.HIRAGANA ||
                Character.UnicodeBlock.of(c) == Character.UnicodeBlock.KATAKANA) {
                width += 2;
            } else {
                width += 1;
            }
        }
        return width;
    }

    public static String padRightVisualWidth(String input, int targetWidth) {
        String stripped = stripAnsi(input);
        int visualWidth = getVisualWidth(stripped);
        int padding = targetWidth - visualWidth;
        return input + " ".repeat(Math.max(0, padding));
    }



}
