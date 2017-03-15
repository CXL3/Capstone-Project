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

package com.crazyhitty.chdev.ks.predator.data.source.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.crazyhitty.chdev.ks.predator.data.PostsDataSource;
import com.crazyhitty.chdev.ks.predator.models.Comment;
import com.crazyhitty.chdev.ks.predator.models.InstallLink;
import com.crazyhitty.chdev.ks.predator.models.Media;
import com.crazyhitty.chdev.ks.predator.models.Post;
import com.crazyhitty.chdev.ks.predator.models.User;
import com.crazyhitty.chdev.ks.predator.utils.CoreUtils;
import com.crazyhitty.chdev.ks.predator.utils.DateUtils;
import com.crazyhitty.chdev.ks.predator.utils.Logger;
import com.crazyhitty.chdev.ks.producthunt_wrapper.models.PostCommentsData;
import com.crazyhitty.chdev.ks.producthunt_wrapper.models.PostDetailsData;
import com.crazyhitty.chdev.ks.producthunt_wrapper.models.PostsData;
import com.crazyhitty.chdev.ks.producthunt_wrapper.rest.ProductHuntRestApi;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Author:      Kartik Sharma
 * Email Id:    cr42yh17m4n@gmail.com
 * Created:     3/3/2017 11:54 AM
 * Description: Unavailable
 */

public class PostsRemoteDataSource implements PostsDataSource {
    private static final String TAG = "PostsRemoteDataSource";

    private static PostsRemoteDataSource INSTANCE;

    private PostsRemoteDataSource() {

    }

    public static PostsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PostsRemoteDataSource();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public Observable<List<Post>> getPosts(@Nullable String authorization, @NonNull String categoryName, @Nullable String day) {
        return ProductHuntRestApi.getApi()
                .getPostsCategoryWise(CoreUtils.getAuthToken(authorization), categoryName, day)
                .map(new Function<PostsData, List<Post>>() {
                    @Override
                    public List<Post> apply(PostsData postsData) throws Exception {
                        return convertPostsFromRemoteSource(postsData);
                    }
                });
    }

    @Override
    public Observable<Post> getPost(@Nullable String authorization, int postId) {
        return ProductHuntRestApi.getApi()
                .getPostDetails(CoreUtils.getAuthToken(authorization), postId)
                .map(new Function<PostDetailsData, Post>() {
                    @Override
                    public Post apply(PostDetailsData postDetailsData) throws Exception {
                        return convertPostDetailsFromRemoteSource(postDetailsData);
                    }
                });
    }

    @Override
    public void savePosts(@NonNull List<Post> posts) {
        Logger.d(TAG, "savePosts: operation unsupported");
        // Do nothing.
    }

    @Override
    public void deletePost(int postId) {
        Logger.d(TAG, "deletePost: operation unsupported");
        // Do nothing.
    }

    @Override
    public void deleteAllPosts() {
        Logger.d(TAG, "deleteAllPosts: operation unsupported");
        // Do nothing.
    }

    @Override
    public void saveUsers(@NonNull List<User> users) {
        Logger.d(TAG, "saveUsers: operation unsupported");
        // Do nothing.
    }

    @Override
    public void deleteUser(int userId) {
        Logger.d(TAG, "deleteUser: operation unsupported");
        // Do nothing.
    }

    @Override
    public void deleteAllUsers() {
        Logger.d(TAG, "deleteAllUsers: operation unsupported");
        // Do nothing.
    }

    @Override
    public void refresh() {
        // Do nothing.
    }

    private List<Post> convertPostsFromRemoteSource(PostsData postsData) {
        List<Post> posts = new ArrayList<>();
        for (PostsData.Posts remotePost : postsData.getPosts()) {
            Post post = new Post();

            post.setPostId(remotePost.getId());
            post.setCategoryId(remotePost.getCategoryId());
            post.setDay(remotePost.getDay());
            post.setName(remotePost.getName());
            post.setTagline(remotePost.getTagline());
            post.setCommentCount(remotePost.getCommentsCount());
            post.setCreatedAt(remotePost.getCreatedAt());
            post.setCreatedAtMillis(DateUtils.predatorDateToMillis(remotePost.getCreatedAt()));
            post.setDiscussionUrl(remotePost.getDiscussionUrl());
            post.setRedirectUrl(remotePost.getRedirectUrl());
            post.setVotesCount(remotePost.getVotesCount());
            post.setThumbnailImageUrl(remotePost.getThumbnail().getImageUrl());
            post.setThumbnailImageUrlOriginal(remotePost.getThumbnail().getOriginalImageUrl());
            post.setScreenshotUrl300px(remotePost.getScreenshotUrl().getValue300px());
            post.setScreenshotUrl850px(remotePost.getScreenshotUrl().getValue850px());
            post.setUsername(remotePost.getUser().getName());
            post.setUsernameAlternative(remotePost.getUser().getUsername());
            post.setUserId(remotePost.getUser().getId());
            post.setUserImageUrl100px(remotePost.getUser().getImageUrl().getValue100px());
            post.setUserImageUrlOriginal(remotePost.getUser().getImageUrl().getOriginal());
            post.setHunter(getPostHunterFromRemoteSource(remotePost));
            post.setMakers(getPostMakersFromRemoteSource(remotePost));

            posts.add(post);
        }
        return posts;
    }

