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
 * @author acer Descrption:TODO WHAT 2015-4-9 ����9:48:29
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
		// ���Զ���common_header.xml������
		mLayoutInflater = LayoutInflater.from(context);
		mHeader = mLayoutInflater.inflate(R.layout.common_header, null);
		addView(mHeader);
		// ��ʼ��header����Ŀؼ�
		initView();

	}

	/**
	 * ��ʼ��header����Ŀؼ�(������)
	 */
	private void initView() {
		// TODO Auto-generated method stub
		lefttView = (LinearLayout) findViewByHeaderId(R.id.header_layout_leftview_container);// ���linearlayout
		rightView = (LinearLayout) findViewByHeaderId(R.id.header_layout_rightview_container);// �ұ�linearlayout
		htv_subtitle = (TextView) findViewByHeaderId(R.id.header_htv_subtitle);
	}

	/**
	 * ��Header�в���View
	 * 
	 * @param id
	 * @return
	 */
	private View findViewByHeaderId(int id) {
		return mHeader.findViewById(id);
	}

	/**
	 * ���Ⱪ¶һ������������header
	 */
	public void init(Integer leftIvId, String title, Integer rightIvId,
			final LeftButtonOnclickListener leftButtonOnclickListener,
			RightButtonOnclickListener rightButtonOnclickListener) {
		// TODO Auto-generated method stub
		// �Ƚ��������ؼ��µ�viewɾ��
		lefttView.removeAllViews();
		rightView.removeAllViews();

		// ���ݴ�������ֵ��header��������

		// Ĭ������
		htv_subtitle.setText(R.string.app_name);
		if (!TextUtils.isEmpty(title)) {
			htv_subtitle.setText(title);
		}
		if (rightIvId != null&&rightButtonOnclickListener!=null) {
			setRightView(rightIvId, rightButtonOnclickListener);
		}
		// ��������
		if (leftIvId != null && rightIvId != null) {
			setLeftView(leftIvId, leftButtonOnclickListener);
			setRightView(rightIvId, rightButtonOnclickListener);
		}
		// ������ߺ��м�
		else if (leftIvId != null) {
			setLeftView(leftIvId, leftButtonOnclickListener);
			// �����м�����
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
	 * �����ṩ�����ص���������Ҫ������ʱʵ��
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
	 * �����ṩ�����ص���������Ҫ������ʱʵ��
	 */
	RightButtonOnclickListener rightButtonOnclickListener;

	public interface RightButtonOnclickListener {
		void onClick();
	}
}
