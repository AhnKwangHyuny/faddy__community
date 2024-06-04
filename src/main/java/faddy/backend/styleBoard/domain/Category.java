package faddy.backend.styleBoard.domain;

public enum Category {
    QUESTION("질문하기"),
    DAILY_SHARE("일상공유"),
    FASHION_SHARE("패션공유"),
    STYLE_TIP("스타일팁"),
    NEWS_SHARE("소식공유");
    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
