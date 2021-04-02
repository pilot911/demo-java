package ru.telenok.newspaper.core.statemachine.action;

import lombok.extern.java.Log;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import ru.telenok.newspaper.common.statemachine.event.ArticleEvent;
import ru.telenok.newspaper.common.statemachine.state.ArticleState;

@Log
public class StateMachineArticleCreatedErrorAction implements Action<ArticleState, ArticleEvent> {

    @Override
    public void execute(StateContext<ArticleState, ArticleEvent> stateContext) {
        log.info("Статья не создана");
    }
}
