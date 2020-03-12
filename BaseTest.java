package com.altrosmart;

import android.support.annotation.CallSuper;
import org.junit.Before;
import javax.inject.Inject;
import com.altrosmart.dagger.TestAppComponent;
import com.altrosmart.di.component.DaggerAppComponent;

public abstract class BaseTest {

    @Before
    @CallSuper
    public void setup() {
        inject((TestAppComponent) DaggerAppComponent.builder().build());
    }

    protected abstract void inject(TestAppComponent testAppComponent);
}
