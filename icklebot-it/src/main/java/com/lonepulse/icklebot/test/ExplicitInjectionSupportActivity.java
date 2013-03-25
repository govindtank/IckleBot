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


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Button;

import com.lonepulse.icklebot.IckleSupportManager;
import com.lonepulse.icklebot.annotation.inject.InjectApplication;
import com.lonepulse.icklebot.annotation.inject.InjectDimension;
import com.lonepulse.icklebot.annotation.inject.InjectDrawable;
import com.lonepulse.icklebot.annotation.inject.InjectInteger;
import com.lonepulse.icklebot.annotation.inject.InjectPojo;
import com.lonepulse.icklebot.annotation.inject.InjectService;
import com.lonepulse.icklebot.annotation.inject.InjectString;
import com.lonepulse.icklebot.annotation.inject.InjectView;
import com.lonepulse.icklebot.annotation.inject.Layout;
import com.lonepulse.icklebot.annotation.inject.Title;
import com.lonepulse.icklebot.test.app.ApplicationService;
import com.lonepulse.icklebot.test.service.AccountsService;
import com.lonepulse.icklebot.test.service.AccountsServiceImpl;

/**
 * <p>An extension of {@link Activity} which is used to test 
 * the {@link IckleSupportManager}'s explicit injection features.
 * 
 * @category test
 * <br><br>
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Layout(R.layout.act_explicit_injection)
@Title(id = R.string.ttl_act_explicit_injection)
public class ExplicitInjectionSupportActivity extends Activity {
	

	@InjectApplication
	ApplicationService application;
	
	@InjectString(R.string.app_name)
	String strAppName;
	
	@InjectInteger(R.integer.major_version)
	int intMajorVersion;
	
	@InjectView(R.id.btnSubmit)
	Button btnSubmit;
	
	@InjectDrawable(R.drawable.ic_launcher)
	Drawable drwLauncherIcon;
	
	@InjectDimension(R.dimen.txt_small)
	float txtSizeSmall;
	
	@InjectService(Context.TELEPHONY_SERVICE)
	TelephonyManager telephonyManager;
	
	@InjectPojo(AccountsServiceImpl.class)
	AccountsService accountsService;

	
	@SuppressWarnings("unused")
	private IckleSupportManager supportManager;
	{
		supportManager = new IckleSupportManager.Builder(this)
		.enableInjectionSupport()
		.build();
	}
	
	
	/**
	 * <p>Exposes {@link #onCreate(Bundle)} and allows unit 
	 * tests to invoke injection from an external context.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}
}
