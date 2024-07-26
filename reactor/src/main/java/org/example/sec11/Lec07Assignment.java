package org.example.sec11;

import org.example.sec11.assignment.SlackMember;
import org.example.sec11.assignment.SlackRoom;
import org.example.utils.Util;

public class Lec07Assignment {
	public static void main(String[] args) {

		SlackRoom slackRoom = new SlackRoom("reactor");

		SlackMember sam = new SlackMember("sam");
		SlackMember jake = new SlackMember("jake");
		SlackMember mike = new SlackMember("mike");

		slackRoom.joinRoom(sam);
		slackRoom.joinRoom(jake);

		sam.says("Hi all..");
		Util.sleepSeconds(4);

		jake.says("Hey!");
		sam.says("I simply wanted to say hi..");
		Util.sleepSeconds(4);

		slackRoom.joinRoom(mike);
		mike.says("Hey guys..glad to be here...");


	}
}
