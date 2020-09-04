package cn.emernet.base.role.utils;


/**
 * @ClassName:CheckPassword
 * @Description:
 * @Author lilei
 * @Date 2020/7/20
 * @Version 1.0
 * */
public class CheckPassword {

    /**
     * 密码强度
     *
     * @return Z = 字母 S = 数字 T = 特殊字符
     */

/*  一、假定密码字符数范围6-16，除英文数字和字母外的字符都视为特殊字符：
    弱：^[0-9A-Za-z]{6,16}$
    中：^(?=.{6,16})[0-9A-Za-z]*[^0-9A-Za-z][0-9A-Za-z]*$
    强：^(?=.{6,16})([0-9A-Za-z]*[^0-9A-Za-z][0-9A-Za-z]*){2,}$
    二、假定密码字符数范围6-16，密码字符允许范围为ASCII码表字符：
    弱：^[0-9A-Za-z]{6,16}$
    中：^(?=.{6,16})[0-9A-Za-z]*[\x00-\x2f\x3A-\x40\x5B-\xFF][0-9A-Za-z]*$
    强：^(?=.{6,16})([0-9A-Za-z]*[\x00-\x2F\x3A-\x40\x5B-\xFF][0-9A-Za-z]*){2,}$*/

    /**
     *
     * @param passwordStr
     * @return
     */
    public static String checkPassword(String passwordStr) {
        String regexz = "\\d*";
        String regexs = "[a-zA-Z]+";
        String regext = "\\W+$";
        String regexzt = "\\D*";
        String regexst = "[\\d\\W]*";
        String regexzs = "\\w*";
        String regexzst = "[\\w\\W]*";

        if (passwordStr.matches(regexz)) {
            return "弱";
        }
        if (passwordStr.matches(regexs)) {
            return "弱";
        }
        if (passwordStr.matches(regext)) {
            return "弱";
        }
        if (passwordStr.matches(regexzt)) {
            return "中";
        }
        if (passwordStr.matches(regexst)) {
            return "中";
        }
        if (passwordStr.matches(regexzs)) {
            return "中";
        }
        if (passwordStr.matches(regexzst)) {
            return "强";
        }
        return passwordStr;

    }
}
