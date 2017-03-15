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

package com.crazyhitty.chdev.ks.predator.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.crazyhitty.chdev.ks.predator.models.Post;
import com.crazyhitty.chdev.ks.predator.models.User;

import java.util.List;

import io.reactivex.Observable;

import static com.crazyhitty.chdev.ks.predator.utils.CoreUtils.checkNotNull;

/**
 * Author:      Kartik Sharma
 * Email Id:    cr42yh17m4n@gmail.com
 * Created:     3/2/2017 4:16 PM
 * Description: Unavailable
 */

public class PostsRepository implements PostsDataSource {
    @Nullable
    private static PostsRepository INSTANCE = null;
    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    @VisibleForTesting
    boolean mCacheIsDirty = false;
    private PostsDataSource mPostsLocalDataSource;
    private PostsDataSource mPostsRemoteDataSource;

    private PostsRepository(@NonNull PostsDataSource postsLocalDataSource,
                            @NonNull PostsDataSource postsRemoteDataSource) {
        mPostsLocalDataSource = checkNotNull(postsLocalDataSource);
        mPostsRemoteDataSource = checkNotNull(postsRemoteDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @return the {@link PostsRepository} instance
     */
    public static PostsRepository getInstance(@NonNull PostsDataSource postsLocalDataSource,
                                              @NonNull PostsDataSource postsRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new PostsRepository(postsLocalDataSource, postsRemoteDataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(PostsDataSource, PostsDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public Observable<List<Post>> getPosts(@Nullable String authorization, @NonNull String categoryName, @Nullable String day) {
        if (mCacheIsDirty) {
            return mPostsRemoteDataSource.getPosts(authorization, categoryName, day);
        } else {
            return mPostsLocalDataSource.getPosts(authorization, categoryName, day);
        }
    }

    @Override
    public Observable<Post> getPost(@Nullable String authorization, int postId) {
        if (mCacheIsDirty) {
            return mPostsRemoteDataSource.getPost(authorization, postId);
        } else {
            return mPostsLocalDataSource.getPost(authorization, postId);
        }
    }

    @Override
    public void savePosts(@NonNull List<Post> posts) {
        mPostsLocalDataSource.savePosts(posts);
    }

    @Override
    public void deletePost(int postId) {
        mPostsLocalDataSource.deletePost(postId);
    }

    @Override
    public void deleteAllPosts() {
        mPostsLocalDataSource.deleteAllPosts();
    }

    @Override
    public void saveUsers(@NonNull List<User> users) {
        mPostsLocalDataSource.saveUsers(users);
    }

    @Override
    public void deleteUser(int userId) {
        mPostsLocalDataSource.deleteUser(userId);
    }

    @Override
    public void deleteAllUsers() {
        mPostsLocalDataSource.deleteAllUsers();
    }

    @Override
    public void refresh() {
        mCacheIsDirty = true;
    }
}
