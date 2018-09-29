package org.chason.demobox.general.adapter;

import android.util.SparseArray;
import android.view.View;

/**
 * ViewHolder一劳永逸做法
 * @author 鸿洋
 */
public class ViewHolderUtils {

    private ViewHolderUtils() {}

    @SuppressWarnings("unchecked")
	public static <T extends View> T get(View convertView, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            convertView.setTag(viewHolder);
        }

        View childView = viewHolder.get(id);

        if (childView == null) {
            childView = convertView.findViewById(id);
            viewHolder.put(id, childView);
        }

        return (T) childView;
    }

}
