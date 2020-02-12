package veil.internetshop.simple.services;

import java.sql.Timestamp;

public interface TimeService{

	/**
	 * Returns current time timestamp
	 *
	 * @return current time timestamp
	 */
	Timestamp getCurrentTime();
}
