package ru.telenok.newspaper.core.config.statemachine;

import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import ru.telenok.newspaper.common.statemachine.event.ArticleEvent;
import ru.telenok.newspaper.common.statemachine.state.ArticleState;
import ru.telenok.newspaper.core.statemachine.listener.StateMachineArticleListener;

import java.util.EnumSet;

@EnableStateMachineFactory
public class StateMachineArticleConfiguration extends EnumStateMachineConfigurerAdapter<ArticleState, ArticleEvent> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<ArticleState, ArticleEvent> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(new StateMachineArticleListener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<ArticleState, ArticleEvent> states) throws Exception {
        states
                .withStates()
                .initial(ArticleState.NEW)
                .states(EnumSet.allOf(ArticleState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<ArticleState, ArticleEvent> transitions) throws Exception {
        transitions
                .withExternal()
                    .source(ArticleState.NEW)
                    .target(ArticleState.DRAFT)
                    .guard(createNewGuard())
                .and()
                    .withExternal()
                    .source(ArticleState.DRAFT)
                    .target(ArticleState.REVIEW)
                .and()
                    .withExternal()
                    .source(ArticleState.REVIEW)
                    .target(ArticleState.PUBLICHED)
                .and()
                    .withExternal()
                    .source(ArticleState.REVIEW)
                    .target(ArticleState.DRAFT)
                .and()
                    .withExternal()
                    .source(ArticleState.PUBLICHED)
                    .target(ArticleState.REVIEW)
        ;
    }

    @Bean
    public Guard<ArticleState, ArticleEvent> createNewGuard() {
        return ctx -> (int) ctx.getExtendedState()
                .getVariables()
                .getOrDefault("approvalCount", 0) > 0;
    }
}
