package com.altrosmart.dagger;

import android.app.Application;
import javax.inject.Singleton;
import com.altrosmart.LoginTest;
import com.altrosmart.di.component.AppComponent;
import com.altrosmart.di.module.AppModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidInjectionModule.class,
                AndroidSupportInjectionModule.class,
                AppModule.class,
        }
)
public interface TestAppComponent extends AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        TestAppComponent build();
    }
    //void inject(TestApplication app);
     void inject(LoginTest test);
   // void inject(SplashActivityTest test);
}
