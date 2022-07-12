package com.kenzie.groupwork.discussioncli.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class TopicMessageDaoTest {
    @InjectMocks
    private TopicMessageDao topicMessageDao;

    @Mock
    private DynamoDBMapper mapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        topicMessageDao = new TopicMessageDao(mapper);
    }

    @Test
    public void createTopicMessage_withEmptyField_throwsException() {
        //GIVEN
        TopicMessage topicMessage = new TopicMessage();
        topicMessage.setTopicName("Movies");
        topicMessage.setAuthor("Nic");

        //WHEN & THEN
        assertThrows(IllegalArgumentException.class,
                () -> topicMessageDao.saveTopicMessage(topicMessage));
    }

    @Test
    public void createTopicMessage_withNull_throwsException() {
        //GIVEN
        //WHEN & THEN
        assertThrows(NullPointerException.class,
                () -> topicMessageDao.saveTopicMessage(null));
    }
}
