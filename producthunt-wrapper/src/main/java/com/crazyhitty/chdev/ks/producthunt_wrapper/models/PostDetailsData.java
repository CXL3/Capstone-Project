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

package com.crazyhitty.chdev.ks.producthunt_wrapper.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Author:      Kartik Sharma
 * Email Id:    cr42yh17m4n@gmail.com
 * Created:     1/16/2017 12:46 PM
 * Description: Unavailable
 */

public class PostDetailsData {

    /**
     * post : {"votes":[{"id":1,"created_at":"2017-01-02T01:18:14.332-08:00","post_id":1,"user_id":6,"user":{"id":6,"created_at":"2017-01-02T01:18:14.304-08:00","name":"Karl User the 173th","username":"producthunter173","headline":"Product Hunter","twitter_username":"hunter173","website_url":"http://awesome.io","profile_url":"http://www.producthunt.com/@producthunter173","image_url":{"100px":"https://avatars-cdn.producthunt.com/6/original?auto=format&fit=crop&crop=faces&w=100&h=100","original":"https://avatars-cdn.producthunt.com/6/original?auto=format&fit=crop&crop=faces&w=original&h=original"}}}],"related_links":[],"related_posts":[{"category_id":1}],"install_links":[{"id":2,"post_id":1,"created_at":"2017-01-02T01:18:14.259-08:00","primary_link":false,"redirect_url":"http://www.producthunt.com/r/bd93aa2d6d7db3/1?app_id=1","platform":"ios"}],"media":[{"id":312823,"kindle_asin":null,"media_type":"video","priority":0,"platform":"youtube","video_id":"4BvwpjaGZCQ","original_width":480,"original_height":270,"metadata":{"platform":"youtube","video_id":"4BvwpjaGZCQ"},"image_url":"https://ph-files.imgix.net/0624fe45-cd99-42fb-a275-df3e331708ab?auto=format"},{"id":312824,"kindle_asin":null,"media_type":"image","priority":0,"platform":null,"video_id":null,"original_width":1200,"original_height":628,"metadata":{},"image_url":"https://ph-files.imgix.net/7d68ff91-9be7-41a9-945a-b994ac0b6929?auto=format"}]}
     */

    private PostDetails post;

    public PostDetails getPost() {
        return post;
    }

    public void setPost(PostDetails post) {
        this.post = post;
    }

    public static class PostDetails {
        @SerializedName("category_id")
        private int categoryId;
        private String day;
        private int id;
        private String name;
        private String tagline;
        @SerializedName("comments_count")
        private int commentsCount;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("current_user")
        private PostsData.Posts.CurrentUser currentUser;
        @SerializedName("discussion_url")
        private String discussionUrl;
        @SerializedName("maker_inside")
        private boolean makerInside;
        @SerializedName("redirect_url")
        private String redirectUrl;
        @SerializedName("screenshot_url")
        private PostsData.Posts.ScreenshotUrl screenshotUrl;
        private PostsData.Posts.Thumbnail thumbnail;
        private PostsData.Posts.User user;
        @SerializedName("votes_count")
        private int votesCount;
        private List<PostsData.Posts.Makers> makers;
        private List<?> platforms;
        private List<PostsData.Posts.Topics> topics;
        private List<Votes> votes;
        @SerializedName("related_links")
        private List<?> relatedLinks;
        @SerializedName("related_posts")
        private List<PostsData.Posts> relatedPosts;
        @SerializedName("install_links")
        private List<InstallLinks> installLinks;
        private List<Media> media;
        private List<PostCommentsData.Comments> comments;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getCommentsCount() {
            return commentsCount;
        }

        public void setCommentsCount(int commentsCount) {
            this.commentsCount = commentsCount;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public PostsData.Posts.CurrentUser getCurrentUser() {
            return currentUser;
        }

        public void setCurrentUser(PostsData.Posts.CurrentUser currentUser) {
            this.currentUser = currentUser;
        }

        public String getDiscussionUrl() {
            return discussionUrl;
        }

        public void setDiscussionUrl(String discussionUrl) {
            this.discussionUrl = discussionUrl;
        }

        public boolean isMakerInside() {
            return makerInside;
        }

        public void setMakerInside(boolean makerInside) {
            this.makerInside = makerInside;
        }

        public String getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }

        public PostsData.Posts.ScreenshotUrl getScreenshotUrl() {
            return screenshotUrl;
        }

        public void setScreenshotUrl(PostsData.Posts.ScreenshotUrl screenshotUrl) {
            this.screenshotUrl = screenshotUrl;
        }

