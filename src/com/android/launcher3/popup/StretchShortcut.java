package com.android.launcher3.popup;

import android.view.View;

import androidx.annotation.NonNull;

import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.model.data.ItemInfo;
import com.android.launcher3.views.ActivityContext;

/**
 * System shortcut for stretching app shortcuts to occupy multiple cells.
 * Allows users to resize shortcuts to 2×1, 1×2, or 2×2 layouts with OnePlus-style capsule design.
 */
public class StretchShortcut<T extends ActivityContext> extends SystemShortcut<T> {

    public StretchShortcut(T target, ItemInfo itemInfo, @NonNull View originalView) {
        super(R.drawable.ic_widget, R.string.stretch_label, target, itemInfo, originalView);
    }

    @Override
    public void onClick(View view) {
        if (mTarget instanceof Launcher) {
            ((Launcher) mTarget).showStretchOptions(mItemInfo);
        }
        dismissTaskMenuView();
    }
}
