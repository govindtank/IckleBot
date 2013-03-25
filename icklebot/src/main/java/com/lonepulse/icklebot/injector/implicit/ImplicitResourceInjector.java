package com.lonepulse.icklebot.injector.implicit;

/*
 * #%L
 * IckleBot
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

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.util.Log;

import com.lonepulse.icklebot.annotation.inject.InjectView;
import com.lonepulse.icklebot.injector.Injector;
import com.lonepulse.icklebot.injector.resolver.InjectionCategory;
import com.lonepulse.icklebot.util.ReflectiveR;

/**
 * <p>An implementation of {@link Injector} which is responsible 
 * for injecting {@link InjectView}s.</p>
 * 
 * @version 1.1.0 
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class ImplicitResourceInjector implements Injector {

	
	/**
	 * <p>Maintains all the {@link InjectionStrategy}s which are used for <b>implicit injection</b>. 
	 */
	private static final Map<InjectionCategory, Injector.InjectionStrategy> IMPLICIT_INJECTION_STRATEGIES;
	
	static
	{
		IMPLICIT_INJECTION_STRATEGIES = new HashMap<InjectionCategory, Injector.InjectionStrategy>();
		
		IMPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_VIEW, new Injector.InjectionStrategy() {
			
			@Override
			public void run(Configuration config) {
			
				Activity context = config.getActivity();
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_VIEW);
				
				for (Field field : fields) {
				
					try {
					
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = ReflectiveR.id(context, field.getName());
						field.set(context, context.findViewById(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Implicit view resource injection failed on ")
						.append(context.getLocalClassName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		IMPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_STRING, new Injector.InjectionStrategy() {
			
			@Override
			public void run(Configuration config) {
				
				Activity context = config.getActivity();
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_STRING);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = ReflectiveR.string(context, field.getName());
						field.set(context, context.getString(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Implicit string resource injection failed on ")
						.append(context.getLocalClassName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		IMPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_DRAWABLE, new Injector.InjectionStrategy() {
			
			@Override
			public void run(Configuration config) {
				
				Activity context = config.getActivity();
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_DRAWABLE);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = ReflectiveR.drawable(context, field.getName());
						field.set(context, context.getResources().getDrawable(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Implicit drawable resource injection failed on ")
						.append(context.getLocalClassName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		IMPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_INTEGER, new Injector.InjectionStrategy() {
			
			@Override
			public void run(Configuration config) {
				
				Activity context = config.getActivity();
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_INTEGER);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = ReflectiveR.integer(context, field.getName());
						field.set(context, context.getResources().getInteger(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Implicit integer resource injection failed on ")
						.append(context.getLocalClassName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		IMPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_DIMENSION, new Injector.InjectionStrategy() {
			
			@Override
			public void run(Configuration config) {
				
				Activity context = config.getActivity();
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_DIMENSION);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = ReflectiveR.dimen(context, field.getName());
						field.set(context, context.getResources().getDimension(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Implicit dimension resource injection failed on ")
						.append(context.getLocalClassName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void inject(Configuration config) {

		Collection<Injector.InjectionStrategy> strategies = IMPLICIT_INJECTION_STRATEGIES.values();
		
		for (InjectionStrategy strategy : strategies) {
			
			strategy.run(config);
		}
	}
}
