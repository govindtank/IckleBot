package com.lonepulse.icklebot.injector.resolver;

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

import com.lonepulse.icklebot.annotation.inject.InjectApplication;
import com.lonepulse.icklebot.annotation.inject.InjectDimension;
import com.lonepulse.icklebot.annotation.inject.InjectDrawable;
import com.lonepulse.icklebot.annotation.inject.InjectInteger;
import com.lonepulse.icklebot.annotation.inject.InjectPojo;
import com.lonepulse.icklebot.annotation.inject.InjectService;
import com.lonepulse.icklebot.annotation.inject.InjectString;
import com.lonepulse.icklebot.annotation.inject.InjectView;

/**
 * <p>An implementation of {@link InjectionResolver} which caters to 
 * <b>Explicit Injections</b> using any of the <i>@Inject...</i> annotations.</p>
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class ExplicitInjectionResolver implements InjectionResolver {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public InjectionCategory resolve(Field field) {
		
		if(field.isAnnotationPresent(InjectApplication.class))
			return InjectionCategory.APPLICATION;
			
		else if(field.isAnnotationPresent(InjectView.class))
			return InjectionCategory.RESOURCE_VIEW;
		
		else if(field.isAnnotationPresent(InjectInteger.class))
			return InjectionCategory.RESOURCE_INTEGER;
		
		else if(field.isAnnotationPresent(InjectString.class))
			return InjectionCategory.RESOURCE_STRING;
		
		else if(field.isAnnotationPresent(InjectDrawable.class))
			return InjectionCategory.RESOURCE_DRAWABLE;
		
		else if(field.isAnnotationPresent(InjectDimension.class))
			return InjectionCategory.RESOURCE_DIMENSION;
			
		else if(field.isAnnotationPresent(InjectPojo.class))
			return InjectionCategory.POJO;
		
		else if(field.isAnnotationPresent(InjectService.class))
			return InjectionCategory.SERVICE;
		
		return InjectionCategory.NONE;
	}
}
