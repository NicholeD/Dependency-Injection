package com.kenzie.groupwork.discussioncli.cli;

import com.kenzie.aws.dynamodb.DynamoDbClientProvider;
import com.kenzie.groupwork.discussioncli.dynamodb.MemberDao;
import com.kenzie.groupwork.discussioncli.dynamodb.TopicDao;
import com.kenzie.groupwork.discussioncli.dynamodb.TopicMessageDao;
import com.kenzie.groupwork.discussioncli.handler.*;
import com.kenzie.input.console.ATAUserInput;
import com.kenzie.groupwork.discussioncli.handler.ExitHandler;
import com.kenzie.groupwork.discussioncli.cli.DiscussionCliOperation;
import com.kenzie.groupwork.discussioncli.cli.DiscussionCliComponent;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import dagger.*;
import org.apache.commons.logging.Log;

/**
 * Provides a main method to instantiate and run the DiscussionCli we will be
 * working with in this lesson.
 */
public class DiscussionCliRunner {

    /**
     * Starts the CLI application.
     * @param args no args expected
     */
    public static void main(String[] args) {
        DiscussionCliComponent dagger = DaggerDiscussionCliComponent.create();
        DiscussionCli discussionCli = dagger.provideDiscussionCli();
        discussionCli.handleRequests();
    }
}
