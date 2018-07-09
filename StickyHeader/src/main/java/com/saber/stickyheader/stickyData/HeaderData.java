package com.saber.stickyheader.stickyData;

import android.support.annotation.LayoutRes;

public interface HeaderData extends StickyMainData {
    @LayoutRes
    int getHeaderLayout();

    int getHeaderType();
}
