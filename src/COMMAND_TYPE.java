import java.util.regex.Pattern;

public enum COMMAND_TYPE {

    EMPTY_LINE("[ ]*"),
    PRINT_STRING("pri \"[a-zA-Z0-9 ]*\""),
    PRINT_VARIABLE("pri [a-zA-Z][a-zA-Z0-9]*[ ]*"),
    STRING_VARIABLE("str [a-zA-Z][a-zA-Z0-9]* [a-zA-Z0-9 ]+"),
    DEFAULT_STRING_VARIABLE("str [a-zA-Z][a-zA-Z0-9]*"),
    INTEGER_VARIABLE("int [a-zA-Z][a-zA-Z0-9]* [0-9]+[ ]*"),
    DEFAULT_INTEGER_VARIABLE("int [a-zA-Z][a-zA-Z0-9]*[ ]*"),
    ADD_INTEGER("add [a-zA-Z][a-zA-Z0-9]* [0-9]+[ ]*\\+[ ]*[0-9]+[ ]*"),
    SUB_INTEGER("sub [a-zA-Z][a-zA-Z0-9]* [0-9]+[ ]*-[ ]*[0-9]+[ ]*");

    private final Pattern pattern;

    COMMAND_TYPE(final String regex) {
        this.pattern = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS);
    }

    public Pattern getPattern() {
        return this.pattern;
    }

}
