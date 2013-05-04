package com.lonepulse.icklebot.fragment;

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


import android.os.Bundle;

import com.lonepulse.icklebot.IckleActivity;
import com.lonepulse.icklebot.annotation.profile.Profiles.PROFILE;
import com.lonepulse.icklebot.injector.InjectionUtils;
import com.lonepulse.icklebot.injector.Injector;
import com.lonepulse.icklebot.profile.ProfileService;

/**
 * <p>This profile offers dependency injection features.
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
abstract class InjectionFragment extends ThreadingFragment {
	
	
	/**
	 * <p>The {@link Injector.Configuration} for this {@link IckleActivity}.</p>
	 * 
	 * @since 1.1.0
	 */
	private final Injector.Configuration INJECTOR_CONFIGURATION;
	{
		INJECTOR_CONFIGURATION = Injector.Configuration.getInstance(this);
	}

	/**
	 * <p>Performs <b>dependency injection</b> by invoking {@link #inject()}.</p>
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		
		if(ProfileService.getInstance(
			getActivity().getApplicationContext()).isActive(this, PROFILE.INJECTION)) {
			
			InjectionUtils.inject(INJECTOR_CONFIGURATION);
		}
	}
}
