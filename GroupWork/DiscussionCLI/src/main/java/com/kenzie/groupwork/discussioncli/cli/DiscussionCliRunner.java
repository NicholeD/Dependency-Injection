package com.kenzie.groupwork.discussioncli.cli;

import com.kenzie.aws.dynamodb.DynamoDbClientProvider;
import com.kenzie.groupwork.discussioncli.dynamodb.MemberDao;
import com.kenzie.groupwork.discussioncli.dynamodb.TopicDao;
import com.kenzie.groupwork.discussioncli.dynamodb.TopicMessageDao;
import com.kenzie.groupwork.discussioncli.handler.ChangeTopicHandler;
import com.kenzie.groupwork.discussioncli.handler.CreateTopicHandler;
import com.kenzie.groupwork.discussioncli.handler.CreateTopicMessageHandler;
import com.kenzie.groupwork.discussioncli.handler.ExitHandler;
import com.kenzie.groupwork.discussioncli.handler.LoginHandler;
import com.kenzie.groupwork.discussioncli.handler.ViewTopicMessagesHandler;
import com.kenzie.groupwork.discussioncli.handler.ViewTopicsHandler;
import com.kenzie.input.console.ATAUserInput;
import com.kenzie.groupwork.discussioncli.cli.DiscussionCliComponent;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import dagger.*;

/**
 * Provides a main method to instantiate and run the DiscussionCli we will be
 * working with in this lesson.
 */
public class DiscussionCliRunner {
    private static DynamoDBMapper mapper;
    private static ATAUserInput userHandler;
    /**
     * Starts the CLI application.
     * @param args no args expected
     */
    public static void main(String[] args) {
        DiscussionCli cli = new DiscussionCli(
                getATAUserInput(),
                getLoginHandler(),
                getViewTopicsHandler(),
                getCreateTopicHandler(),
                getViewTopicMessagesHandler(),
                getChangeTopicHandler(),
                getCreateTopicMessageHandler(),
                getExitHandler(),
                new DiscussionCliState()
        );

        cli.handleRequests();
    }

    /**
     * Provides an ATAUserInput.
     * @return the ATAUserInput
     */
    private static ATAUserInput getATAUserInput() {
        if (null == userHandler) {
            userHandler = new ATAUserInput();
        }
        return userHandler;
    }

    /**
     * Provides the ChangeTopicHandler.
     * @return a new ChangeTopicHandler
     */
    private static ChangeTopicHandler getChangeTopicHandler() {
        return new ChangeTopicHandler(getATAUserInput());
    }

    /**
     * Provides the CreateTopicMessageHandler.
     * @return a new CreateTopicMessageHandler
     */
    private static CreateTopicMessageHandler getCreateTopicMessageHandler() {
        return new CreateTopicMessageHandler(getATAUserInput());
    }

    /**
     * Provides the LoginHandler.
     * @return a new LoginHandler
     */
    private static LoginHandler getLoginHandler() {
        return new LoginHandler(getMemberDao(), getATAUserInput());
    }

    /**
     * Provides the ViewTopicsHandler.
     * @return a new ViewTopicsHandler
     */
    private static ViewTopicsHandler getViewTopicsHandler() {
        return new ViewTopicsHandler(getTopicDao(), getATAUserInput());
    }

    /**
     * Provides the CreateTopicHandler.
     * @return a new CreateTopicHandler
     */
    private static CreateTopicHandler getCreateTopicHandler() {
        return new CreateTopicHandler(getTopicDao(), getATAUserInput());
    }

    /**
     * Provides the ViewTopicMessagesHandler.
     * @return a new ViewTopicMessagesHandler
     */
    private static ViewTopicMessagesHandler getViewTopicMessagesHandler() {
        return new ViewTopicMessagesHandler(getTopicMessageDao());
    }

    /**
     * Provides the ExitHandler.
     * @return a new ExitHandler
     */
    private static ExitHandler getExitHandler() {
        return new ExitHandler();
    }

    /**
     * Provides the MemberDao to access member items.
     * @return a new MemberDao
     */
    private static MemberDao getMemberDao() {
        return new MemberDao(getDynamoDBMapper());
    }

    /**
     * Provides the TopicDao to access member items.
     * @return a new TopicDao
     */
    private static TopicDao getTopicDao() {
        return new TopicDao(getDynamoDBMapper());
    }

    /**
     * Provides the TopicMessageDao to access member items.
     * @return a new TopicMessageDao
     */
    private static TopicMessageDao getTopicMessageDao() {
        return new TopicMessageDao(getDynamoDBMapper());
    }

    /**
     * Uses the default DynamoDB client provider for default region to return a (shared) DynamoDBMapper instance.
     * @return DynamoDBMapper, ready to use!
     */
    private static DynamoDBMapper getDynamoDBMapper() {
        if (null == mapper) {
            mapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
        }
        return mapper;
    }

}
