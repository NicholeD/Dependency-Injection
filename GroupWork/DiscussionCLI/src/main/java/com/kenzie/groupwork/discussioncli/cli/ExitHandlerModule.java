package com.kenzie.groupwork.discussioncli.cli;

import com.kenzie.groupwork.discussioncli.handler.ExitHandler;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ExitHandlerModule {

    @Singleton
    @Provides
    public ExitHandler provideExitHandler() {
        return new ExitHandler();
    }
}
