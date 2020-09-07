package com.demo.stream;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 重写HttpServletRequestWrapper，复用 流 数据
 * @author nijiejie
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    private final String body;

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);

        StringBuilder stringBuilder = new StringBuilder();

        /**
         * BufferedReader类从字符输入流中读取文本并"缓冲"字符，以便有效地读取字符，数组和行
         * 缓冲区大小设置：构造方法可以传递数值指定缓冲区大小,也可以由类中的默认大小指定
         */
        BufferedReader bufferedReader = null;

        try{

            //从请求中获取输入流
            InputStream inputStream = request.getInputStream();
            if (inputStream != null){

                /**
                 * 把输入流转化为InputStreamReader -->  字节流转化为字符流  -->  用于创建BufferedReader对象
                 */
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;

                /**
                 * read()方法：读取1个或多个字节，返回一个字符，当读取到文件末尾时，返回-1
                 */
                while((bytesRead = bufferedReader.read(charBuffer)) > 0){
                    stringBuilder.append(charBuffer,0,bytesRead);
                }

            }

        } finally {

            if (bufferedReader != null){
                try{
                    bufferedReader.close();
                }catch(IOException e){
                    throw  e;
                }
            }

        }

        body = stringBuilder.toString();

    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        //创建字节输入流 ByteArrayInputStream ，
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        ServletInputStream servletInputStream = new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };

        return servletInputStream;

    }

    @Override
    public BufferedReader getReader() throws IOException{

        return new BufferedReader(new InputStreamReader(this.getInputStream()));

    }

    public String getBody(){
        return this.body;
    }

}