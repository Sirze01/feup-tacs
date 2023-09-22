package org.eclipse.example.bowling.tutorial.popup.actions;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import bowling.BowlingFactory;
import bowling.BowlingPackage;
import bowling.League;
import bowling.Player;

public abstract class AbstractLeagueExampleDialog extends Dialog {

	private static final int _ADDPLAYER = 1001;
	protected Label numberOfMatchups;

	protected AbstractLeagueExampleDialog(Shell parentShell) {
		super(parentShell);
	}

	private League league;
	private ComposedAdapterFactory composedAdapterFactory;
	private Resource resource;

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite root = (Composite) super.createDialogArea(parent);
		GridLayoutFactory.fillDefaults().applyTo(root);

		
		ListViewer listViewer = new ListViewer(root, SWT.SINGLE | SWT.H_SCROLL
				| SWT.V_SCROLL);
		listViewer.setContentProvider(new AdapterFactoryContentProvider(getAdapterFactory()));
		listViewer.setLabelProvider(new AdapterFactoryLabelProvider(getAdapterFactory()));
		listViewer.setInput(getLeague());
		listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				if(selection instanceof IStructuredSelection){
					IStructuredSelection iStructuredSelection = (IStructuredSelection) selection;
					setActivePlayer((Player)iStructuredSelection.getFirstElement());
				}
				
			}
		});
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(listViewer.getControl());
		Composite nameComposite = new Composite(root, SWT.None);
		GridLayoutFactory.fillDefaults().numColumns(2).equalWidth(false).applyTo(nameComposite);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, false).applyTo(nameComposite);
		createNameComposite(nameComposite);
		return parent;
	}


	protected abstract void setActivePlayer(Player firstElement);


	protected abstract void createNameComposite(Composite nameComposite);


	protected void loadContent(IFile file) throws IOException {
		AdapterFactoryEditingDomain editingDomain = new AdapterFactoryEditingDomain(
				getAdapterFactory(), new BasicCommandStack());
		resource = editingDomain.createResource(file.getFullPath().toString());
		resource.load(null);
		EObject eObject = resource.getContents().get(0);
		setLeague((League) eObject);
	}

	protected void save() throws IOException {
		resource.save(null);
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}


	@Override
	protected void buttonPressed(int buttonId) {
		switch (buttonId) {
		case _ADDPLAYER:
			addPlayer();
			break;
		default:
			super.buttonPressed(buttonId);
		}
	}

	

	protected void addPlayer(){
		Player player = BowlingFactory.eINSTANCE.createPlayer();
		EditingDomain editingDomain = AdapterFactoryEditingDomain
				.getEditingDomainFor(getLeague());
		Command command = AddCommand.create(editingDomain, getLeague(),
				BowlingPackage.eINSTANCE.getLeague_Players(), player);
		editingDomain.getCommandStack().execute(command);
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, _ADDPLAYER, "Add Player", true);
		super.createButtonsForButtonBar(parent);
	}

	@Override
	protected void okPressed() {
		try {
			save();
		} catch (IOException e) {
			Status status = new Status(IStatus.ERROR,
					"org.eclipse.example.bowling.tutorial", 0, e.getMessage(),
					null);
			ErrorDialog.openError(this.getShell(), "Error on save",
					"Something went wrong on save", status);
		}
		super.okPressed();
	}




	/**
	 * Return an ComposedAdapterFactory for all registered modesl
	 * 
	 * @return a ComposedAdapterFactory
	 */
	protected AdapterFactory getAdapterFactory() {
		if (composedAdapterFactory == null) {
			composedAdapterFactory = new ComposedAdapterFactory(
					ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		}
		return composedAdapterFactory;
	}

	protected boolean isResizable() {
		return true;
	}
	
	protected Point getInitialSize() {
		return new Point(640, 400);
	}
}