    private Post convertPostDetailsFromRemoteSource(PostDetailsData postDetailsData) {
        Post postDetails = new Post();

        postDetails.setName(postDetailsData.getPost().getName());
        postDetails.setTagline(postDetailsData.getPost().getTagline());
        postDetails.setDay(postDetailsData.getPost().getDay());
        postDetails.setDate(postDetailsData.getPost().getCreatedAt());
        postDetails.setBackdropUrl(postDetailsData.getPost().getThumbnail().getImageUrl());
        postDetails.setRedirectUrl(postDetailsData.getPost().getRedirectUrl());
        postDetails.setDiscussionUrl(postDetailsData.getPost().getDiscussionUrl());
        postDetails.setCategoryId(postDetailsData.getPost().getCategoryId());
        postDetails.setVoteCount(postDetailsData.getPost().getVotesCount());

        List<Comment> comments = new ArrayList<Comment>();
        convertCommentsFromRemoteSource(postDetailsData.getPost().getComments(), comments);
        postDetails.setComments(comments);

        postDetails.setInstallLinks(convertInstallLinksFromRemoteSource(postDetailsData.getPost().getInstallLinks()));

        postDetails.setMedia(convertMediaFromRemoteSource(postDetailsData.getPost().getId(),
                postDetailsData.getPost().getMedia()));

        postDetails.setHunter(getPostHunterFromRemoteSource(postDetailsData.getPost().getId(),
                postDetailsData.getPost().getUser()));

        postDetails.setMakers(getPostMakersFromRemoteSource(postDetailsData.getPost().getId(),
                postDetailsData.getPost().getMakers()));

        postDetails.setVoters(getPostVotersFromRemoteSource(postDetailsData.getPost().getId(),
                postDetailsData.getPost().getVotes()));

        return postDetails;
    }

    private void convertCommentsFromRemoteSource(List<PostCommentsData.Comments> commentsSource, List<Comment> comments) {
        for (PostCommentsData.Comments commentSource : commentsSource) {
            Comment comment = new Comment();

            comment.setCommentId(commentSource.getId());
            comment.setBody(commentSource.getBody());
            comment.setCreatedAt(commentSource.getCreatedAt());
            comment.setCreatedAtMillis(DateUtils.predatorDateToMillis(commentSource.getCreatedAt()));
            comment.setParentCommentId(commentSource.getParentCommentIdInteger());
            comment.setPostId(commentSource.getPostId());
            comment.setUserId(commentSource.getUserId());
            comment.setUsername(commentSource.getUser().getUsername());
            comment.setUserHeadline(commentSource.getUser().getHeadline());
            comment.setUserImageThumbnailUrl(commentSource.getUser().getImageUrl().getValue100px());
            comment.setVotes(commentSource.getVotes());
            comment.setSticky(commentSource.isSticky());
            comment.setMaker(commentSource.isMaker());
            comment.setHunter(commentSource.isHunter());
            comment.setLiveGuest(commentSource.isLiveGuest());

            comments.add(comment);

            convertCommentsFromRemoteSource(commentSource.getChildComments(), comments);
        }
    }

    private List<InstallLink> convertInstallLinksFromRemoteSource(List<PostDetailsData.PostDetails.InstallLinks> installLinksSource) {
        List<InstallLink> installLinks = new ArrayList<>();

        for (PostDetailsData.PostDetails.InstallLinks installLinkSource : installLinksSource) {
            InstallLink installLink = new InstallLink();

            installLink.setInstallLinkId(installLinkSource.getId());
            installLink.setPostId(installLinkSource.getPostId());
            installLink.setCreatedAt(installLinkSource.getCreatedAt());
            installLink.setPrimaryLink(installLinkSource.isPrimaryLink());
            installLink.setRedirectUrl(installLinkSource.getRedirectUrl());
            installLink.setPlatform(installLinkSource.getPlatform());

            installLinks.add(installLink);
        }

        return installLinks;
    }