        public PostsData.Posts.Thumbnail getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(PostsData.Posts.Thumbnail thumbnail) {
            this.thumbnail = thumbnail;
        }

        public PostsData.Posts.User getUser() {
            return user;
        }

        public void setUser(PostsData.Posts.User user) {
            this.user = user;
        }

        public int getVotesCount() {
            return votesCount;
        }

        public void setVotesCount(int votesCount) {
            this.votesCount = votesCount;
        }

        public List<PostsData.Posts.Makers> getMakers() {
            return makers;
        }

        public void setMakers(List<PostsData.Posts.Makers> makers) {
            this.makers = makers;
        }

        public List<?> getPlatforms() {
            return platforms;
        }

        public void setPlatforms(List<?> platforms) {
            this.platforms = platforms;
        }

        public List<PostsData.Posts.Topics> getTopics() {
            return topics;
        }

        public void setTopics(List<PostsData.Posts.Topics> topics) {
            this.topics = topics;
        }

        public List<Votes> getVotes() {
            return votes;
        }

        public void setVotes(List<Votes> votes) {
            this.votes = votes;
        }

        public List<?> getRelatedLinks() {
            return relatedLinks;
        }

        public void setRelatedLinks(List<?> relatedLinks) {
            this.relatedLinks = relatedLinks;
        }

        public List<PostsData.Posts> getRelatedPosts() {
            return relatedPosts;
        }

        public void setRelatedPosts(List<PostsData.Posts> relatedPosts) {
            this.relatedPosts = relatedPosts;
        }

        public List<InstallLinks> getInstallLinks() {
            return installLinks;
        }

        public void setInstallLinks(List<InstallLinks> installLinks) {
            this.installLinks = installLinks;
        }

        public List<Media> getMedia() {
            return media;
        }

        public void setMedia(List<Media> media) {
            this.media = media;
        }

        public List<PostCommentsData.Comments> getComments() {
            return comments;
        }

        public void setComments(List<PostCommentsData.Comments> comments) {
            this.comments = comments;
        }

        public static class CurrentUser {
            /**
             * voted_for_post : false
             * commented_on_post : false
             */

            @SerializedName("voted_for_post")
            private boolean votedForPost;
            @SerializedName("commented_on_post")
            private boolean commentedOnPost;

            public boolean isVotedForPost() {
                return votedForPost;
            }

            public void setVotedForPost(boolean votedForPost) {
                this.votedForPost = votedForPost;
            }

            public boolean isCommentedOnPost() {
                return commentedOnPost;
            }

            public void setCommentedOnPost(boolean commentedOnPost) {
                this.commentedOnPost = commentedOnPost;
            }
        }

        public static class ScreenshotUrl {
            /**
             * 300px : https://api.url2png.com/v6/P5329C1FA0ECB6/214be07406f113edc738de73de07bf75/png/?thumbnail_max_width=300&url=https%3A%2F%2Fanimaapp.github.io%2FAuto-Layout%2F
             * 850px : https://api.url2png.com/v6/P5329C1FA0ECB6/fabbd143b0e0363a954bd8423ac226f5/png/?thumbnail_max_width=850&url=https%3A%2F%2Fanimaapp.github.io%2FAuto-Layout%2F
             */

            @SerializedName("300px")
            private String value300px;
            @SerializedName("850px")
            private String value850px;

            public String getValue300px() {
                return value300px;
            }

            public void setValue300px(String value300px) {
                this.value300px = value300px;
            }

            public String getValue850px() {
                return value850px;
            }

            public void setValue850px(String value850px) {
                this.value850px = value850px;
            }
        }

        public static class Thumbnail {
            /**
             * id : 305696
             * media_type : image
             * image_url : https://ph-files.imgix.net/b8a112b7-1311-4cfb-9098-74d72d5f592d?auto=format&fit=crop&h=570&w=430
             */

            private int id;
            @SerializedName("media_type")
            private String mediaType;
            @SerializedName("image_url")
            private String imageUrl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMediaType() {
                return mediaType;
            }

            public void setMediaType(String mediaType) {
                this.mediaType = mediaType;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getOriginalImageUrl() {
                return imageUrl.replace("h=570&w=430", "h=original&w=original");
            }
        }

        public static class User {
            /**
             * id : 347650
             * name : Umit Akcan
             * username : umitakcn
             * image_url : {"48px":"https://ph-avatars.imgix.net/347650/original?auto=format&fit=crop&crop=faces&w=100&h=100","original":"https://ph-avatars.imgix.net/347650/original?auto=format&fit=crop&crop=faces&w=original&h=original"}
             */

