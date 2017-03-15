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

package com.crazyhitty.chdev.ks.predator.core.posts;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.crazyhitty.chdev.ks.predator.R;
import com.crazyhitty.chdev.ks.predator.data.Constants;
import com.crazyhitty.chdev.ks.predator.data.PostsDataSource;
import com.crazyhitty.chdev.ks.predator.data.source.local.PredatorContract;
import com.crazyhitty.chdev.ks.predator.models.Post;
import com.crazyhitty.chdev.ks.predator.ui.widget.PredatorPostsWidgetProvider;
import com.crazyhitty.chdev.ks.predator.utils.DateUtils;
import com.crazyhitty.chdev.ks.predator.utils.Logger;
import com.crazyhitty.chdev.ks.producthunt_wrapper.models.PostsData;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.crazyhitty.chdev.ks.predator.utils.CoreUtils.checkNotNull;


/**
 * Author:      Kartik Sharma
 * Email Id:    cr42yh17m4n@gmail.com
 * Created:     1/2/2017 10:13 PM
 * Description: Unavailable
 */

public class PostsPresenter implements PostsContract.Presenter {
    private static final String TAG = "PostsPresenter";

    private boolean mLoadMore = false;

    private HashMap<Integer, String> mDateHashMap = new HashMap<>();
    private String mLastDate = DateUtils.getPredatorCurrentDate();

    @NonNull
    private PostsContract.View mView;

    @NonNull
    private PostsDataSource mPostsDataSource;

    private CompositeDisposable mCompositeDisposable;

    public PostsPresenter(@NonNull PostsContract.View view, @NonNull PostsDataSource postsDataSource) {
        mView = checkNotNull(view, "PostsContract.View cannot be null");
        mPostsDataSource = checkNotNull(postsDataSource, "PostsDataSource cannot be null");
        mView.setPresenter(this);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        mCompositeDisposable.clear();
    }

