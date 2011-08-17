package gueei.binding.viewAttributes.tabHost;

import android.view.View;
import android.widget.TabHost;
import gueei.binding.Binder;
import gueei.binding.listeners.OnTabChangedListener;
import gueei.binding.viewAttributes.ViewEventAttribute;

/**
 * User: =ra=
 * Date: 17.08.11
 * Time: 17:06
 */
public class OnTabChangedViewEvent extends ViewEventAttribute<View> implements TabHost.OnTabChangeListener {

	public OnTabChangedViewEvent(View view) {
		super(view, "onTabChanged");
	}

	String mTabTag;

	public void onTabChanged(String tabId) {
		if (mTabTag != tabId) {
			mTabTag = tabId;
			this.invokeCommand(getView(), tabId);
		}
	}

	@Override protected void registerToListener(View view) {
		Binder.getMulticastListenerForView(view, OnTabChangedListener.class).register(this);
	}
}