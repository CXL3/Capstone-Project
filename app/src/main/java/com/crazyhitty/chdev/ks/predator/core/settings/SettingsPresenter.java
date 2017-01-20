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

package com.crazyhitty.chdev.ks.predator.core.settings;

import android.support.annotation.NonNull;

import com.crazyhitty.chdev.ks.predator.MainApplication;
import com.crazyhitty.chdev.ks.predator.data.PredatorContract;
import com.crazyhitty.chdev.ks.predator.utils.Logger;
import com.facebook.drawee.backends.pipeline.Fresco;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Author:      Kartik Sharma
 * Email Id:    cr42yh17m4n@gmail.com
 * Created:     1/16/2017 9:53 AM
 * Description: Unavailable
 */

public class SettingsPresenter implements SettingsContract.Presenter {
    private static final String TAG = "SettingsPresenter";

    @NonNull
    private SettingsContract.View mView;

    private CompositeSubscription mCompositeSubscription;

    public SettingsPresenter(@NonNull SettingsContract.View view) {
        this.mView = view;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void clearCache() {
        Observable<Boolean> clearCacheObservable = Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                // Delete all values from all tables.
                // Delete all values from posts_table.
                MainApplication.getContentResolverInstance()
                        .delete(PredatorContract.PostsEntry.CONTENT_URI_POSTS_DELETE,
                                null,
                                null);
                // Delete all values from users_table.
                MainApplication.getContentResolverInstance()
                        .delete(PredatorContract.UsersEntry.CONTENT_URI_USERS_DELETE,
                                null,
                                null);
                // Delete all values from comments_table.
                MainApplication.getContentResolverInstance()
                        .delete(PredatorContract.CommentsEntry.CONTENT_URI_COMMENTS_DELETE,
                                null,
                                null);
                // Delete all values from install_links_table.
                MainApplication.getContentResolverInstance()
                        .delete(PredatorContract.InstallLinksEntry.CONTENT_URI_INSTALL_LINKS_DELETE,
                                null,
                                null);
                // Delete all values from media_table.
                MainApplication.getContentResolverInstance()
                        .delete(PredatorContract.MediaEntry.CONTENT_URI_MEDIA_DELETE,
                                null,
                                null);
                // Delete all values from collections_table.
                MainApplication.getContentResolverInstance()
                        .delete(PredatorContract.CollectionsEntry.CONTENT_URI_COLLECTIONS_DELETE,
                                null,
                                null);

                // Clear fresco cache.
                Fresco.getImagePipeline().clearCaches();

                subscriber.onNext(true);
                subscriber.onCompleted();
            }
        });

        clearCacheObservable.subscribeOn(Schedulers.io());
        clearCacheObservable.observeOn(AndroidSchedulers.mainThread());

        mCompositeSubscription.add(clearCacheObservable.subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {
                // Done
            }

            @Override
            public void onError(Throwable e) {
                Logger.e(TAG, "onError: " + e.getMessage(), e);
                mView.unableToWipeCache();
            }

            @Override
            public void onNext(Boolean aBoolean) {
                Logger.d(TAG, "onNext: cache cleared");
                mView.cacheCleared();
            }
        }));
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.clear();
        }
    }
}
