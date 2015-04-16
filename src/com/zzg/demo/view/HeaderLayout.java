/**
 * 
 */
package com.zzg.demo.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzg.demo.R;
import com.zzg.demo.utils.PixelUtil;

/**
 * @author acer Descrption:TODO WHAT 2015-4-9 上午9:48:29
 */
public class HeaderLayout extends LinearLayout {

	LayoutInflater mLayoutInflater;
	View mHeader;

	LinearLayout lefttView, rightView;
	TextView htv_subtitle;
	private LinearLayout mLeftImageButtonLayout;
	private LinearLayout mRightImageButtonLayout;

	/**
	 * @param context
	 */
	public HeaderLayout(Context context) {
		super(context);
		init(context);
		// TODO Auto-generated constructor stub
	}

	public HeaderLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public void init(Context context) {
		// 将自定义common_header.xml填充进来
		mLayoutInflater = LayoutInflater.from(context);
		mHeader = mLayoutInflater.inflate(R.layout.common_header, null);
		addView(mHeader);
		// 初始化header里面的控件
		initView();

	}

	/**
	 * 初始化header里面的控件(左中右)
	 */
	private void initView() {
		// TODO Auto-generated method stub
		lefttView = (LinearLayout) findViewByHeaderId(R.id.header_layout_leftview_container);// 左边linearlayout
		rightView = (LinearLayout) findViewByHeaderId(R.id.header_layout_rightview_container);// 右边linearlayout
		htv_subtitle = (TextView) findViewByHeaderId(R.id.header_htv_subtitle);
	}

	/**
	 * 从Header中查找View
	 * 
	 * @param id
	 * @return
	 */
	private View findViewByHeaderId(int id) {
		return mHeader.findViewById(id);
	}

	/**
	 * 对外暴露一个方法来设置header
	 */
	public void init(Integer leftIvId, String title, Integer rightIvId,
			final LeftButtonOnclickListener leftButtonOnclickListener,
			RightButtonOnclickListener rightButtonOnclickListener) {
		// TODO Auto-generated method stub
		// 先将左右两控件下的view删除
		lefttView.removeAllViews();
		rightView.removeAllViews();

		// 根据传进来的值对header进行设置

		// 默认设置
		htv_subtitle.setText(R.string.app_name);
		if (!TextUtils.isEmpty(title)) {
			htv_subtitle.setText(title);
		}
		if (rightIvId != null&&rightButtonOnclickListener!=null) {
			setRightView(rightIvId, rightButtonOnclickListener);
		}
		// 设置两边
		if (leftIvId != null && rightIvId != null) {
			setLeftView(leftIvId, leftButtonOnclickListener);
			setRightView(rightIvId, rightButtonOnclickListener);
		}
		// 设置左边和中间
		else if (leftIvId != null) {
			setLeftView(leftIvId, leftButtonOnclickListener);
			// 设置中间文字
			htv_subtitle.setText(title);
		}

	}

	private void setLeftView(int leftIvId,
			final LeftButtonOnclickListener leftButtonOnclickListener) {
		View mleftImageButtonView = mLayoutInflater.inflate(
				R.layout.common_header_button, null);
		lefttView.addView(mleftImageButtonView);
		mLeftImageButtonLayout = (LinearLayout) mleftImageButtonView
				.findViewById(R.id.header_layout_imagebuttonlayout);
		ImageButton mLeftImageButton = (ImageButton) mleftImageButtonView
				.findViewById(R.id.header_ib_imagebutton);
		mLeftImageButton.setImageResource(leftIvId);
		mLeftImageButtonLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (leftButtonOnclickListener != null) {
					leftButtonOnclickListener.onClick();
				}
			}
		});
	}

	private void setRightView(int rightIvId,
			final RightButtonOnclickListener righButtonOnclickListener) {
		View mRightImageButtonView = mLayoutInflater.inflate(
				R.layout.common_header_rightbutton, null);
		rightView.addView(mRightImageButtonView);
		mRightImageButtonLayout = (LinearLayout) mRightImageButtonView
				.findViewById(R.id.header_layout_imagebuttonlayout);
		Button mRightImageButton = (Button) mRightImageButtonView
				.findViewById(R.id.header_ib_imagebutton);
		mRightImageButton.setWidth(PixelUtil.dp2px(35));
		mRightImageButton.setHeight(PixelUtil.dp2px(30));
		mRightImageButton.setBackgroundResource(rightIvId);
		mRightImageButtonLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (righButtonOnclickListener != null) {
					righButtonOnclickListener.onClick();
				}
			}
		});
	}

	/**
	 * 对外提供两个回调函数，需要被调用时实现
	 */
	LeftButtonOnclickListener leftButtonOnclickListener;

	/**
	 * @param leftButtonOnclickListener
	 *            the leftButtonOnclickListener to set
	 */
	public void setLeftButtonOnclickListener(
			LeftButtonOnclickListener leftButtonOnclickListener) {
		this.leftButtonOnclickListener = leftButtonOnclickListener;
	}

	public interface LeftButtonOnclickListener {
		void onClick();
	}

	/**
	 * @param rightButtonOnclickListener
	 *            the rightButtonOnclickListener to set
	 */
	public void setRightButtonOnclickListener(
			RightButtonOnclickListener rightButtonOnclickListener) {
		this.rightButtonOnclickListener = rightButtonOnclickListener;
	}

	/**
	 * 对外提供两个回调函数，需要被调用时实现
	 */
	RightButtonOnclickListener rightButtonOnclickListener;

	public interface RightButtonOnclickListener {
		void onClick();
	}
}
