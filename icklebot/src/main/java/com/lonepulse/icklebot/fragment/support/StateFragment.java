package com.lonepulse.icklebot.fragment.support;

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

import com.lonepulse.icklebot.annotation.profile.Profiles.PROFILE;
import com.lonepulse.icklebot.profile.ProfileService;
import com.lonepulse.icklebot.state.StateUtils;

/**
 * <p>This profile offers state management features.
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
abstract class StateFragment extends InjectionFragment {
	
	
	/**
	 * <p><b>Restores</b> instance variables annotated with {@code @Stateful}.</p>
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		
		if(ProfileService.getInstance(
			getActivity().getApplicationContext()).isActive(this, PROFILE.STATE)) {
		
			if(!getRetainInstance()) setRetainInstance(true);
			
			StateUtils.onRestoreInstanceState(this, savedInstanceState);
		}
	}
	
	/**
	 * <p><b>Saves</b> instance variables annotated with {@code @Stateful}.</p>
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		
		super.onSaveInstanceState(outState);
		
		if(ProfileService.getInstance(
			getActivity().getApplicationContext()).isActive(this, PROFILE.STATE)) {
			
			StateUtils.onSaveInstanceState(this, outState);
		}
	}
}
