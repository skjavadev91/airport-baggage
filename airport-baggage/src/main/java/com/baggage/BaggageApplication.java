package com.baggage;

import com.baggage.controller.BaggageController;
import com.baggage.exception.BaggageException;

public class BaggageApplication 
{
    public static void main( String[] args )
    {
    	BaggageController repo = new BaggageController();
        try {
			repo.processInput();
			String result = repo.routeBaggages(repo.getConveyorsMap(), repo.getBagIdToBagMap(), repo.getFlightIdToDepartureMap());
			System.out.println(result);
		} catch (BaggageException ex) {
			System.out.println("Exception: " +BaggageException.getStackTrace(ex));
		}catch (Exception ex) {
			System.out.println("Exception: " +BaggageException.getStackTrace(ex));
		}
        
    }
}
