package controllers;

import core.Controller;
import views.*;


/**
 * Main controller. It will be responsible for program's main screen behavior.
 */
public class HomeController extends Controller 
{
	//-----------------------------------------------------------------------
	//		Attributes
	//-----------------------------------------------------------------------
	private HomeView homeView;
	private EventListController eventListController = new EventListController();
	private EliminarEventoController eliminarEventoController = new EliminarEventoController();
	private NewEventController newEventController = new NewEventController(eventListController, eliminarEventoController);
	private RegistrarInvitadoController registrarInvitadoController = new RegistrarInvitadoController();

	//-----------------------------------------------------------------------
	//		Methods
	//-----------------------------------------------------------------------
	@Override
	public void run()
	{
		// Initializes others controllers
		eventListController.run();
		eliminarEventoController.run();
		newEventController.run();
		registrarInvitadoController.run();
		
		// Initializes HomeView
		homeView = new HomeView(this, mainFrame);
		addView("HomeView", homeView);
		
		// Displays the program window
		mainFrame.setVisible(true);
	}
	
	
	//-----------------------------------------------------------------------
	//		Getters
	//-----------------------------------------------------------------------
	public EventListView getEventListView()
	{
		return eventListController.getView();
	}
	
	public NewEventView getNewEventView()
	{
		return newEventController.getView();
	}

	public EliminarEventoView getEliminarEventoView()
	{
		return eliminarEventoController.getView();
	}

	public RegistrarInvitadoView getRegistrarInvitadoView()
	{
		return registrarInvitadoController.getView();
	}
}