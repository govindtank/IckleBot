package com.lonepulse.icklebot.listener;

/*
 * #%L
 * IckleBot Library
 * %%
 * Copyright (C) 2013 Lonepulse
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import java.lang.reflect.Method;
import java.util.Set;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.lonepulse.icklebot.annotation.listener.Click;
import com.lonepulse.icklebot.listener.resolver.ListenerCategory;

/**
 * <p>A concrete implementation of {@link ListenerLinker} which links methods 
 * annotated with {@code @Click} to the specified {@link View}s.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class ClickListenerLinker implements ListenerLinker {

	
	/**
	 * <p>An <i>eager initialized</i> instance of {@link ClickListenerLinker}.</p>
	 * 
	 * @since 1.0.0
	 */
	public static final ClickListenerLinker INSTANCE; 

	static 
	{
		INSTANCE = new ClickListenerLinker();
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void link(ListenerLinker.Configuration config) {

		final Activity listenerTemplate = config.getActivity();
		
		Set<Method> methods = config.getListenerTargets(ListenerCategory.CLICK);
		
		for (final Method method : methods) {
			
			OnClickListener onClickListener = new OnClickListener() {
				
				@Override
				public void onClick(View v) {

					try {
						
						method.invoke(listenerTemplate, v);
					} 
					catch (Exception e) {
						
						StringBuilder builder = new StringBuilder()
						.append("Invocation of ")
						.append(method.getName())
						.append(" at ")
						.append(listenerTemplate.getClass().getName())
						.append(" failed for event OnClick.");
						
						Log.e(getClass().getName(), builder.toString(), e);
					}
				}
			};
			
			try {
				
				if(!method.isAccessible()) method.setAccessible(true);
				
				int[] views = method.getAnnotation(Click.class).value();
				
				for (int id : views) {

					try {
						
						listenerTemplate.findViewById(id).setOnClickListener(onClickListener);
					}
					catch (Exception e) {
						
						StringBuilder builder = new StringBuilder()
						.append("Listener linking failed on method ")
						.append(method.getName())
						.append(" at ")
						.append(listenerTemplate.getClass().getName())
						.append(" for view with ID ")
						.append(listenerTemplate.getResources().getResourceName(id))
						.append(".");
						
						Log.e(getClass().getName(), builder.toString(), e);
					}
				}
			} 
			catch (Exception e) {
				
				StringBuilder builder = new StringBuilder()
				.append("Listener linking failed on method ")
				.append(method.getName())
				.append(" at ")
				.append(listenerTemplate.getClass().getName())
				.append(".");
				
				Log.e(getClass().getName(), builder.toString(), e);
			}
		}
	}
}
