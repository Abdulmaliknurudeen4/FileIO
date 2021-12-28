package com.example.vaos;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Main main = new Main();
        main.writeFileBufferred();

    }

    private void readFile() throws IOException {
        long startTime = System.nanoTime();
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            fileInputStream = new FileInputStream("myinputfile.txt");
            bufferedInputStream = new BufferedInputStream(fileInputStream, (8 * 1024));
            int i;
            while ((i = bufferedInputStream.read()) != -1) {
                //  so as long the Buffer isn't emtpy
//                System.out.print((char)i);
            }
            long time = System.nanoTime() - startTime;
            System.out.printf("Time to decode %,d MB was %,d ms%n",
                    fileInputStream.available() / 1024 / 1024, time / 1000000);
        } finally {
            if (fileInputStream != null)
                fileInputStream.close();
            if (bufferedInputStream != null)
                bufferedInputStream.close();
        }

    }

    private void readFileIntoBytes() throws IOException {
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            fileInputStream = new FileInputStream("myinputfile.txt");
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            //  Sets the destination bytes where the bufferedInputStream read data into
            byte[] destBuffer = new byte[20];

            // Read specified amount of bytes with offest beginnning at zero and 11 bytes
            int numberOfBytesread = bufferedInputStream.read(destBuffer, 0, 11);
            for (byte b : destBuffer) {
                System.out.print((char) b);
            }

        } finally {
            if (fileInputStream != null)
                fileInputStream.close();
            if (bufferedInputStream != null)
                bufferedInputStream.close();
        }
    }

    private void writeFileBufferred() throws IOException {
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try{
            fileOutputStream = new FileOutputStream("output.txt");
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            String str = "Peter is going to India.";
            byte[] strArray = str.getBytes();

            bufferedOutputStream.write(strArray);
            bufferedOutputStream.flush();
            System.out.println("SuccessFully written to the file" +
                    "Please check the file for confirmation.");
        }finally {
            if (fileOutputStream != null)
                fileOutputStream.close();
            if (bufferedOutputStream != null)
                bufferedOutputStream.close();
        }
    }
}
