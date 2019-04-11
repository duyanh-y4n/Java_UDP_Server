package con.y4n.Utils.MessageUtils;

import com.y4n.Utils.DataFormatUtils;
import com.y4n.Utils.MessageUtils.Message;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class MessageTest {
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
        com.y4n.Utils.MessageUtils.Message message = new com.y4n.Utils.MessageUtils.Message(inputBytes);
        System.out.println(inputBytes[0]);
        Assert.assertArrayEquals(inputBytes,message.getRawContent());
    }

    @Test
    public void itShouldReturnInputParamAsMessageContent2(){
        byte[] inputHeader = genBytes();
        byte[] inputBody = genBytes();
        byte[] inputRaw = DataFormatUtils.concatBytesArr(inputHeader,inputBody);
        com.y4n.Utils.MessageUtils.Message message = new com.y4n.Utils.MessageUtils.Message(inputHeader,inputBody);
        Assert.assertArrayEquals(inputRaw,message.getRawContent());
        Assert.assertArrayEquals(inputBody,message.getBody());
        Assert.assertArrayEquals(inputHeader,message.getHeader());
    }


    @Test
    public void itShouldReturnCorrectHeader(){
        byte[] inputBytes = genBytes();
        int headerLength = new Random().nextInt(inputBytes.length);
        com.y4n.Utils.MessageUtils.Message message = new com.y4n.Utils.MessageUtils.Message(inputBytes);
        message.setHeaderLength(headerLength);
        Assert.assertEquals(headerLength,message.getHeader().length);
    }

    @Test
    public void itShouldReturnCorrectBody(){
        byte[] inputBytes = genBytes();
        int bodyLength = new Random().nextInt(inputBytes.length);
        com.y4n.Utils.MessageUtils.Message message = new com.y4n.Utils.MessageUtils.Message(inputBytes);
        message.setBodyLength(bodyLength);
        Assert.assertEquals(bodyLength,message.getBody().length);
    }

    @Test
    public void itShouldReturnOnlyBodyWhenDeleteHeader(){
        byte[] inputBytes = genBytes();
        int headerLength = new Random().nextInt(inputBytes.length);
        com.y4n.Utils.MessageUtils.Message message = new com.y4n.Utils.MessageUtils.Message(inputBytes);
        message.setHeaderLength(headerLength);
        byte[] body = message.getBody();
        message.deleteHeader();
        Assert.assertArrayEquals(body,message.getRawContent());
    }

    @Test
    public void itShouldReturnOnlyHeaderWhenDeleteBody(){
        byte[] inputBytes = genBytes();
        int bodyLength = new Random().nextInt(inputBytes.length);
        com.y4n.Utils.MessageUtils.Message message = new com.y4n.Utils.MessageUtils.Message(inputBytes);
        message.setBodyLength(bodyLength);
        byte[] header = message.getHeader();
        message.deleteBody();
        Assert.assertArrayEquals(header,message.getRawContent());
    }

    @Test
    public void itShouldReturnNewHeaderAndOldBodyWhenChangeHeader(){
        byte[] oldHeader = genBytes();
        byte[] oldBody = genBytes();
        com.y4n.Utils.MessageUtils.Message message = new com.y4n.Utils.MessageUtils.Message(oldHeader,oldBody);
        byte[] newHeader = genBytes();
        byte[] newMessContent = DataFormatUtils.concatBytesArr(newHeader,oldBody);
        message.changeHeader(newHeader);
        Assert.assertArrayEquals(newMessContent,message.getRawContent());
    }

    @Test
    public void itShouldReturnOldHeaderAndNewBodyWhenChangeBody(){
        byte[] oldHeader = genBytes();
        byte[] oldBody = genBytes();
        com.y4n.Utils.MessageUtils.Message message = new com.y4n.Utils.MessageUtils.Message(oldHeader,oldBody);
        byte[] newBody = genBytes();
        byte[] newMessContent = DataFormatUtils.concatBytesArr(oldHeader,newBody);
        message.changeBody(newBody);
        Assert.assertArrayEquals(newMessContent,message.getRawContent());
    }

}
