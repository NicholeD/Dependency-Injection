package com.kenzie.groupwork.discussioncli.handler;

import com.kenzie.groupwork.discussioncli.cli.DiscussionCliState;

import javax.inject.Singleton;

/**
 * General interface for the Handler classes.
 */

public interface DiscussionCliOperationHandler {
    /**
     * Handles the relevant operation requested and returns response to be displayed to
     * user (if any).
     * @param state The current CLI app state
     * @return String of content to be rendered to user, if any
     */
    @Singleton
    String handleRequest(DiscussionCliState state);
}
