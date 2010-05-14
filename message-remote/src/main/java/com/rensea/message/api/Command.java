package com.rensea.message.api;


public interface Command<Result> {

	Result execute() throws ApiException;

}
