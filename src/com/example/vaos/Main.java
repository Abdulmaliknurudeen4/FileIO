package com.example.vaos;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) throws IOException {

        Main main = new Main();
        main.ByteArrayOutPutStream();
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
        try {
            fileOutputStream = new FileOutputStream("output.txt");
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            String str = "Peter is going to India.";
            byte[] strArray = str.getBytes();

            bufferedOutputStream.write(strArray);
            bufferedOutputStream.flush();
            System.out.println("SuccessFully written to the file" +
                    "Please check the file for confirmation.");
        } finally {
            if (fileOutputStream != null)
                fileOutputStream.close();
            if (bufferedOutputStream != null)
                bufferedOutputStream.close();
        }
    }

    private void writeFileBufferredOffset() throws IOException {
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("output.txt");
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            String str = "Peter is going to India.";
            byte[] strArray = str.getBytes();

            //  write starting from postion 18 and write 5 character.
            //  India
            bufferedOutputStream.write(strArray, 18, 5);
            bufferedOutputStream.flush();
            System.out.println("SuccessFully written to the file" +
                    "Please check the file for confirmation.");
        } finally {
            if (fileOutputStream != null)
                fileOutputStream.close();
            if (bufferedOutputStream != null)
                bufferedOutputStream.close();
        }
    }

    private void readFileReader() throws IOException {
        //  Reads line by line
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader("myinputfile.txt");
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } finally {
            if (bufferedReader != null)
                bufferedReader.close();
            if (fileReader != null)
                fileReader.close();
        }
    }

    private void readFileReaderChar() throws IOException {
        //  Reads line by line
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader("myinputfile.txt");
            bufferedReader = new BufferedReader(fileReader);
            int i;
            while ((i = bufferedReader.read()) != -1) {
                //  Read Character by character instead of line by line
                System.out.print((char) i);
            }
        } finally {
            if (bufferedReader != null)
                bufferedReader.close();
            if (fileReader != null)
                fileReader.close();
        }
    }

    private void writerFileWriter() throws IOException {
        //  Reads line by line
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter("output.txt");
            bufferedWriter = new BufferedWriter(fileWriter, (8 * 1024));
            String line1 = "AbdulMalik is going to Japan.";
            String line2 = "Gafar is going to Saudi Arabia.";

            //  Writes firstline
            bufferedWriter.write(line1);
            // Writes Carriage return
            bufferedWriter.newLine();
            bufferedWriter.write(line2);
            //  flushes unput data unto the stream.
            bufferedWriter.flush();

        } finally {
            if (bufferedWriter != null)
                bufferedWriter.close();
            if (bufferedWriter != null)
                bufferedWriter.close();
        }
    }

    private void readFromConsole() {
        Console console = System.console();
        System.out.println("Enter the Username: ");
        String username = console.readLine();

        System.out.println("Enter the password :");
        String password = String.valueOf(console.readPassword());

        System.out.println("Username " + username);
        System.out.println("Password " + password);
    }

    private void readCharFromKeyboard() throws IOException {
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        System.out.println("Enter Your Name");
        try {
            inputStreamReader = new InputStreamReader(System.in);
            bufferedReader = new BufferedReader(inputStreamReader);

            //  Reads a line from the keyboard
            //  ends with the \n delimeter (An Enter Key)
            String name = bufferedReader.readLine();
            System.out.println("Welcome " + name);
        } finally {
            if (inputStreamReader != null)
                inputStreamReader.close();
            if (bufferedReader != null)
                bufferedReader.close();
        }
    }

    private void readValuesScanner() {
        //  Sets up a Scanner that uses the System keyboad
        //  as in inputStream
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Your Id In Intergers");
        int rollno = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Your Name");
        String name = scanner.nextLine();

        System.out.println("Enter your Height");
        double height = scanner.nextDouble();

        System.out.println("Your id is " + rollno);
        System.out.println("Your name is " + name);
        System.out.println("Your height is " + height);
        scanner.close();
    }

    private void readValuesScannerUsingDelimeters() {
        //  Sets up a Scanner that uses the System keyboad
        String date = "10-20-30-40-50";
        Scanner scanner = new Scanner(date);
        scanner.useDelimiter("-");
        int sum = 0;

        while (scanner.hasNext()) {
            sum += scanner.nextInt();
        }
        System.out.println("Total sum = " + sum);


        scanner.close();
    }

    private void readFileUsingScanner() throws FileNotFoundException {
        //  Sets up a Scanner that uses the System keyboad
        File file = new File("myinputfile.txt");
        Scanner scanner = new Scanner(file);
        int nol = 0;

        while (scanner.hasNext()) {
            nol++;
            System.out.println(scanner.nextLine());
        }
        System.out.println("Total sum = " + nol);


        scanner.close();
    }

    private void readFileUsingScannerBuffered() throws FileNotFoundException {
        //  Sets up a Scanner that uses the System keyboad
        System.out.println("Using a Buffering Strategy");
        BufferedReader file = new BufferedReader(new FileReader("myinputfile.txt"), (8 * 1024));
        Scanner scanner = new Scanner(file);
        int nol = 0;

        while (scanner.hasNext()) {
            nol++;
            System.out.println(scanner.nextLine());
        }
        System.out.println("Total sum = " + nol);


        scanner.close();
    }

    private void readFileUsingScannerBufferedLocale() throws FileNotFoundException {
        //  Sets up a Scanner that uses the System keyboad
        System.out.println("Using a Buffering Strategy");
        BufferedReader file = new BufferedReader(new FileReader("intergers.txt"), (8 * 1024));
        //  reads in the content of intergers.txt without considering the commas since it's currency
        Scanner scanner = new Scanner(file);
        //  to use local specific symbols.
        scanner.useLocale(Locale.UK);
        double nol = 0;

        while (scanner.hasNext()) {
            nol += scanner.nextDouble();
        }
        System.out.println("Total sum = " + nol);


        scanner.close();
    }

    private void readNumbersFromKeyboard() throws IOException {
        Scanner scanner =
                new Scanner(new BufferedInputStream(System.in));
        System.out.println("Enter Your First Number");
        int numberOne = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Your Second Number");
        int numberTow = scanner.nextInt();

        System.out.println(numberOne);
        System.out.println(numberTow);
        scanner.close();
    }

    private void readArrayOfNames() {
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        ArrayList<String> names = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String value = scanner.nextLine();
            if (value.equalsIgnoreCase("quit"))
                break;
            names.add(value);
        }
        Consumer<? super String> printName = (Consumer<String>) s -> System.out.println(s);
        names.forEach(printName);
    }

    private void printUsingPrintStream() throws IOException {
        FileOutputStream outputStream = null;
        PrintStream printStream = null;
        try {
            outputStream = new FileOutputStream("myfile2.txt");
            printStream = new PrintStream(outputStream);

            printStream.println(1123);
            printStream.println("Nurudeen Abdulmalik");
            printStream.println(true);
            printStream.print(32342.3334);
            printStream.printf("\n%S - %.2f", "Nurudeen", 9.92);
        } finally {
            if (printStream != null)
                printStream.close();
            if (outputStream != null)
                outputStream.close();
        }
    }

    private void printWriterFile() throws IOException {

        PrintWriter printWriter = null;
        try {
            File file = new File("myfile2.txt");
            printWriter = new PrintWriter(file);
            int intValue = 100;
            double doubleValue = 200.56;
            printWriter.printf("i = %d and k = %f", intValue, doubleValue);
            System.out.println("SuccessFully written value to file");
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }

    }

    private void formatPrint() {
        int i = 20;
        double value = Math.sqrt(i);
        System.out.printf("The square root of %d is %f", i, value);
    }

    private void ByteArrayInputStreamTrail() throws IOException {
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            String str = "I am going to India In March. ?";
            byte[] bytes = str.getBytes();
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            int character;
            while ((character = byteArrayInputStream.read()) != -1) {
                System.out.print((char) character);
            }
        } finally {
            if (byteArrayInputStream != null) {
                byteArrayInputStream.close();
            }
        }

    }

    private void ByteArrayInputStreamNumbers() throws IOException {
        ByteArrayInputStream byStream = null;

        try {
            byte[] buffer = new byte[5];
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = (byte) i;
            }

            byStream = new ByteArrayInputStream(buffer);
            System.out.println("All Elements in the buffer");

            int num;
            while ((num = byStream.read()) != -1) {
                System.out.println(num);
            }
        } finally {
            byStream.close();
        }
    }


    private void ReadWriteByStream() throws IOException {
        byte[] srcBuffer = new byte[10];
        byte[] destBuffer = new byte[6];

        for (int i = 0; i < srcBuffer.length; i++) srcBuffer[i] = (byte) i;

        try (ByteArrayInputStream byStream = new ByteArrayInputStream(srcBuffer)) {
            byStream.read(destBuffer, 1, 5);
            for (int i = 0; i < destBuffer.length; i++) System.out.print(destBuffer[i] + " ");
        }


    }

    private void ByteArrayInputStreamPractice() {
        try(Scanner scanner = new Scanner(System.in)){
            System.out.println("enter your name");
            String name = scanner.nextLine();
            ByteArrayInputStream byStream = new ByteArrayInputStream(name.getBytes());
            //  ByteArrayInputStream(byte[], int offset, int length);
            StringBuilder sb = new StringBuilder();

            int character;
            while ((character=byStream.read())!=-1){
                sb.append(Character.toUpperCase((char) character));
            }
            System.out.println(sb.toString());
            System.out.println("Resetting");


            sb  = new StringBuilder();
            byStream.reset();


            while (byStream.available()!= 0){
                sb.append(Character.toLowerCase((char) byStream.read()));
            }
            System.out.println(sb.toString());
        }
    }

    private void ByteArrayOutPutStream() throws IOException{
        String outputStr = "Welcome to Nigeria ";
        ByteArrayOutputStream byOutStr = new ByteArrayOutputStream();
        byOutStr.write(outputStr.getBytes());

        System.out.println(byOutStr.toString());
    }

}
