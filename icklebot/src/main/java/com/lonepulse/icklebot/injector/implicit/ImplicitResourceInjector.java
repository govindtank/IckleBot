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

import android.animation.AnimatorInflater;
import android.content.Context;
import android.util.Log;
import android.view.animation.AnimationUtils;

import com.lonepulse.icklebot.annotation.inject.InjectView;
import com.lonepulse.icklebot.injector.InjectionProvider;
import com.lonepulse.icklebot.injector.resolver.InjectionCategory;
import com.lonepulse.icklebot.util.ContextUtils;
import com.lonepulse.icklebot.util.ReflectiveR;

/**
 * <p>An implementation of {@link InjectionProvider} which is responsible 
 * for injecting {@link InjectView}s.</p>
 * 
 * @version 1.1.0 
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class ImplicitResourceInjector implements InjectionProvider {

	
	/**
	 * <p>Maintains all the {@link InjectionProvider}s which are used for <b>implicit injection</b>. 
	 */
	private static final Map<InjectionCategory, InjectionProvider.InjectionProvider> IMPLICIT_INJECTION_STRATEGIES;
	
	static
	{
		IMPLICIT_INJECTION_STRATEGIES = new HashMap<InjectionCategory, InjectionProvider.InjectionProvider>();
		
		IMPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_VIEW, new InjectionProvider.InjectionProvider() {
			
			@Override
			public void run(Configuration config) {
			
				Object context = config.getTarget();
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_VIEW);
				
				for (Field field : fields) {
				
					try {
					
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = ReflectiveR.id(ContextUtils.discover(context), field.getName());
						
						if(ContextUtils.isActivity(context))
							field.set(context, ContextUtils.asActivity(context).findViewById(id));
						
						else if(ContextUtils.isFragment(context)) 
							field.set(context, ContextUtils.asFragment(context).getView().findViewById(id));
						
						else if(ContextUtils.isSupportFragment(context))
							field.set(context, ContextUtils.asSupportFragment(context).getView().findViewById(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Implicit view resource injection failed on ")
						.append(context.getClass().getName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		IMPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_STRING, new InjectionProvider.InjectionProvider() {
			
			@Override
			public void run(Configuration config) {
				
				Context baseContext = ContextUtils.discover(config.getTarget());
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_STRING);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = ReflectiveR.string(baseContext, field.getName());
						field.set(config.getTarget(), baseContext.getString(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Implicit string resource injection failed on ")
						.append(baseContext.getClass().getName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		IMPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_DRAWABLE, new InjectionProvider.InjectionProvider() {
			
			@Override
			public void run(Configuration config) {
				
				Context baseContext = ContextUtils.discover(config.getTarget());
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_DRAWABLE);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = ReflectiveR.drawable(baseContext, field.getName());
						field.set(config.getTarget(), baseContext.getResources().getDrawable(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Implicit drawable resource injection failed on ")
						.append(baseContext.getClass().getName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});

		IMPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_COLOR, new InjectionProvider.InjectionProvider() {
			
			@Override
			public void run(Configuration config) {
				
				Context baseContext = ContextUtils.discover(config.getTarget());
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_COLOR);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = ReflectiveR.color(baseContext, field.getName());
						field.set(config.getTarget(), baseContext.getResources().getColor(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Implicit color resource injection failed on ")
						.append(baseContext.getClass().getName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		IMPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_INTEGER, new InjectionProvider.InjectionProvider() {
			
			@Override
			public void run(Configuration config) {
				
				Context baseContext = ContextUtils.discover(config.getTarget());
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_INTEGER);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = ReflectiveR.integer(baseContext, field.getName());
						field.set(config.getTarget(), baseContext.getResources().getInteger(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Implicit integer resource injection failed on ")
						.append(baseContext.getClass().getName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		IMPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_DIMENSION, new InjectionProvider.InjectionProvider() {
			
			@Override
			public void run(Configuration config) {
				
				Context baseContext = ContextUtils.discover(config.getTarget());
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_DIMENSION);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = ReflectiveR.dimen(baseContext, field.getName());
						field.set(config.getTarget(), baseContext.getResources().getDimension(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Implicit dimension resource injection failed on ")
						.append(baseContext.getClass().getName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		IMPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_BOOLEAN, new InjectionProvider.InjectionProvider() {
			
			@Override
			public void run(Configuration config) {
				
				Context baseContext = ContextUtils.discover(config.getTarget());
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_BOOLEAN);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = ReflectiveR.bool(baseContext, field.getName());
						field.set(config.getTarget(), baseContext.getResources().getBoolean(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Implicit boolean resource injection failed on ")
						.append(baseContext.getClass().getName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		IMPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_ARRAY, new InjectionProvider.InjectionProvider() {
			
			@Override
			public void run(Configuration config) {
				
				Context baseContext = ContextUtils.discover(config.getTarget());
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_ARRAY);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = ReflectiveR.array(baseContext, field.getName());
						
						if(field.getType().equals(String[].class)) {
							
							field.set(config.getTarget(), baseContext.getResources().getStringArray(id));
						}
						else if(field.getType().equals(int[].class) || field.getType().equals(Integer[].class)) {
							
							field.set(config.getTarget(), baseContext.getResources().getIntArray(id));
						}
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Implicit array resource injection failed on ")
						.append(baseContext.getClass().getName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		IMPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_ANIMATION, new InjectionProvider.InjectionProvider() {
			
			@Override
			public void run(Configuration config) {
				
				Context baseContext = ContextUtils.discover(config.getTarget());
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_ANIMATION);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = ReflectiveR.anim(baseContext, field.getName());
						field.set(config.getTarget(), AnimationUtils.loadAnimation(baseContext, id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Implicit animation resource injection failed on ")
						.append(baseContext.getClass().getName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		IMPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_ANIMATOR, new InjectionProvider.InjectionProvider() {
			
			@Override
			public void run(Configuration config) {
				
				Context baseContext = ContextUtils.discover(config.getTarget());
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_ANIMATOR);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = ReflectiveR.animator(baseContext, field.getName());
						field.set(config.getTarget(), AnimatorInflater.loadAnimator(baseContext, id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Implicit animator resource injection failed on ")
						.append(baseContext.getClass().getName())
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

		Collection<InjectionProvider.InjectionProvider> strategies = IMPLICIT_INJECTION_STRATEGIES.values();
		
		for (InjectionProvider strategy : strategies) {
			
			strategy.run(config);
		}
	}
}
