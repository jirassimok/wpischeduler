package edu.wpi.scheduler.client.timechooser;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.RequiresResize;

import edu.wpi.scheduler.client.controller.StudentSchedule;
import edu.wpi.scheduler.shared.model.Term;

public class TimeTablesGrid extends Grid implements ResizeHandler
{
	TimeTable ATermTable;
	TimeTable BTermTable; 
	TimeTable CTermTable; 
	TimeTable DTermTable; 

	HandlerRegistration resizeHandler;

	public TimeTablesGrid(StudentSchedule studentSchedule) 
	{
		super(2,2);
		getElement().getStyle().setWidth(100.0, Unit.PCT);
		getElement().getStyle().setHeight(100.0, Unit.PCT);
		getElement().getStyle().setPosition(Position.ABSOLUTE);

		ATermTable = new TimeTable(Term.A, studentSchedule.ATermTimes);
		BTermTable = new TimeTable(Term.B, studentSchedule.BTermTimes);
		CTermTable = new TimeTable(Term.C, studentSchedule.CTermTimes);
		DTermTable = new TimeTable(Term.D, studentSchedule.DTermTimes);
		this.addTimeTable(0, 0, ATermTable);
		this.addTimeTable(0, 1, BTermTable);
		this.addTimeTable(1, 0, CTermTable);
		this.addTimeTable(1, 1, DTermTable);
		this.setCellPadding(10);
		onResize(null);
	}

	public void addTimeTable( int row, int column, TimeTable table )
	{
		setWidget(row, column, table);
		getCellFormatter().setWidth(row, column, "50%");
		getCellFormatter().setHeight(row, column, "50%");
		getCellFormatter().setAlignment(row, column, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);
	}

	@Override
	public void onLoad()
	{
		resizeHandler = Window.addResizeHandler(this);
		onResize(null);
	}

	@Override
	public void onUnload()
	{
		resizeHandler.removeHandler();
	}

	@Override
	public void onResize(ResizeEvent event) 
	{
		onResize();
	}

	public void onResize() 
	{
		try 
		{
			ATermTable.setSize((int) (getParentElement().getClientWidth() * 0.47), (int) (getParentElement().getClientHeight() * 0.47));
			BTermTable.setSize((int) (getParentElement().getClientWidth() * 0.47), (int) (getParentElement().getClientHeight() * 0.47));
			CTermTable.setSize((int) (getParentElement().getClientWidth() * 0.47), (int) (getParentElement().getClientHeight() * 0.47));
			DTermTable.setSize((int) (getParentElement().getClientWidth() * 0.47), (int) (getParentElement().getClientHeight() * 0.47));
		} 
		catch(Exception e){}
	}

	public Element getParentElement(){
		return getElement().getParentElement();
	}



	/*try
		{
			initWidget(uiBinder.createAndBindUi(this));

			ATermGrid.add(new TimeChooser(studentSchedule.ATermTimes, gridWidth, gridHeight));
			BTermGrid.add(new TimeChooser(studentSchedule.BTermTimes, gridWidth, gridHeight));
			CTermGrid.add(new TimeChooser(studentSchedule.CTermTimes, gridWidth, gridHeight));
			DTermGrid.add(new TimeChooser(studentSchedule.DTermTimes, gridWidth, gridHeight));

			ATermGrid.setPixelSize(gridWidth, gridHeight);
			ATermHours.setPixelSize(columnWidth, totalHeight);
			ATermDays.setPixelSize(totalWidth, rowHeight);
			BTermGrid.setPixelSize(gridWidth, gridHeight);
			BTermHours.setPixelSize(columnWidth, totalHeight);
			BTermDays.setPixelSize(totalWidth, rowHeight);
			CTermGrid.setPixelSize(gridWidth, gridHeight);
			CTermHours.setPixelSize(columnWidth, totalHeight);
			CTermDays.setPixelSize(totalWidth, rowHeight);
			DTermGrid.setPixelSize(gridWidth, gridHeight);
			DTermHours.setPixelSize(columnWidth, totalHeight);
			DTermDays.setPixelSize(totalWidth, rowHeight);

			for(int i = 0; i < TimeCell.NUM_DAYS; i++)
			{
				ATermDays.add(new Label(TimeCell.week[TimeCell.START_DAY+i].name()), (gridWidth/TimeCell.NUM_DAYS)*(i), 0);
				BTermDays.add(new Label(TimeCell.week[TimeCell.START_DAY+i].name()), (gridWidth/TimeCell.NUM_DAYS)*(i), 0);
				CTermDays.add(new Label(TimeCell.week[TimeCell.START_DAY+i].name()), (gridWidth/TimeCell.NUM_DAYS)*(i), 0);
				DTermDays.add(new Label(TimeCell.week[TimeCell.START_DAY+i].name()), (gridWidth/TimeCell.NUM_DAYS)*(i), 0);
			}

			Time t = new Time(TimeCell.START_HOUR, TimeCell.START_MIN);
			for(int i = 0; i <= TimeCell.NUM_HOURS * TimeCell.CELLS_PER_HOUR; i++)
			{
				ATermHours.add(new Label(t.toString()), 0, (int) ((gridHeight/(TimeCell.NUM_HOURS*TimeCell.CELLS_PER_HOUR))*( i + .5)));
				BTermHours.add(new Label(t.toString()), 0, (int) ((gridHeight/(TimeCell.NUM_HOURS*TimeCell.CELLS_PER_HOUR))*( i + .5)));
				CTermHours.add(new Label(t.toString()), 0, (int) ((gridHeight/(TimeCell.NUM_HOURS*TimeCell.CELLS_PER_HOUR))*( i + .5)));
				DTermHours.add(new Label(t.toString()), 0, (int) ((gridHeight/(TimeCell.NUM_HOURS*TimeCell.CELLS_PER_HOUR))*( i + .5)));
				t.increment(0, 60/TimeCell.CELLS_PER_HOUR);
			}
		}
		catch(Exception e)
		{
			TimeChooserPanel.add(new Label("Your internet browser does not support canvases; All class times are selected by default\n" +
											"If you would like to select specific times to take classes, please use the latest version of IE, Firefox, or Chrome"));
		}
	}*/
}