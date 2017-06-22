package com.andrea.firebaseintegration.model;

/**
 * Created by Andrea on 20/06/17.
 */

public class Note {
    private String noteId;
    private String title;
    private String content;
    private String audioPath;
    private String imagePath;
    private String sketchPath;
    private String noteType;
    private long dateCreated;
    private long nextRemainder;
    private Category category;
    private boolean cloudAudioExists;
    private boolean cloudImageExists;
    private boolean cloudSketchExists;

    public Note() {
    }

    public Note(String aNoteId, String aTitle, String aContent, String aAudioPath, String aImagePath, String aSketchPath, String aNoteType, long aDateCreated, long aNextRemainder, Category aCategory, boolean aCloudAudioExists, boolean aCloudImageExists, boolean aCloudSketchExists) {
        noteId = aNoteId;
        title = aTitle;
        content = aContent;
        audioPath = aAudioPath;
        imagePath = aImagePath;
        sketchPath = aSketchPath;
        noteType = aNoteType;
        dateCreated = aDateCreated;
        nextRemainder = aNextRemainder;
        category = aCategory;
        cloudAudioExists = aCloudAudioExists;
        cloudImageExists = aCloudImageExists;
        cloudSketchExists = aCloudSketchExists;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String aNoteId) {
        noteId = aNoteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String aTitle) {
        title = aTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String aContent) {
        content = aContent;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String aAudioPath) {
        audioPath = aAudioPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String aImagePath) {
        imagePath = aImagePath;
    }

    public String getSketchPath() {
        return sketchPath;
    }

    public void setSketchPath(String aSketchPath) {
        sketchPath = aSketchPath;
    }

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String aNoteType) {
        noteType = aNoteType;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long aDateCreated) {
        dateCreated = aDateCreated;
    }

    public long getNextRemainder() {
        return nextRemainder;
    }

    public void setNextRemainder(long aNextRemainder) {
        nextRemainder = aNextRemainder;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category aCategory) {
        category = aCategory;
    }

    public boolean isCloudAudioExists() {
        return cloudAudioExists;
    }

    public void setCloudAudioExists(boolean aCloudAudioExists) {
        cloudAudioExists = aCloudAudioExists;
    }

    public boolean isCloudImageExists() {
        return cloudImageExists;
    }

    public void setCloudImageExists(boolean aCloudImageExists) {
        cloudImageExists = aCloudImageExists;
    }

    public boolean isCloudSketchExists() {
        return cloudSketchExists;
    }

    public void setCloudSketchExists(boolean aCloudSketchExists) {
        cloudSketchExists = aCloudSketchExists;
    }
}
