package com.altrosmart.dagger;

import com.altrosmart.LoginTest;
import dagger.Module;
import dagger.Provides;

@Module
public class TestBuilderModule {

    @Provides
    LoginTest loginTest()
    {
        return new LoginTest();
    }

}
