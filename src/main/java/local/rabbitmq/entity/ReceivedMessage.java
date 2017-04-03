package local.rabbitmq.entity;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import local.rabbitmq.model.CustomBlobSerializer;

@Entity
@Table(name = "received_message")
public class ReceivedMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	long id;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "message")
	@Lob
	@JsonSerialize(using = CustomBlobSerializer.class)
	private Blob message;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Blob getMessage() {
		return message;
	}

	public void setMessage(Blob message) {
		this.message = message;
	}

}
