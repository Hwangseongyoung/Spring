package com.example.fileupload.dto;

public class FileDto {
    private String oriName;
    private String saveFileName;
    private String savePathFileName;
    private Long saveFileSize;

    public FileDto(String oriName, String saveFileName, String savePathFileName, Long saveFileSize) {
        this.oriName = oriName;
        this.saveFileName = saveFileName;
        this.savePathFileName = savePathFileName;
        this.saveFileSize = saveFileSize;
    }

    public String getOriName() {
        return oriName;
    }

    public void setOriName(String oriName) {
        this.oriName = oriName;
    }

    public String getSaveFileName() {
        return saveFileName;
    }

    public void setSaveFileName(String saveFileName) {
        this.saveFileName = saveFileName;
    }

    public String getSavePathFileName() {
        return savePathFileName;
    }

    public void setSavePathFileName(String savePathFileName) {
        this.savePathFileName = savePathFileName;
    }

    public Long getSaveFileSize() {
        return saveFileSize;
    }

    public void setSaveFileSize(Long saveFileSize) {
        this.saveFileSize = saveFileSize;
    }
}
