package com.android.launcher3.popup;

import android.view.View;

import androidx.annotation.NonNull;

import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.model.data.ItemInfo;
import com.android.launcher3.views.ActivityContext;

/**
 * System shortcut for enlarging folders to occupy multiple cells.
 * Allows users to resize folders to 2×2 layout with OnePlus-style capsule design.
 */
public class EnlargeFolderShortcut<T extends ActivityContext> extends SystemShortcut<T> {

    public EnlargeFolderShortcut(T target, ItemInfo itemInfo, @NonNull View originalView) {
        super(R.drawable.ic_widget, R.string.enlarge_folder_label, target, itemInfo,
                originalView);
    }

    @Override
    public void onClick(View view) {
        if (mTarget instanceof Launcher) {
            ((Launcher) mTarget).showStretchOptions(mItemInfo);
        }
        dismissTaskMenuView();
    }
}
