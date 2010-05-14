package com.rensea.message.dto;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MessageClassCastTest {

	@Test
	public void countMessage() {
		String messageString = "{userId:1,count:5,messageType:'COUNT_UPDATE',countType:'mention'}";
		CountUpdateMessage message = MessageFactory.fromJson(messageString);
		assertThat(message.getCount(), is(5));
		assertThat(message.getCountType(), is("mention"));
	}

	@Test(expected = java.lang.ClassCastException.class)
	public void castExceptionMessage() {
		String messageString = "{userId:1,count:5,messageType:'COUNT_UPDATE',countType:'mention'}";
		@SuppressWarnings("unused")
		StatusMessage message = MessageFactory.fromJson(messageString);
	}

	@Test
	public void statusMessageFollowers() {
		String messageString = "{statusId:1,followers:['1','2','4'],messageType:'STATUS',countType:'mention'}";
		StatusMessage message = MessageFactory.fromJson(messageString);
		assertThat(message.getFollowers(), hasItems("1", "2", "4"));
		assertThat(message.getStatusId(), is(1l));
	}
}
