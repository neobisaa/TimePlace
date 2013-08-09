package com.app.time.Data;


/**
 * Represents a DB Handler interface
 * Used by Controller to get questions from the DB
 * 
 * @author San4
 */
public interface IDBHandler {

	public Question GetNextQuestion(String topic);
	
	
}
