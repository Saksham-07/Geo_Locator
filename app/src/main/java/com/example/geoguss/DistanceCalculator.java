package com.example.geoguss;

import android.location.Location;

public class DistanceCalculator {

    // Radius of the Earth in kilometers
    private static final double EARTH_RADIUS_KM = 6371.0;

    /**
     * Calculate the distance between two locations in kilometers using the Haversine formula.
     *
     * @param lat1 Latitude of the first location.
     * @param lon1 Longitude of the first location.
     * @param lat2 Latitude of the second location.
     * @param lon2 Longitude of the second location.
     * @return The distance between the two locations in kilometers.
     */
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
}

