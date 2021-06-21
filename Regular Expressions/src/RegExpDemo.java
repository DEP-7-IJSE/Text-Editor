import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpDemo {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
        String input="This is my phone number 077-1234567 and this is office 011-1234567";
        Matcher matcher = pattern.matcher(input);
        System.out.println(matcher.matches());
        System.out.println(input.matches("\\d{3}-\\d{7}"));
        System.out.println(Pattern.matches("\\d{3}-\\d{7}",input));
        //String.matches = Pattern.matches = Matcher.matches ^\d{3}-\d{7}$

        System.out.println(matcher.find());
        System.out.println(matcher.start());
        System.out.println(matcher.end());
        System.out.println(input.substring(matcher.start(), matcher.end()));

        System.out.println(matcher.find());
        System.out.println(matcher.start());
        System.out.println(matcher.end());
        System.out.println(input.substring(matcher.start(), matcher.end()));
    }
}
