package ru.telenok.newspaper.core.statemachine.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import ru.telenok.newspaper.common.statemachine.event.ArticleEvent;
import ru.telenok.newspaper.common.statemachine.state.ArticleState;

@Slf4j
public class StateMachineArticleListener implements StateMachineListener<ArticleState, ArticleEvent> {
    @Override
    public void stateChanged(State<ArticleState, ArticleEvent> from, State<ArticleState, ArticleEvent> to) {
        if (from.getId() != null) {
            log.info("Переход из статуса " + from.getId() + " в статус " + to.getId());
        }
    }

    @Override
    public void stateEntered(State<ArticleState, ArticleEvent> state) {

    }

    @Override
    public void stateExited(State<ArticleState, ArticleEvent> state) {

    }

    @Override
    public void eventNotAccepted(Message<ArticleEvent> event) {
        log.warn("Event не принят " + event);
    }

    @Override
    public void transition(Transition<ArticleState, ArticleEvent> transition) {

    }

    @Override
    public void transitionStarted(Transition<ArticleState, ArticleEvent> transition) {

    }

    @Override
    public void transitionEnded(Transition<ArticleState, ArticleEvent> transition) {

    }

    @Override
    public void stateMachineStarted(StateMachine<ArticleState, ArticleEvent> stateMachine) {
        log.warn("Machine is started");
    }

    @Override
    public void stateMachineStopped(StateMachine<ArticleState, ArticleEvent> stateMachine) {
        log.warn("Machine is stopped");
    }

    @Override
    public void stateMachineError(StateMachine<ArticleState, ArticleEvent> stateMachine, Exception e) {
        log.warn("Machine generate error");
    }

    @Override
    public void extendedStateChanged(Object o, Object o1) {

    }

    @Override
    public void stateContext(StateContext<ArticleState, ArticleEvent> stateContext) {

    }
}
