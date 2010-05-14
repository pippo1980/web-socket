/**
 *
 */
package com.rensea.message.api;

import com.rensea.message.dto.StatusMessage;

/**
 * @author pippo
 *
 */
public interface ApiFacade {

	void newStatus(StatusMessage status) throws ApiException;

	StatusMessage getStatus(String id) throws ApiException;

}
