package hw_15_4;

public class Command {

    private String name;
    private String text;
    private String regexRule;

    public Command(String name, String text, String regexRule) {
        this.name = name;
        this.text = text;
        this.regexRule = regexRule;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getRegexRule() {
        return regexRule;
    }
}
