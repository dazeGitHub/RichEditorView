package me.jingbin.richeditorview.tools;

import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 *     author : ZYZ
 *     e-mail : zyz163mail@163.com
 *     time   : 2021/06/08
 *     desc   : 需要处理的 : EmojiUtils 里的 bamaMap 、 emoji.js
 *     version: 1.0
 * </pre>
 */
public class EmojiUtils {

    public HashMap<String, String> bamaMap = new HashMap<>();

    private String mBamaIdentifier = "八马";
    private String mBamaImgLabelPreStr = "<img class=\"emojibox\" src=\"https://bbs.78dm.net/assets/f1a0e8de/images/smileys/";

    private EmojiUtils() {
        bamaMap.put("bama001.png\">", ":八马_哼:");
    }

    public static final EmojiUtils instance = new EmojiUtils();

    public String replaceImgLabel2Bama(String inputStr){
        String toReplaceRegex = "<img class=\"emojibox\".*?bama";
        String resultStr = "";

        Pattern pattern = Pattern.compile(toReplaceRegex); //去掉空格符合换行符
        Matcher matcher = pattern.matcher(inputStr);
        resultStr = matcher.replaceAll("bama");

        Set<String> keySet = EmojiUtils.instance.bamaMap.keySet();
        for (String key :  keySet){
            resultStr = resultStr.replace(key, EmojiUtils.instance.bamaMap.get(key));
        }

        return resultStr;
    }

    //将八马字符串替换为链接
    public String replaceBama2ImgLabel(String inputStr){
        String resultStr = inputStr;
        if(inputStr.contains(mBamaIdentifier)){
            Set<String> keySet = EmojiUtils.instance.bamaMap.keySet();
            for (String key :  keySet){
                resultStr = resultStr.replace(EmojiUtils.instance.bamaMap.get(key), mBamaImgLabelPreStr + key);
            }
        }
        return resultStr;
    }

}
