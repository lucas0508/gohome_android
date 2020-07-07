package com.qujiali.jiaogegongren.bean;

import java.io.Serializable;

/**
 * @author QiZai
 * @desc
 * @date 2018/7/20 16:20
 */

public class NewsEntity implements Serializable {

    private String id;

    /**
     * 内容
     */
    public String content;


    private String title;

    private String url;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 作者
     */
    private String author;

    /**
     * 链接
     */
    private String link;

    /**
     * 缩略图
     */
    private String thumb;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorLabel() {
        if (author != null) return "文/" + author;
        return null;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }


    @Override
    public String toString() {
        return "NewsEntity{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", author='" + author + '\'' +
                ", link='" + link + '\'' +
                ", thumb='" + thumb + '\'' +
                '}';
    }
}
