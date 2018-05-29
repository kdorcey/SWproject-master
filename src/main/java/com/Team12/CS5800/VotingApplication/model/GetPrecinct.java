package com.Team12.CS5800.VotingApplication.model;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;
import com.sun.org.apache.xalan.internal.utils.FeatureManager;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.filter.text.cql2.CQL;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;

import org.geotools.swing.JMapFrame;
import org.geotools.swing.data.JFileDataStoreChooser;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.Filter;
import org.springframework.core.io.ClassPathResource;

import sun.misc.JavaIOAccess;
import java.util.Scanner;
import java.io.*;

//api key AIzaSyCIQ4M-00zxbBzpQE3hOg7-D3n5vR_MnFU
//http://maps.googleapis.com/maps/api/geocode/outputFormat?parameters
//outputFormat = json
//https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyCIQ4M-00zxbBzpQE3hOg7-D3n5vR_MnFU

public class GetPrecinct {

    public static String precinctLookup(String address,String city, String state)throws Exception{
    	/**
    	 * IMPORTANT: the file path must be specified like this. For some reason
    	 * general file path directories return "file not found exceptions"
    	 */
    	

        File shapeFile = new ClassPathResource("data/ia_final.shp").getFile(); //if code doesn't work check file addresses
        System.out.println(shapeFile.canRead());
        
        FileDataStore store = FileDataStoreFinder.getDataStore(shapeFile);
        SimpleFeatureSource featureSource = store.getFeatureSource();



        GeoApiContext apiContext = new GeoApiContext.Builder().apiKey("AIzaSyCIQ4M-00zxbBzpQE3hOg7-D3n5vR_MnFU").build();

        GeocodingResult[] coords= GeocodingApi.geocode(apiContext, ""+address+", "+city+", "+state+"").await();



        Filter coordToCheck = CQL.toFilter("CONTAINS(the_geom, POINT( "+coords[0].geometry.location.lng+" "+coords[0].geometry.location.lat+"))");
        SimpleFeatureCollection collection = featureSource.getFeatures(coordToCheck);
        SimpleFeatureIterator iterator = collection.features();

        String precinct =new String();
        try{
            while(iterator.hasNext()){
                SimpleFeature feature =iterator.next();

                precinct = (String)feature.getAttribute( "NAME10" );
                
            }

        }finally {
            iterator.close();
        }

        return precinct;

    }

}
