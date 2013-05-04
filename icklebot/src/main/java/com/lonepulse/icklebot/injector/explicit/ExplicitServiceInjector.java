package com.lonepulse.icklebot.injector.explicit;

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
import java.util.Set;

import android.content.Context;
import android.util.Log;

import com.lonepulse.icklebot.annotation.inject.InjectService;
import com.lonepulse.icklebot.injector.Injector;
import com.lonepulse.icklebot.injector.resolver.InjectionCategory;
import com.lonepulse.icklebot.util.ContextUtils;

/**
 * <p>An implementation of {@link Injector} which is responsible 
 * for injecting <i>POJOs</i>.</p>
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class ExplicitServiceInjector implements Injector {
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void inject(Configuration config) {

		Context context = ContextUtils.discover(config.getContext());
		
		Set<Field> fields = config.getInjectionTargets(InjectionCategory.SERVICE);
				
		for (Field field : fields) {
			
			if(!field.isAccessible()) field.setAccessible(true);
			
			try {
				
				String serviceName = field.getAnnotation(InjectService.class).value();
				
				field.set(context, context.getSystemService(serviceName));
			} 
			catch (Exception e) {
				
				Log.e(getClass().getName(), "Injection Failed!", e);
			}
		}
	}
}
