/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.morgner.expedia.api;

/**
 *
 * @author Christian Morgner
 */
public class ParameterBase implements Parameter {

	private String key   = null;
	private String value = null;

	public ParameterBase(final String key, final String value) {
		this.key   = key;
		this.value = value;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getValue() {
		return value;
	}
}
