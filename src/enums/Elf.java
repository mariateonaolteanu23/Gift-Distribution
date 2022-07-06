package enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Elf {
    @JsonProperty("YELLOW")
    YELLOW("Yellow"),

    @JsonProperty("Black")
    BLACK("Black"),

    @JsonProperty("Pink")
    PINK("Pink"),

    @JsonProperty("White")
    WHITE("White");

    private final String value;

    Elf(final String value) {
        this.value = value;
    }
}
