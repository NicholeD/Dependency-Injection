package com.kenzie.groupwork.discussioncli.handler;

import com.kenzie.groupwork.discussioncli.cli.DiscussionCliOperation;
import com.kenzie.groupwork.discussioncli.cli.DiscussionCliState;

/**
 * Handler for the EXIT operation.
 */
public class ExitHandler implements DiscussionCliOperationHandler {
    @Override
    public String handleRequest(DiscussionCliState state) {
        state.setNextOperation(DiscussionCliOperation.EXIT);
        return "Hope you enjoyed, good-bye!";
    }
}
