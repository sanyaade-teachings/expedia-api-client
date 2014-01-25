/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.morgner.expedia;

import de.morgner.expedia.api.City;
import de.morgner.expedia.api.Country;
import de.morgner.expedia.api.DestinationId;
import de.morgner.expedia.api.DestinationString;
import de.morgner.expedia.api.HotelIdList;
import de.morgner.expedia.api.HotelList;
import de.morgner.expedia.api.Parameter;
import de.morgner.expedia.api.ParameterBase;
import de.morgner.expedia.rest.ExpediaException;
import de.morgner.expedia.rest.JsonObject;
import de.morgner.expedia.rest.RestClientException;
import java.io.IOException;

/**
 * A JiffyBox API client.
 *
 * @author Christian Morgner
 */
public class ExpediaClient {

	private static final String BASE_URL         = "http://api.ean.com/ean-services/rs";
	private static final String BASE_BOOKING_URL = "https://book.api.ean.com";
	private static final int MINOR_REVISION      = 26;
	
	public static final String HOTELS_ROOT       = "/hotel/v3/list";
	
	private long customerId                      = 0L;
	private String userAgent                     = null;
	private String ipAddress                     = null;
	private String apiKey                        = null;
	
	/**
	 * Creates a new Expedia EAN API client with the given customer ID,
	 * API key, customer browser user agent and customer IP address.
	 * 
	 * @param apiKey the API key
	 */
	public ExpediaClient(final long customerId, final String apiKey, final String userAgent, final String ipAddress) {
		this.customerId = customerId;
		this.apiKey     = apiKey;
		this.userAgent  = userAgent;
		this.ipAddress  = ipAddress;
	}

	public HotelList getHotelsByCountryAndCity(final String countryCode, final String city) throws IOException, RestClientException, ExpediaException {
		return JsonObject.doGet(HotelList.class, buildPath("/hotel/v3/list", new City(city), new Country(countryCode)));
	}
	
	public HotelList getHotelsById(final Long... hotelIds) throws IOException, RestClientException, ExpediaException {
		return JsonObject.doGet(HotelList.class, buildPath("/hotel/v3/list", new HotelIdList(hotelIds)));
	}
	
	public HotelList getHotelsByFreeTextDestination(final String freeTextDestination) throws IOException, RestClientException, ExpediaException {
		return JsonObject.doGet(HotelList.class, buildPath("/hotel/v3/list", new DestinationString(freeTextDestination)));
	}
	
	public HotelList getHotelsByDestinationId(final String destinationId) throws IOException, RestClientException, ExpediaException {
		return JsonObject.doGet(HotelList.class, buildPath("/hotel/v3/list", new DestinationId(destinationId)));
	}
	
	public HotelList getHotelsByGeolocation(final double latitude, final double longitude, final double radius) throws IOException, RestClientException, ExpediaException {
		return JsonObject.doGet(HotelList.class, buildPath("/hotel/v3/list",
			new ParameterBase("latitude", Double.valueOf(latitude).toString()),
			new ParameterBase("longitude", Double.valueOf(longitude).toString()),
			new ParameterBase("searchRadius", Double.valueOf(radius).toString()),
			new ParameterBase("searchRadiusUnit", "KM"),
			new ParameterBase("sort", "proximity")
		));
	}
	
	
	
	public void test() {
		
		try {
			
			final HotelList hotels = getHotelsById(326875L);
			
			System.out.println(hotels);
			
		} catch (Throwable t) {
			
			t.printStackTrace();
		}
	}

	// ----- private methods -----
	private String buildPath(final String path, final Parameter... parameters) {
		
		final StringBuilder buf = new StringBuilder(BASE_URL);
		
		buf.append(path);
		buf.append("?cid=").append(customerId);
		buf.append("&apiKey=").append(apiKey);
		buf.append("&minorRev=").append(MINOR_REVISION);
		buf.append("&currencyCode=EUR");
		buf.append("&locale=de_DE");
		buf.append("&customerUserAgent=").append(userAgent);
		buf.append("&customerIpAddress=").append(ipAddress);

		for (final Parameter parameter : parameters) {
			buf.append("&").append(parameter.getKey()).append("=").append(parameter.getValue());
		}
		
		return buf.toString();
	}
}
