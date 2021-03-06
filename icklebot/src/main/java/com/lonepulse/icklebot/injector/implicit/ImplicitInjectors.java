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

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.lonepulse.icklebot.annotation.inject.InjectAll;
import com.lonepulse.icklebot.injector.InjectionException;
import com.lonepulse.icklebot.injector.Injector;

/**
 * <p>Maintains a set of {@link Injector} <i>singletons</i> which are used 
 * by any {@link Activity} which implements the {@link Activity} 
 * and has requested <b>Implicit Injection</b> via {@link InjectAll}.</p>
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum ImplicitInjectors implements Injector {

	/**
	 * <p>This {@link Injector} is responsible for injecting 
	 * the {@link Application} instance.</p>
	 * 
	 * <p>Injection will succeed with a custom {@link Application} 
	 * extension as well; for example:</p>
	 * 
	 * <pre><br>MyApplication myApplication;</pre>
	 * 
	 * @since 1.0.0
	 */
	APPLICATION(new ImplicitApplicationInjector()),
	
	/**
	 * <p>This {@link Injector} is responsible for injecting 
	 * the <i>Resources</i> of an {@link Activity}.</p>
	 * 
	 * @since 1.0.0
	 */
	RESOURCES(new ImplicitResourceInjector()),
	
	/**
	 * <p>This {@link Injector} is responsible for injecting any 
	 * <i>System Services</i> in an {@link Activity}.</p>
	 * 
	 * @since 1.0.0
	 */
	SYSTEM_SERVICES(new ImplicitSystemServiceInjector()),
	
	/**
	 * <p>This {@link Injector} is responsible for injecting any 
	 * <i>Ickle Services</i> in an {@link Activity}.</p>
	 * 
	 * @since 1.0.0
	 */
	ICKLE_SERVICES(new ImplicitIckleServiceInjector()),
	
	/**
	 * <p>This {@link Injector} is responsible for injecting 
	 * any <i>POJOs</i> in an {@link Activity}.</p>
	 * 
	 * @since 1.0.0
	 */
	POJOS(new ImplicitPojoInjector());
	
	
	
	/**
	 * <p>The wrapped {@link Injector} <i>singleton</i>.</pS>
	 * 
	 * @since 1.0.0
	 */
	private Injector implicitInjector;
	
	/**
	 * <p>A parameterized constructor which initializes the 
	 * {@link #implicitInjector}.</p>
	 * 
	 * @param implicitInjector
	 * 			populates {@link #implicitInjector}
	 * <br><br>
	 * @since 1.0.0
	 */
	private ImplicitInjectors(Injector implicitInjector) {
		
		this.implicitInjector = implicitInjector;
	}

	/**
	 * <p>Delegate for invoking the {@link #implicitInjector}'s 
	 * {@link Injector#inject(com.lonepulse.icklebot.injector.Injector.Configuration)} service.</p>
	 * 
	 * @since 1.0.0
	 */
	@Override
	public void inject(Configuration config) throws InjectionException {
	
		try {
					
			implicitInjector.inject(config);
		}
		catch(Exception e) {
					
			StringBuilder stringBuilder = new StringBuilder();
					
			stringBuilder.append("Injection using ");
			stringBuilder.append(implicitInjector.getClass().getName());
			stringBuilder.append(" failed on activity ");
			stringBuilder.append(config.getContext().getClass().getName());
			stringBuilder.append(". ");
			
			Log.e(getClass().getName(), stringBuilder.toString(), e);
		}
	}
}
