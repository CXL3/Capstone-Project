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

package com.crazyhitty.chdev.ks.predator.data.source.local;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.crazyhitty.chdev.ks.predator.data.PostsDataSource;
import com.crazyhitty.chdev.ks.predator.models.Post;
import com.crazyhitty.chdev.ks.predator.models.User;
import com.crazyhitty.chdev.ks.predator.utils.CursorUtils;
import com.crazyhitty.chdev.ks.predator.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

import static com.crazyhitty.chdev.ks.predator.utils.CoreUtils.checkNotNull;

/**
 * Author:      Kartik Sharma
 * Email Id:    cr42yh17m4n@gmail.com
 * Created:     3/3/2017 11:54 AM
 * Description: Unavailable
 */

public class PostsLocalDataSource implements PostsDataSource {
    @Nullable
    private static PostsLocalDataSource INSTANCE;

    private ContentResolver mContentResolver;

    private PostsLocalDataSource(Context context) {
        mContentResolver = checkNotNull(context.getContentResolver(), "Context cannot be null");
    }

    public static PostsLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new PostsLocalDataSource(context);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public Observable<List<Post>> getPosts(@Nullable String authorization, @NonNull String categoryName, @Nullable String day) {
        return Observable.create(new ObservableOnSubscribe<List<Post>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Post>> emitter) throws Exception {
                Cursor cursor = mContentResolver.query(PredatorContract.PostsEntry.CONTENT_URI_POSTS,
                        null,
                        PredatorContract.PostsEntry.COLUMN_FOR_DASHBOARD + "=1",
                        null,
                        PredatorContract.PostsEntry.COLUMN_CREATED_AT_MILLIS + " DESC");
                if (cursor != null && cursor.getCount() != 0) {
                    emitter.onNext(getPostsFromCursor(cursor));
                    cursor.close();
                } else {
                    emitter.onNext(new ArrayList<Post>());
                }
                emitter.onComplete();
            }
        });
    }

    @Override
    public Observable<Post> getPost(@Nullable String authorization, int postId) {
        return null;
    }

    @Override
    public void savePosts(@NonNull List<Post> posts) {
        mContentResolver.bulkInsert(PredatorContract.PostsEntry.CONTENT_URI_POSTS_ADD,
                getContentValuesForPosts(posts));
    }

    @Override
    public void deletePost(int postId) {
        mContentResolver.delete(PredatorContract.PostsEntry.CONTENT_URI_POSTS_DELETE,
                PredatorContract.PostsEntry.COLUMN_POST_ID + "=" + postId,
                null);
    }

    @Override
    public void deleteAllPosts() {
        mContentResolver.delete(PredatorContract.PostsEntry.CONTENT_URI_POSTS_DELETE,
                null,
                null);
    }

    @Override
    public void saveUsers(@NonNull List<User> users) {
        mContentResolver.bulkInsert(PredatorContract.UsersEntry.CONTENT_URI_USERS_ADD,
                getContentValuesForPostUsers(users));
    }

    @Override
    public void deleteUser(int userId) {
        mContentResolver.delete(PredatorContract.UsersEntry.CONTENT_URI_USERS_DELETE,
                PredatorContract.UsersEntry.COLUMN_USER_ID + "=" + userId,
                null);
    }

    @Override
    public void deleteAllUsers() {
        mContentResolver.delete(PredatorContract.UsersEntry.CONTENT_URI_USERS_DELETE,
                null,
                null);
    }

    @Override
    public void refresh() {
        // Do nothing.
    }

    private List<Post> getPostsFromCursor(Cursor cursor) {
        List<Post> posts = new ArrayList<>();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            Post post = new Post();
            post.setId(CursorUtils.getInt(cursor, PredatorContract.PostsEntry.COLUMN_ID));
            post.setPostId(CursorUtils.getInt(cursor, PredatorContract.PostsEntry.COLUMN_POST_ID));
            post.setCategoryId(CursorUtils.getInt(cursor, PredatorContract.PostsEntry.COLUMN_CATEGORY_ID));
            post.setDay(CursorUtils.getString(cursor, PredatorContract.PostsEntry.COLUMN_DAY));
            post.setName(CursorUtils.getString(cursor, PredatorContract.PostsEntry.COLUMN_NAME));
            post.setTagline(CursorUtils.getString(cursor, PredatorContract.PostsEntry.COLUMN_TAGLINE));
            post.setCommentCount(CursorUtils.getInt(cursor, PredatorContract.PostsEntry.COLUMN_COMMENT_COUNT));
            post.setCreatedAt(CursorUtils.getString(cursor, PredatorContract.PostsEntry.COLUMN_CREATED_AT));
            post.setCreatedAtMillis(CursorUtils.getInt(cursor, PredatorContract.PostsEntry.COLUMN_CREATED_AT_MILLIS));
            post.setDiscussionUrl(CursorUtils.getString(cursor, PredatorContract.PostsEntry.COLUMN_DISCUSSION_URL));
            post.setRedirectUrl(CursorUtils.getString(cursor, PredatorContract.PostsEntry.COLUMN_REDIRECT_URL));
            post.setVotesCount(CursorUtils.getInt(cursor, PredatorContract.PostsEntry.COLUMN_VOTES_COUNT));
            post.setThumbnailImageUrl(CursorUtils.getString(cursor, PredatorContract.PostsEntry.COLUMN_THUMBNAIL_IMAGE_URL));
            post.setThumbnailImageUrlOriginal(CursorUtils.getString(cursor, PredatorContract.PostsEntry.COLUMN_THUMBNAIL_IMAGE_URL_ORIGINAL));
            post.setScreenshotUrl300px(CursorUtils.getString(cursor, PredatorContract.PostsEntry.COLUMN_SCREENSHOT_URL_300PX));
            post.setScreenshotUrl850px(CursorUtils.getString(cursor, PredatorContract.PostsEntry.COLUMN_SCREENSHOT_URL_850PX));
            post.setUsername(CursorUtils.getString(cursor, PredatorContract.PostsEntry.COLUMN_USER_NAME));
            post.setUsernameAlternative(CursorUtils.getString(cursor, PredatorContract.PostsEntry.COLUMN_USER_USERNAME));
            post.setUserId(CursorUtils.getInt(cursor, PredatorContract.PostsEntry.COLUMN_USER_ID));
            post.setUserImageUrl100px(CursorUtils.getString(cursor, PredatorContract.PostsEntry.COLUMN_USER_IMAGE_URL_100PX));
            post.setUserImageUrlOriginal(CursorUtils.getString(cursor, PredatorContract.PostsEntry.COLUMN_USER_IMAGE_URL_ORIGINAL));
            posts.add(post);
        }
        return posts;
    }

    private ContentValues[] getContentValuesForPosts(List<Post> posts) {
        ContentValues[] contentValuesArr = new ContentValues[posts.size()];

        for (int i = 0; i < posts.size(); i++) {
            Post post = posts.get(i);

            ContentValues contentValues = new ContentValues();
            contentValues.put(PredatorContract.PostsEntry.COLUMN_POST_ID, post.getId());
            contentValues.put(PredatorContract.PostsEntry.COLUMN_CATEGORY_ID, post.getCategoryId());
            contentValues.put(PredatorContract.PostsEntry.COLUMN_DAY, post.getDay());
            contentValues.put(PredatorContract.PostsEntry.COLUMN_NAME, post.getName());
            contentValues.put(PredatorContract.PostsEntry.COLUMN_TAGLINE, post.getTagline());
            contentValues.put(PredatorContract.PostsEntry.COLUMN_COMMENT_COUNT, post.getCommentCount());
            contentValues.put(PredatorContract.PostsEntry.COLUMN_CREATED_AT, post.getCreatedAt());
            contentValues.put(PredatorContract.PostsEntry.COLUMN_CREATED_AT_MILLIS, DateUtils.predatorDateToMillis(post.getCreatedAt()));
            contentValues.put(PredatorContract.PostsEntry.COLUMN_DISCUSSION_URL, post.getDiscussionUrl());
            contentValues.put(PredatorContract.PostsEntry.COLUMN_REDIRECT_URL, post.getRedirectUrl());
            contentValues.put(PredatorContract.PostsEntry.COLUMN_VOTES_COUNT, post.getVotesCount());
            contentValues.put(PredatorContract.PostsEntry.COLUMN_THUMBNAIL_IMAGE_URL, post.getThumbnailImageUrl());
            contentValues.put(PredatorContract.PostsEntry.COLUMN_THUMBNAIL_IMAGE_URL_ORIGINAL, post.getThumbnailImageUrlOriginal());
            contentValues.put(PredatorContract.PostsEntry.COLUMN_SCREENSHOT_URL_300PX, post.getScreenshotUrl300px());
            contentValues.put(PredatorContract.PostsEntry.COLUMN_SCREENSHOT_URL_850PX, post.getScreenshotUrl850px());
            contentValues.put(PredatorContract.PostsEntry.COLUMN_USER_NAME, post.getUsername());
            contentValues.put(PredatorContract.PostsEntry.COLUMN_USER_USERNAME, post.getUsernameAlternative());
            contentValues.put(PredatorContract.PostsEntry.COLUMN_USER_ID, post.getUserId());
            contentValues.put(PredatorContract.PostsEntry.COLUMN_USER_IMAGE_URL_100PX, post.getUserImageUrl100px());
            contentValues.put(PredatorContract.PostsEntry.COLUMN_USER_IMAGE_URL_ORIGINAL, post.getUserImageUrlOriginal());
            contentValues.put(PredatorContract.PostsEntry.COLUMN_FOR_DASHBOARD, 1);

            contentValuesArr[i] = contentValues;
        }

        return contentValuesArr;
    }

    private ContentValues[] getContentValuesForPostUsers(List<User> users) {
        ContentValues[] contentValuesArr = new ContentValues[users.size()];

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);

            ContentValues contentValues = new ContentValues();
            contentValues.put(PredatorContract.UsersEntry.COLUMN_USER_ID, user.getId());
            contentValues.put(PredatorContract.UsersEntry.COLUMN_CREATED_AT, user.getId());
            contentValues.put(PredatorContract.UsersEntry.COLUMN_NAME, user.getId());
            contentValues.put(PredatorContract.UsersEntry.COLUMN_USERNAME, user.getId());
            contentValues.put(PredatorContract.UsersEntry.COLUMN_HEADLINE, user.getId());
            contentValues.put(PredatorContract.UsersEntry.COLUMN_WEBSITE_URL, user.getId());
            contentValues.put(PredatorContract.UsersEntry.COLUMN_IMAGE_URL_100PX, user.getId());
            contentValues.put(PredatorContract.UsersEntry.COLUMN_IMAGE_URL_ORIGINAL, user.getId());
            contentValues.put(PredatorContract.UsersEntry.COLUMN_MAKER_POST_IDS, user.getId());
            contentValues.put(PredatorContract.UsersEntry.COLUMN_HUNTER_POST_IDS, user.getId());

            contentValuesArr[i] = contentValues;
        }

        return contentValuesArr;
    }
}