            private int id;
            private String name;
            private String username;
            private String headline;
            @SerializedName("website_url")
            private String websiteUrl;
            @SerializedName("image_url")
            private PostsData.Posts.User.ImageUrl imageUrl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getWebsiteUrl() {
                return websiteUrl;
            }

            public void setWebsiteUrl(String websiteUrl) {
                this.websiteUrl = websiteUrl;
            }

            public String getHeadline() {
                return headline;
            }

            public void setHeadline(String headline) {
                this.headline = headline;
            }

            public PostsData.Posts.User.ImageUrl getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(PostsData.Posts.User.ImageUrl imageUrl) {
                this.imageUrl = imageUrl;
            }

            public static class ImageUrl {
                /**
                 * 48px : https://ph-avatars.imgix.net/347650/original?auto=format&fit=crop&crop=faces&w=48&h=48
                 * original : https://ph-avatars.imgix.net/347650/original?auto=format&fit=crop&crop=faces&w=original&h=original
                 */

                @SerializedName("100px")
                private String value100px;
                private String original;

                public String getValue100px() {
                    return value100px;
                }

                public void setValue100px(String value100px) {
                    this.value100px = value100px;
                }

                public String getOriginal() {
                    return original;
                }

                public void setOriginal(String original) {
                    this.original = original;
                }
            }
        }

        public static class Topics {
            /**
             * id : 44
             * name : Design Tools
             * slug : design-tools
             */

            private int id;
            private String name;
            private String slug;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }
        }

        public static class Votes {
            /**
             * id : 1
             * created_at : 2017-01-02T01:18:14.332-08:00
             * post_id : 1
             * user_id : 6
             * user : {"id":6,"created_at":"2017-01-02T01:18:14.304-08:00","name":"Karl User the 173th","username":"producthunter173","headline":"Product Hunter","twitter_username":"hunter173","website_url":"http://awesome.io","profile_url":"http://www.producthunt.com/@producthunter173","image_url":{"100px":"https://avatars-cdn.producthunt.com/6/original?auto=format&fit=crop&crop=faces&w=100&h=100","original":"https://avatars-cdn.producthunt.com/6/original?auto=format&fit=crop&crop=faces&w=original&h=original"}}
             */

            private int id;
            @SerializedName("created_at")
            private String createdAt;
            @SerializedName("post_id")
            private int postId;
            @SerializedName("user_id")
            private int userId;
            private User user;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public int getPostId() {
                return postId;
            }

