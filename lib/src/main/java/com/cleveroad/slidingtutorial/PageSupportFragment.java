/*
 *   The MIT License (MIT)
 *
 *   Copyright (c) 2016 Cleveroad
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 */
package com.cleveroad.slidingtutorial;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Single page for {@link TutorialSupportFragment}.
 */
public abstract class PageSupportFragment extends Fragment {

    private PageImpl mPage;
    private PageImpl.InternalFragment mInternalFragment = new PageImpl.InternalFragment() {
        @Override
        public int getLayoutResId() {
            return PageSupportFragment.this.getLayoutResId();
        }

        @Override
        public TransformItem[] getTransformItems() {
            return PageSupportFragment.this.getTransformItems();
        }

        @Override
        public Bundle getArguments() {
            return PageSupportFragment.this.getArguments();
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = new PageImpl(mInternalFragment);
    }

    @SuppressWarnings("ConstantConditions")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return mPage.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * Method that apply a custom transformation to the page views
     *
     * @param pageWidth pageWidth
     * @param position  Position of page relative to the current front-and-center
     *                  position of the pager. 0 is front and center. 1 is one full
     *                  page position to the right, and -1 is one page position to the left.
     */
    final void transformPage(int pageWidth, float position) {
        mPage.transformPage(pageWidth, position);
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    @NonNull
    protected abstract TransformItem[] getTransformItems();
}

