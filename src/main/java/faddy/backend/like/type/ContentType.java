package faddy.backend.like.type;

public enum ContentType {
    SNAP,
    STYLE_BOARD,
    STYLE_BOARD_COMMENT,
    USER;

    public static ContentType fromString(String text) {
        StringBuilder builder = new StringBuilder();
        boolean nextUpperCase = true;

        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) {
                builder.append("_").append(Character.toLowerCase(c));
                nextUpperCase = false;
            } else {
                if (nextUpperCase) {
                    builder.append(Character.toUpperCase(c));
                } else {
                    builder.append(c);
                }
                nextUpperCase = false;
            }
        }

        String upperText = builder.toString().replace("_", "");

        try {
            return ContentType.valueOf(upperText);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("알 수 없는 객체 타입입니다: " + upperText);
        }
    }
}
