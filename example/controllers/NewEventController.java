package controllers;

import javax.swing.JOptionPane;

import core.Controller;
import models.SchedulerEvent;
import models.SchedulerIO;
import views.EventListView;
import views.NewEventView;


/**
 * Responsible for {@link NewEventView} behavior.
 */
public class NewEventController extends Controller 
{
	//-----------------------------------------------------------------------
	//		Attributes
	//-----------------------------------------------------------------------
	private NewEventView newEventView;
	private EventListController eventListController;
	private EliminarEventoController eliminarEventoController;

	
	//-----------------------------------------------------------------------
	//		Constructor
	//-----------------------------------------------------------------------
	/**
	 * Responsible for create a {@link SchedulerEvent new event}.
	 *
	 * @param eventListController      {@link EventListController}, because it will
	 *                                 add new events created in {@link NewEventView}.
	 * @param eliminarEventoController
	 */
	public NewEventController(EventListController eventListController, EliminarEventoController eliminarEventoController)
	{
		this.eventListController = eventListController;
		this.eliminarEventoController = eliminarEventoController;
	}
	
	
	//-----------------------------------------------------------------------
	//		Methods
	//-----------------------------------------------------------------------
	@Override
	public void run() 
	{
		newEventView = new NewEventView(this);
	}
	
	/**
	 * Creates a new {@link SchedulerEvent} and puts it on {@link EventListView}.
	 *
	 * @param event Event to be added
	 * @return
	 */
	public void addEvent(SchedulerEvent event)
	{
		Object[] metadata = new Object[5];
		metadata[0] = event.getDate();
		metadata[1] = event.getEventDesc();
		metadata[2] = event.getFrequency();
		metadata[3] = event.getFwdEmail();
		metadata[4] = event.getAlarm() ? "ON" : "OFF";

		try {
			SchedulerIO schedulerIO = new SchedulerIO();
			schedulerIO.attach(newEventView);
			schedulerIO.saveEvent(event);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}

		eventListController.addNewRow(metadata);

		Object[] metadataRemove = new Object[6];
		metadataRemove[0] = metadata[0];
		metadataRemove[1] = metadata[1];
		metadataRemove[2] = metadata[2];
		metadataRemove[3] = metadata[3];
		metadataRemove[4] = metadata[4];
		metadataRemove[5] = false;
		eliminarEventoController.addNewRow(metadataRemove);
	}
	
	
	//-----------------------------------------------------------------------
	//		Getters
	//-----------------------------------------------------------------------
	/**
	 * Gets the {@link NewEventView view associated with this controller}.
	 * 
	 * @return View associated with this controller
	 */
	public NewEventView getView()
	{
		return newEventView;
	}
}