package edu.hitsz.application;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class recordDAO implements DAO{

    private File file = new File("resource/score_record.txt");
    public void addRecord(scoreRecord record) {
        try (OutputStream output = new BufferedOutputStream(new FileOutputStream(file, true));
        OutputStreamWriter writer = new OutputStreamWriter(output, StandardCharsets.UTF_8)) {
            writer.write(record.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("write error");
        }

    }
    public List<scoreRecord> getHead(int num){
        List<scoreRecord> records = new ArrayList<>();
        try (
                InputStream input = new BufferedInputStream(new FileInputStream(file));
                InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8)
        ) {
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null && num > 0) {
                scoreRecord record1 = new scoreRecord(line);
                records.add(record1);
                num--;
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("write error");
        }
        return records;
    }

    public void findRecord(String UserName) {
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }
        try (
                InputStream input = new BufferedInputStream(new FileInputStream(file));
                InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(reader);
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                scoreRecord record1 = new scoreRecord(line);
                if (record1.getUserName().equals(UserName)) {
                    System.out.println(record1.toString());
                }
            }
        }  catch (FileNotFoundException e) {
            System.out.println("文件未找到: " + file.getPath());
        } catch (IOException e) {
            System.out.println("读取文件时发生错误: " + file.getPath());
        } catch (Exception e) {
            // 捕获其他未预期的异常
            System.out.println("发生意外错误: " + e.getMessage());
        }

    }

    public void deleteRecord(String UserName) {

    }

    public void showAllRecord() {
        List<scoreRecord> records = new ArrayList<>();
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }
        try (
                InputStream input = new BufferedInputStream(new FileInputStream(file));
                InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(reader);
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                scoreRecord record1 = new scoreRecord(line);
                records.add(record1);
            }
            Collections.sort(records);
            for(scoreRecord record : records){
                record.rank = records.indexOf(record)+1;
                record.print();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("InputStream error");
        }
    }
}

