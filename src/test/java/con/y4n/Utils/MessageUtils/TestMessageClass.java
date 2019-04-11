package con.y4n.Utils.MessageUtils;

import com.y4n.Utils.DataFormatUtils;
import com.y4n.Utils.MessageUtils.Message;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class TestMessageClass {
    public byte[] genBytes(){
        Random random = new Random();
        byte[] bytes = new byte[random.nextInt(100)];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) random.nextInt(256);
        }
        return bytes;
    }

    @Test
    public void itShouldReturnInputParamAsMessageContent(){
        byte[] inputBytes = genBytes();
        Message message = new Message(inputBytes);
        System.out.println(inputBytes[0]);
        Assert.assertArrayEquals(inputBytes,message.getRawContent());
    }

    @Test
    public void itShouldReturnInputParamAsMessageContent2(){
        byte[] inputHeader = genBytes();
        byte[] inputBody = genBytes();
        byte[] inputRaw = DataFormatUtils.concatBytesArr(inputHeader,inputBody);
        Message message = new Message(inputHeader,inputBody);
        Assert.assertArrayEquals(inputRaw,message.getRawContent());
        Assert.assertArrayEquals(inputBody,message.getBody());
        Assert.assertArrayEquals(inputHeader,message.getHeader());
    }


    @Test
    public void itShouldReturnCorrectHeader(){
        byte[] inputBytes = genBytes();
        int headerLength = new Random().nextInt(inputBytes.length);
        Message message = new Message(inputBytes);
        message.setHeaderLength(headerLength);
        Assert.assertEquals(headerLength,message.getHeader().length);
    }

    @Test
    public void itShouldReturnCorrectBody(){
        byte[] inputBytes = genBytes();
        int bodyLength = new Random().nextInt(inputBytes.length);
        Message message = new Message(inputBytes);
        message.setBodyLength(bodyLength);
        Assert.assertEquals(bodyLength,message.getBody().length);
    }
}