    /*@Override
    public void getOfflinePosts() {
        Observable<List<Post>> postsDataObservable = mPostsDataSource.getPosts(null,
                Constants.Posts.CATEGORY_ALL,
                null)
                *//*.doOnNext(new Consumer<List<Post>>() {
                    @Override
                    public void accept(List<Post> posts) throws Exception {
                        dateMatcher(posts);
                    }
                })*//*
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        mCompositeDisposable.add(postsDataObservable.subscribeWith(new DisposableObserver<List<Post>>() {
            @Override
            public void onComplete() {
                // Done
            }

            @Override
            public void onError(Throwable e) {
                Logger.e(TAG, "onError: " + e.getMessage(), e);
                mView.unableToGetPosts(false, true, e.getMessage());
            }

            @Override
            public void onNext(List<Post> posts) {
                mView.showPosts(posts, mDateHashMap);
            }
        }));
    }

    @Override
    public void getPosts(final String token,
                         final boolean clearPrevious) {
        Logger.d(TAG, "getPosts: called");
        if (clearPrevious) {
            mLoadMore = false;
            mLastDate = DateUtils.getPredatorCurrentDate();
            mDateHashMap = new HashMap<>();
        }
        mPostsDataSource.refresh();
        Observable<List<Post>> postsDataObservable = mPostsDataSource.getPosts(token,
                Constants.Posts.CATEGORY_ALL,
                mLastDate)
                *//*.doOnNext(new Consumer<List<Post>>() {
                    @Override
                    public void accept(List<Post> posts) throws Exception {
                        dateMatcher(posts);
                    }
                })*//*
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        *//*Observable<List<Post>> postsDataObservable = ProductHuntRestApi.getApi()
                .getPostsCategoryWise(CoreUtils.getAuthToken(token), Constants.Posts.CATEGORY_ALL, mLastDate)
                .map(new Function<PostsData, List<Post>>() {
                    @Override
                    public List<Post> apply(PostsData postsData) throws Exception {
                        if (clearPrevious) {
                            MainApplication.getContentResolverInstance()
                                    .delete(PredatorContract.PostsEntry.CONTENT_URI_POSTS_DELETE,
                                            null,
                                            null);
                            MainApplication.getContentResolverInstance()
                                    .delete(PredatorContract.UsersEntry.CONTENT_URI_USERS_DELETE,
                                            null,
                                            null);
                            MainApplication.getContentResolverInstance()
                                    .delete(PredatorContract.CommentsEntry.CONTENT_URI_COMMENTS_DELETE,
                                            null,
                                            null);
                            MainApplication.getContentResolverInstance()
                                    .delete(PredatorContract.MediaEntry.CONTENT_URI_MEDIA_DELETE,
                                            null,
                                            null);
                        }

                        if (postsData.getPosts() == null || postsData.getPosts().isEmpty()) {
                            return new ArrayList<Post>();
                        }

                        for (PostsData.Posts post : postsData.getPosts()) {
                            Logger.d(TAG, "post: " + post.getName());
                            MainApplication.getContentResolverInstance()
                                    .insert(PredatorContract.PostsEntry.CONTENT_URI_POSTS_ADD,
                                            getContentValuesForPosts(post));

                            // Add/update users.
                            MainApplication.getContentResolverInstance()
                                    .insert(PredatorContract.UsersEntry.CONTENT_URI_USERS_ADD,
                                            getContentValuesForHunterUser(post.getId(), post.getUser()));
                            for (PostsData.Posts.Makers maker : post.getMakers()) {
                                MainApplication.getContentResolverInstance()
                                        .insert(PredatorContract.UsersEntry.CONTENT_URI_USERS_ADD,
                                                getContentValuesForMakerUser(post.getId(), maker));
                            }
                        }

                        Cursor cursor = MainApplication.getContentResolverInstance()
                                .query(PredatorContract.PostsEntry.CONTENT_URI_POSTS,
                                        null,
                                        PredatorContract.PostsEntry.COLUMN_FOR_DASHBOARD + "=1",
                                        null,
                                        PredatorContract.PostsEntry.COLUMN_CREATED_AT_MILLIS + " DESC");

                        List<Post> posts = new ArrayList<Post>();
                        if (cursor != null && cursor.getCount() != 0) {
                            dateMatcher(cursor);
                            posts = getPostsFromCursor(cursor);
                            cursor.close();
                        }
                        Logger.d(TAG, "apply: posts: " + posts);
                        return posts;
                    }
                })
                .flatMap(new Function<List<Post>, ObservableSource<List<Post>>>() {
                    @Override
                    public ObservableSource<List<Post>> apply(final List<Post> posts) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<List<Post>>() {
                            @Override
                            public void subscribe(ObservableEmitter<List<Post>> emitter) throws Exception {
                                if (posts != null && posts.size() != 0) {
                                    emitter.onNext(posts);
                                } else {
                                    loadMorePosts(token);
                                }
                                emitter.onComplete();
                            }
                        });
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());*//*

        mCompositeDisposable.add(postsDataObservable.subscribeWith(new DisposableObserver<List<Post>>() {
            @Override
            public void onComplete() {
                // Done
            }

            @Override
            public void onError(Throwable e) {
                Logger.e(TAG, "onError: " + e.getMessage(), e);
                mView.unableToGetPosts(mLoadMore, false, e.getMessage());
            }

            @Override
            public void onNext(List<Post> posts) {
                mView.showPosts(posts, mDateHashMap);
            }
        }));
    }*/

