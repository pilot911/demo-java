package ru.telenok.newspaper.core.statemachine


import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.messaging.Message
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.config.StateMachineBuilder
import org.springframework.statemachine.state.State
import org.springframework.statemachine.support.StateMachineInterceptor
import org.springframework.statemachine.transition.Transition
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.ContextHierarchy
import ru.telenok.newspaper.admin.config.db.DaoTestConfiguration
import ru.telenok.newspaper.admin.initializer.TestContextInitializer
import spock.lang.Specification
import spock.mock.DetachedMockFactory

@ContextHierarchy(
        [
                @ContextConfiguration(classes = DaoTestConfiguration.class, initializers = TestContextInitializer.class),
                @ContextConfiguration(classes = Config.class)
        ]
)
@Slf4j
class StateMachineArticleConfigurationTest extends Specification {

    @Autowired
    StateMachine<String, String> stateMachine

    def "new article state"() {
        when:
            stateMachine.sendEvent("E1")
        then:
            stateMachine.getState().getId() == "S2"
    }

    @TestConfiguration
    static class Config {
        def detachedMockFactory = new DetachedMockFactory()

        @Bean
        StateMachine<String, String> stateMachine() {
            StateMachineBuilder.Builder<String, String> builder = StateMachineBuilder.builder()
            builder.configureStates()
                    .withStates()
                    .initial("S1")
                    .states(["S1", "S2"] as Set<String>)

            builder.configureTransitions()
                    .withExternal()
                    .source("S1")
                    .target("S2")
                    .event("E1")

            StateMachine<String, String> sm = builder.build()

            sm.getStateMachineAccessor().withRegion()
                    .addStateMachineInterceptor(new StateMachineInterceptor<String, String>() {
                        @Override
                        Message<String> preEvent(Message<String> message, StateMachine<String, String> machine) {
                            log.info("## preEvent. message : {} / state machine : {}", message, machine.getState().getId())
                            message
                        }

                        @Override
                        void preStateChange(State<String, String> state, Message<String> message, Transition<String, String> transition, StateMachine<String, String> machine) {
                            log.info("## preStateChange. stateContext : {}", machine)
                        }

                        @Override
                        void preStateChange(State<String, String> state, Message<String> message, Transition<String, String> transition, StateMachine<String, String> machine, StateMachine<String, String> rootStateMachine) {

                        }

                        @Override
                        void postStateChange(State<String, String> state, Message<String> message, Transition<String, String> transition, StateMachine<String, String> machine) {

                        }

                        @Override
                        void postStateChange(State<String, String> state, Message<String> message, Transition<String, String> transition, StateMachine<String, String> machine, StateMachine<String, String> rootStateMachine) {

                        }

                        @Override
                        StateContext<String, String> preTransition(StateContext<String, String> stateContext) {
                            log.info("## preTransition. stateContext : {}", stateContext)
                            stateContext
                        }

                        @Override
                        StateContext<String, String> postTransition(StateContext<String, String> stateContext) {
                            log.info("## postTransition : {}", stateContext)


                            stateContext
                        }

                        @Override
                        Exception stateMachineError(StateMachine<String, String> machine, Exception exception) {
                            log.warn("stateMachineError :: " + exception.getMessage())
                            exception
                        }
                    })

            sm.start()
            sm
        }
    }
}