    private List<Media> convertMediaFromRemoteSource(int postId, List<PostDetailsData.PostDetails.Media> mediaSource) {
        List<Media> media = new ArrayList<>();

        for (PostDetailsData.PostDetails.Media mediaObjSource : mediaSource) {
            Media mediaObj = new Media();

            mediaObj.setMediaId(mediaObjSource.getId());
            mediaObj.setPostId(postId);
            mediaObj.setMediaType(mediaObjSource.getMediaType());
            mediaObj.setPlatform(mediaObjSource.getPlatform());
            mediaObj.setVideoId(mediaObjSource.getVideoId());
            mediaObj.setOriginalWidth(mediaObjSource.getOriginalWidth());
            mediaObj.setOriginalHeight(mediaObjSource.getOriginalHeight());
            mediaObj.setImageUrl(mediaObjSource.getImageUrl());

            media.add(mediaObj);
        }

        return media;
    }

    private User getPostHunterFromRemoteSource(int postId, PostsData.Posts.User user) {
        User hunter = new User();
        hunter.setUserId(user.getId());
        hunter.setName(user.getName());
        hunter.setUsername(user.getUsername());
        hunter.setHeadline(user.getHeadline());
        hunter.setWebsiteUrl(user.getWebsiteUrl());
        hunter.setImage(user.getImageUrl().getValue100px());
        hunter.setAssociatedPostId(postId);
        hunter.setType(User.TYPE.HUNTER);
        return hunter;
    }

    private List<User> getPostMakersFromRemoteSource(int postId, List<PostsData.Posts.Makers> makersSource) {
        List<User> makers = new ArrayList<>();

        for (PostsData.Posts.Makers makerSource : makersSource) {
            User maker = new User();
            maker.setUserId(makerSource.getId());
            maker.setName(makerSource.getName());
            maker.setUsername(makerSource.getUsername());
            maker.setHeadline(makerSource.getHeadline());
            maker.setWebsiteUrl(makerSource.getWebsiteUrl());
            maker.setImage(makerSource.getImageUrlMaker().getValue48px());
            maker.setAssociatedPostId(postId);
            maker.setType(User.TYPE.MAKER);
            makers.add(maker);
        }

        return makers;
    }

    private List<User> getPostVotersFromRemoteSource(int postId, List<PostDetailsData.PostDetails.Votes> votesSource) {
        List<User> voters = new ArrayList<>();

        for (PostDetailsData.PostDetails.Votes voteSource : votesSource) {
            User voter = new User();
            voter.setUserId(voteSource.getUser().getId());
            voter.setName(voteSource.getUser().getName());
            voter.setUsername(voteSource.getUser().getUsername());
            voter.setHeadline(voteSource.getUser().getHeadline());
            voter.setWebsiteUrl(voteSource.getUser().getWebsiteUrl());
            voter.setImage(voteSource.getUser().getImageUrl().getValue100px());
            voter.setAssociatedPostId(postId);
            voter.setType(User.TYPE.UPVOTER);
            voters.add(voter);
        }

        return voters;
    }

    private User getPostHunterFromRemoteSource(PostsData.Posts postSource) {
        User hunter = new User();
        hunter.setUserId(postSource.getUser().getId());
        hunter.setName(postSource.getUser().getName());
        hunter.setUsername(postSource.getUser().getUsername());
        hunter.setHeadline(postSource.getUser().getHeadline());
        hunter.setWebsiteUrl(postSource.getUser().getWebsiteUrl());
        hunter.setImage(postSource.getUser().getImageUrl().getValue100px());
        hunter.setAssociatedPostId(postSource.getId());
        hunter.setType(User.TYPE.HUNTER);
        return hunter;
    }

    private List<User> getPostMakersFromRemoteSource(PostsData.Posts postSource) {
        List<User> users = new ArrayList<>();

        for (final PostsData.Posts.Makers makerSource : postSource.getMakers()) {
            User maker = new User();
            maker.setUserId(makerSource.getId());
            maker.setName(makerSource.getName());
            maker.setUsername(makerSource.getUsername());
            maker.setHeadline(makerSource.getHeadline());
            maker.setWebsiteUrl(makerSource.getWebsiteUrl());
            maker.setImage(makerSource.getImageUrlMaker().getValue48px());
            maker.setAssociatedPostId(postSource.getId());
            maker.setType(User.TYPE.MAKER);
            users.add(maker);
        }

        return users;
    }
}
