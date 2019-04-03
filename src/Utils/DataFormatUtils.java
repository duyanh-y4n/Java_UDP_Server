package Utils;

import java.util.ArrayList;

public class DataFormatUtils {
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHEXs(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static ArrayList<String> HEXStrToHEXArrList(String HexStr) {
        ArrayList<String> HexArr = new ArrayList<String>();
        for (int i = 0; i < HexStr.length(); i += 2) {
            HexArr.add(HexStr.substring(i, i + 2));
        }
        return HexArr;
    }

    public static ArrayList<String> bytesToHEXArrList(byte[] bytes) {
        return HEXStrToHEXArrList(bytesToHEXs(bytes));
    }

    public static int[] bytesToIntArr(byte[] bytes){
        int[] intArr = new int[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            intArr[i]=bytes[i];
        }
        return intArr;
    }

    public static byte[] intArrToRawBytes(ArrayList<Integer> intArr){
        byte[] rawBytes = new byte[intArr.size()];
        for (int i = 0; i < intArr.size(); i++) {
            rawBytes[i] = intArr.get(i).byteValue();
        }
        return rawBytes;
    }

    public static byte[] intArrToBytes( int[] intArr){
        byte[] bytes = new byte[intArr.length];
        for (int i = 0; i < intArr.length; i++) {
            bytes[i]=(byte) intArr[i];
        }
        return bytes;
    }

}
