package com.kenzie.groupwork.discussioncli.cli;

import com.kenzie.groupwork.discussioncli.handler.ChangeTopicHandler;
import com.kenzie.groupwork.discussioncli.handler.CreateTopicHandler;
import com.kenzie.groupwork.discussioncli.handler.CreateTopicMessageHandler;
import com.kenzie.groupwork.discussioncli.handler.ExitHandler;
import com.kenzie.groupwork.discussioncli.handler.LoginHandler;
import com.kenzie.groupwork.discussioncli.handler.ViewTopicMessagesHandler;
import com.kenzie.groupwork.discussioncli.handler.ViewTopicsHandler;
import com.kenzie.input.console.ATAUserInput;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {ExitHandlerModule.class, MapperModule.class, ATAUserInputModule.class})
public interface DiscussionCliComponent {
    DiscussionCli provideDiscussionCli();
}
