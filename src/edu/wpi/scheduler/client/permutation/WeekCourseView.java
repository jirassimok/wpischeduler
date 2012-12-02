package edu.wpi.scheduler.client.permutation;

import com.google.gwt.user.client.ui.HorizontalPanel;

import edu.wpi.scheduler.client.controller.SchedulePermutation;
import edu.wpi.scheduler.shared.model.DayOfWeek;

public class WeekCourseView extends HorizontalPanel {
	
	private SchedulePermutation permutation;


	public WeekCourseView( SchedulePermutation permutation ){
		this.permutation = permutation;
		
		addColumn( DayOfWeek.MONDAY );
		addColumn( DayOfWeek.TUESDAY );
		addColumn( DayOfWeek.WEDNESDAY );
		addColumn( DayOfWeek.THURSDAY );
		addColumn( DayOfWeek.FRIDAY );
		
		this.getElement().getStyle().setProperty("width", "100%");
		this.getElement().getStyle().setProperty("height", "100%");
	}
	

	private void addColumn(DayOfWeek day) {
		
		this.add( new WeekCourseColumn(permutation, day) );
		
		
	}
	
	
}