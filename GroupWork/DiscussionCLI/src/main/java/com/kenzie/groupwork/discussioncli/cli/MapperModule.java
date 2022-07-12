package com.kenzie.groupwork.discussioncli.cli;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.aws.dynamodb.DynamoDbClientProvider;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class MapperModule {
    @Singleton
    @Provides
    public DynamoDBMapper provideMapper() {
        return new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
    }
}
