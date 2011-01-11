package com.gueei.android.binding.bindingProviders;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gueei.android.binding.Binder;
import com.gueei.android.binding.BindingMap;
import com.gueei.android.binding.Command;
import com.gueei.android.binding.R;
import com.gueei.android.binding.ViewAttribute;
import com.gueei.android.binding.listeners.TextWatcherMulticast;
import com.gueei.android.binding.viewAttributes.TextViewAttribute;

public class TextViewProvider extends BindingProvider {

	@SuppressWarnings("unchecked")
	@Override
	public <Tv extends View>ViewAttribute<Tv, ?> createAttributeForView(View view, int attributeId) {
		if (!(view instanceof TextView)) return null;
		try{
			if (attributeId == R.id.attribute_text){
				// TODO: Can change to very specific class to avoid the reflection methods
				TextViewAttribute attr = new TextViewAttribute((TextView)view, "text");
				if (view instanceof EditText){
					Binder.getMulticastListenerForView
						(view, TextWatcherMulticast.class).register(attr);
				}
				return (ViewAttribute<Tv, ?>) attr;
			}
		}
		catch(Exception e){
			// Actually it should never reach this statement
		}
		return null;
	}

	@Override
	public boolean bindCommand(View view, int attrId, Command command) {
		return false;
	}

	@Override
	public void mapBindings(View view, Context context, AttributeSet attrs,
			BindingMap map) {
		if (!(view instanceof TextView)) return;
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BindableTextViews);
		String text = a.getString(R.styleable.BindableTextViews_text);
		if (text != null)
			map.attributes.put(R.id.attribute_text, text);
	}
}