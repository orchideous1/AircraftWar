package edu.hitsz.application;

import java.util.List;

public interface DAO {
    void findRecord(String UserName);
    List<scoreRecord> getHead(int num);

    void addRecord(scoreRecord record);

    void deleteRecord(String rank);

    void showAllRecord();

    String[][] getAllRecord();
}

