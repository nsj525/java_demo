package com.demo.mp3;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;

import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Mp3Demo {
    public static void cutMusic() throws IOException{
        File file=new File("/Users/nijiejie/Downloads/zmj-9602-14148/师说.mp3");
        File file2=new File("/Users/nijiejie/Downloads/zmj-9602-14148/");
        FileInputStream fis =new FileInputStream(file);
        FileOutputStream fos=null;
        int len=0;
        int x=0;
        int y=1020*1024;
        byte [] one=new byte[y];
        if(file.length()%y!=0) {
            x=(int)(file.length()/y+1);
        }else if(file.length()%y==0) {
            x=(int)(file.length()/y);
        }
        for(int i=1;i<=x;i++) {
            len=fis.read(one);
            fos=new FileOutputStream (new File(file2,i+".mp3"));
            fos.write(one,0,len);
        }
        fis.close();
        fos.close();
    }

    public static void cutMusic1() throws IOException, InvalidAudioFrameException, TagException, ReadOnlyFileException {
        File file=new File("/Users/nijiejie/Downloads/线上音频.mp3");
        File file2=new File("/Users/nijiejie/Downloads/zmj-9602-14148/");
        FileInputStream fis =new FileInputStream(file);
        FileOutputStream fos=null;

        //MP3文件大小 = 码率（kbps） * 时间（秒） / 8 ,单位KB
        int bitRate = getBit();

        int len=0;
        int y= bitRate * 3700 / 8 * 1024 /1000;
        int y1= bitRate * 3300 / 8 * 1024 /1000;
        int y2= bitRate * 13800 / 8 * 1024 /1000;



        byte [] one=new byte[y];
        len=fis.read(one);
        fos=new FileOutputStream (new File(file2,"线上1.mp3"));
        fos.write(one,0,len);

        byte [] one1=new byte[y1];
        len=fis.read(one1);
        fos=new FileOutputStream (new File(file2,"线上2.mp3"));
        fos.write(one1,0,len);

        byte [] one2=new byte[y2];
        len=fis.read(one2);
        fos=new FileOutputStream (new File(file2,"线上3.mp3"));
        fos.write(one2,0,len);

        fis.close();
        fos.close();
    }

    public static Integer getBit() throws ReadOnlyFileException, TagException, InvalidAudioFrameException, IOException {
        MP3File mp3File = new MP3File("/Users/nijiejie/Downloads/线上音频.mp3");
        MP3AudioHeader header = mp3File.getMP3AudioHeader();
        System.out.println("比特率为："+header.getBitRate());
        return Integer.valueOf(header.getBitRate());
    }


    public static void main(String[] args) throws IOException, InvalidAudioFrameException, TagException, ReadOnlyFileException {
        cutMusic1();
        // cutMusic();
        // getBit();
    }
}
