package test;

import java.security.MessageDigest;
public class MD5Test {
    public static void main(String[] args) throws Exception {
        String original = "1";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(original.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        System.out.println("original:" + original);
        System.out.println("digested(hex):" + sb.toString());
        System.out.println("premier élément du digest:"+digest[0]);

        md.update("19".getBytes());
        digest = md.digest();
        System.out.println(digest[0]);
        md.update("24".getBytes());
        digest = md.digest();
        System.out.println(digest[0]);
    }
}