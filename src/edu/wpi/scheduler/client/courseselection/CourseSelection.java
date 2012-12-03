package edu.wpi.scheduler.client.courseselection;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.wpi.scheduler.client.controller.StudentScheduleEvent;
import edu.wpi.scheduler.client.controller.StudentScheduleEventHandler;
import edu.wpi.scheduler.client.controller.StudentScheduleEvents;
import edu.wpi.scheduler.shared.model.Course;

public class CourseSelection extends ComplexPanel implements StudentScheduleEventHandler {

	private CourseSelectionController selectionController;

	public CourseSelection(final CourseSelectionController selectionController) {
		this.selectionController = selectionController;

		this.setElement(DOM.createTable());

		this.setStyleName("courseList");

	}

	@Override
	public void add(Widget child) {
		this.add(child, this.getElement());
	}

	/**
	 * Add listeners when the object is added to the document/dom tree
	 */
	@Override
	protected void onLoad() {
		selectionController.getStudentSchedule().addStudentScheduleHandler(this);

		// TODO: Possible bug, the list might be out of date
		// If we removed this widget from the DOM tree, update the course list,
		// And later re-add this widget
	}

	@Override
	protected void onUnload() {
		selectionController.getStudentSchedule().removeStudentScheduleHandler(this);
	}

	@Override
	public void onCoursesChanged(StudentScheduleEvent event) {
		if( event.event == StudentScheduleEvents.ADD){
			addCourse(event.getCourse());
		} else if (event.event == StudentScheduleEvents.REMOVE){
			removeCourse(event.getCourse());
		}
	
	}
	
	private void addCourse( Course course ){
		CourseSelectionItem item = new CourseSelectionItem(selectionController, course);

		this.add(item);
	}
	
	private void removeCourse( Course course ){
		for (Widget widget : this.getChildren()) {
			CourseSelectionItem item = (CourseSelectionItem) widget;

			if (item.getCourse().equals(course)) {
				this.remove(widget);
			}
		}		
	}

}
