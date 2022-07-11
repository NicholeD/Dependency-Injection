package com.kenzie.groupwork.discussioncli.cli;

import com.kenzie.input.console.ATAUserInput;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ATAUserInputModule {
    @Singleton
    @Provides
    public ATAUserInput provideATAUserInput() {
        return new ATAUserInput();
    }
}
