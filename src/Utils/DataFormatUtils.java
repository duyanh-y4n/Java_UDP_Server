package Utils;

import java.util.ArrayList;
import java.util.List;

// TODO: 08.04.19 refactor all methode to Pipe function
public class DataFormatUtils {
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String byteArrToHEXStr(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static List<String> HEXStrToHEXCharList(String HexStr) {
        List<String> HexArr = new ArrayList<String>();
        for (int i = 0; i < HexStr.length(); i += 2) {
            HexArr.add(HexStr.substring(i, i + 2));
        }
        return HexArr;
    }

    public static List<String> byteArrToHEXCharList(byte[] bytes){
        return HEXStrToHEXCharList(byteArrToHEXStr(bytes));
    }

    public static List<Integer> byteArrToIntList(byte[] bytes){
        List<Integer> intArr = new ArrayList<Integer>();
        for (int _byte:
                bytes) {
            intArr.add(_byte);
        }
        return intArr;
    }

    public static byte[] IntListToByteArr(List<Integer> intArr){
        byte[] rawBytes = new byte[intArr.size()];
        for (int i = 0; i < intArr.size(); i++) {
            rawBytes[i] = intArr.get(i).byteValue();
        }
        return rawBytes;
    }

    public static List<Byte> byteArrToByteList(byte[] bytes){
        List<Byte> byteList = new ArrayList<Byte>();
        for (int i = 0; i < bytes.length; i++) {
            byteList.add(bytes[i]);
        }
        return byteList;
    }

    public static byte[] intArrToByteArr(int[] buffer) {
        byte[] bytes = new byte[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            bytes[i] = (byte) buffer[i];
        }
        return bytes;
    }

    public static String byteArrToStr(byte[] data) {
        return new String(data);
    }

    public static int[] byteArrToIntArr(byte[] bytes){
        int[] intArr = new int[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            intArr[i] = bytes[i];
        }
        return intArr;
    }
}
