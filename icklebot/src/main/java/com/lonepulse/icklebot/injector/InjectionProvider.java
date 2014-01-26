package com.lonepulse.icklebot.injector;

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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.lonepulse.icklebot.annotation.inject.InjectAll;
import com.lonepulse.icklebot.app.SupportFragment;
import com.lonepulse.icklebot.injector.resolver.InjectionCategory;
import com.lonepulse.icklebot.injector.resolver.InjectionResolver;
import com.lonepulse.icklebot.injector.resolver.InjectionResolvers;
import com.lonepulse.icklebot.util.ContextUtils;

/**
 * <p>This is the common contract which all injectors must implement.</p>
 * 
 * @version 1.4.0  
 * <br><br>
 * @since 1.1.0  
 * <br><br>
 * @author <a href="http://sahan.me">Lahiru Sahan Jayasinghe</a>
 */
public interface InjectionProvider {
	
	/**
	 * <p>Retails information about the injection bindings for a single target. The information includes 
	 * the target fields grouped into their categories by {@link InjectionCategory}.</p>
	 * 
	 * @version 1.1.0
	 * <br><br>
	 * @since 1.1.0
	 * <br><br>
	 * @author <a href="http://sahan.me">Lahiru Sahan Jayasinghe</a>
	 */
	public static final class Configuration {
		
		private final InjectionMode injectionMode;
		private final Object target;
		private final Context context;
		private final Map<InjectionCategory, Set<Field>> injectionTargets;
		
		/**
		 * <p>Creates a <b>new</b> {@link InjectionProvider.Configuration} for the given target. The supplied 
		 * target should either be an {@link Activity}, {@link FragmentActivity}, {@link Fragment} or 
		 * {@link SupportFragment}.</p>
		 * 
		 * @param target
		 * 			the {@link Context} which requested dependency injection
		 * <br><br>
		 * @return a new {@link InjectionProvider.Configuration} for the supplied target
		 * <br><br>
		 * @throws IllegalArgumentException
		 * 			if the supplied target is {@code null}
		 * <br><br>
		 * @throws IllegalContextException
		 * 			if the supplied target is not an {@link Activity}, {@link FragmentActivity}, 
		 * 			{@link Fragment} or {@link SupportFragment}
		 * <br><br>
		 * @since 1.1.0
		 */
		public static Configuration newInstance(Object target) {
			
			if(target == null) {
				
				new IllegalArgumentException("A target must be supplied.");
			}

			if(!ContextUtils.isActivity(target)
				&& !ContextUtils.isFragment(target)
				&& !ContextUtils.isSupportFragment(target)) { 
				
				Set<Class<?>> applicableContexts = new HashSet<Class<?>>();
				applicableContexts.add(Activity.class);
				applicableContexts.add(Fragment.class);
				applicableContexts.add(android.support.v4.app.Fragment.class);
				
				throw new IllegalContextException(target, applicableContexts);
			}
			
			return new Configuration(target);
		}

		private Configuration(Object target) {
			
			this.target = target;
			this.context = ContextUtils.discover(target);
			this.injectionTargets = new HashMap<InjectionCategory, Set<Field>>();
			
			for (InjectionCategory injectionCategory : InjectionCategory.values()) {
				
				this.injectionTargets.put(injectionCategory, new HashSet<Field>());
			}
			
			this.injectionMode = target.getClass()
				.isAnnotationPresent(InjectAll.class)? InjectionMode.IMPLICIT :InjectionMode.EXPLICIT;
			
			InjectionResolver injectionResolver = InjectionResolvers.get(this.injectionMode);
			
			Field[] fields = target.getClass().getDeclaredFields();
			
			for (Field field : fields) {
				
				InjectionCategory injectionCategory = injectionResolver.resolve(target, field);
				injectionTargets.get(injectionCategory).add(field);
			}
		}
		
		/**
		 * <p>Retrieves the {@link InjectionMode} for this {@link InjectionProvider.Configuration}.</p>
		 * 
		 * @return the {@link InjectionMode} for this {@link InjectionProvider.Configuration}
		 * <br><br>
		 * @since 1.0.0
		 */		
		public InjectionMode getInjectionMode() {
			
			return injectionMode;
		}

		/**
		 * <p>Retrieves the target for this {@link InjectionProvider.Configuration}.</p>
		 * 
		 * @return the target for this {@link InjectionProvider.Configuration}
		 * <br><br>
		 * @since 1.0.0
		 */ 
		public Object getTarget() {
			
			return target;
		}
		
		/**
		 * <p>Retrieves the {@link Context} for this {@link InjectionProvider.Configuration}.</p>
		 * 
		 * @return the {@link Context} for this {@link InjectionProvider.Configuration}
		 * <br><br>
		 * @since 1.0.0
		 */ 
		public Context getContext() {
			
			return context;
		}

		/**
		 * <p>Retrieves all the injection targets for this {@link InjectionProvider.Configuration} which is a 
		 * map of {@link InjectionCategory} versus target {@link Field}s.</p>
		 * 
		 * @return the injection targets for this {@link InjectionProvider.Configuration}
		 * <br><br>
		 * @since 1.0.0
		 */ 
		public Map<InjectionCategory, Set<Field>> getInjectionTargets() {
			
			return Collections.unmodifiableMap(injectionTargets);
		}
		
		/**
		 * <p>Retrieves the targeted target {@link Field}s for the given {@link InjectionCategory}.</p> 
		 * 
		 * @param injectionCategory
		 * 			the {@link InjectionCategory} which identifies the targeted fields to retrieve	
		 * <br><br>
		 * @return the {@link Set} of targeted {@link Field}s related to the given {@link InjectionCategory}  
		 * <br><br>
		 * @since 1.0.0
		 */
		public Set<Field> getInjectionTargets(InjectionCategory injectionCategory) {
			
			return Collections.unmodifiableSet(injectionTargets.get(injectionCategory));
		}
	 }

	/**
	 * <p>Takes an {@link InjectionProvider.Configuration} and injects the dependent <i>resources</i> which this 
	 * implementation is responsible for.</p>
	 * 
	 * @param config
	 * 			the {@link InjectionProvider.Configuration} which supplies the information for injection
	 * <br><br>
	 * @throws InjectionException
	 * 			if injection failed on at least one of the dependent fields
	 * <br><br>
	 * @since 1.0.0
	 */
	void run(final InjectionProvider.Configuration config); 
}