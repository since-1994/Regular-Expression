import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.*;
import java.io.*;

class Regex1 {
    public static void main(String[] args) throws IOException {
        Pattern pattern = Pattern.compile("^Hello");
        Matcher matcher = pattern.matcher("Hello world");
        System.out.print(matcher.find());
    }
}