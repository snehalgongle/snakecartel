package com.altrosmart;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import javax.inject.Inject;
import com.altrosmart.data.DataManager;
import com.altrosmart.ui.base.BaseViewModel;
import com.altrosmart.ui.login.LoginViewModel;
import com.altrosmart.utils.rx.SchedulerProvider;
import dagger.Provides;
import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(PowerMockRunner.class)
public abstract class BaseViewModelTest<T extends BaseViewModel> extends BaseTest {

    @Inject
    protected T viewModel;

    @Before
    public void setup() {
        super.setup();
    }
}