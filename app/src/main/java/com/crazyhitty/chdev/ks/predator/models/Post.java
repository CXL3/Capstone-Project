/*
 * MIT License
 *
 * Copyright (c) 2016 Kartik Sharma
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.crazyhitty.chdev.ks.predator.models;

import java.util.List;

/**
 * Author:      Kartik Sharma
 * Email Id:    cr42yh17m4n@gmail.com
 * Created:     1/20/2017 7:12 PM
 * Description: Unavailable
 */

public class Post {
    private int id;
    private int postId;
    private int collectionId;
    private int categoryId;
    private String day;
    private String name;
    private String tagline;
    private int commentCount;
    private String createdAt;
    private long createdAtMillis;
    private String discussionUrl;
    private String redirectUrl;
    private int votesCount;
    private String thumbnailImageUrl;
    private String thumbnailImageUrlOriginal;
    private String screenshotUrl300px;
    private String screenshotUrl850px;
    private String username;
    private String usernameAlternative;
    private int userId;
    private String userImageUrl100px;
    private String userImageUrlOriginal;
    private boolean isInCollection;
    private String date;
    private String backdropUrl;
    private String category;
    private int voteCount;
    private User hunter;
    private List<User> makers;
    private List<User> voters;
    private List<Comment> comments;
    private List<Media> media;
    private List<InstallLink> installLinks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public long getCreatedAtMillis() {
        return createdAtMillis;
    }

    public void setCreatedAtMillis(long createdAtMillis) {
        this.createdAtMillis = createdAtMillis;
    }

    public String getDiscussionUrl() {
        return discussionUrl;
    }

    public void setDiscussionUrl(String discussionUrl) {
        this.discussionUrl = discussionUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public int getVotesCount() {
        return votesCount;
    }

    public void setVotesCount(int votesCount) {
        this.votesCount = votesCount;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public String getThumbnailImageUrlOriginal() {
        return thumbnailImageUrlOriginal;
    }

    public void setThumbnailImageUrlOriginal(String thumbnailImageUrlOriginal) {
        this.thumbnailImageUrlOriginal = thumbnailImageUrlOriginal;
    }

    public String getScreenshotUrl300px() {
        return screenshotUrl300px;
    }

    public void setScreenshotUrl300px(String screenshotUrl300px) {
        this.screenshotUrl300px = screenshotUrl300px;
    }

    public String getScreenshotUrl850px() {
        return screenshotUrl850px;
    }

    public void setScreenshotUrl850px(String screenshotUrl850px) {
        this.screenshotUrl850px = screenshotUrl850px;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameAlternative() {
        return usernameAlternative;
    }

    public void setUsernameAlternative(String usernameAlternative) {
        this.usernameAlternative = usernameAlternative;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserImageUrl100px() {
        return userImageUrl100px;
    }

    public void setUserImageUrl100px(String userImageUrl100px) {
        this.userImageUrl100px = userImageUrl100px;
    }

    public String getUserImageUrlOriginal() {
        return userImageUrlOriginal;
    }

    public void setUserImageUrlOriginal(String userImageUrlOriginal) {
        this.userImageUrlOriginal = userImageUrlOriginal;
    }

    public boolean isInCollection() {
        return isInCollection;
    }

    public void setInCollection(boolean inCollection) {
        isInCollection = inCollection;
    }

    public void setInCollection(int inCollection) {
        isInCollection = inCollection == 1;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBackdropUrl() {
        return backdropUrl;
    }

    public void setBackdropUrl(String backdropUrl) {
        this.backdropUrl = backdropUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public User getHunter() {
        return hunter;
    }

    public void setHunter(User hunter) {
        this.hunter = hunter;
    }

    public List<User> getMakers() {
        return makers;
    }

    public void setMakers(List<User> makers) {
        this.makers = makers;
    }

    public List<User> getVoters() {
        return voters;
    }

    public void setVoters(List<User> voters) {
        this.voters = voters;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    public List<InstallLink> getInstallLinks() {
        return installLinks;
    }

    public void setInstallLinks(List<InstallLink> installLinks) {
        this.installLinks = installLinks;
    }
}
