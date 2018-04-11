package com.wayne.viewtools;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewTool {
	public static int heightPixels;
	public static int widthPixels;
	
	private static void initPixels(View views, float width, float height) {

		if (views instanceof ViewGroup) {
			ViewGroup viewGroup = (ViewGroup) views;
			for (int i = 0; i < viewGroup.getChildCount(); i++) {
				View view = viewGroup.getChildAt(i);
				ViewGroup.LayoutParams lp = view.getLayoutParams();
				if (lp.height > 0) {
					lp.height = (int) (((float) lp.height) / height * heightPixels);
				}
				if (lp.width > 0) {
					lp.width = (int) (((float) lp.width) / width * widthPixels);
				}

				if (views instanceof LinearLayout) {
					LinearLayout.LayoutParams lllp = (LinearLayout.LayoutParams) lp;

					lllp.leftMargin = (int) (((float) lllp.leftMargin) / width * widthPixels);
					lllp.rightMargin = (int) (((float) lllp.rightMargin)
							/ width * widthPixels);
					lllp.topMargin = (int) (((float) lllp.topMargin) / height * heightPixels);
					lllp.bottomMargin = (int) (((float) lllp.bottomMargin)
							/ height * heightPixels);
				} else if (views instanceof FrameLayout) {
					FrameLayout.LayoutParams fllp = (FrameLayout.LayoutParams) lp;

					fllp.leftMargin = (int) (((float) fllp.leftMargin) / width * widthPixels);
					fllp.rightMargin = (int) (((float) fllp.rightMargin)
							/ width * widthPixels);
					fllp.topMargin = (int) (((float) fllp.topMargin) / height * heightPixels);
					fllp.bottomMargin = (int) (((float) fllp.bottomMargin)
							/ height * heightPixels);
				}
				
				if (view instanceof TextView) {
					TextView textView = (TextView) view;
					float sp = (float) (width) / (float) (widthPixels);
					textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
							textView.getTextSize() / sp);

				}else if (view instanceof Button) {
					Button button = (Button) view;
					float sp = (float) (width) / (float) (widthPixels);
					button.setTextSize(TypedValue.COMPLEX_UNIT_PX,
							button.getTextSize() / sp);

				}else if (view instanceof ViewGroup) {
					initPixels(view, width, height);
				}

			}
		}

	}

	public static View inflateLayoutPixels(Context context, int layoutId,
			float width, float height) {
		View views = LayoutInflater.from(context).inflate(layoutId,null);
		if (views == null) {
			return null;
		}
		if (heightPixels <= 0) {
			DisplayMetrics dm = context.getResources().getDisplayMetrics();
			heightPixels = dm.heightPixels;
			widthPixels = dm.widthPixels;
		}
		initPixels(views, width, height);
		return views;
	}
	public static View inflateFragmentPixels(Context context, int layoutId,ViewGroup container,
			float width, float height) {
		View views = LayoutInflater.from(context).inflate(layoutId,container,false);
		if (views == null) {
			return null;
		}
		if (heightPixels <= 0) {
			DisplayMetrics dm = context.getResources().getDisplayMetrics();
			heightPixels = dm.heightPixels;
			widthPixels = dm.widthPixels;
		}
		initPixels(views, width, height);
		return views;
	}
}
