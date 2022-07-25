package org.foodsafety.importfood.redundancyfacility.commons;

public class TextPreprocessing {

    public TextPreprocessing() {
    }

    public String TextPreprocessing(String str, PreprocessType type) {
        if (type == PreprocessType.REMOVE_PUNCTUATION) {
            str = removePunctuation(str);


        } else if (type == PreprocessType.REMOVE_WHITESPACE) {
            str = removeWhitespace(str);
        } else {
            str = str;
        }
        return str;
    }

    public String removeWhitespace(String str) {
        String match = "\\s";
        String removeStr = str.replaceAll(match, "");
        return removeStr;
    }


    public String removePunctuation(String str) {

        String match = "[^\uAC00-\uD7A30-9a-zA-Z\\s]";
        String removeStr = str.replaceAll(match, "");
        return removeStr;

    }

    public static String removePunctuationForEach(String str) {

        // 특수문자 정의 ARRAY
        String[] punctuations = {"!", "\"", "#", "$", "%", "&", "(", ")", "{", "}", "@", "`", "*", ":", "+", ";", "-", ".", "<", ">", ",", "^", "~", "|", "'", "[", "]"};

        String result = "";
        // 특정 특수문자 제외한, 특수문자 제거
        for (String item : punctuations) {
            System.out.println(item);
            result = str.replaceAll(item, "");

        }
        return result;
    }


}
