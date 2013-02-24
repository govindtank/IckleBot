package com.lonepulse.icklebot.profile;

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


import android.app.Activity;

import com.lonepulse.icklebot.IckleActivity;
import com.lonepulse.icklebot.annotation.profile.Profiles;
import com.lonepulse.icklebot.annotation.profile.Profiles.PROFILE;

/**
 * <p>This is a utility class which is used to determine the activation status 
 * of a {@link PROFILE} for a particular {@link IckleActivity}.
 * 
 * @version 1.1.1
 * <b></b>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class ProfileService implements ProfileManager {


	/**
	 * <p>An <i>eager initialized</i> instance of {@link ProfileService}.</p>
	 * 
	 * @since 1.1.0
	 */
	public static final ProfileManager INSTANCE = new ProfileService();
	
	
	/**
	 * <p>Instantiation is restricted. Use the {@link #newInstance()} 
	 * or {@link #INSTANCE} instead.
	 */
	private ProfileService() {}
	
	
	/**
	 * <p>Creates a new instance of an implementation of 
	 * {@link ProfileManager}.
	 * 
	 * @return a new implementation of {@link ProfileManager}.
	 * 
	 * @since 1.1.1
	 */
	public static final ProfileManager newInstance() {
		
		return new ProfileService();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isActive(Activity activity, PROFILE profile) {
		
		if(activity == null || profile == null) {
			
			StringBuilder builder = new StringBuilder()
			.append("Failed to determine profile active profiles. ");
			
			if(activity == null)
				builder.append("Actvitiy cannot be null. ");
				
			if(profile == null)
				builder.append("PROFILE cannot be null. ");
			
			throw new IllegalArgumentException(builder.toString());
		}
		
		if(activity.getClass().isAnnotationPresent(Profiles.class)) {
			
			Profiles profiles = activity.getClass().getAnnotation(Profiles.class);
			
			PROFILE[] activeProfiles = profiles.value();
			
			for (PROFILE currentProfile : activeProfiles)
				if(currentProfile.compareTo(profile) == 0) return true;
			
			return false;
		}
		else {
			
			return true;
		}
	}
}
