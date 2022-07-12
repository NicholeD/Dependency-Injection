package com.kenzie.groupwork.discussioncli.handler;

import com.kenzie.groupwork.discussioncli.cli.DiscussionCliOperation;
import com.kenzie.groupwork.discussioncli.cli.DiscussionCliState;
import com.kenzie.groupwork.discussioncli.dynamodb.TopicMessage;
import com.kenzie.groupwork.discussioncli.dynamodb.TopicMessageDao;
import com.kenzie.input.console.ATAUserInput;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Handler for the CREATE_TOPIC operation.
 */
public class CreateTopicMessageHandler implements DiscussionCliOperationHandler {
    private ATAUserInput userHandler;
    private TopicMessage topicMessage;
    private TopicMessageDao topicMessageDao;
    private DiscussionCliState state;

    /**
     * Constructs handler with its dependencies.
     * @param userHandler the ATAUserInput, for user input
     */
    @Inject
    public CreateTopicMessageHandler(ATAUserInput userHandler, DiscussionCliState state, TopicMessageDao topicMessageDao) {
        this.userHandler = userHandler;
        this.state = state;
        this.topicMessageDao = topicMessageDao;
    }

    @Override
    public String handleRequest(DiscussionCliState state) {
        if (null == state.getCurrentMember()) {
            throw new IllegalStateException(
                "Encountered request to create topic message but there is no current member. Exiting"
            );
        }
        if (null == state.getCurrentTopic()) {
            state.setNextOperation(DiscussionCliOperation.VIEW_TOPICS);
            return "You must select a topic first.";
        }

        String messageContent = userHandler.getString("Message:");
        // PARTICIPANTS: Create the TopicMessage and save it
        topicMessage = new TopicMessage();
        String topicName = state.getCurrentTopic().getName();
        topicMessage.setTopicName(topicName);
        topicMessage.setMessageContent(messageContent);
        topicMessage.setAuthor(state.getCurrentMember().getUsername());
        topicMessage = topicMessageDao.saveTopicMessage(topicMessage);
        List<TopicMessage> messages = state.getListedTopicMessages();

        if (messages != null) {
            messages.add(topicMessage);
        } else {
            messages = new ArrayList<>();
            messages.add(topicMessage);
        }

        state.setListedTopicMessages(messages);
        state.setNextOperation(DiscussionCliOperation.VIEW_TOPIC_MESSAGES);


        return state.getListedTopicMessages().toString();
    }
}