            public void setPostId(int postId) {
                this.postId = postId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public User getUser() {
                return user;
            }

            public void setUser(User user) {
                this.user = user;
            }

            public static class User {
                /**
                 * id : 6
                 * created_at : 2017-01-02T01:18:14.304-08:00
                 * name : Karl User the 173th
                 * username : producthunter173
                 * headline : Product Hunter
                 * twitter_username : hunter173
                 * website_url : http://awesome.io
                 * profile_url : http://www.producthunt.com/@producthunter173
                 * image_url : {"100px":"https://avatars-cdn.producthunt.com/6/original?auto=format&fit=crop&crop=faces&w=100&h=100","original":"https://avatars-cdn.producthunt.com/6/original?auto=format&fit=crop&crop=faces&w=original&h=original"}
                 */

                private int id;
                @SerializedName("created_at")
                private String createdAt;
                private String name;
                private String username;
                private String headline;
                @SerializedName("twitter_username")
                private String twitterUsername;
                @SerializedName("website_url")
                private String websiteUrl;
                @SerializedName("profile_url")
                private String profileUrl;
                @SerializedName("image_url")
                private ImageUrl imageUrl;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getCreatedAt() {
                    return createdAt;
                }

                public void setCreatedAt(String createdAt) {
                    this.createdAt = createdAt;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getHeadline() {
                    return headline;
                }

                public void setHeadline(String headline) {
                    this.headline = headline;
                }

                public String getTwitterUsername() {
                    return twitterUsername;
                }

                public void setTwitterUsername(String twitterUsername) {
                    this.twitterUsername = twitterUsername;
                }

                public String getWebsiteUrl() {
                    return websiteUrl;
                }

                public void setWebsiteUrl(String websiteUrl) {
                    this.websiteUrl = websiteUrl;
                }

                public String getProfileUrl() {
                    return profileUrl;
                }

                public void setProfileUrl(String profileUrl) {
                    this.profileUrl = profileUrl;
                }

                public ImageUrl getImageUrl() {
                    return imageUrl;
                }

                public void setImageUrl(ImageUrl imageUrl) {
                    this.imageUrl = imageUrl;
                }

                public static class ImageUrl {
                    /**
                     * 100px : https://avatars-cdn.producthunt.com/6/original?auto=format&fit=crop&crop=faces&w=100&h=100
                     * original : https://avatars-cdn.producthunt.com/6/original?auto=format&fit=crop&crop=faces&w=original&h=original
                     */

                    @SerializedName("100px")
                    private String value100px;
                    private String original;

                    public String getValue100px() {
                        return value100px;
                    }

                    public void setValue100px(String value100px) {
                        this.value100px = value100px;
                    }

                    public String getOriginal() {
                        return original;
                    }

                    public void setOriginal(String original) {
                        this.original = original;
                    }
                }
            }
        }

        public static class InstallLinks {
            /**
             * id : 2
             * post_id : 1
             * created_at : 2017-01-02T01:18:14.259-08:00
             * primary_link : false
             * redirect_url : http://www.producthunt.com/r/bd93aa2d6d7db3/1?app_id=1
             * platform : ios
             */

            private int id;
            @SerializedName("post_id")
            private int postId;
            @SerializedName("created_at")
            private String createdAt;
            @SerializedName("primary_link")
            private boolean primaryLink;
            @SerializedName("redirect_url")
            private String redirectUrl;
            private String platform;

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

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public boolean isPrimaryLink() {
                return primaryLink;
            }

            public void setPrimaryLink(boolean primaryLink) {
                this.primaryLink = primaryLink;
            }

            public String getRedirectUrl() {
                return redirectUrl;
            }

            public void setRedirectUrl(String redirectUrl) {
                this.redirectUrl = redirectUrl;
            }

            public String getPlatform() {
                return platform;
            }

            public void setPlatform(String platform) {
                this.platform = platform;
            }
        }

        public static class Media {
            /**
             * id : 312823
             * kindle_asin : null
             * media_type : video
             * priority : 0
             * platform : youtube
             * video_id : 4BvwpjaGZCQ
             * original_width : 480
             * original_height : 270
             * metadata : {"platform":"youtube","video_id":"4BvwpjaGZCQ"}
             * image_url : https://ph-files.imgix.net/0624fe45-cd99-42fb-a275-df3e331708ab?auto=format
             */

            private int id;
            @SerializedName("kindle_asin")
            private Object kindleAsin;
            @SerializedName("media_type")
            private String mediaType;
            private int priority;
            private String platform;
            @SerializedName("video_id")
            private String videoId;
            @SerializedName("original_width")
            private int originalWidth;
            @SerializedName("original_height")
            private int originalHeight;
            private Metadata metadata;
            @SerializedName("image_url")
            private String imageUrl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getKindleAsin() {
                return kindleAsin;
            }

            public void setKindleAsin(Object kindleAsin) {
                this.kindleAsin = kindleAsin;
            }

            public String getMediaType() {
                return mediaType;
            }

            public void setMediaType(String mediaType) {
                this.mediaType = mediaType;
            }

            public int getPriority() {
                return priority;
            }

            public void setPriority(int priority) {
                this.priority = priority;
            }

            public String getPlatform() {
                return platform;
            }

            public void setPlatform(String platform) {
                this.platform = platform;
            }

            public String getVideoId() {
                return videoId;
            }

            public void setVideoId(String videoId) {
                this.videoId = videoId;
            }

            public int getOriginalWidth() {
                return originalWidth;
            }

            public void setOriginalWidth(int originalWidth) {
                this.originalWidth = originalWidth;
            }

            public int getOriginalHeight() {
                return originalHeight;
            }

            public void setOriginalHeight(int originalHeight) {
                this.originalHeight = originalHeight;
            }

            public Metadata getMetadata() {
                return metadata;
            }

            public void setMetadata(Metadata metadata) {
                this.metadata = metadata;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public static class Metadata {
                /**
                 * platform : youtube
                 * video_id : 4BvwpjaGZCQ
                 */

                private String platform;
                @SerializedName("video_id")
                private String videoId;

                public String getPlatform() {
                    return platform;
                }

                public void setPlatform(String platform) {
                    this.platform = platform;
                }

                public String getVideoId() {
                    return videoId;
                }

                public void setVideoId(String videoId) {
                    this.videoId = videoId;
                }
            }
        }
    }
}
