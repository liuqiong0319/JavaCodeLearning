package java.learning.self.stringBufferDemo;

/**
 * Created by qiong.liu on 2018/3/21.
 * 获取两个字符串最大相同子串
 */
public class CompareSubStringMethod {

    public static  String compareString(String str1,String str2){
        //比较两个字符串的长度,确保从较小长度中的字符串拿出子串进行比较
        String max,min;
        max=(str1.length()>=str2.length())?str1:str2;
        min=max.equals(str1)?str2:str1;
        for (int i = 0; i < min.length(); i++) {
            for (int j = 0, k = min.length() - i; k < min.length(); j++, k++) {
                if (max.contains(min.substring(j, k))) {
                    String sameSubString = min.substring(j, k);
                    return sameSubString;
                }
            }
        }
        return  null;
    }

    public static void main(String[] args) {
        String str1="helloworldyouknowme";
        String str2="dyoukno";
        String sameString=compareString(str1,str2);
        System.out.println(sameString);

    }
}
