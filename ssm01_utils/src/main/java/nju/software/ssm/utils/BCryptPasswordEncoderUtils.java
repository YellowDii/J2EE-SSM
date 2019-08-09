package nju.software.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

//      测试BCryptPasswordEncoder
//    public static void main(String[] args) {
//        String s1="12355222d";
//        String s2="hdaowana123ad";
//        for (int i=0;i<10;i++) {
//            System.out.println("s1:"+bCryptPasswordEncoder.encode(s1));
//        }
//        for (int i=0;i<10;i++){
//            System.out.println("s2:"+bCryptPasswordEncoder.encode(s2));
//        }
//    }
    public static void main(String[] args) {
        String s="123";
        System.out.println(bCryptPasswordEncoder.encode(s));
//        List<String> list=new ArrayList<>();
//        for (int i=0;i<1000;i++){
//            System.out.print("完成"+i+"....");
//            list.add(bCryptPasswordEncoder.encode(s));
//        }
//        System.out.println(isExistSameString(list));
    }

    public static int existSameString(List<String> list){
        for (int i=0;i<list.size();i++){
            for (int j=i+1;j<list.size();j++){
                if(list.get(i).equals(list.get(j))){
                    return 1;
                }
            }
        }
        return 0;
    }
}
