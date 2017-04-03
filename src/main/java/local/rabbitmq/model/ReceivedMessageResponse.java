package local.rabbitmq.model;

import java.util.List;

import local.rabbitmq.entity.ReceivedMessage;

public class ReceivedMessageResponse {
	private List<ReceivedMessage> data;

	public List<ReceivedMessage> getData() {
		return data;
	}

	public void setData(List<ReceivedMessage> data) {
		this.data = data;
	}
	
}
