package xmltest;

import org.junit.platform.commons.util.StringUtils;

public class match {
   
 public static void main(String[] args) {
	 String source = "@mohmedsmeer@Mhmdkmaal@mohmedsmeer@Mhmdkmaal😂@morten_panda😂🤣🤣🤣😂😂@morten_panda😂@mohmedsmeer😹😂@mohmedsmeer😂☝️😅https://t.co/PNSQMqtolh@mohmedsmeer😢@mohmedsmeer😂😂😂@mohmedsmeer😂@m_farahat1😂@mohabbasherX🌚@m_farahat1@Sudani_sd@badralmaarif7@HishamJR73🌚😂@m_farahat1@Sudani_sd@badralmaarif7@HishamJR73@m_farahat1@Sudani_sd@badralmaarif7@HishamJR73😹😂https://t.co/CvSTbbfQLk@razanaziz26@moh_e_rex@AhmedGaffer10😂😂😂@AhmedGaffer10😂😂@mohmedsmeer😂@mohmedsmeer☹️@mohmedsmeer🌚🌚@m_farahat1🔪@mohmedsmeer@mohmedsmeer@mohmedsmeer😂@mohmedsmeer😂😂😂😂😂😂😂😂@xjblii@m_farahat1😂😂😂😂@mohmedsmeerelex22@mazin199715😂😂😂😂🤣🤣@mazin199715🤣🤣🤣🤣😂😂@HElmagzoub@mohmedsmeer😂@mohmedsmeer@mazin199715😂👌@mohmedsmeer@mazin199715@mohmedsmeer@mazin199715😂😂😂🌚@mazin199715@mohmedsmeer😂@mohmedsmeershow🌚@mohmedsmeer😂😂😂😂😂😂😂https://t.co/8xepMA8mqS@M7mdMos3b@mohmedsmeer😂@mohmedsmeer😂😂@mohmedsmeer🌚🌚@mohmedsmeer😂@mohmedsmeer🌚@mohmedsmeer😂😂😂😂😂😂😂😂😂😂😂@mohmedsmeer😂https://t.co/X26FN4smnd@mohmedsmeer😂@MuhtdiAl_Tekina@AhmedGaffer10@m_farahat1@3mroo_77@mohmedsmeer😂😂😂@mohmedsmeer😎https://t.co/49PCnPU2Jb@m_farahat1@m_farahat1@3mroo_77🙌🏻🙌🏻@mohmedsmeer😂😂😂😂@mohmedsmeer😕@mohmedsmeer🌚@mohmedsmeer@mohmedsmeer**@3mroo_77😂😂@mohdawad25avihttps://t.co/qlBTWqWEF6@amoasj@Waleedkona!@amoasj@Waleedkona!@_Mu2_@Waleedkona@m_farahat1😂@abdellahomerhttps://t.co/jicY4YMIPk🌚https://t.co/HemQ71e9q8@m_farahat1@mohmedsmeer@mohdawad25😪👌🏽👌🏽https://t.co/oKcEftkoN1@mohmedsmeer@mo2mein😏@m_farahat1@mo2mein@ramah_kamal🌚:-#_https://t.co/SIaSzbvM3Z👇🏽https://t.co/a7SimkUDRL😂https://t.co/oEXwjASq5i@wadalkayhttps://t.co/lRkWosaVqA@wadalkay!@wadalkay@wadalkay!!!!!https://t.co/HlsEKIRnI3@amribrahim98https://t.co/KD3V7DP297😂😂#_#__#__https://t.co/DwgM5fyBa3@ibrahim_alfarse😪@BradVegeta97🌚@mohmedsmeer😂@mohmedsmeer🌚@mohmedsmeer@Mhmdkmaal😂https://t.co/UKU24yptl3😂#_https://t.co/UpktSbnWhn@mohmedsmeer@badralmaarif7@Sol_tuberosum😂😂😂😂@3mroo_77🌚@m_farahat1@Sol_tuberosum@badralmaarif7🌚😎https://t.co/nAuTwpxBmA@yabani103@zozacher😅https://t.co/RMqtdcK0M5@mohmedsmeer😂";
	 System.out.println(filterEmoji(source ));
 }
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }
 
    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if (StringUtils.isBlank(source)) {
            return source;
        }
        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
            }
        }
        if (buf == null) {
            return source;
        } else {
            if (buf.length() == len) {
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }}
