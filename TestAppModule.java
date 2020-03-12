package com.altrosmart.dagger;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.inject.Singleton;

import com.altrosmart.LoginTest;
import com.altrosmart.R;
import com.altrosmart.data.AppDataManager;
import com.altrosmart.data.DataManager;
import com.altrosmart.data.local.db.AppDatabase;
import com.altrosmart.data.local.db.AppDbHelper;
import com.altrosmart.data.local.db.IDbHelper;
import com.altrosmart.data.local.preference.AppPreferencesHelper;
import com.altrosmart.data.local.preference.IPreferencesHelper;
import com.altrosmart.data.remote.ApiHeader;
import com.altrosmart.data.remote.ApiHelper;
import com.altrosmart.data.remote.AppApiHelper;
import com.altrosmart.di.ApiInfo;
import com.altrosmart.di.DatabaseInfo;
import com.altrosmart.di.PreferenceInfo;
import com.altrosmart.utils.AppConstants;
import com.altrosmart.utils.rx.AppSchedulerProvider;
import com.altrosmart.utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@Module
public class TestAppModule {


    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return /*BuildConfig.API_KEY*/ "";
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }


    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    IDbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    IPreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                           IPreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey,
                preferencesHelper.getCurrentUserId(),
                preferencesHelper.getAccessToken());
    }

    @Provides
    LoginTest provideLoginViewModel() {
        return new LoginTest();
    }
}
