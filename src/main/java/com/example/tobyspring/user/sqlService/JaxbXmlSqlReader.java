package com.example.tobyspring.user.sqlService;

public class JaxbXmlSqlReader implements SqlReader {
    private String sqlmapFile;

    public void setSqlmapFile(String sqlmapFile) {
        this.sqlmapFile = sqlmapFile;
    }

    @Override
    public void read(SqlRegistry sqlRegistry) {
        // jaxb를 활용한 파일 읽어오기
    }
}
