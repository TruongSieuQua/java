package org.example.sec11.assignment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SlackRoom {
	private String name;
	// A sink can receive items on multiple thread and expose a mono or flux to emit items with multiple operators
	private Sinks.Many<SlackMessage> sink;
	private Flux<SlackMessage> flux;

	public SlackRoom(String name) {
		this.name = name;
		// a sink can emit item
		this.sink = Sinks.many().replay().all(); // contain all message
		this.flux = this.sink.asFlux(); // to publish message
	}

	public void joinRoom(SlackMember slackMember) {
		System.out.println(slackMember.getName() + "------------- Joined ---------------" + this.name);
		this.subscribe(slackMember);
		slackMember.setMessageConsumer(
			msg -> this.postMessage(msg, slackMember)
		);
	}

	// Pull messages
	// When subscribe all message receive will be pull to this slackMember (receiver)
	private void subscribe(SlackMember slackMember) {
		// This flux will contain all messages
		this.flux
			// filter to not include messages which this member sent
			.filter(sm -> !sm.getSender().equals(slackMember.getName()))
			// set receiver of all messages sending
			.doOnNext(sm -> sm.setReceiver(slackMember.getName()))
			.map(SlackMessage::toString)
			.subscribe(slackMember::receives);
	}

	// Post message
	private void postMessage(String msg, SlackMember slackMember) {
		SlackMessage slackMessage = new SlackMessage();
		slackMessage.setSender(slackMember.getName());
		slackMessage.setMessage(msg);
		this.sink.tryEmitNext(slackMessage);
	}


}
