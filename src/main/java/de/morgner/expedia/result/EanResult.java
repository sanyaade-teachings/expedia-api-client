/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.morgner.expedia.result;

import com.google.gson.annotations.Expose;
import de.morgner.expedia.rest.JsonObject;

/**
 *
 * @author Christian Morgner
 */
public abstract class EanResult extends JsonObject {
	
	@Expose
	private String customerSessionId = null;
	
	@Expose
	private EanWSError EanWSError = null;

	public String getCustomerSessionId() {
		return customerSessionId;
	}

	public void setCustomerSessionId(String customerSessionId) {
		this.customerSessionId = customerSessionId;
	}

	public EanWSError getEanWSError() {
		return EanWSError;
	}

	public void setEanWSError(EanWSError EanWSError) {
		this.EanWSError = EanWSError;
	}
	
	
}
