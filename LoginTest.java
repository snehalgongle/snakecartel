package com.altrosmart;

import com.altrosmart.dagger.TestAppComponent;
import com.altrosmart.ui.login.LoginViewModel;

import org.junit.Test;

public class LoginTest extends BaseViewModelTest<LoginViewModel> {

    @Test
    public void login() {
        viewModel.login("lokesh@colanonline.com","Lokesh@123");
       // assertEquals("Invalid email", viewModel.emailError.get());
    }

    @Override
    protected void inject(TestAppComponent testAppComponent) {
        testAppComponent.inject(this);
    }
}
