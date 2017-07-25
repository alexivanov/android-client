/*
 * This project is licensed under the open source MPL V2.
 * See https://github.com/openMF/android-client/blob/master/LICENSE.md
 */

package com.mifos.mifosxdroid;

import android.content.Intent;
import android.os.Bundle;

import com.mifos.api.BaseUrl;
import com.mifos.mifosxdroid.core.MifosBaseActivity;
import com.mifos.mifosxdroid.login.LoginActivity;
import com.mifos.mifosxdroid.online.DashboardActivity;
import com.mifos.mifosxdroid.twofactor.TwoFactorActivity;
import com.mifos.utils.ForegroundChecker;
import com.mifos.utils.PrefManager;


/**
 * This is the First Activity which can be used for initial checks, inits at app Startup
 */
public class SplashScreenActivity extends MifosBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ForegroundChecker.init();
        if (!PrefManager.isAuthenticated()) {
            PrefManager.setInstanceUrl(BaseUrl.PROTOCOL_HTTPS
                    + BaseUrl.API_ENDPOINT + BaseUrl.API_PATH);
            startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
        } else {
            if (PrefManager.isTwoFactorTokenNearExpiry()) {
                startActivity(new Intent(SplashScreenActivity.this, TwoFactorActivity.class));
            } else {
                startActivity(new Intent(SplashScreenActivity.this, DashboardActivity.class));
            }
        }
        finish();
    }
}
