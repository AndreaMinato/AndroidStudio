package com.andrea.myapplication.data.model;


import android.text.Editable;

import org.kohsuke.randname.RandomNameGenerator;

import java.util.UUID;

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
        RandomNameGenerator vGenerator = new RandomNameGenerator();
        noteId = UUID.randomUUID().toString();
        title = "Nuova nota";
        content = "";
    }

    public Note( String aTitle, String aContent) {
        title = aTitle;
        content = aContent;
    }

    public Note(String aId, String aTitle, String aContent) {
        noteId = aId;
        title = aTitle;
        content = aContent;
    }
    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getSketchPath() {
        return sketchPath;
    }

    public void setSketchPath(String sketchPath) {
        this.sketchPath = sketchPath;
    }

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public long getNextRemainder() {
        return nextRemainder;
    }

    public void setNextRemainder(long nextRemainder) {
        this.nextRemainder = nextRemainder;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isCloudAudioExists() {
        return cloudAudioExists;
    }

    public void setCloudAudioExists(boolean cloudAudioExists) {
        this.cloudAudioExists = cloudAudioExists;
    }

    public boolean isCloudImageExists() {
        return cloudImageExists;
    }

    public void setCloudImageExists(boolean cloudImageExists) {
        this.cloudImageExists = cloudImageExists;
    }

    public boolean isCloudSketchExists() {
        return cloudSketchExists;
    }

    public void setCloudSketchExists(boolean cloudSketchExists) {
        this.cloudSketchExists = cloudSketchExists;
    }
}
