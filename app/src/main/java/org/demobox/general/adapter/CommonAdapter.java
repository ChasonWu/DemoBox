package org.demobox.general.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Adapter一劳永逸做法，ListView、GridView通用的BaseAdapter，鸿洋版
 * @author 鸿洋
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

	protected LayoutInflater mInflater;
	protected Context mContext;
	protected List<T> mDatas;
	protected final int mItemLayoutId;

	public CommonAdapter(Context context, List<T> datas, int itemLayoutId) {
		mContext = context;
		mDatas = datas;
		mItemLayoutId = itemLayoutId;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public T getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder = getViewHolder(position, convertView, parent);
		convert(viewHolder, getItem(position));
		return viewHolder.getConvertView();
	}

	/**
	 * 把viewHolder和本Item对应的Bean对象给传出去，通过ViewHolder把View找到，通过Item设置值
	 * 
	 * @param helper
	 * @param item
	 */
	public abstract void convert(ViewHolder helper, T item);

	private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
		return ViewHolder.get(mContext, convertView, parent, mItemLayoutId, position);
	}

}