    @Override
    public void loadPosts(@Nullable String token, final boolean forceUpdate) {
        Observable<List<Post>> postsDataObservable;

        if (forceUpdate) {
            // Online
            mPostsDataSource.refresh();
            postsDataObservable = mPostsDataSource.getPosts(token,
                    Constants.Posts.CATEGORY_ALL,
                    mLastDate)
                    .doOnNext(new Consumer<List<Post>>() {
                        @Override
                        public void accept(List<Post> posts) throws Exception {
                            dateMatcher(posts);
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            // Offline
            postsDataObservable = mPostsDataSource.getPosts(null,
                    Constants.Posts.CATEGORY_ALL,
                    null)
                    .doOnNext(new Consumer<List<Post>>() {
                        @Override
                        public void accept(List<Post> posts) throws Exception {
                            dateMatcher(posts);
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
        mCompositeDisposable.add(postsDataObservable.subscribeWith(new DisposableObserver<List<Post>>() {
            @Override
            public void onComplete() {
                // Done
            }

            @Override
            public void onError(Throwable e) {
                Logger.e(TAG, "mLoadMore: " + mLoadMore + ", forceUpdate: " + forceUpdate + ", onError: " + e.getMessage(), e);
                if (mLoadMore) {
                    // Load more
                    mView.unableToLoadMorePosts(e.getMessage());
                } else if (forceUpdate) {
                    // Online
                    mView.unableToLoadOnlinePosts(e.getMessage());
                } else {
                    // Offline
                    mView.unableToLoadOfflinePosts(e.getMessage());
                }
            }

            @Override
            public void onNext(List<Post> posts) {
                mView.stopLoading();
                if (mLoadMore) {
                    // Load more
                    if (posts != null && posts.size() != 0) {
                        mView.showMorePosts(posts, mDateHashMap);
                    } else {
                        mView.unableToLoadMorePosts("Unable to load more posts");
                    }
                } else if (forceUpdate) {
                    // Online
                    if (posts != null && posts.size() != 0) {
                        mView.showOnlinePosts(posts, mDateHashMap);
                    } else {
                        mView.unableToLoadOnlinePosts("No online posts available");
                    }
                } else {
                    // Offline
                    if (posts != null && posts.size() != 0) {
                        mView.showOfflinePosts(posts, mDateHashMap);
                    } else {
                        mView.unableToLoadOfflinePosts("No offline posts available");
                    }
                }
            }
        }));
    }

    @Override
    public void loadMorePosts(@NonNull String token) {
        mLastDate = DateUtils.getPredatorPostPreviousDate(mLastDate);
        mLoadMore = true;
        loadPosts(token, true);
    }

    @Override
    public void updateWidgets(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int appWidgetIds[] = appWidgetManager.getAppWidgetIds(
                new ComponentName(context, PredatorPostsWidgetProvider.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.list_view_posts);
    }

    private ContentValues getContentValuesForPosts(PostsData.Posts post) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PredatorContract.PostsEntry.COLUMN_POST_ID, post.getId());
        contentValues.put(PredatorContract.PostsEntry.COLUMN_CATEGORY_ID, post.getCategoryId());
        contentValues.put(PredatorContract.PostsEntry.COLUMN_DAY, post.getDay());
        contentValues.put(PredatorContract.PostsEntry.COLUMN_NAME, post.getName());
        contentValues.put(PredatorContract.PostsEntry.COLUMN_TAGLINE, post.getTagline());
        contentValues.put(PredatorContract.PostsEntry.COLUMN_COMMENT_COUNT, post.getCommentsCount());
        contentValues.put(PredatorContract.PostsEntry.COLUMN_CREATED_AT, post.getCreatedAt());
        contentValues.put(PredatorContract.PostsEntry.COLUMN_CREATED_AT_MILLIS, DateUtils.predatorDateToMillis(post.getCreatedAt()));
        contentValues.put(PredatorContract.PostsEntry.COLUMN_DISCUSSION_URL, post.getDiscussionUrl());
        contentValues.put(PredatorContract.PostsEntry.COLUMN_REDIRECT_URL, post.getRedirectUrl());
        contentValues.put(PredatorContract.PostsEntry.COLUMN_VOTES_COUNT, post.getVotesCount());
        contentValues.put(PredatorContract.PostsEntry.COLUMN_THUMBNAIL_IMAGE_URL, post.getThumbnail().getImageUrl());
        contentValues.put(PredatorContract.PostsEntry.COLUMN_THUMBNAIL_IMAGE_URL_ORIGINAL, post.getThumbnail().getOriginalImageUrl());
        contentValues.put(PredatorContract.PostsEntry.COLUMN_SCREENSHOT_URL_300PX, post.getScreenshotUrl().getValue300px());
        contentValues.put(PredatorContract.PostsEntry.COLUMN_SCREENSHOT_URL_850PX, post.getScreenshotUrl().getValue850px());
        contentValues.put(PredatorContract.PostsEntry.COLUMN_USER_NAME, post.getUser().getName());
        contentValues.put(PredatorContract.PostsEntry.COLUMN_USER_USERNAME, post.getUser().getUsername());
        contentValues.put(PredatorContract.PostsEntry.COLUMN_USER_ID, post.getUser().getId());
        contentValues.put(PredatorContract.PostsEntry.COLUMN_USER_IMAGE_URL_100PX, post.getUser().getImageUrl().getValue100px());
        contentValues.put(PredatorContract.PostsEntry.COLUMN_USER_IMAGE_URL_ORIGINAL, post.getUser().getImageUrl().getOriginal());
        contentValues.put(PredatorContract.PostsEntry.COLUMN_FOR_DASHBOARD, 1);
        return contentValues;
    }

    private ContentValues getContentValuesForHunterUser(int postId, PostsData.Posts.User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PredatorContract.UsersEntry.COLUMN_USER_ID, user.getId());
        contentValues.put(PredatorContract.UsersEntry.COLUMN_NAME, user.getName());
        contentValues.put(PredatorContract.UsersEntry.COLUMN_USERNAME, user.getUsername());
        contentValues.put(PredatorContract.UsersEntry.COLUMN_HEADLINE, user.getHeadline());
        contentValues.put(PredatorContract.UsersEntry.COLUMN_WEBSITE_URL, user.getWebsiteUrl());
        contentValues.put(PredatorContract.UsersEntry.COLUMN_IMAGE_URL_100PX, user.getImageUrl().getValue100px());
        contentValues.put(PredatorContract.UsersEntry.COLUMN_IMAGE_URL_ORIGINAL, user.getImageUrl().getOriginal());
        contentValues.put(PredatorContract.UsersEntry.COLUMN_HUNTER_POST_IDS, postId);
        return contentValues;
    }

    private ContentValues getContentValuesForMakerUser(int postId, PostsData.Posts.Makers maker) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PredatorContract.UsersEntry.COLUMN_USER_ID, maker.getId());
        contentValues.put(PredatorContract.UsersEntry.COLUMN_CREATED_AT, maker.getCreatedAt());
        contentValues.put(PredatorContract.UsersEntry.COLUMN_NAME, maker.getName());
        contentValues.put(PredatorContract.UsersEntry.COLUMN_USERNAME, maker.getUsername());
        contentValues.put(PredatorContract.UsersEntry.COLUMN_HEADLINE, maker.getHeadline());
        contentValues.put(PredatorContract.UsersEntry.COLUMN_WEBSITE_URL, maker.getWebsiteUrl());
        contentValues.put(PredatorContract.UsersEntry.COLUMN_IMAGE_URL_100PX, maker.getImageUrlMaker().getValue48px());
        contentValues.put(PredatorContract.UsersEntry.COLUMN_IMAGE_URL_ORIGINAL, maker.getImageUrlMaker().getOriginal());
        contentValues.put(PredatorContract.UsersEntry.COLUMN_MAKER_POST_IDS, postId);
        return contentValues;
    }

    /**
     * Matches all the post publish dates and create a hashmap for positions and dates wherever the
     * dates are changed in the cursor.
     *
     * @param posts List of multiple posts
     */
    private void dateMatcher(List<Post> posts) {
        for (int i = 0; i < posts.size(); i++) {
            // Match post date with current date
            String date = posts.get(i).getDay();

            String dateToBeShown = DateUtils.getPredatorPostDate(date);

            if (!mDateHashMap.containsValue(dateToBeShown)) {
                mDateHashMap.put(i, dateToBeShown);
                mLastDate = date;
            }
        }
    }
}
