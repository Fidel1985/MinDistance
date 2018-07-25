import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);
	private static final String COMMA = ",";

	public static void main(String[] args) {
		/*List<Coordinate> inputList = new ArrayList<>();
		try {
			File file = new File("D:\\cgn_bc_csv_eng.csv");
			InputStream inputFS = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
			// skip the header of the csv
			inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		Coordinate c1 = new Coordinate(32.9697, -96.80322);
		Coordinate c2 = new Coordinate(29.46786, -98.53506);

		System.out.println(distance(c1, c2) + " Miles");

		//System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, 'M') + " Miles\n");
		//System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, 'K') + " Kilometers\n");
		//System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, 'N') + " Nautical Miles\n");
	}

	private static Function<String, Coordinate> mapToItem = (line) -> {
		String[] p = line.split(COMMA);// a CSV has comma separated lines
		Coordinate item = new Coordinate();
		item.setLatitude(Double.valueOf(p[8]));
		item.setLongitude(Double.valueOf(p[9]));
		return item;
	};

	private static double distance(Coordinate c1, Coordinate c2) {
		double theta = c1.getLongitude() - c2.getLongitude();
		double dist = Math.sin(deg2rad(c1.getLatitude())) * Math.sin(deg2rad(c2.getLatitude())) + Math.cos(deg2rad(c1.getLatitude())) *
				Math.cos(deg2rad(c2.getLatitude())) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		return dist;
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::  This function converts decimal degrees to radians             :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::  This function converts radians to decimal degrees             :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}


}


