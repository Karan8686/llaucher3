package com.android.launcher3;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.launcher3.model.data.FolderInfo;
import com.android.launcher3.model.data.ItemInfo;

/**
 * Helper class for showing stretch options dialog and handling item resizing.
 */
public class StretchOptionsHelper {

    private static final int OPTION_NORMAL = 0;
    private static final int OPTION_HORIZONTAL = 1;
    private static final int OPTION_VERTICAL = 2;
    private static final int OPTION_LARGE = 3;

    /**
     * Shows a dialog to select stretch size for the given item.
     *
     * @param launcher The launcher activity
     * @param itemInfo The item to stretch
     */
    public static void showStretchOptions(Launcher launcher, ItemInfo itemInfo) {
        Context context = launcher;
        boolean isFolder = itemInfo instanceof FolderInfo;
        
        String[] options;
        if (isFolder) {
            // Folders can only be enlarged to 2×2
            options = new String[]{
                    "Normal (1×1)",
                    "Large Tile (2×2)"
            };
        } else {
            // Apps can be stretched in multiple ways
            options = new String[]{
                    "Normal (1×1)",
                    "Horizontal (2×1)",
                    "Vertical (1×2)",
                    "Large Tile (2×2)"
            };
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(isFolder ? "Enlarge Folder" : "Stretch Icon");
        builder.setItems(options, (dialog, which) -> {
            int spanX, spanY;
            
            if (isFolder) {
                // Folder options
                switch (which) {
                    case 0: // Normal
                        spanX = 1;
                        spanY = 1;
                        break;
                    case 1: // Large (2×2)
                        spanX = 2;
                        spanY = 2;
                        break;
                    default:
                        return;
                }
            } else {
                // App icon options
                switch (which) {
                    case OPTION_NORMAL:
                        spanX = 1;
                        spanY = 1;
                        break;
                    case OPTION_HORIZONTAL:
                        spanX = 2;
                        spanY = 1;
                        break;
                    case OPTION_VERTICAL:
                        spanX = 1;
                        spanY = 2;
                        break;
                    case OPTION_LARGE:
                        spanX = 2;
                        spanY = 2;
                        break;
                    default:
                        return;
                }
            }

            // Apply the resize
            launcher.resizeWorkspaceItem(itemInfo, spanX, spanY);
            dialog.dismiss();
        });
        
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
}
