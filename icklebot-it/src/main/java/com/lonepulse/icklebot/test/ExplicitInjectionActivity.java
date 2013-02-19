package com.lonepulse.icklebot.test;

/*
 * #%L
 * IckleBot Integration Tests
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


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Button;

import com.lonepulse.icklebot.IckleActivity;
import com.lonepulse.icklebot.annotation.InjectApplication;
import com.lonepulse.icklebot.annotation.InjectDrawable;
import com.lonepulse.icklebot.annotation.InjectInteger;
import com.lonepulse.icklebot.annotation.InjectService;
import com.lonepulse.icklebot.annotation.InjectString;
import com.lonepulse.icklebot.annotation.InjectView;
import com.lonepulse.icklebot.annotation.Layout;
import com.lonepulse.icklebot.annotation.Title;
import com.lonepulse.icklebot.test.app.ApplicationService;

/**
 * <p>An extension of {@link IckleActivity} which is used to test the 
 * <b>explicit runtime injection</b> features of IckleBot.
 * 
 * @category test
 * <br><br>
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Layout(R.layout.act_explicit_injection)
@Title(id = R.string.ttl_act_explicit_injection)
public class ExplicitInjectionActivity extends IckleActivity {
	

	@InjectApplication
	ApplicationService application;
	
	@InjectString(R.string.app_name)
	String strAppName;
	
	@InjectInteger(R.integer.major_version)
	int intMajorVersion;
	
	@InjectView(R.id.btn_submit)
	Button btnSubmit;
	
	@InjectService(Context.TELEPHONY_SERVICE)
	TelephonyManager telephonyManager;
	
	@InjectDrawable(R.drawable.ic_launcher)
	Drawable drwLauncherIcon;
	
	
	/**
	 * <p>Exposes {@link #onCreate(Bundle)} and allows unit 
	 * tests to invoke injection from an external context.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}
}
