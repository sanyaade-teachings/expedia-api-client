/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.morgner.expedia.api;

import com.google.gson.annotations.Expose;
import de.morgner.expedia.result.EanResult;
import java.util.List;

/**
 *
 * @author Christian Morgner
 */
public class HotelList extends EanResult {

	/**
	 * Indicates if there are more property results available to page.
	 * If true, more results can be obtained via the paging process.
	 */
	@Expose
	private boolean moreResultsAvailable = false;

	/**
	 * Confirms the number of rooms originally requested (number of Room nodes)
	 */
	@Expose
	private int numberOfRoomsRequested = 0;
	
	/**
	 * The key to the cache of the current response returned.
	 * Use this value in your next paging request.
	 */
	@Expose
	private String cacheKey = null;
	
	/**
	 * Defines the EAN server location of the cache for the current response returned.
	 * Use this value in your next paging request.
	 */
	@Expose
	private String cacheLocation = null;
	
	/**
	 * Container for HotelSummary array.
	 * Contains attributes size, to indicate the number of individual properties in the response,
	 * and activePropertyCount, to indicate the total number of active (not necessarily available)
	 * properties in the specified location.
	 */
	@Expose
	private List<Hotel> HotelList = null;

	public boolean isMoreResultsAvailable() {
		return moreResultsAvailable;
	}

	public void setMoreResultsAvailable(boolean moreResultsAvailable) {
		this.moreResultsAvailable = moreResultsAvailable;
	}

	public int getNumberOfRoomsRequested() {
		return numberOfRoomsRequested;
	}

	public void setNumberOfRoomsRequested(int numberOfRoomsRequested) {
		this.numberOfRoomsRequested = numberOfRoomsRequested;
	}

	public String getCacheKey() {
		return cacheKey;
	}

	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}

	public String getCacheLocation() {
		return cacheLocation;
	}

	public void setCacheLocation(String cacheLocation) {
		this.cacheLocation = cacheLocation;
	}

	public List<Hotel> getHotelList() {
		return HotelList;
	}

	public void setHotelList(List<Hotel> HotelList) {
		this.HotelList = HotelList;
	}
}
